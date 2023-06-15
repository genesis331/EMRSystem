package group57.emrsystem;

public class MedicalHistory {
    private String id;
    private String date;
    private String time;
    private String ward;
    private String treatment_results;
    private String observation;
    private String major_complications;
    private String attending_doctor;//or nurses

    public MedicalHistory(String id, String date, String time, String ward, String treatment_results, String observation, String major_complications, String attending_doctor) {
        this.id = id;
        this.time = time;
        this.ward = ward;
        this.treatment_results = treatment_results;
        this.observation = observation;
        this.major_complications = major_complications;
        this.attending_doctor = attending_doctor;
    }

    public MedicalHistory getMedicalHistory() {
        return this;
    }

    public void modifyid(String id){

        this.id = id;
    }

    public void modifydate(String date) {

        this.date = date;
    }

    public void modifytime(String time) {

        this.time = time;
    }

    public void modifyward(String ward) {

        this.ward = ward;
    }

    public void modifytreatment_results(String treatment_results) {

        this.treatment_results = treatment_results;
    }
    
    public void modifyobservation(String observation) {

        this.observation = observation;
    }
    
    public void modifymajor_complications(String major_complications) {
        this.major_complications = major_complications;
    }
    
    public void modifyattending_doctor(String attending_doctor) {

        this.attending_doctor = attending_doctor;
    }
}
