package group57.emrsystem;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PatientController implements Initializable {
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
    private Button addRecordButton;

    @FXML
    private Button logoutButtonUser;

    @FXML
    private Button logoutButtonAdmin;

    @FXML
    public TableView<Patient> patientAdminTable;

    @FXML
    public TableColumn<Patient, String> patientAdminNationalID;

    @FXML
    public TableColumn<Patient, String> patientAdminName;

    @FXML
    private TableColumn<Patient, String> patientAdminAge;

    @FXML
    public TableColumn<Patient, String> patientAdminGender;

    @FXML
    public TableColumn<Patient, String> patientAdminContactNo;

    @FXML
    public TableColumn<Patient, String> patientAdminAddress;

    @FXML
    public TableColumn<Patient, String> patientAdminActions;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        renderData();
    }

    public PatientController(Stage stage) {
        this.stage = stage;
    }

    public static List<Patient> readCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Patient> data = new ArrayList<Patient>();
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
        ObservableList<Patient> list = FXCollections.observableArrayList(data);
        patientAdminNationalID.setCellValueFactory(new PropertyValueFactory<>("NationalID"));
        patientAdminName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        patientAdminAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        patientAdminGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        patientAdminContactNo.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
        patientAdminAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
//        patientAdminActions.setCellValueFactory(new PropertyValueFactory<>("Actions"));
        patientAdminTable.setItems(list);
    }
}
