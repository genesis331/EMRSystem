package group57.emrsystem;

import javafx.beans.property.SimpleStringProperty;

public class Patient {
    private String id;
    private SimpleStringProperty name;
    private SimpleStringProperty national_id;
    private SimpleStringProperty age;
    private SimpleStringProperty gender;
    private SimpleStringProperty address;
    private SimpleStringProperty contact_no;

    public Patient(String id, String name, String national_id, String age, String gender, String address, String contact_no) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.national_id = new SimpleStringProperty(national_id);
        this.age = new SimpleStringProperty(age);
        this.gender = new SimpleStringProperty(gender);
        this.address = new SimpleStringProperty(address);
        this.contact_no = new SimpleStringProperty(contact_no);
    }

    public String getName() {
        return name.get();
    }

    public String getID() {
        return id;
    }

    public String getNationalID() {
        return national_id.get();
    }

    public String getAge() {
        return age.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getContactNo() {
        return contact_no.get();
    }
}
