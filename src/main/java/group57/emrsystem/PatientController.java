package group57.emrsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    private Stage stage;

    private Boolean isAdmin;

    private String username;

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
    public Button addRecordButton;

    @FXML
    private Button logoutButtonUser;

    @FXML
    private Button logoutButtonAdmin;

    @FXML
    public TableView<Patient> patientTable;

    @FXML
    public TableColumn<Patient, String> patientNationalID;

    @FXML
    public TableColumn<Patient, String> patientName;

    @FXML
    private TableColumn<Patient, String> patientAge;

    @FXML
    public TableColumn<Patient, String> patientGender;

    @FXML
    public TableColumn<Patient, String> patientContactNo;

    @FXML
    public TableColumn<Patient, String> patientAddress;

    private List<String> data = new ArrayList<>();

    public void ToAddRecord() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource("newpatient.fxml"));
        Stage stage = new Stage();
        fxmlLoader.setController(new NewPatientController(stage));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void ToViewMedicalHistory(boolean isAdmin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource(isAdmin ? "medicalhistory-admin.fxml" : "medicalhistory-user.fxml"));
        fxmlLoader.setController(new MedicalHistoryController(stage, isAdmin, isAdmin ? "admin" : username));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void ToViewTreatmentCourse(boolean isAdmin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource(isAdmin ? "treatmentcourse-admin.fxml" : "treatmentcourse-user.fxml"));
        fxmlLoader.setController(new TreatmentCourseController(stage, isAdmin, isAdmin ? "admin" : username));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void ToViewAnalysisForm(boolean isAdmin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource(isAdmin ? "analysis-admin.fxml" : "analysis-user.fxml"));
        fxmlLoader.setController(new AnalysisController(stage, isAdmin, isAdmin ? "admin" : username));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void ToViewDiagnosisForm(boolean isAdmin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource(isAdmin ? "diagnosis-admin.fxml" : "diagnosis-user.fxml"));
        fxmlLoader.setController(new DiagnosisController(stage, isAdmin, isAdmin ? "admin" : username));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void ToViewProcedureandMedicineForm(boolean isAdmin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource(isAdmin ? "procedureandmedicine-admin.fxml" : "procedureandmedicine-user.fxml"));
        fxmlLoader.setController(new ProcedureAndMedicineController(stage, isAdmin, isAdmin ? "admin" : username));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setScene(scene);
        stage.show();
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isAdmin) {
            addRecordButton.setOnAction(e -> {
                try {
                    ToAddRecord();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            logoutButtonAdmin.setOnAction(e -> {
                stage.close();
            });
        } else {
            saveButton.setOnAction(e -> {
                saveData();
            });
            logoutButtonUser.setOnAction(e -> {
                stage.close();
            });
        }
        viewMedicalHistoryButton.setOnAction(e -> {
            try {
                ToViewMedicalHistory(isAdmin);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        viewTreatmentCourseButton.setOnAction(e -> {
            try {
                ToViewTreatmentCourse(isAdmin);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        viewAnalysisFormButton.setOnAction(e -> {
            try {
                ToViewAnalysisForm(isAdmin);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        viewDiagnosisFormButton.setOnAction(e -> {
            try {
                ToViewDiagnosisForm(isAdmin);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        viewProcedureandMedicineFormButton.setOnAction(e -> {
            try {
                ToViewProcedureandMedicineForm(isAdmin);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        renderData();
    }

    public PatientController(Stage stage, Boolean isAdmin, String username) {
        this.stage = stage;
        this.isAdmin = isAdmin;
        this.username = username;
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

    Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<>() {
        @Override
        public TableCell<Patient, Void> call(final TableColumn<Patient, Void> param) {
            return new TableCell<>() {

                private final Button btn = new Button("View");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Patient data = getTableView().getItems().get(getIndex());
                        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource("viewpatient-admin.fxml"));
                        fxmlLoader.setController(new ViewPatientController(stage, data.getID()));
                        Stage stage = new Stage();
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 1080, 720);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        stage.setScene(scene);
                        stage.show();
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
        }
    };

    public void renderData() {
        List<Patient> data = readCSV(Objects.requireNonNull(PatientController.class.getResource("patient.csv")).getPath());
        ObservableList<Patient> list = FXCollections.observableArrayList(data);
        if (isAdmin) {
            TableColumn<Patient, Void> colBtn = new TableColumn<>("Actions");
            patientNationalID.setCellValueFactory(new PropertyValueFactory<>("NationalID"));
            patientName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            patientAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
            patientGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            patientContactNo.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
            patientAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
            colBtn.setCellFactory(cellFactory);
            patientTable.getColumns().add(colBtn);
            patientTable.setItems(list);
        } else {
            boolean found = false;
            for (Patient patient : data) {
                if (patient.getNationalID().equals(username)) {
                    found = true;
                    nationalIDField.setText(patient.getNationalID());
                    nameField.setText(patient.getName());
                    ageField.setText(patient.getAge());
                    genderField.setText(patient.getGender());
                    contactNumberField.setText(patient.getContactNo());
                    addressArea.setText(patient.getAddress());
                }
            }
            if (!found) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Patient not found!");
                alert.showAndWait();
                stage.close();
            }
        }
    }
}
