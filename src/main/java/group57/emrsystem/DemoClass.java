package group57.emrsystem;

public class DemoClass {
    private int id;
    private String name;
    private String address;
    private String phone;

    public DemoClass(int id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public DemoClass getDemoClass() {
        return this;
    }

    public void modifyId(int id) {
        this.id = id;
    }

    public void modifyName(String name) {
        this.name = name;
    }

    public void modifyAddress(String address) {
        this.address = address;
    }

    public void modifyPhone(String phone) {
        this.phone = phone;
    }
}
