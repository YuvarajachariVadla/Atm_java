package atm;

import java.math.BigDecimal;
import java.util.List;

/**
 * Business logic layer: create accounts, authenticate, and operate on accounts.
 */
public class ATMService {

    public Account createAccount(String accountNumber, String cardNumber, String holderName, String pinPlain, BigDecimal openingBalance) {
        if (AccountStore.findByCardNumber(cardNumber) != null) {
            throw new IllegalArgumentException("Card number already exists.");
        }
        String pinHash = SecurePinHasher.hash(pinPlain);
        Account acc = new Account(accountNumber, cardNumber, pinHash, holderName, openingBalance);
        boolean added = AccountStore.addAccount(acc);
        if (!added) throw new IllegalStateException("Failed to create account (card exists).");
        return acc;
    }

    public Account authenticate(String cardNumber, String pinPlain) {
        Account acc = AccountStore.findByCardNumber(cardNumber);
        if (acc == null) throw new IllegalArgumentException("Card not found.");
        if (acc.isLocked()) throw new IllegalArgumentException("Account is locked due to multiple failed attempts.");

        if (!SecurePinHasher.verify(pinPlain, acc.getPinHash())) {
            acc.recordFailedAttempt();
            int tries = acc.getFailedAttempts();
            if (acc.isLocked()) {
                throw new IllegalArgumentException("Invalid PIN. Account locked after " + tries + " failed attempts.");
            } else {
                throw new IllegalArgumentException("Invalid PIN. Attempt " + tries + "/3.");
            }
        }
        // Successful authentication — reset failed attempts
        acc.resetFailedAttempts();
        return acc;
    }

    public BigDecimal getBalance(Account acc) {
        acc.recordBalanceInquiry();
        return acc.getBalance();
    }

    public void deposit(Account acc, BigDecimal amount) {
        acc.deposit(amount);
    }

    public void withdraw(Account acc, BigDecimal amount) {
        acc.withdraw(amount);
    }

    public List<Transaction> miniStatement(Account acc, int lastN) {
        return acc.getRecentTransactions(lastN);
    }
}
