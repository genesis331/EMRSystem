package group57.emrsystem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MedicalHistoryController {
    private Stage stage;
    @FXML
    private TextField DateTextField;
    private TextField TimeTextField;
    private TextField WardTextField;
    private TextField TreatmentResultsTextField;
    private TextField ObervationTextField;
    private TextField MajorComplicationsTextField;
    private TextField AttendingDoctorTextField;
    private Button SaveButton;
    private TableView UserTableView;
    private TableView AdminTableView;
    private Button AdminAddRecordButton;

    public MedicalHistoryController(Stage stage)
    {
        this.stage = stage;
    }
}