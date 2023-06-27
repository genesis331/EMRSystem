package group57.emrsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

public class ViewPatientController implements Initializable {
    private Stage stage;
    private String id;

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
    private Button viewMedicalHistoryButton;

    @FXML
    private Button viewTreatmentCourseButton;

    @FXML
    private Button viewAnalysisFormButton;

    @FXML
    private Button viewDiagnosisFormButton;

    @FXML
    private Button viewProcedureandMedicineFormButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button deleteButton;

    private List<String> data = new ArrayList<>();

    public ViewPatientController(Stage stage, String id) {
        this.stage = stage;
        this.id = id;
    }

    public void ToViewMedicalHistory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource("medicalhistory-admin.fxml"));
        fxmlLoader.setController(new MedicalHistoryController(stage, true, id));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void ToViewTreatmentCourse() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource("treatmentcourse-admin.fxml"));
        fxmlLoader.setController(new TreatmentCourseController(stage, true, id));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void ToViewAnalysisForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource("analysis-admin.fxml"));
        fxmlLoader.setController(new AnalysisController(stage, true, id));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void ToViewDiagnosisForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource("diagnosis-admin.fxml"));
        fxmlLoader.setController(new DiagnosisController(stage, true, id));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void ToViewProcedureandMedicineForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource("procedureandmedicine-admin.fxml"));
        fxmlLoader.setController(new ProcedureAndMedicineController(stage, true, id));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    public List<Patient> readCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Patient> data = new ArrayList<>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    Patient patient = new Patient (String.valueOf(Integer.parseInt(tokens[0])), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                    data.add(patient);
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

    public void renderData() {
        List<Patient> data = readCSV(Objects.requireNonNull(PatientController.class.getResource("patient.csv")).getPath());
        for (Patient patient : data) {
            if (patient.getID().equals(id)) {
                nationalIDField.setText(patient.getNationalID());
                nameField.setText(patient.getName());
                ageField.setText(patient.getAge());
                genderField.setText(patient.getGender());
                contactNumberField.setText(patient.getContactNo());
                addressArea.setText(patient.getAddress());
            }
        }
    }

    private void saveData() {
        String nationalID = nationalIDField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        String gender = genderField.getText();
        String contactNumber = contactNumberField.getText();
        String address = addressArea.getText();
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(Objects.requireNonNull(PatientController.class.getResource("patient.csv")).getPath());

        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    if (tokens[2].equals(nationalID)) {
                        data.add(tokens[0] + "," + name + "," + nationalID + "," + age + "," + gender + "," + address + "," + contactNumber + "\n");
                    } else {
                        data.add(line + "\n");
                    }
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

        List<String> stringArrays = new ArrayList<>();
        stringArrays.add("id,name,national_id,age,gender,address,contact_no\n");
        stringArrays.addAll(data);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Objects.requireNonNull(NewDiagnosisController.class.getResource("patient.csv")).getPath()))) {
            for (String stringArray : stringArrays) {
                writer.write(stringArray);
            }
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    private void deleteData() {
        List<String> data = new ArrayList<>();
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(Objects.requireNonNull(PatientController.class.getResource("patient.csv")).getPath());

        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    if (!tokens[0].equals(id)) {
                        data.add(line + "\n");
                    }
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

        List<String> stringArrays = new ArrayList<>();
        stringArrays.add("id,name,national_id,age,gender,address,contact_no\n");
        stringArrays.addAll(data);
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewMedicalHistoryButton.setOnAction(e -> {
            try {
                ToViewMedicalHistory();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        viewTreatmentCourseButton.setOnAction(e -> {
            try {
                ToViewTreatmentCourse();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        viewAnalysisFormButton.setOnAction(e -> {
            try {
                ToViewAnalysisForm();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        viewDiagnosisFormButton.setOnAction(e -> {
            try {
                ToViewDiagnosisForm();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        viewProcedureandMedicineFormButton.setOnAction(e -> {
            try {
                ToViewProcedureandMedicineForm();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        saveButton.setOnAction(e -> {
            saveData();
        });
        deleteButton.setOnAction(e -> {
            deleteData();
        });
        renderData();
    }
}
