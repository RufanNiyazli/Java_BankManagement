package org.bank.model;

import java.time.LocalDate;

public class Loan {
    private int id;
    private String customerId; // Müştərinin FIN kodu
    private double amount; // Kredit məbləği
    private double interestRate; // İllik faiz
    private int durationMonths; // Müddət (aylarla)
    private double remainingBalance; // Qalan borc
    private LocalDate startDate;

    public Loan(int id, String customerId, double amount, double interestRate, int durationMonths, double remainingBalance, LocalDate startDate) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.durationMonths = durationMonths;
        this.remainingBalance = remainingBalance;
        this.startDate = startDate;
    }


}
