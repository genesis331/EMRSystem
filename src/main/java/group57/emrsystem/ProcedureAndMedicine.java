package group57.emrsystem;

public class ProcedureAndMedicine {
    private String id;
    private String date;
    private String time;
    private String proceduretype;
    private String medication;
    private String amountprocedure;
    private String frequency;


    public ProcedureAndMedicine(String id, String date, String time, String proceduretype, String medication, String amountprocedure, String frequency) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.proceduretype = proceduretype;
        this.medication = medication;
        this.amountprocedure = amountprocedure;
        this.frequency = frequency;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTypeOfProcedure() {
        return proceduretype;
    }

    public String getMedication() {
        return medication;
    }

    public String getAmount() {
        return amountprocedure;
    }

    public String getFrequency() {
        return frequency;
    }

    public void modifyId(String id){
        this.id = id;
    }
    public void modifyDate(String date) {
        this.date = date;
    }

    public void modifyTime(String type) {
        this.time = type;
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
