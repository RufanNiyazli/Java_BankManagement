package org.bank.dao;

import org.bank.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {
    public boolean transferMoney(String receiver_account_id, String sender_account_id, double amount) {
        String Sql = "INSERT INTO transfers (sender_account_id,receiver_account_id,amount) VALUES (?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(Sql)
        ) {
            stmt.setString(1, sender_account_id);
            stmt.setString(2, receiver_account_id);
            stmt.setDouble(3, amount);
            return stmt.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
