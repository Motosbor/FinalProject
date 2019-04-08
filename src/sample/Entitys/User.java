package sample.Entitys;

import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = {"id","name","lastName","login","password"})
public class User {

    private int id;
    private String name;
    private String lastName;
    private String login;
    private String password;


    public User() { }

    public User(int id) {
        this.id = id;
    }

    public User(String name) {
        this.name = name;
    }
    public User(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public User(int id,String name, String lastName, String login, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public User(String name, String lastName, String login, String password) {
        this.name = name;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

}
