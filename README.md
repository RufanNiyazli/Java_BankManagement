# Bank System Database Schema

## Database Name: `bank_system`

This database is designed for a banking system that manages customers, accounts, loans, transactions, and transfers.

## Tables

### 1. Customers Table
```sql
CREATE TABLE customers (
  id VARCHAR(7) NOT NULL,
  name VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  email VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY phone (phone),
  UNIQUE KEY email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

### 2. Accounts Table
```sql
CREATE TABLE accounts (
  id INT NOT NULL AUTO_INCREMENT,
  card_number CHAR(16) NOT NULL,
  cvv CHAR(3) NOT NULL,
  pin_code CHAR(4) NOT NULL,
  balance DECIMAL(15,2) NOT NULL DEFAULT '0.00',
  customer_id VARCHAR(7) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY iban (card_number),
  KEY customer_id (customer_id),
  CONSTRAINT accounts_ibfk_1 FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

### 3. Loans Table
```sql
CREATE TABLE loans (
  id INT NOT NULL AUTO_INCREMENT,
  customer_id VARCHAR(7) NOT NULL,
  amount DECIMAL(15,2) NOT NULL,
  interest_rate DECIMAL(5,2) NOT NULL,
  duration_months INT NOT NULL,
  remaining_balance DECIMAL(15,2) NOT NULL DEFAULT '0.00',
  created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY customer_id (customer_id),
  CONSTRAINT loans_ibfk_1 FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

### 4. Loan Payments Table
```sql
CREATE TABLE loan_payments (
  id INT NOT NULL AUTO_INCREMENT,
  loan_id INT NOT NULL,
  amount_paid DECIMAL(15,2) NOT NULL,
  payment_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY loan_id (loan_id),
  CONSTRAINT loan_payments_ibfk_1 FOREIGN KEY (loan_id) REFERENCES loans (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

### 5. Transactions Table
```sql
CREATE TABLE transactions (
  id INT NOT NULL AUTO_INCREMENT,
  account_id VARCHAR(7) NOT NULL,
  transaction_type ENUM('deposit', 'withdraw', 'transfer') NOT NULL,
  amount DECIMAL(15,2) NOT NULL,
  created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY transactions_ibfk_1 (account_id),
  CONSTRAINT transactions_ibfk_1 FOREIGN KEY (account_id) REFERENCES accounts (customer_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

### 6. Transfers Table
```sql
CREATE TABLE transfers (
  id INT NOT NULL AUTO_INCREMENT,
  sender_account_id INT NOT NULL,
  receiver_account_id INT NOT NULL,
  amount DECIMAL(15,2) NOT NULL,
  created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY sender_account_id (sender_account_id),
  KEY receiver_account_id (receiver_account_id),
  CONSTRAINT transfers_ibfk_1 FOREIGN KEY (sender_account_id) REFERENCES accounts (id) ON DELETE CASCADE,
  CONSTRAINT transfers_ibfk_2 FOREIGN KEY (receiver_account_id) REFERENCES accounts (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

## How to Use
1. Create the database:
   ```sql
   CREATE DATABASE bank_system;
   USE bank_system;
   ```
2. Copy and execute the table creation SQL commands in your MySQL environment.
3. Ensure foreign key constraints are maintained for data integrity.

This schema helps manage a simple banking system with customer accounts, transactions, loans, and money transfers.

