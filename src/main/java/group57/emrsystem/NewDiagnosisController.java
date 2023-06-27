package group57.emrsystem;

import javafx.event.ActionEvent;
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

public class NewDiagnosisController implements Initializable{
    private Stage stage;

    @FXML
    private ChoiceBox PatientIdDropdown;
    @FXML
    private TextField DiagnosisDateTextField;
    @FXML
    private TextField DiagnosisNameTextField;
    @FXML
    private TextField DiagnosisDiagnosedSicknessTextField;
    @FXML
    public Button DiagnosisSaveButton;

    private List<String> usedIds = new ArrayList<>();
    private List<String> data = new ArrayList<>();

    public NewDiagnosisController(Stage stage) {
        this.stage = stage;
    }

    private void readCSV() {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(Objects.requireNonNull(PatientController.class.getResource("diagnosis.csv")).getPath());

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

    private void ToBeSaved(){
        String username = PatientIdDropdown.getValue().toString();
        String date = DiagnosisDateTextField.getText();
        String name = DiagnosisNameTextField.getText();
        String diagnosedSickness = DiagnosisDiagnosedSicknessTextField.getText();
        readCSV();
        String id = String.valueOf((int) (Math.random() * 10));
        while (usedIds.contains(id)) {
            id = String.valueOf((int) (Math.random() * 10));
        }
        List<String> stringArrays = new ArrayList<>();
        stringArrays.add("username,id,date,name,diagnosedSickness\n");
        stringArrays.addAll(data);
        String newDiagnosisString = username + "," + id + "," + date + "," + name + "," + diagnosedSickness + "\n";
        stringArrays.add(newDiagnosisString);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Objects.requireNonNull(NewDiagnosisController.class.getResource("diagnosis.csv")).getPath()))) {
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
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        DiagnosisSaveButton.setOnAction(actionEvent -> ToBeSaved());
        initDropdown();
    }
}

