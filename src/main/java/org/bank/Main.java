package org.bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome,Sir.");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Sign in -->1: Sign up-->2: ");
        int choice= scanner.nextInt();
        scanner.nextLine();
        if (choice==1){

        } else if (choice==2) {
            System.out.println("----Sign up Menu----");
            System.out.print("Enter the name: ");
            String customerSignUpName = scanner.nextLine().trim();
            System.out.print("Enter the Surname: ");
            String customerSignUpSurname= scanner.nextLine().trim();
            System.out.print("Enter Your Fin Code: ");
            String signUpFin= scanner.nextLine().trim();
            while(signUpFin.length()!=7){
                System.out.print("Please enter valid Fin code: ");
                signUpFin=scanner.nextLine().trim();
            }

        }
        {

        }

    }
}