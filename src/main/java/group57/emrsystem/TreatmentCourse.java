package group57.emrsystem;

public class TreatmentCourse {
    private int start_date; 
    private int end_date;
    
     public TreatmentCourse(int start_date, int end_date){
         this.start_date = start_date;
         this.end_date = end_date;
     }
     
     public TreatmentCourse getTreatmentCourse(){
         return this;
     }
     
     public void modifystart_date(int start_date){
         this.start_date = start_date;
     }
     
     public void modifyend_date(int end_date){
         this.end_date = end_date;
     }
}
