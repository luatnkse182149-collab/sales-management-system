package main;

import java.util.Scanner;
import manager.CustomerList;
import manager.ProductList;
import model.Customer;
import model.VipCustomer;
import model.Product;
import model.ImportedProduct;
import model.Order;
import java.util.ArrayList;

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

    // 1. Select Customer
    System.out.print("Enter Customer ID: ");
    String custCode = sc.nextLine();

    Customer selectedCustomer = customerList.findCustomerByCode(custCode);

    if (selectedCustomer == null) {
        System.out.println("Customer not found! Please add customer first.");
        break;
    }

    // 2. Create Order
    String orderCode = "OR-" + System.currentTimeMillis();
    Order order = new Order(orderCode, selectedCustomer);

    // 3. Add Products
    boolean addingProducts = true;

    while (addingProducts) {

        System.out.print("Enter Product ID (or 'done'): ");
        String prodCode = sc.nextLine();

        if (prodCode.equalsIgnoreCase("done")) {
            break;
        }

        Product selectedProduct = productList.findProductByCode(prodCode);

        if (selectedProduct == null) {
            System.out.println("Product not found!");
            continue;
        }

        System.out.print("Enter Quantity: ");
        int qty = Integer.parseInt(sc.nextLine());

        if (qty <= 0) {
            System.out.println("Quantity must be greater than 0!");
            continue;
        }

        if (qty > selectedProduct.getStockQuantity()) {
            System.out.println("Not enough stock!");
            System.out.println("Current stock: "
                    + selectedProduct.getStockQuantity());
            continue;
        }

        for (int i = 0; i < qty; i++) {
            order.addProductToOrder(selectedProduct);
        }

        selectedProduct.setStockQuantity(
                selectedProduct.getStockQuantity() - qty);

        System.out.println("Added "
                + qty
                + " x "
                + selectedProduct.getProductName());

    }

    if (order.getItems().isEmpty()) {
        System.out.println("Order has no items.");
        break;
    }

    // Calculate Total
    double total = order.calculateTotal();

    // Print Bill
    System.out.println("\n================ SALES BILL ================");

    System.out.println("Order ID : " + order.getOrderID());

    System.out.println("Customer : "
            + selectedCustomer.getCustomerName()
            + (selectedCustomer instanceof VipCustomer
            ? " (VIP)"
            : " (Regular)"));

    System.out.println("---------------------------------------------------------------");

    System.out.printf("%-5s %-20s %-8s %-12s %-12s\n",
            "No",
            "Product",
            "Qty",
            "Unit Price",
            "Line Total");

    System.out.println("---------------------------------------------------------------");

    ArrayList<Product> printed = new ArrayList<>();

    int no = 1;

    for (Product p : order.getItems()) {

        if (printed.contains(p))
            continue;

        int qty = 0;

        for (Product item : order.getItems()) {

            if (item == p) {
                qty++;
            }

        }

        double lineTotal = qty * p.getPrice();

        System.out.printf("%-5d %-20s %-8d %-12.2f %-12.2f\n",
                no++,
                p.getProductName(),
                qty,
                p.getPrice(),
                lineTotal);

        printed.add(p);

    }

    System.out.println("---------------------------------------------------------------");

    System.out.printf("%-47s %.2f\n",
            "Grand Total ($):",
            total);

    System.out.println("===============================================================");

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
