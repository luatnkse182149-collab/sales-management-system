
package manager;

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
    
}
