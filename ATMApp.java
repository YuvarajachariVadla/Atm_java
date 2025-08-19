package atm;

import java.io.Console;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Console UI: shows initial menu (create account / login), then ATM menu after login.
 */
public class ATMApp {
    public static void main(String[] args) {
        ATMService service = new ATMService();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Welcome to ATM ===");

        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1) Create Account");
            System.out.println("2) Login");
            System.out.println("3) Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> handleCreateAccount(sc, service);
                case "2" -> handleLogin(sc, service);
                case "3" -> {
                    running = false;
                    System.out.println("Thank You! Have a Nice Day!");
                }
                default -> System.out.println("Invalid option.");
            }
        }
        sc.close();
    }

    private static void handleCreateAccount(Scanner sc, ATMService service) {
        try {
            System.out.print("Enter new account number: ");
            String accNo = sc.nextLine().trim();
            System.out.print("Enter card number (16 digits recommended): ");
            String card = sc.nextLine().trim();
            System.out.print("Enter holder name: ");
            String name = sc.nextLine().trim();
            String pin = readPin(sc);
            System.out.print("Enter opening balance (or press Enter for 0): ");
            String balStr = sc.nextLine().trim();
            BigDecimal opening = balStr.isBlank() ? BigDecimal.ZERO : new BigDecimal(balStr);

            Account acc = service.createAccount(accNo, card, name, pin, opening);
            System.out.println("Account created successfully. Card: " + acc.getCardNumber());
        } catch (Exception e) {
            System.out.println("Failed to create account: " + e.getMessage());
        }
    }

    private static void handleLogin(Scanner sc, ATMService service) {
        try {
            System.out.print("Enter card number: ");
            String card = sc.nextLine().trim();
            String pin = readPin(sc);
            Account session = service.authenticate(card, pin);
            System.out.println("Login successful. Welcome, " + session.getHolderName() + ".");
            atmSession(sc, service, session);
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    private static void atmSession(Scanner sc, ATMService service, Account session) {
        boolean active = true;
        while (active) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1) Check Balance");
            System.out.println("2) Deposit");
            System.out.println("3) Withdraw");
            System.out.println("4) Mini Statement (last 5)");
            System.out.println("5) Logout");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> System.out.println("Balance: ₹ " + service.getBalance(session));
                    case "2" -> {
                        BigDecimal amt = readAmount(sc, "Enter deposit amount: ");
                        service.deposit(session, amt);
                        System.out.println("Deposited. New balance: ₹ " + session.getBalance());
                    }
                    case "3" -> {
                        BigDecimal amt = readAmount(sc, "Enter withdraw amount: ");
                        service.withdraw(session, amt);
                        System.out.println("Withdrawn. New balance: ₹ " + session.getBalance());
                    }
                    case "4" -> {
                        List<Transaction> txns = service.miniStatement(session, 5);
                        if (txns.isEmpty()) {
                            System.out.println("No transactions yet.");
                        } else {
                            System.out.println("---- Mini Statement ----");
                            txns.forEach(System.out::println);
                        }
                    }
                    case "5" -> {
                        active = false;
                        System.out.println("Logged out.");
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Attempts to mask PIN if System.console() is available
    private static String readPin(Scanner sc) {
        Console console = System.console(); // may be null in some IDEs
        if (console != null) {
            char[] chars = console.readPassword("Enter PIN: ");
            return new String(chars);
        } else {
            System.out.print("Enter PIN: ");
            return sc.nextLine().trim();
        }
    }

    // Reads a positive amount; throws error if invalid
    private static BigDecimal readAmount(Scanner sc, String prompt) {
        System.out.print(prompt);
        String s = sc.nextLine().trim();
        try {
            BigDecimal amt = new BigDecimal(s);
            if (amt.compareTo(BigDecimal.ZERO) <= 0) throw new NumberFormatException();
            return amt;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Enter a positive numeric amount.");
        }
    }
}
