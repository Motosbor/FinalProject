package sample.Entitys;

public class Product {
    private String productName;
    private double price;
    private int balance;

    public Product(String productName, double price, int balance) {
        this.productName = productName;
        this.price = price;
        this.balance = balance;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
