package group57.emrsystem;

public class TreatmentCourse {
    private String start_date; 
    private String end_date;
    
     public TreatmentCourse(String start_date, String end_date){
         this.start_date = start_date;
         this.end_date = end_date;
     }
     
     public TreatmentCourse getTreatmentCourse(){
         return this;
     }
     
     public void modifystart_date(String start_date){
         this.start_date = start_date;
     }
     
     public void modifyend_date(String end_date){
         this.end_date = end_date;
     }
}

