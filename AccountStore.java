package atm;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory account repository. Supports adding and finding accounts.
 * For production, replace with JDBC repository.
 */
public class AccountStore {
    // Keyed by cardNumber
    private static final Map<String, Account> ACCOUNTS = new HashMap<>();

    static {
        // Seed demo accounts (you may keep or remove these)
        ACCOUNTS.put("1111222233334444",
            new Account("AC001", "1111222233334444",
                SecurePinHasher.hash("1234"), "Demo User 1", new BigDecimal("1000.00")));
        ACCOUNTS.put("5555666677778888",
            new Account("AC002", "5555666677778888",
                SecurePinHasher.hash("4321"), "Demo User 2", new BigDecimal("2500.00")));
    }

    public static synchronized boolean addAccount(Account account) {
        if (ACCOUNTS.containsKey(account.getCardNumber())) return false;
        ACCOUNTS.put(account.getCardNumber(), account);
        return true;
    }

    public static synchronized Account findByCardNumber(String cardNumber) {
        return ACCOUNTS.get(cardNumber);
    }

    public static synchronized Map<String, Account> allAccounts() {
        return Collections.unmodifiableMap(ACCOUNTS);
    }
}
