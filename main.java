
package main;

import java.util.Scanner;
import manager.CustomerList;
import manager.ProductList;

public class main {

    public static void main(String[] args) {

        System.out.println("System Running...");

        Scanner sc = new Scanner(System.in);

        ProductList productList
                = new ProductList();

        CustomerList customerList
                = new CustomerList();

        int choice;

        do {

            System.out.println(
                    "\n===== SALES MANAGEMENT SYSTEM =====");

            System.out.println(
                    "1. Manage Products");

            System.out.println(
                    "2. Manage Customers");

            System.out.println(
                    "3. View Products");

            System.out.println(
                    "4. View Customers");

            System.out.println(
                    "5. Exit");

            System.out.print(
                    "Choose: ");

            choice
                    = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    productList.addProduct();
                    break;

                case 2:
                    customerList.addCustomer();
                    break;

                case 3:
                    productList.viewProduct();
                    break;

                case 4:
                    customerList.viewCustomer();
                    break;

                case 5:
                    System.out.println(
                            "Exit Program");
                    break;

                default:
                    System.out.println(
                            "Invalid Choice!");
            }

        } while (choice != 5);
    }
}
