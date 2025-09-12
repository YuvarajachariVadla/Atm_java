🏦 ATM Simulation Project (Java)
📌 Overview

This is a console-based ATM simulation project built using Java.
The project mimics the basic functionality of an ATM machine, allowing users to:

* Create a new account

* Authenticate with a PIN

* Deposit money

* Withdraw money

* Check balance

* View transaction history

The project demonstrates Object-Oriented Programming (OOP) concepts such as classes, objects, encapsulation, abstraction, and method calls.
It is designed for beginners in Java who want to strengthen their fundamentals and build portfolio projects.

🚀 Features

✅ Account Creation with username and PIN

✅ Secure Authentication with hashed PIN storage

✅ Deposit & Withdraw with balance validation

✅ Check Balance instantly

✅ Transaction History (Deposit/Withdraw)

✅ User-Friendly Menu in terminal/console

🛠️ Technologies Used

Language: Java (JDK 24 or above)

IDE: Eclipse / VS Code

Version Control: Git & GitHub

## 📂 Project Structure

```
ATM_Java_Project/
│── src/
│   └── atm/
│       ├── Account.java
│       ├── AccountStore.java
│       ├── ATMApp.java
│       ├── ATMService.java
│       ├── SecurePinHasher.java
│       ├── Transaction.java
│       └── TransactionType.java
│── README.md

```


🔑 How It Works

1. Account Creation:
User creates an account with a username and PIN. PIN is hashed for security.

2. Authentication:
User logs in with username and PIN. The system validates credentials.

3. ATM Menu:
Once logged in, users can:

   * Deposit money

   * Withdraw money (only if balance is sufficient)

   * Check account balance

   * View transaction history

4. Transactions:
All deposits and withdrawals are recorded as transactions.

▶️ How to Run the Project
1. Clone the Repository
  ```
   git clone https://github.com/your-username/atm-java.git

   cd atm-java
  ```

3. Compile the Code
    
    Navigate to src folder:
     ```

    cd src/atm
     
    javac *.java
    ```
3. Run the Project
   ```
      java ATMApp
   ```  

📸 Sample Output


=== Welcome to ATM ===
1. Create Account
2. Login
3. Exit
Enter choice: 1
Enter username: Alice
Enter PIN: ****
✅ Account created successfully!

=== Login ===
Enter username: Alice
Enter PIN: ****
✅ Login successful!

--- ATM Menu ---
1. Deposit
2. Withdraw
3. Check Balance
4. View Transactions
5. Exit
Enter choice: 1
Enter deposit amount: 5000
✅ Deposit successful! Balance: 5000


🎯 Learning Outcomes

By building this project, you will learn:

* Java basics (classes, objects, methods)

* File/project structure in Java

* Secure PIN handling (hashing)

* How to build menu-driven console applications

* GitHub repository setup and project deployment
  
