package org.bank.dao;

import org.bank.DatabaseConnection;
import org.bank.model.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class AccountDAO {

    public BankAccount newAccount(String pin_code, String customer_id) {
        String Sql = "INSERT INTO accounts (card_number, cvv, pin_code, customer_id) VALUES (?, ?, ?, ?)";
        BankData result = generateNumberandCvv(); // Kart nömrəsi və CVV yaradılır

        BankAccount bankAccount = new BankAccount(customer_id, result.cardNumber(), result.cvv(), 0); // BankAccount obyektini yaradıq
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(Sql)
        ) {
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
}
