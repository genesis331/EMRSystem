package group57.emrsystem;

import javafx.beans.property.SimpleStringProperty;

public class Demo {
    private int id;
    private SimpleStringProperty name;
    private SimpleStringProperty address;
    private SimpleStringProperty phone;

    public Demo(int id, String name, String address, String phone) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
    }

    public String getName() {
        return name.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public void modifyId(int id) {
        this.id = id;
    }

    public void modifyName(String name) {
        new SimpleStringProperty(name);
    }

    public void modifyAddress(String address) {
        new SimpleStringProperty(address);
    }

    public void modifyPhone(String phone) {
        new SimpleStringProperty(phone);
    }
}
