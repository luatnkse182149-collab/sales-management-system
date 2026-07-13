package manager;

import java.util.ArrayList;
import java.util.Scanner;
import model.Product;
import model.ImportedProduct; // Import lớp con mới tạo

// import sau khi tạo saveProductsToFile và loadProductsFromFile 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class ProductList {
    
    private final ArrayList<Product> productList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void addProduct() {
        System.out.println("--- Select Product Type ---");
        System.out.println("1. Local Product (Standard)");
        System.out.println("2. Imported Product");
        System.out.print("Choose: ");
        int type = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Product ID: ");
        String id = sc.nextLine();

        // Thuật toán kiểm tra trùng ID (Giữ nguyên từ M2)
        for(Product p : productList) {
            if(p.getProductID().equalsIgnoreCase(id)) {
                System.out.println("Duplicate Product ID!");
                return;
            }
        }

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Enter Stock Quantity: ");
        int stock = Integer.parseInt(sc.nextLine());

        if(price < 0 || stock < 0) {
            System.out.println("Invalid Data!");
            return;
        }

        if (type == 1) {
            // Khởi tạo sản phẩm thường
            Product product = new Product(id, name, category, price, stock);
            productList.add(product);
            System.out.println("Standard Product Added Successfully.");
        } else if (type == 2) {
            // Thu thập thêm thông tin riêng của sản phẩm nhập khẩu
            System.out.print("Enter Import Tax (e.g., 0.1 for 10%): ");
            double tax = Double.parseDouble(sc.nextLine());
            
            System.out.print("Enter Origin Country: ");
            String country = sc.nextLine();
            
            if(tax < 0) {
                System.out.println("Invalid Tax Rate!");
                return;
            }

            // Lưu đối tượng con vào danh sách kiểu dữ liệu lớp cha (Polymorphic Collection)
            Product importedProduct = new ImportedProduct(id, name, category, price, stock, tax, country);
            productList.add(importedProduct);
            System.out.println("Imported Product Added Successfully.");
        } else {
            System.out.println("Invalid Type Choice!");
        }
    }

    public void viewProduct() {
        System.out.println("===== PRODUCT LIST =====");
        for(Product p : productList) {
// Cơ chế Đa hình lúc chạy (Run-time Polymorphism):
// Nếu p là ImportedProduct, hàm getPrice() đã ghi đè sẽ tự động được kích hoạt để in ra giá đã cộng thuế
            System.out.print(p.getProductID() + " | " 
                             + p.getProductName() + " | " 
                             + p.getCategory() + " | " 
                             + p.getPrice() + " | " 
                             + p.getStockQuantity());
            
            // Kiểm tra và hiển thị thêm thông tin nếu là sản phẩm nhập khẩu
            if (!(p instanceof model.ImportedProduct)) {
            } else {
                model.ImportedProduct ip = (model.ImportedProduct) p;
                System.out.print(" | Tax: " + (ip.getImportTax() * 100) + "% | Origin: " + ip.getOriginCountry());
            }
            System.out.println();
        }
    }
    
    // Bạn có thể bổ sung thêm phương thức lấy danh sách hoặc tìm kiếm phục vụ cho logic đơn hàng của bạn Lâm
    public ArrayList<Product> getRawList() {
        return productList;
    }
    
    public Product findProductByCode(String id) {
    for (Product p : productList) {
        if (p.getProductID().equalsIgnoreCase(id)) {
            return p;
        }
    }
    return null;
}
//  saveProductsToFile() ở đây

  public void saveProductsToFile(String fileName) {

    try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {

        for (Product p : productList) {

            if (p instanceof ImportedProduct) {

                ImportedProduct ip = (ImportedProduct) p;

                pw.println(
                        "IMPORTED,"
                        + ip.getProductID() + ","
                        + ip.getProductName() + ","
                        + ip.getCategory() + ","
                        + ip.getPrice() + ","
                        + ip.getStockQuantity() + ","
                        + ip.getImportTax() + ","
                        + ip.getOriginCountry());

            } else {

                pw.println(
                        "PRODUCT,"
                        + p.getProductID() + ","
                        + p.getProductName() + ","
                        + p.getCategory() + ","
                        + p.getPrice() + ","
                        + p.getStockQuantity());

            }

        }

        System.out.println("Product data saved successfully.");

    } catch (IOException e) {

        System.out.println("Error saving product file.");

    }

}
    
// loadProductsFromFile() ở đây    
    public void loadProductsFromFile(String fileName) {

    productList.clear();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            if (data[0].equalsIgnoreCase("PRODUCT")) {

                Product p = new Product(
data[1],
                        data[2],
                        data[3],
                        Double.parseDouble(data[4]),
                        Integer.parseInt(data[5]));

                productList.add(p);

            }

            else if (data[0].equalsIgnoreCase("IMPORTED")) {

                ImportedProduct ip =
                        new ImportedProduct(
                                data[1],
                                data[2],
                                data[3],
                                Double.parseDouble(data[4]),
                                Integer.parseInt(data[5]),
                                Double.parseDouble(data[6]),
                                data[7]);

                productList.add(ip);

            }

        }

        System.out.println("Product data loaded successfully.");

    } catch (IOException e) {

        System.out.println("Error loading product file.");

    }

    
}


}