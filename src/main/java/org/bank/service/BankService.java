package org.bank.service;

import org.bank.dao.AccountDAO;
import org.bank.dao.CustomerDAO;
import org.bank.dao.TransactionDAO;
import org.bank.model.BankAccount;

public class BankService {
    private final CustomerDAO customerDAO;
    private final AccountDAO accountDAO;
    private final TransactionDAO transactionDAO;

    public BankService(CustomerDAO customerDAO, AccountDAO accountDAO, TransactionDAO transactionDAO) {
        this.customerDAO = customerDAO;
        this.accountDAO = accountDAO;
        this.transactionDAO = transactionDAO;
    }

    public void registesCustomer(String id, String name, String surname, String phone, String email, String pin_code) {

        if (customerDAO.signUp(id, name, surname, phone, email)) {

            BankAccount bankAccount = accountDAO.newAccount(pin_code, id);

            if (bankAccount != null) {
                System.out.println("You have successfully registered.");

                System.out.println("Account Details: ");
                System.out.println(bankAccount.toString());
            } else {
                System.out.println("Problem occurred while creating account.");
            }
        } else {
            System.out.println("Problem occurred while registering customer.");
        }
    }

    public boolean login(String card_number, String pin_code, String customer_id) {
        if (accountDAO.login(card_number, pin_code, customer_id)) {
            System.out.println("You succesfully login...");
            return true;
        } else {
            System.out.println("Error occured");
            return false;
        }
    }

    //    login show balance
    public void showBalance(String card_number) {
        double balance = accountDAO.showBalanca(card_number);
        if (balance != -1) {
            System.out.println("Your balance: " + balance);
        } else {
            System.out.println("Balance is not find.");
        }
    }

    public void transferMoney(String from_card_number, String to_card_number, double amount) {
        if (accountDAO.checkBalance(from_card_number, amount)) {
            accountDAO.transfer(to_card_number, amount, from_card_number);
        } else {
            System.out.println("there is not much money");
        }

    }
//    transfer cedveli yazmaq

    public void transferSheet(String receiver_account_id, String sender_account_id, double amount) {
        if (transactionDAO.transferMoney(receiver_account_id, sender_account_id, amount)) {
            System.out.println("Succesfullly write on the shhet");
        } else {
            System.out.println("problem occur");
        }
    }
}
