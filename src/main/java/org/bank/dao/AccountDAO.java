package org.bank.dao;

import org.bank.DatabaseConnection;
import org.bank.model.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class AccountDAO {

    public BankAccount newAccount(String pin_code, String customer_id) {
        String Sql = "INSERT INTO accounts (card_number, cvv, pin_code, customer_id) VALUES (?, ?, ?, ?)";
        BankData result = generateNumberandCvv(); // Kart nömrəsi və CVV yaradılır

        BankAccount bankAccount = new BankAccount(customer_id, result.cardNumber(), result.cvv(), 0); // BankAccount obyektini yaradıq
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(Sql)) {
            stmt.setString(1, result.cardNumber());
            stmt.setString(2, result.cvv());
            stmt.setString(3, pin_code);
            stmt.setString(4, customer_id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return bankAccount;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static BankData generateNumberandCvv() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        String cvv = String.format("%03d", random.nextInt(1000));
        return new BankData(cardNumber.toString(), cvv);
    }


    public record BankData(String cardNumber, String cvv) {
    }

    //    Login
    public boolean login(String card_number, String pin_code, String customer_id) {
        String Sql = "SELECT * FROM accounts WHERE card_number=? AND pin_code=? AND customer_id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(Sql);

        ) {
            stmt.setString(1, card_number);
            stmt.setString(2, pin_code);
            stmt.setString(3, customer_id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //    login show balanace
    public double showBalanca(String card_number) {
        String Sql = "SELECT * FROM accounts WHERE card_number =?";
        double balance = 0;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(Sql);) {
            stmt.setString(1, card_number);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("balance");
                return balance;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //          login increase
    public void transfer(String to_card_number, double amount, String from_card_number) {
        String withdrawSql = "UPDATE accounts SET balance = balance - ? WHERE card_number = ?";
        String depositSql = "UPDATE accounts SET balance = balance + ? WHERE card_number = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement withdrawstmt = conn.prepareStatement(withdrawSql);
                 PreparedStatement depositstmt = conn.prepareStatement(depositSql)) {


                withdrawstmt.setDouble(1, amount);
                withdrawstmt.setString(2, from_card_number);
                int withdrawResult = withdrawstmt.executeUpdate();

                if (withdrawResult == 0) {
                    conn.rollback();
                    System.out.println("Unsuccessful: Withdrawal failed.");
                    return;
                }


                depositstmt.setDouble(1, amount);
                depositstmt.setString(2, to_card_number);
                int depositResult = depositstmt.executeUpdate();

                if (depositResult == 0) {
                    conn.rollback();
                    System.out.println("Unsuccessful: Deposit failed.");
                    return;
                }

                conn.commit();
                System.out.println("Transfer successful.");

            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Transaction failed. Rolled back.", e);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }


    public boolean checkBalance(String from_card, double transfer_money) {
        String Sql = "Select * FROM accounts WHERE card_number=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(Sql)) {
            stmt.setString(1, from_card);
            double from_balance = 0;
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    from_balance = Double.parseDouble(rs.getString("balance"));
                }
                if (from_balance > transfer_money) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public void updateBalance(String card_number, double balance) {
//        String Sql = "UPDATE accounts SET balance = ? WHERE card_number = ?";
//        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(Sql)) {
//            stmt.setString(1, card_number);
//            stmt.setDouble(2, balance);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
