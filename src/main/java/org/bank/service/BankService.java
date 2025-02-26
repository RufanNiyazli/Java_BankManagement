package org.bank.service;

import org.bank.dao.AccountDAO;
import org.bank.dao.CustomerDAO;
import org.bank.model.BankAccount;

public class BankService {
    private final CustomerDAO customerDAO;
    private final AccountDAO accountDAO;

    public BankService(CustomerDAO customerDAO, AccountDAO accountDAO) {
        this.customerDAO = customerDAO;
        this.accountDAO = accountDAO;
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
}
