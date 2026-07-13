package main;

import java.util.Scanner;
import manager.CustomerList;
import manager.ProductList;
import model.Customer;
import model.VipCustomer;
import model.Product;
import model.ImportedProduct;
import model.Order;

public class main {

    public static void main(String[] args) {

        System.out.println("System Running...");

        Scanner sc = new Scanner(System.in);

        ProductList productList = new ProductList();
        CustomerList customerList = new CustomerList();
        customerList.loadCustomersFromFile("customers.txt");
        productList.loadProductsFromFile("products.txt");

        int choice;

        do {
            System.out.println("\n===== SALES MANAGEMENT SYSTEM =====");
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Customers");
            System.out.println("3. View Products");
            System.out.println("4. View Customers");
            System.out.println("5. View VIP Customers");
            System.out.println("6. Create & Process Demo Transaction");
            System.out.println("7. Exit");
            System.out.print("Choose: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    productList.addProduct();
                    break;

                case 2:
                    System.out.println("\n--- Add Customer Sub-Menu ---");
                    System.out.println("1. Add Regular Customer");
                    System.out.println("2. Add VIP Customer");
                    System.out.print("Choose option: ");
                    int subChoice = Integer.parseInt(sc.nextLine());
                    
                    switch (subChoice) {
                        case 1:
                            customerList.addCustomer();
                            break;
                        case 2:
                            customerList.addVipCustomer();
                            break;
                        default:
                            System.out.println("Invalid Choice!");
                    }
                    break;

                case 3:
                    productList.viewProduct();
                    break;

                case 4:
                    customerList.viewCustomer();
                    break;

                case 5:
                    customerList.viewVipCustomer();
                    break;

                case 6:
                    
                    System.out.println("\n===== CREATE DEMO TRANSACTION =====");
                    
                    
                    Customer normalCust = new Customer("C01", "Tran Minh Nam", "090111", "HCMC");
                    VipCustomer vipCust = new VipCustomer("V02", "Vu Le Hoang Long", "090222", "Hanoi", "Gold", 0.15, "VIP-GOLD-777", 500.0);
                    
                    
                    Product regularProd = new Product("P01", "Standard Laptop", "Tech", 1000.0, 10);
                    
                    ImportedProduct importProd = new ImportedProduct("P02", "Imported Wine", "Food", 100.0, 5, 0.2, "France");

                    System.out.println("----------------------------------------------------------------");
                    System.out.println(">>> TRANSACTION 1: Standard Customer buying an Imported Product");
                    
                    Order order1 = new Order("OR-001", normalCust); 
                    order1.addProductToOrder(importProd); 
                    
                    
                    double total1 = order1.calculateTotal();
                    System.out.println("Final Bill Amount for " + normalCust.getCustomerName() + ": $" + total1); 
                    

                    System.out.println("----------------------------------------------------------------");
                    System.out.println(">>> TRANSACTION 2: VIP Customer buying the same Imported Product");
                    
                    Order order2 = new Order("OR-002", vipCust); 
                    order2.addProductToOrder(importProd); // Thêm cùng mặt hàng rượu nhập khẩu
                    
                    
                    double total2 = order2.calculateTotal();
                    System.out.println("Final Bill Amount for " + vipCust.getCustomerName() + ": $" + total2);
                    
                    System.out.println("----------------------------------------------------------------");
                    break;

                case 7:
                    // === TÍCH HỢP M4: TỰ ĐỘNG LƯU DỮ LIỆU TRƯỚC KHI THOÁT ===
                System.out.println("\nSaving data to system...");
                productList.saveProductsToFile("products.txt");
                customerList.saveCustomersToFile("customers.txt");
                
                System.out.println("Exit Program. Goodbye!");
                break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 7);
    }
}