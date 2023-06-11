package group57.emrsystem;

public class Diagnosis {
  private String date;
    private String name;
    private String diagnosed_sickness;
    

    public Diagnosis(String date, String name, String diagnosed_sickness) {
        this.date = date;
        this.name = name;
        this.diagnosed_sickness = diagnosed_sickness;
        
    }

    public Diagnosis getDiagnosis() {
        return this;
    }

    public void modifydate(String date) {
        this.date = date;
    }

    public void modifyname(String name) {
        this.name = name;
    }
     
    public void modifytime(String diagnosed_sickness) {
        this.diagnosed_sickness = diagnosed_sickness;
    }
}
