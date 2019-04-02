package sample.Entitys;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement
public class AllUsers {

    List<User> allusers;

    public void setAllusers(List<User> users){ this.allusers = users; }

    public AllUsers(List<User> allusers) { this.allusers = allusers; }

    @XmlElement(name = "Users")
    public List<User> getAllusers() { return allusers; }

    public AllUsers() { }
}
