package group57.emrsystem;

import javafx.beans.property.SimpleStringProperty;

public class Diagnosis extends TreatmentCourse {
    private SimpleStringProperty id;
    private SimpleStringProperty date;
    private SimpleStringProperty name;
    private SimpleStringProperty diagnosed_sickness;

    public Diagnosis(String id, String date, String name, String diagnosed_sickness) {
        super(id, date, name, diagnosed_sickness);
        this.id = new SimpleStringProperty(id);
        this.date = new SimpleStringProperty(date);
        this.name = new SimpleStringProperty(name);
        this.diagnosed_sickness = new SimpleStringProperty(diagnosed_sickness);
    }

    public String getId() {
        return id.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getName() {
        return name.get();
    }


    public String getDiagnosedSickness() {
        return diagnosed_sickness.get();
    }
}
