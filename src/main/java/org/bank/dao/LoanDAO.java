package org.bank.dao;

import org.bank.model.Loan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoanDAO {
    private Connection connection;

    public LoanDAO(Connection connection) {
        this.connection = connection;
    }

    // Yeni kredit yarat
    public void createLoan(Loan loan) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

        }
    }

    // Müştərinin kreditini gətir
    public Loan getLoanByCustomerId(String customerId) throws SQLException {
        String sql = "SELECT * FROM loans WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

            }
        }
        return null;
    }

    // Ödənişden sonra yenilemek balansi
    public void updateLoanBalance(int loanId, double newBalance) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

        }
    }
}
