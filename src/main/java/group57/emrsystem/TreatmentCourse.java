package group57.emrsystem;

public class TreatmentCourse {
    private String id;
    private String start_date; 
    private String end_date;
    
     public TreatmentCourse(String id,String start_date, String end_date){
         this.id = id;
         this.start_date = start_date;
         this.end_date = end_date;
     }
     
     public TreatmentCourse getTreatmentCourse(){
         return this;
     }

     public void modifyid(String id){
         this.id = id;
     }

     public void modifystart_date(String start_date){
         this.start_date = start_date;
     }
     
     public void modifyend_date(String end_date){
         this.end_date = end_date;
     }
}

