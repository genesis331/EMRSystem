package group57.emrsystem;

import javafx.beans.property.SimpleStringProperty;

public class TreatmentCourse {
    private String id;
    private SimpleStringProperty treatment;
    private SimpleStringProperty start_date;
    private SimpleStringProperty end_date;
    
     public TreatmentCourse(String id,String treatment,String start_date, String end_date){
         this.id = id;
         this.treatment = new SimpleStringProperty(treatment);
         this.start_date = new SimpleStringProperty(start_date);
         this.end_date = new SimpleStringProperty(end_date);
     }
     
     public String getTreatment(){
         return treatment.get();
     }

     public String getStartDate(){
         return start_date.get();
     }

     public String getEndDate(){
         return end_date.get();
     }

     public void modifyid(String id){
         this.id = id;
     }

     public void modifytreatment(String treatment){
         this.treatment = new SimpleStringProperty(treatment);
     }

     public void modifystart_date(String start_date){
         this.start_date = new SimpleStringProperty(start_date);
     }
     
     public void modifyend_date(String end_date){
         this.end_date = new SimpleStringProperty(end_date);
     }
}

