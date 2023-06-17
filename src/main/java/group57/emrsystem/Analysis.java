package group57.emrsystem;

public class Analysis {
    private String id;
    private String date;
    private String type_of_test;
    private String result;
    
    public Analysis(String id,String date, String type_of_test, String result){
        this.id = id;
        this.date = date;
        this.type_of_test = type_of_test;
        this.result = result;
    }
    
    public Analysis getAnalysis (){
        return this;
    }

    public void modifyid(String id){
        this.id = id;
    }
    
    public void modifydate(String date){
        this.date = date;
    }
    
    public void modifytype_of_test(String type_of_test){
        this.type_of_test = type_of_test;
    }
    
    public void modiftresult(String result){
        this.result = result;
    }
}
