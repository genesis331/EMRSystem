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

    public String getNational_ID() {
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

    public String getContact_No() {
        return contact_no.get();
    }

    public void modifyID(String id) {
        this.id = id;
    }

    public void modifyName(String name) {
        this.name = name;
    }

    public void modifyNational_ID(String national_id) {
        this.national_id = national_id;
    }

    public void modifyAge(String age) {
        this.age = age;
    }

    public void modifyGender(String gender) {
        this.gender = gender;
    }

    public void modifyAddress(String address) {
        this.address = address;
    }

    public void modifyContact_No(String contact_no) {
        this.contact_no = contact_no;
    }
}
