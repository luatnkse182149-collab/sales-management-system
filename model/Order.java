package model;

import java.util.ArrayList;

public class Order {
    private String orderID;
    private Customer customer; // Đa hình: Dùng lớp cha để tham chiếu đến cả Customer và VipCustomer
    private ArrayList<Product> items; // Danh sách sản phẩm trong đơn hàng

    public Order() {
        this.items = new ArrayList<>();
    }

    public Order(String orderID, Customer customer) {
        this.orderID = orderID;
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    // Thêm sản phẩm vào đơn hàng
    public void addProductToOrder(Product p) {
        this.items.add(p);
    }

    /**
     
     * Hàm tự động tính toán tổng tiền sau khi đã áp dụng các chính sách giảm giá/thuế đa hình.
     */
    public double calculateTotal() {
        double total = 0;

        // 1. Tính tổng tiền sản phẩm (Đa hình: Nếu là ImportedProduct, p.getPrice() tự cộng thuế)
        for (Product p : items) {
            total += p.getPrice();
        }

        // 2. Kiểm tra đa hình khách hàng (Dynamic Binding)
        // Nếu đối tượng thực tế chạy ở Runtime là VipCustomer, áp dụng discountRate của riêng họ
        if (this.customer instanceof VipCustomer) {
            VipCustomer vip = (VipCustomer) this.customer;
            System.out.println("\n[Polymorphism Alert] VIP Customer detected: " + vip.getCustomerName());
            System.out.println("-> Applying personal discount rate: " + (vip.getDiscountRate() * 100) + "%");
            total = total * (1.0 - vip.getDiscountRate());
        } else {
            System.out.println("\nStandard Customer detected: " + this.customer.getCustomerName() + ". No discount applied.");
        }

        return total;
    }

    // Getter và Setter
    public String getOrderID() { return orderID; }
    public void setOrderID(String orderID) { this.orderID = orderID; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public ArrayList<Product> getItems() { return items; }
}