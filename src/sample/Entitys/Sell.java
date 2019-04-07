package sample.Entitys;

import java.util.Date;

public class Sell {
    private int sellerId;
    private String seller;
    private String customer;
    private String product;
    private int count;
    private double totalPrice;
    private Date dateOfSell;

    public Sell(int sellerId,String seller, String customer, String product, int count, double totalPrice, Date dateOfSell) {
        this.sellerId = sellerId;
        this.seller = seller;
        this.customer = customer;
        this.product = product;
        this.count = count;
        this.totalPrice = totalPrice;
        this.dateOfSell = dateOfSell;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDateOfSell() {
        return dateOfSell;
    }

    public void setDateOfSell(Date dateOfSell) {
        this.dateOfSell = dateOfSell;
    }

    public int getSellerId() { return sellerId; }

    public void setSellerId(int sellerId) { this.sellerId = sellerId; }
}
