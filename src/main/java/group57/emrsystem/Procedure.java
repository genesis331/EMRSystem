package group57.emrsystem;

public class Procedure {
    private String id;
    private String date;
    private String type;
    private String proceduretype;
    private String medication;
    private String amountprocedure;
    private String frequency;


    public Procedure(String id, String date, String type, String proceduretype, String medication, String amountprocedure, String frequency) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.proceduretype = proceduretype;
        this.medication = medication;
        this.amountprocedure = amountprocedure;
        this.frequency = frequency;
    }

    public Procedure getProcedureAndMedicine() {
        return this;
    }

    public void modifyId(String id){
        this.id = id;
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

    public void modifyAmountProcedure(String amountprocedure) {
        this.amountprocedure = amountprocedure;
    }
    public void modifyFrequency(String frequency) {
        this.frequency = frequency;
    }



}
