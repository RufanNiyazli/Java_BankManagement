package org.bank;

import org.bank.dao.AccountDAO;
import org.bank.dao.CustomerDAO;
import org.bank.service.BankService;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();
        AccountDAO accountDAO = new AccountDAO();
        BankService bankService = new BankService(customerDAO, accountDAO);

        System.out.println("Welcome,Sir.");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Sign in -->1: Sign up-->2: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.println("----Sign in Page----");
            System.out.print("Enter your card Number: ");
            String signInCardNumber = scanner.nextLine().trim();
            while (signInCardNumber.length() != 16 || !signInCardNumber.matches("[0-9]{16}")) {
                System.out.print("Please enter valid card number: ");
                signInCardNumber = scanner.nextLine();
            }
            System.out.print("Enter Your pin code: ");
            String signInPin = scanner.nextLine().trim();
            while (signInPin.length() != 4 || !signInPin.matches("[0-9]{4}")) {
                System.out.print("Please enter valid pin: ");
                signInPin = scanner.nextLine();
            }
            System.out.print("Enter Fin code: ");
            String signInFin = scanner.nextLine().trim();
            while (signInFin.length() != 7) {
                System.out.print("Enter valid Fin code: ");
                signInFin = scanner.nextLine().trim();
            }

            bankService.login(signInCardNumber, signInPin, signInFin);

        } else if (choice == 2) {
            System.out.println("----Sign up Menu----");
            System.out.print("Enter the name: ");
            String customerSignUpName = scanner.nextLine().trim();
            System.out.print("Enter the Surname: ");
            String customerSignUpSurname = scanner.nextLine().trim();
            System.out.print("Enter Your Fin Code: ");
            String signUpFin = scanner.nextLine().trim();
            while (signUpFin.length() != 7) {
                System.out.print("Please enter valid Fin code: ");
                signUpFin = scanner.nextLine().trim();
            }
            System.out.print("Enter your Phone Number: ");
            String signUpPhone = scanner.nextLine().trim();
            System.out.print("Enter Your email: ");
            String signUpEmail = scanner.nextLine().trim();
            while (!signUpEmail.matches(".*@.*")) {
                System.out.print("Enter valid email: ");
                signUpEmail = scanner.nextLine().trim();

            }
            System.out.print("Enter your pin code(Pin code must be contain 4 number): ");
            String signUpPin = scanner.nextLine();
            while (signUpPin.length() != 4 || !signUpPin.matches("[0-9]{4}")) {
                System.out.print("Please enter valid: ");
                signUpPin = scanner.nextLine();
            }


            bankService.registesCustomer(signUpFin, customerSignUpName, customerSignUpSurname, signUpPhone, signUpEmail, signUpPin);


        }
        {

        }

    }
}