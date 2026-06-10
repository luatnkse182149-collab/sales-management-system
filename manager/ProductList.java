package manager;

import java.util.ArrayList;
import java.util.Scanner;
import model.Product;

public class ProductList {
    
    private final ArrayList<Product> productList =
            new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void addProduct() {

        System.out.print("Enter Product ID: ");
        String id = sc.nextLine();

        for(Product p : productList) {

            if(p.getProductID()
                    .equalsIgnoreCase(id)) {

                System.out.println(
                        "Duplicate Product ID!");
                return;
            }
        }

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Price: ");
        double price =
                Double.parseDouble(sc.nextLine());

        System.out.print("Enter Stock Quantity: ");
        int stock =
                Integer.parseInt(sc.nextLine());        

        if(price < 0 || stock < 0) {

            System.out.println(
                    "Invalid Data!");
            return;
        }

        Product product =
                new Product(id, name,
                        category, price, stock);

        productList.add(product);

        System.out.println(
                "Product Added Successfully.");
    }

    public void viewProduct() {

        System.out.println(
                "===== PRODUCT LIST =====");

        for(Product p : productList) {

            System.out.println(
                    p.getProductID()
                    + " | "
                    + p.getProductName()
                    + " | "
                    + p.getCategory()
                    + " | "
                    + p.getPrice()
                    + " | "
                    + p.getStockQuantity());
        }
    }
    }
