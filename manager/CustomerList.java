
package manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import model.Customer;

/**
 *
 * @author ADMIN
 */
public class CustomerList {
    private final ArrayList<Customer> customerList =
            new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void addCustomer() {

        System.out.print("Enter Customer ID: ");
        String id = sc.nextLine();

        for(Customer c : customerList) {

            if(c.getCustomerID()
                    .equalsIgnoreCase(id)) {

                System.out.println(
                        "Duplicate Customer ID!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        Customer customer =
                new Customer(id, name,
                        phone, address);

        customerList.add(customer);

        System.out.println(
                "Customer Added Successfully.");
    }

    public void viewCustomer() {

        System.out.println(
                "===== CUSTOMER LIST =====");

        for(Customer c : customerList) {

            System.out.println(
                    c.getCustomerID()
                    + " | "
                    + c.getCustomerName()
                    + " | "
                    + c.getPhone()
                    + " | "
                    + c.getAddress());
        }
    }

    public void viewVipCustomer() {
        System.out.println(
                "===== VIP CUSTOMER LIST =====");

        for(Customer c : customerList) {

            if(c instanceof model.VipCustomer) {

                ((model.VipCustomer) c).viewVipCustomer();
            }
        }
    }
    public void addVipCustomer() {

        System.out.print("Enter Customer ID: ");
        String id = sc.nextLine();

        for(Customer c : customerList) {

            if(c.getCustomerID()
                    .equalsIgnoreCase(id)) {

                System.out.println(
                        "Duplicate Customer ID!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        System.out.print("Enter VIP Level: ");
        String vipLevel = sc.nextLine();

        System.out.print("Enter Discount Rate: ");
        double discountRate =
                Double.parseDouble(sc.nextLine());

        System.out.print("Enter Membership Card ID: ");
        String membershipCardID = sc.nextLine();

        System.out.print("Enter Reward Points: ");
        double rewardPoints =
                Double.parseDouble(sc.nextLine());

        model.VipCustomer vipCustomer =
                new model.VipCustomer(id, name,
                        phone, address, vipLevel, discountRate, membershipCardID, rewardPoints);

        customerList.add(vipCustomer);

        System.out.println(
                "VIP Customer Added Successfully.");
    
}
public void saveCustomersToFile(String fileName) {

    try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {

        for (Customer c : customerList) {

            if (c instanceof model.VipCustomer) {

                model.VipCustomer vip = (model.VipCustomer) c;

                pw.println(
                        "VIP,"
                        + vip.getCustomerID() + ","
                        + vip.getCustomerName() + ","
                        + vip.getPhone() + ","
                        + vip.getAddress() + ","
                        + vip.getVipLevel() + ","
                        + vip.getDiscountRate() + ","
                        + vip.getMembershipCardID() + ","
                        + vip.getRewardPoints());

            } else {

                pw.println(
                        "CUSTOMER,"
                        + c.getCustomerID() + ","
                        + c.getCustomerName() + ","
                        + c.getPhone() + ","
                        + c.getAddress());

            }

        }

        System.out.println("Customer data saved successfully.");

    } catch (IOException e) {

        System.out.println("Error saving customer file.");

    }

}
public void loadCustomersFromFile(String fileName) {

    customerList.clear();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            if (data[0].equalsIgnoreCase("CUSTOMER")) {

                Customer c = new Customer(
                        data[1],
                        data[2],
                        data[3],
                        data[4]);

                customerList.add(c);

            }

            else if (data[0].equalsIgnoreCase("VIP")) {
    model.VipCustomer vip = new model.VipCustomer(
            data[1], // ID
            data[2], // Name
            data[3], // Phone
            data[4], // Address
            data[5], // VIP Level
            Double.parseDouble(data[6]), // Sửa thành Double để đọc discountRate (ví dụ: 0.15)
            data[7], // Membership Card ID
            Double.parseDouble(data[8])  // Sửa thành Double để đọc rewardPoints (ví dụ: 500.0)
    );
    customerList.add(vip);
}

        }

        System.out.println("Customer data loaded successfully.");

    } catch (IOException e) {

        System.out.println("Error loading customer file.");

    }

}


}
