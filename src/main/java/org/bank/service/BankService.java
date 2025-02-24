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
}
