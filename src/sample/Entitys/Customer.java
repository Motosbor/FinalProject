package sample.Entitys;


import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id","customerName","discount"})
public class Customer  {
    private int id;
    private String customerName;
    private double discount;

    public Customer(int id,String customerName, double discount) {
        this.id = id;
        this.customerName = customerName;
        this.discount = discount;
    }


    public Customer() { }

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

    public int getId() {return id; }

    public void setId(int id) { this.id = id; }
}
