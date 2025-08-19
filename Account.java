package atm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Account holds balance, card number, PIN hash, holder name, and transaction history.
 * Synchronized methods provide thread-safety for balance updates.
 */
public class Account {
    private final String accountNumber;
    private final String cardNumber;
    private final String pinHash; // SHA-256 hash of PIN
    private final String holderName;
    private BigDecimal balance;
    private final List<Transaction> transactions = new ArrayList<>();

    // Security / state
    private int failedAttempts = 0;
    private boolean locked = false;

    public Account(String accountNumber, String cardNumber, String pinHash, String holderName, BigDecimal openingBalance) {
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.pinHash = pinHash;
        this.holderName = holderName;
        this.balance = openingBalance == null ? BigDecimal.ZERO : openingBalance;
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getCardNumber() { return cardNumber; }
    public String getPinHash() { return pinHash; }
    public String getHolderName() { return holderName; }

    // Account state methods
    public synchronized boolean isLocked() { return locked; }
    public synchronized void lock() { locked = true; }
    public synchronized void resetFailedAttempts() { failedAttempts = 0; }
    public synchronized void recordFailedAttempt() { failedAttempts++; if (failedAttempts >= 3) locked = true; }
    public synchronized int getFailedAttempts() { return failedAttempts; }

    // Balance & transaction methods
    public synchronized BigDecimal getBalance() { return balance; }

    public synchronized void deposit(BigDecimal amount) {
        requirePositive(amount);
        balance = balance.add(amount);
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount, balance, "Cash deposit"));
        resetFailedAttempts(); // optional: successful operation resets attempts
    }

    public synchronized void withdraw(BigDecimal amount) {
        requirePositive(amount);
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance = balance.subtract(amount);
        transactions.add(new Transaction(TransactionType.WITHDRAW, amount, balance, "Cash withdraw"));
        resetFailedAttempts();
    }

    public synchronized void recordBalanceInquiry() {
        transactions.add(new Transaction(TransactionType.BALANCE_INQUIRY, BigDecimal.ZERO, balance, "Balance check"));
        resetFailedAttempts();
    }

    public synchronized List<Transaction> getRecentTransactions(int limit) {
        int from = Math.max(0, transactions.size() - limit);
        return new ArrayList<>(transactions.subList(from, transactions.size()));
    }

    private void requirePositive(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }
}
