package group57.emrsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NewTreatmentCourseController {
    private Stage stage;
    private String delimiter = ",";

    @FXML
    private TextField treatment_textfield;
    @FXML
    private TextField start_date_textfield;
    @FXML
    private TextField end_date_textfield;
    @FXML
    public Button save_button;
    @FXML
    public TableView<TreatmentCourse> treatment_admin_table;
    @FXML
    public TableView<TreatmentCourse> treatment_user_table;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_user_treatment;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_user_start_date;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_user_end_date;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_user_actions;
    @FXML
    public Button add_record_button;
    private String newline = "\n";
    private String header = "id,treatment,start_date,end-date,actions";

    public static List<TreatmentCourse> readCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<TreatmentCourse> students = new ArrayList<TreatmentCourse>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    TreatmentCourse treatmentCourse = new TreatmentCourse(String.valueOf(Integer.parseInt(tokens[0])), tokens[1], tokens[2], tokens[3]);
                    students.add(treatmentCourse);
                }
            }
        } catch (FileNotFoundException e) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bReader != null)
                    bReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

//      public void create(TreatmentCourse treatmentCourse) {
//          List<TreatmentCourse> d = this.readCSV(TreatmentCourse.fileName);
//          d.add(treatmentCourse);
//          writeCSV(d, TreatmentCourse.fileName);
//        }

//        public void ToBeSaved() {
//                save_button.setOnAction(e -> {
//                        ;
//                        String id = "0";
//                        String treatment = treatment_textfield.getText();
//                        String start_date = start_date_textfield.getText();
//                        String end_date = end_date_textfield.getText();
//                        TreatmentCourse treatmentCourse = new TreatmentCourse(id, treatment, start_date, end_date);
//                        CSVHandler csv = new CSVHandler();
//                        NewTreatmentCourseController n = new NewTreatmentCourseController();
//                        n.create(treatmentCourse);
//                });
//        }
}
