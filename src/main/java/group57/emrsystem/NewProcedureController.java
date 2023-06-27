package group57.emrsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NewProcedureController {
        private Stage stage;
        private String delimiter = ",";

        @FXML
        private TextField textdateprocedure;
        @FXML
        private TextField texttimeprocedure;
        @FXML
        private TextField textproceduretypeprocedure;
        @FXML
        public TextField textmedicationprocedure;
        @FXML
        public TextField textamountprocedure;
        @FXML
        public TextField textfrequencyprocedure;
        @FXML
        public TableView<Procedure> tableadmin;
        @FXML
        public TableView<Procedure> tableuser;
        @FXML
        public TableColumn<Procedure, String> table_date_procedure;
        @FXML
        public TableColumn<Procedure, String> table_time_procedure;
        @FXML
        public TableColumn<Procedure, String> table_type_procedure;
        @FXML
        public TableColumn<Procedure, String> table_medication_procedure;
        @FXML
        public TableColumn<Procedure, String> table_amount_procedure;
        @FXML
        public TableColumn<Procedure, String> table_frequency_procedure;
        @FXML
        public TableColumn<Procedure, String> admin_table_action_procedure;
        @FXML
        public Button savebuttonprocedure;
        private String newline = "\n";
        private String header = "id,date,time,type,medication,amount,frequency,actions";


//        public void writeCSV(List<Procedure> Procedures, String fileName) {
//            FileWriter fileWriter = null;
//            try {
//                File file = new File(fileName);
//                fileWriter = new FileWriter(file);
//                fileWriter.append(header);
//                fileWriter.append(newline);
//
//                // process content line by line
//                for (Procedure procedure : Procedures) {
//                    fileWriter.append(String.valueOf(procedure.getDate()));
//                    fileWriter.append(delimiter);
//                    fileWriter.append(procedure.getTime());
//                    fileWriter.append(delimiter);
//                    fileWriter.append(String.valueOf(procedure.getType()));
//                    fileWriter.append(delimiter);
//                    fileWriter.append(String.valueOf(procedure.getDiagnosedSickness()));
//                    fileWriter.append(newline);
//                }
//            } catch (Exception e) {
//                // handle exception
//                e.printStackTrace();
//            } finally {
//                try {
//                    fileWriter.flush();
//                    fileWriter.close();
//                } catch (IOException e) {
//                    // handle exception
//                    e.printStackTrace();
//                }
//            }
//        }


    public static List<Procedure> readCSV(String fileName){
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Procedure> data = new ArrayList<Procedure>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    Procedure procedure = new Procedure (String.valueOf(Integer.parseInt(tokens[0])), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                    data.add(procedure);
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
        return data;
    }



//    public void create(Procedure procedure) {
//            List<Procedure> d = this.readCSV(Procedure.fileName);
//            d.add(procedure);
//            writeCSV(d, Procedure.fileName);
//
//        }
//
//        @FXML
//        private void ToBeSaved(ActionEvent event) {
//            savebuttonprocedure.setOnAction(e -> {
//                ;
//                String id = "0";
//                String date = textdateprocedure.getText();
//                String name = texttimeprocedure.getText();
//                String procedure_type = textproceduretypeprocedure.getText();
//                String medication = textmedicationprocedure.getText();
//                String amount = textamountprocedure.getText();
//                String frequency = textfrequencyprocedure.getText();
//                Procedure procedure = new Procedure(id, date, name, procedure_type, medication, amount, frequency);
//                CSVHandler csv = new CSVHandler();
//                group57.emrsystem.NewDiagnosisController n = new group57.emrsystem.NewDiagnosisController();
//                n.create(diagnosis);
//            });
//        }
}
