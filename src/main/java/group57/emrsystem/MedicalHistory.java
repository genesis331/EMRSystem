package group57.emrsystem;

import javafx.beans.property.SimpleStringProperty;

public class MedicalHistory {
    private String id;
    private SimpleStringProperty date;
    private SimpleStringProperty time;
    private SimpleStringProperty ward;
    private SimpleStringProperty treatment_results;
    private SimpleStringProperty observation;
    private SimpleStringProperty major_complications;
    private SimpleStringProperty attending_doctor;//or nurses

    public MedicalHistory(String id, String date, String time, String ward, String treatment_results, String observation, String major_complications, String attending_doctor) {
        this.id = id;
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.ward = new SimpleStringProperty(ward);
        this.treatment_results = new SimpleStringProperty(treatment_results);
        this.observation = new SimpleStringProperty(observation);
        this.major_complications = new SimpleStringProperty(major_complications);
        this.attending_doctor = new SimpleStringProperty(attending_doctor);
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date.get();
    }

    public String getTime() {
        return time.get();
    }

    public String getWard() {
        return ward.get();
    }

    public String getTreatmentResults() {
        return treatment_results.get();
    }

    public String getObservation() {
        return observation.get();
    }

    public String getMajorComplications() {
        return major_complications.get();
    }

    public String getAttendingDoctor() {
        return attending_doctor.get();
    }
}
