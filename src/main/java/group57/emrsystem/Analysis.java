package group57.emrsystem;

public class Analysis {
    private int date;
    private String type_of_test;
    private String result;
    
    public Analysis(int date, String type_of_test, String result){
        this.date = date;
        this.type_of_test = type_of_test;
        this.result = result;
    }
    
    public Analysis getAnalysis (){
        return this;
    }
    
    public void modifydate(int date){
        this.date = date;
    }
    
    public void modifytype_of_test(String type_of_test){
        this.type_of_test = type_of_test;
    }
    
    public void modiftresult(String result){
        this.result = result;
    }
}
