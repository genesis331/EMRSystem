package group57.emrsystem;

public class Analysis extends TreatmentCourse {
    private String id;
    private String date;
    private String type_of_test;
    private String result;
    
    public Analysis(String id,String date, String type_of_test, String result){
        super(id, date, type_of_test, result);
        this.id = id;
        this.date = date;
        this.type_of_test = type_of_test;
        this.result = result;
    }

    public String getId(){
        return id;
    }

    public String getDate(){
        return date;
    }

    public String getTypeOfTest(){
        return type_of_test;
    }

    public String getResult(){
        return result;
    }
}
