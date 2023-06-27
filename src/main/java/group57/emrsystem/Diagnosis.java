package group57.emrsystem;

import javafx.beans.property.SimpleStringProperty;

public class Diagnosis {
    private SimpleStringProperty id;
    private SimpleStringProperty date;
    private SimpleStringProperty name;
    private SimpleStringProperty diagnosed_sickness;
    public final static String fileName = "diagnosis.csv";
    

    public Diagnosis(String id, String date, String name, String diagnosed_sickness) {
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

    public void modifydate(String date) {

        this.date = new SimpleStringProperty(date);
    }

    public void modifyname(String name) {

        this.name = new SimpleStringProperty(name);
    }
     
    public void modifytime(String diagnosed_sickness) {

        this.diagnosed_sickness = new SimpleStringProperty(diagnosed_sickness);
    }
}
