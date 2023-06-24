package group57.emrsystem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MedicalHistoryController {
    private Stage stage;
    @FXML
    private TextField MedHisDateTextField;
    private TextField MedHisTimeTextField;
    private TextField MedHisWardTextField;
    private TextField MedHisTreatmentResultsTextField;
    private TextField MedHisObervationTextField;
    private TextField MedHisMajorComplicationsTextField;
    private TextField MedHisAttendingDoctorTextField;
    private Button MedHisSaveButton;
    private TableView MedHisUserTableView;
    private TableView MedHisAdminTableView;
    private Button MedHisAdminAddRecordButton;

    public MedicalHistoryController(Stage stage)
    {
        this.stage = stage;
    }
}