package group57.emrsystem;
import java.io.*;
import java.util.*;

public class CSVHandler {
    private String delimiter = ",";
    private String newline = "\n";
    private String header = "id,name,age"; // e.g for Student class data

    // default constructor
    public CSVHandler() {
        
    }

    // You can also use a constructor to set the delimiter, header and newline
    public CSVHandler(String header, String delimiter, String newline) {
        this.header = header;
        this.delimiter = delimiter;
        this.newline = newline;
    }

}
