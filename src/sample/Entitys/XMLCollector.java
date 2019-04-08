package sample.Entitys;

import sample.Entitys.Customer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
@XmlSeeAlso({Customer.class,Sell.class,Product.class})
public class XMLCollector {

    ArrayList<Object> objectList;

    public XMLCollector() { }

    public XMLCollector(ArrayList<Object> objectList) {
        this.objectList = objectList;
    }


    @XmlElement
    public ArrayList<Object> getObjectList() {
        return objectList;
    }


    public void setObjectList(ArrayList<Object> objectList) {
        this.objectList = objectList;
    }
}
