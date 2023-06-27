package group57.emrsystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
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
    private TableView<Patient> patientAdminTable;

    @FXML
    private TableView<Patient, String> patientAdminNationalID;

    @FXML
    private TableView<Patient, String> patientAdminName;

    @FXML
    private TableView<Patient, String> patientAdminAge;

    @FXML
    private TableView<Patient, String> patientAdminGender;

    @FXML
    private TableView<Patient, String> patientAdminContactNo;

    @FXML
    private TableView<Patient, String> patientAdminAddress;

    @FXML
    private TableView<Patient, String> patientAdminActions;

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
                    Patient patient = new Patient (String.valueOf(Integer.parseInt(tokens[0])), tokens[1], tokens[2], tokens[3]);
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
        patientAdminNationalID.setCellValueFactory(new PropertyValueFactory<>("National ID"));
        patientAdminName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        patientAdminAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        patientAdminGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        patientAdminContactNo.setCellValueFactory(new PropertyValueFactory<>("Contact No."));
        patientAdminAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        patientAdminActions.setCellValueFactory(new PropertyValueFactory<>("Actions"));
        tableView.setItems(list);
    }
}
