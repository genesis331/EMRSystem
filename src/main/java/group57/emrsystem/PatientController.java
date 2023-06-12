package group57.emrsystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;

public class PatientController {
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
    private TableView displayTable;

}
