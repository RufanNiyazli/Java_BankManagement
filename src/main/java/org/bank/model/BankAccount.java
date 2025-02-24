package org.bank.model;

public class BankAccount {
    private String customer_id;
    private String card_number;
    private String cvv;
    private int balance;

    public BankAccount(String customer_id,String card_number,String cvv,int balance){
        this.balance=balance;
        this.card_number=card_number;
        this.cvv=cvv;
        this.customer_id=customer_id;
    }
    public BankAccount(){}
    @Override
    public String toString() {
        return "BankAccount{" +
                "customer_id='" + customer_id + '\'' +
                ", card_number='" + card_number + '\'' +
                ", cvv='" + cvv + '\'' +
                ", balance=" + balance +
                '}';
    }
    public  String  getCustomer_id() {
        return customer_id;
    }

    public String getCvv() {
        return cvv;
    }

    public String getCard_number() {
        return card_number;
    }

    public int getBalance() {
        return balance;
    }
}
