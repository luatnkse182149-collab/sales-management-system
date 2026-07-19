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

    System.out.printf(
            "%-10s %-15s %-15s %-15s %-12s %-20s %-15s %-20s\n",
            "VIP",
            "Discount",
            "Card ID",
            "Points",
            "Cust ID",
            "Name",
            "Phone",
            "Address");

    System.out.println("---------------------------------------------------------------------------------------------------------------");

    System.out.printf(
            "%-10s %-15.2f %-15s %-15.0f %-12s %-20s %-15s %-20s\n",
            vipLevel,
            discountRate,
            membershipCardID,
            rewardPoints,
            getCustomerID(),
            getCustomerName(),
            getPhone(),
            getAddress());
}
    
}
