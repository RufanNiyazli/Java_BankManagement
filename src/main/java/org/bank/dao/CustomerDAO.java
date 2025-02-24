package org.bank.dao;

import org.bank.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {
    public boolean signUp(String id, String name, String surname, String phone, String email) {
        String Sql = "INSERT INTO customers (id, name, surname, phone, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(Sql);
        ) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, surname);
            stmt.setString(4, phone);
            stmt.setString(5, email);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

