package group57.emrsystem;

public class Patient {
    private String name;
    private int national_id;
    private int age;
    private String gender;
    private String address;
    private int contact_no;

    public Patient(String name, int national_id, int age, String gender, String address, int contact_no) {
        this.name = name;
        this.national_id = national_id;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.contact_no = contact_no;
    }

    public Patient getPatient() {
        return this;
    }

    public void modifyName(String name) {
        this.name = name;
    }

    public void modifyNational_ID(int national_id) {
        this.national_id = national_id;
    }

    public void modifyAge(int age) {
        this.age = age;
    }

    public void modifyGender(String gender) {
        this.gender = gender;
    }

    public void modifyAddress(String address) {
        this.address = address;
    }

    public void modifyContact_No(int contact_no) {
        this.contact_no = contact_no;
    }
}
