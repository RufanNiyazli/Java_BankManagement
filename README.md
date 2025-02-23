﻿# Java_BankManagement
-- Verilənlər bazasını yaradın
CREATE DATABASE IF NOT EXISTS bank_system;
USE bank_system;

-- Müştərilər cədvəli
CREATE TABLE customers (
    id VARCHAR(7) PRIMARY KEY,  -- ID olaraq FIN kodu istifadə edilir
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

-- Bank hesabları cədvəli
CREATE TABLE accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    iban VARCHAR(34) UNIQUE NOT NULL,
    cvv VARCHAR(4) NOT NULL,
    pin_code VARCHAR(6) NOT NULL,  -- PIN kod üçün sütun əlavə etdik
    balance DECIMAL(15,2) NOT NULL DEFAULT 0,  -- DEFAULT 0 əlavə etdik
    customer_id VARCHAR(7) NOT NULL,  -- Müştəri ID-si FIN kodu olur
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
);


-- Əməliyyatlar (Transactions) cədvəli
CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL,
    transaction_type ENUM('deposit', 'withdraw', 'transfer') NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

-- Köçürmələr (Transferlər üçün əlavə cədvəl)
CREATE TABLE transfers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_account_id INT NOT NULL,
    receiver_account_id INT NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_account_id) REFERENCES accounts(id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_account_id) REFERENCES accounts(id) ON DELETE SET NULL
);

-- Kreditlər cədvəli
CREATE TABLE loans (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id VARCHAR(7) NOT NULL,  -- Müştərinin FIN kodu ID kimi istifadə olunur
    amount DECIMAL(15,2) NOT NULL,
    interest_rate DECIMAL(5,2) NOT NULL,
    duration_months INT NOT NULL,
    remaining_balance DECIMAL(15,2) NOT NULL DEFAULT 0,  -- Default olaraq 0 əlavə etdik
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
);

-- Kredit ödənişləri cədvəli
CREATE TABLE loan_payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    loan_id INT NOT NULL,
    amount_paid DECIMAL(15,2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (loan_id) REFERENCES loans(id) ON DELETE CASCADE
);
