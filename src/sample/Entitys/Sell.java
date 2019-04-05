package sample.Entitys;

import java.util.Date;

public class Sell {
    private String seller;
    private String customer;
    private String product;
    private int count;
    private double totalPrice;
    private Date dateOfSell;

    public Sell(String seller, String customer, String product, int count, double totalPrice, Date dateOfSell) {
        this.seller = seller;
        this.customer = customer;
        this.product = product;
        this.count = count;
        this.totalPrice = totalPrice;
        this.dateOfSell = dateOfSell;
    }
}
