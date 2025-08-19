package atm;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Simple SHA-256 PIN hashing utility (demo only).
 * PRODUCTION TIP: add a random per-user salt and store it with the hash.
 */
public class SecurePinHasher {
    public static String hash(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(pin.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 unsupported", e);
        }
    }

    public static boolean verify(String plainPin, String expectedHash) {
        return hash(plainPin).equals(expectedHash);
    }
}
