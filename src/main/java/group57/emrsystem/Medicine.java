package group57.emrsystem;

public class Medicine {
    private String id;
    private String frequency;
    private String medicinename;
    private String description;
    private int amountmedicine;

    public Medicine( String id, String frequency, String medicinename, String description, int amountmedicine) {
        this.id = id;
        this.frequency = frequency;
        this.medicinename = medicinename;
        this.description = description;
        this.amountmedicine = amountmedicine;
    }
    public Medicine getMedicine(){ return this;
    }
    public void modifyId(String id){this.id = id;
    }
    public void modifyFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void modifyMedicineName(String medicinename) {
        this.medicinename = medicinename;
    }

    public void modifyDescription(String description) {
        this.description = description;
    }

    public void modifyAmountMedicine(int amountmedicine) {
        this.amountmedicine = amountmedicine;
    }
}
