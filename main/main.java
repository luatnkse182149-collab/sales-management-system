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
            System.out.println("6. Create Order & Print Bill");
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
                    System.out.println("\n===== CREATE ORDER =====");

                    // 1. Select an existing customer
                    System.out.print("Enter Customer ID: ");
                    String custCode = sc.nextLine();
                    Customer selectedCustomer = customerList.findCustomerByCode(custCode);

                    if (selectedCustomer == null) {
                        System.out.println("Customer not found! Please add the customer first (option 2).");
                        break;
}

                    // 2. Create a new order for that customer
                    String orderCode = "OR-" + System.currentTimeMillis();
                    System.out.println("Generated Order ID: " + orderCode);
                    Order order = new Order(orderCode, selectedCustomer);

                    // 3. Add products to the order (loop until user types 'done')
                    boolean addingProducts = true;
                    while (addingProducts) {
                        System.out.print("Enter Product ID to add (or type 'done' to finish): ");
                        String prodCode = sc.nextLine();

                        if (prodCode.equalsIgnoreCase("done")) {
                            addingProducts = false;
                            continue;
                        }

                        Product selectedProduct = productList.findProductByCode(prodCode);
                        if (selectedProduct == null) {
                            System.out.println("Product not found with ID: " + prodCode);
                            continue;
                        }

                        //System.out.print("Enter quantity: ");
                        //int qty = Integer.parseInt(sc.nextLine());

                        //if (qty <= 0) {
                            //System.out.println("Invalid quantity!");
                            //continue;
                        //}
                        // code mới Không cho bán vượt số lượng tồn

                        if (qty > selectedProduct.getStockQuantity()) {
                            System.out.println("Not enough stock!");
                            System.out.println("Current stock: " + selectedProduct.getStockQuantity());
                            continue;
                            }
                         // kết thúc
                        // Order only supports adding one product at a time, so loop by quantity
                        for (int i = 0; i < qty; i++) {
                            order.addProductToOrder(selectedProduct);
                        }

                        System.out.println("Added " + qty + " x " + selectedProduct.getProductName());
                    }

                    if (order.getItems().isEmpty()) {
                        System.out.println("Order has no items. Cancelling bill creation.");
                        break;
                    }

                    // 4. Calculate total and print the bill
                    double total = order.calculateTotal();
                    // code thêm Sau khi bán tự động trừ kho
                    for (Product p : order.getItems()) {
                         p.setStockQuantity(p.getStockQuantity() - 1);
                    }
                    System.out.println("\n========== BILL ==========");
                    System.out.println("Order ID: " + order.getOrderID());
                    System.out.println("Customer: " + selectedCustomer.getCustomerName()
                            + (selectedCustomer instanceof VipCustomer ? " (VIP)" : " (Regular)"));
                    System.out.println("---------------------------");
                    for (Product p : order.getItems()) {
                        System.out.println("- " + p.getProductName() + " : $" + p.getPrice());
                    }
                    System.out.println("---------------------------");
                    System.out.println("Total Amount: $" + total);
                    System.out.println("===========================");
                    break;

                case 7:
                    
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
