package group57.emrsystem;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NewAnalysisController {
        private Stage stage;
        private String delimiter = ",";

        @FXML
        private TextField date_textfield;
        @FXML
        private TextField type_of_test_textfield;
        @FXML
        private TextField result_textfield;
        @FXML
        public Button save_button;
        @FXML
        public TableView<Analysis> analysis_admin_table;
        @FXML
        public TableView<Analysis> analysis_user_table;
        @FXML
        public TableColumn<Analysis, String> analysis_user_date;
        @FXML
        public TableColumn<Analysis, String> analysis_user_type_of_test;
        @FXML
        public TableColumn<Analysis, String> analysis_user_result;
        @FXML
        public TableColumn<Analysis, String> analysis_user_action;
        @FXML
        public Button add_record_button;
        private String newline = "\n";
        private String header = "id,date,type_of_test,result";

        public static List<Analysis> readCSV(String fileName){
                String delimiter = ",";
                BufferedReader bReader = null;
                File file = new File(fileName);
                List<Analysis> students = new ArrayList<Analysis>();
                try {
                        String line = "";
                        bReader = new BufferedReader(new FileReader(file));
                        bReader.readLine();
                        while ((line = bReader.readLine()) != null) {
                                String[] tokens = line.split(delimiter);
                                if (tokens.length > 0) {
                                        Analysis analysis = new Analysis (String.valueOf(Integer.parseInt(tokens[0])), tokens[1], tokens[2], tokens[3]);
                                        students.add(analysis);
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

//        public void create(Diagnosis diagnosis) {
//                List<Analysis> d = this.readCSV(Analysis.fileName);
//                d.add(analysis);
//                writeCSV(d, Analysis.fileName);
//        }

//        public void ToBeSaved() {
//                save_button.setOnAction(e -> {
//                        ;
//                        String id = "0";
//                        String date = date_textfield.getText();
//                        String type_of_test = type_of_test_textfield.getText();
//                        String result = result_textfield.getText();
//                        Diagnosis diagnosis = new Diagnosis(id, date, type_of_test, result);
//                        CSVHandler csv = new CSVHandler();
//                        NewAnalysisController n = new NewAnalysisController();
//                        n.create(diagnosis);
//                });
//        }

}
