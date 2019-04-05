package sample.Entitys;

public class Customer {
    private String customerName;
    private double discount;

    public Customer(String customerName, double discount) {
        this.customerName = customerName;
        this.discount = discount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
