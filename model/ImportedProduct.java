package model;
public class ImportedProduct extends Product {
    // 1. Thuộc tính bổ sung riêng cho sản phẩm nhập khẩu
    private double importTax; // Thuế nhập khẩu (ví dụ: 0.1 nghĩa là 10%)
    private String originCountry; // Quốc gia xuất xứ

    // 2. Hàm khởi tạo không tham số (No-arg constructor)
    public ImportedProduct() {
        super();
    }

    // 3. Hàm khởi tạo đầy đủ tham số (Constructor Kế thừa)
    // Sửa lỗi truyền tham số từ ProductList sang
    public ImportedProduct(String productID, String productName, String category, 
                           double price, int stockQuantity, double importTax, String originCountry) {
        // Dùng super() để chuyển các thuộc tính chung lên lớp cha Product xử lý
        super(productID, productName, category, price, stockQuantity);
        this.importTax = importTax;
        this.originCountry = originCountry;
    }

    // 4. Các hàm Getter và Setter thực tế thay thế cho UnsupportedOperationException
    public double getImportTax() {
        return importTax;
    }

    public void setImportTax(double importTax) {
        this.importTax = importTax;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    // 5. Áp dụng tính Đa hình (Method Overriding)
    // Ghi đè lại hàm getPrice() của lớp cha để tính giá đã gồm thuế nhập khẩu
    @Override
    public double getPrice() {
        double originalPrice = super.getPrice(); // Gọi lấy giá gốc từ class Product
        return originalPrice + (originalPrice * this.importTax); // Trả về giá đã cộng thuế
    }
}