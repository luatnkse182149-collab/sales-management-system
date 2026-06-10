package model;


public class VipCustomer extends Customer {
    private String vipLevel;
    private double discountRate;
    private String membershipCardID;
    private double rewardPoints;

    public VipCustomer() {
        super();
    }

    public VipCustomer(String customerID,
                       String customerName,
                       String phone,
                       String address,
                       String vipLevel,
                       double discountRate,
                       String membershipCardID,
                       double rewardPoints) {
        super(customerID, customerName, phone, address);
        this.vipLevel = vipLevel;
        this.discountRate = discountRate;
        this.membershipCardID = membershipCardID;
        this.rewardPoints = rewardPoints;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public String getMembershipCardID() {
        return membershipCardID;
    }

    public void setMembershipCardID(String membershipCardID) {
        this.membershipCardID = membershipCardID;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(double rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    @Override
    public void viewVipCustomer() {
        System.out.println("===== VIP CUSTOMER DETAILS =====");

        System.out.printf  ("%-10s | %-13s | %-20s | %-15s | %-15s | %-15s | %-15s | %-20s\n",
                "VIP Level", "Discount Rate", "Membership Card ID", "Reward Points", "Customer ID", "Customer Name", "Phone", "Address");

        System.out.println(vipLevel + " | " + discountRate + " | " + membershipCardID + " | " + rewardPoints + " | " + getCustomerID() + " | " + getCustomerName() + " | " + getPhone() + " | " + getAddress());
    }
    
}
