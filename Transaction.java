package atm;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a single transaction (deposit, withdraw, or balance inquiry).
 * Keeps timestamp, type, amount, post-transaction balance, and an optional note.
 */
public class Transaction {
    private final LocalDateTime timestamp;
    private final TransactionType type;
    private final BigDecimal amount;      // Zero for balance inquiry
    private final BigDecimal postBalance;
    private final String note;

    public Transaction(TransactionType type, BigDecimal amount, BigDecimal postBalance, String note) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.amount = amount == null ? BigDecimal.ZERO : amount;
        this.postBalance = postBalance;
        this.note = note;
    }

    @Override
    public String toString() {
        String time = timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String amtPart = (type != TransactionType.BALANCE_INQUIRY) ? (" | Amt: " + amount) : "";
        String notePart = (note != null && !note.isBlank()) ? (" | " + note) : "";
        return time + " | " + type + amtPart + " | Balance: " + postBalance + notePart;
    }
}
