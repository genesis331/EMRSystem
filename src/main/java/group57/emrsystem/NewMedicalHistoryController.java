package group57.emrsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewMedicalHistoryController implements Initializable {
    private Stage stage;
    private Boolean isAdmin = false;
    @FXML
    private TextField MedHisDateTextField;
    private TextField MedHisTimeTextField;
    private TextField MedHisWardTextField;
    private TextField MedHisTreatmentResultsTextField;
    private TextField MedHisObservationsTextField;
    private TextField MedHisMajorComplicationsTextField;
    private TextField MedHisAttendingDoctorTextField;
    private Button MedHisSaveButton;
    private TableView MedHisUserTableView;
    private TableView MedHisAdminTableView;
    private Button MedHisAdminAddRecordButton;
    private TableColumn MedHisUserDateColumn;
    private TableColumn MedHisUserTimeColumn;
    private TableColumn MedHisUserWardColumn;
    private TableColumn MedHisUserTreatmentResultsColumn;
    private TableColumn MedHisUserObservationsColumn;
    private TableColumn MedHisUserMajorComplicationsColumn;
    private TableColumn MedHisUserAttendingDoctorColumn;
    private TableColumn MedHisUserActionsColumn;

    @FXML
    private void ToBeSaved(ActionEvent event){
        MedHisSaveButton.setOnAction(e-> {

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        MedHisSaveButton.setOnAction(this :: ToBeSaved);
    }
}

