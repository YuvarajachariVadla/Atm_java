# ATM Java Project

## Overview
This is a simple **ATM Simulation Project in Java** built for learning backend concepts.  
It allows users to:
- Create an account
- Authenticate with a PIN
- Deposit money
- Withdraw money
- Check balance
- View transaction history

## Project Structure
- `Account.java` → Represents a bank account with fields for account number, balance, and hashed PIN.
- `AccountStore.java` → Stores and manages all accounts in memory.
- `ATMService.java` → Contains the main ATM operations like deposit, withdraw, balance check.
- `SecurePinHasher.java` → Provides secure hashing for PIN authentication.
- `Transaction.java` → Represents a transaction (deposit/withdraw).
- `TransactionType.java` → Enum for transaction type (DEPOSIT, WITHDRAW).
- `ATMApp.java` → Main entry point (runs the program).

## How to Run
1. Open project in **Eclipse** or **VS Code (with Java Extension Pack)**.
2. Run `ATMApp.java`.
3. Follow the on-screen instructions.

## How to Compile & Run via Command Line (Optional)
```sh
cd src/atm
javac atm\*.java
java atm.ATMApp
```

## Features for Resume
- Demonstrates **Object-Oriented Programming (OOP)**: Encapsulation, Abstraction, Polymorphism.
- Shows **modular coding practices** with multiple classes.
- Includes **secure PIN handling** with hashing.
- Resume Highlight: *"Developed an ATM simulation system in Java using OOP principles with secure authentication and transaction management."*

## Future Enhancements
- Add file/database storage for account persistence.
- Add multiple users with login/logout system.
- Add GUI using JavaFX or Swing.
