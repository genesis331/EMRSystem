package group57.emrsystem;

public class ProcedureAndMedicine {
    private String date;
    private String type;
    private String proceduretype;
    private String medication;
    private int amountprocedure;
    private String frequency;
    private String medicinename;
    private String description;
    private int amountmedicine;

    public ProcedureAndMedicine(String date, String type, String proceduretype, String medication, int amountprocedure, String frequency, 
    String medicinename, String description, int amountmedicine) {
        this.date = date;
        this.type = type;
        this.proceduretype = proceduretype;
        this.medication = medication;
        this.amountprocedure = amountprocedure;
        this.frequency = frequency;
        this.medicinename = medicinename;
        this.description = description;
        this.amountmedicine = amountmedicine;
    }

    public ProcedureAndMedicine getProcedureAndMedicine() {
        return this;
    }

    public void modifyDate(String date) {
        this.date = date;
    }

    public void modifyType(String type) {
        this.type = type;
    }

    public void modifyProcedureType(String proceduretype) {
        this.proceduretype = proceduretype;
    }

    public void modifyMedication(String medication) {
        this.medication = medication;
    }

    public void modifyAmountProcedure(int amountprocedure) {
        this.amountprocedure = amountprocedure;
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
