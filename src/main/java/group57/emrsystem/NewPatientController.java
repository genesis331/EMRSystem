package group57.emrsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewPatientController implements Initializable {
    private Stage stage;

    @FXML
    private TextField nationalIDField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField contactNumberField;

    @FXML
    private TextArea addressArea;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private List<String> usedIds = new ArrayList<>();
    private List<String> data = new ArrayList<>();

    public NewPatientController(Stage stage) {
        this.stage = stage;
    }

    private void readCSV() {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(Objects.requireNonNull(PatientController.class.getResource("patient.csv")).getPath());

        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                data.add(line + "\n");
                if (tokens.length > 0) {
                    usedIds.add(tokens[0]);
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

    private void ToBeSaved(){
        String nationalID = nationalIDField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        String gender = genderField.getText();
        String contactNumber = contactNumberField.getText();
        String address = addressArea.getText();
        readCSV();
        String id = String.valueOf((int) (Math.random() * 10));
        while (usedIds.contains(id)) {
            id = String.valueOf((int) (Math.random() * 10));
        }
        List<String> stringArrays = new ArrayList<>();
        stringArrays.add("id,name,national_id,age,gender,address,contact_no\n");
        stringArrays.addAll(data);
        String newPatientString = id + "," + name + "," + nationalID + "," + age + "," + gender + "," + address + "," + contactNumber + "\n";
        stringArrays.add(newPatientString);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Objects.requireNonNull(NewDiagnosisController.class.getResource("patient.csv")).getPath()))) {
            for (String stringArray : stringArrays) {
                writer.write(stringArray);
            }
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setOnAction(actionEvent -> ToBeSaved());
        cancelButton.setOnAction(actionEvent -> stage.close());
    }
}
