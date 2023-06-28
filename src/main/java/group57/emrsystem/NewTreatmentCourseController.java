package group57.emrsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewTreatmentCourseController implements Initializable {
    private Stage stage;
    private TreatmentCourseController parentController;

    @FXML
    private ChoiceBox PatientIdDropdown;

    @FXML
    private TextField treatment_textfield;
    @FXML
    private TextField start_date_textfield;
    @FXML
    private TextField end_date_textfield;
    @FXML
    public Button save_button;

    private List<String> usedIds = new ArrayList<>();
    private List<String> data = new ArrayList<>();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setParentController(TreatmentCourseController parentController) {
        this.parentController = parentController;
    }

    private void readCSV() {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(Objects.requireNonNull(PatientController.class.getResource("treatmentcourse.csv")).getPath());

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
        String treatment = treatment_textfield.getText();
        String start_date = start_date_textfield.getText();
        String end_date = end_date_textfield.getText();
        readCSV();
        String id = String.valueOf((int) (Math.random() * 10));
        while (usedIds.contains(id)) {
            id = String.valueOf((int) (Math.random() * 10));
        }
        List<String> stringArrays = new ArrayList<>();
        stringArrays.add("username,id,treatment,start_date,end_date\n");
        stringArrays.addAll(data);
        String newTreatmentCourseString = username + "," + id + "," + treatment + "," + start_date + "," + end_date + "\n";
        stringArrays.add(newTreatmentCourseString);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Objects.requireNonNull(NewDiagnosisController.class.getResource("treatmentcourse.csv")).getPath()))) {
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
        parentController.AdminRenderData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        save_button.setOnAction(actionEvent -> ToBeSaved());
        initDropdown();
    }

}
