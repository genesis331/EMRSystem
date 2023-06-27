package group57.emrsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewProcedureAndMedicineController implements Initializable {
        private Stage stage;
        private ProcedureAndMedicineController parentController;

        @FXML
        private ChoiceBox PatientIdDropdown;
        @FXML
        public TextField textdateprocedure;
        @FXML
        public TextField texttimeprocedure;
        @FXML
        public TextField textproceduretypeprocedure;
        @FXML
        public TextField textmedicationprocedure;
        @FXML
        public TextField textamountprocedure;
        @FXML
        public TextField textfrequencyprocedure;
        @FXML
        public Button savebuttonprocedure;

        private List<String> usedIds = new ArrayList<>();
        private List<String> data = new ArrayList<>();

        public void setStage(Stage stage) {
                this.stage = stage;
        }

        public void setParentController(ProcedureAndMedicineController parentController) {
                this.parentController = parentController;
        }

        private void readCSV() {
                String delimiter = ",";
                BufferedReader bReader = null;
                File file = new File(Objects.requireNonNull(PatientController.class.getResource("analysis.csv")).getPath());

                try {
                        String line = "";
                        bReader = new BufferedReader(new FileReader(file));
                        bReader.readLine();
                        while ((line = bReader.readLine()) != null) {
                                String[] tokens = line.split(delimiter);
                                data.add(line + "\n");
                                if (tokens.length > 0) {
                                        usedIds.add(tokens[1]);
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
        }

        private void initDropdown() {
                String delimiter = ",";
                BufferedReader bReader = null;
                File file = new File(Objects.requireNonNull(PatientController.class.getResource("patient.csv")).getPath());
                List<String> dropdownList = new ArrayList<>();
                try {
                        String line = "";
                        bReader = new BufferedReader(new FileReader(file));
                        bReader.readLine();
                        while ((line = bReader.readLine()) != null) {
                                String[] tokens = line.split(delimiter);
                                if (tokens.length > 0) {
                                        dropdownList.add(tokens[2]);
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
                PatientIdDropdown.getItems().addAll(dropdownList);
        }

        @FXML
        private void ToBeSaved(){
                String username = PatientIdDropdown.getValue().toString();
                String date = textdateprocedure.getText();
                String time = texttimeprocedure.getText();
                String type = textproceduretypeprocedure.getText();
                String medication = textmedicationprocedure.getText();
                String amount = textamountprocedure.getText();
                String frequency = textfrequencyprocedure.getText();
                readCSV();
                String id = String.valueOf((int) (Math.random() * 10));
                while (usedIds.contains(id)) {
                        id = String.valueOf((int) (Math.random() * 10));
                }
                List<String> stringArrays = new ArrayList<>();
                stringArrays.add("username,id,date,time,proceduretype,medication,amount,frequency\n");
                stringArrays.addAll(data);
                String newProcedureAndMedicineString = username + "," + id + "," + date + "," + time + "," + type + "," + medication + "," + amount + "," + frequency + "\n";
                stringArrays.add(newProcedureAndMedicineString);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(Objects.requireNonNull(NewDiagnosisController.class.getResource("procedureandmedicine.csv")).getPath()))) {
                        for (String stringArray : stringArrays) {
                                writer.write(stringArray);
                        }
                        System.out.println("Data has been written to the file.");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("New record has been saved!");
                        alert.showAndWait();
                        stage.close();
                        parentController.AdminRenderData();
                } catch (IOException e) {
                        System.err.println("An error occurred while writing to the file: " + e.getMessage());
                }
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle){
                savebuttonprocedure.setOnAction(actionEvent -> ToBeSaved());
                initDropdown();
        }
}
