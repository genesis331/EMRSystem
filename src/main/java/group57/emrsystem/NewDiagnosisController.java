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

public class NewDiagnosisController implements Initializable{
    private Stage stage;
    @FXML
    private TextField DiagnosisDateTextField;
    @FXML
    private TextField DiagnosisNameTextField;
    @FXML
    private TextField DiagnosisDiagnosedSicknessTextField;
    @FXML
    public TableView<Diagnosis> DiagnosisAdminTableView;
    @FXML
    public TableView<Diagnosis> DiagnosisUserTableView;
    @FXML
    public TableColumn<Diagnosis, String> DiagnosisUserDateColumn;
    @FXML
    public TableColumn<Diagnosis, String> DiagnosisUserNameColumn;
    @FXML
    public TableColumn<Diagnosis, String> DiagnosisUserDiagnosedSicknessColumn;
    @FXML
    public Button DiagnosisSaveButton;
    @FXML
    public Button DiagnosisAdminAddRecordButton;

    @FXML
    private void ToBeSaved(ActionEvent event){
        DiagnosisSaveButton.setOnAction(e-> {

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        DiagnosisSaveButton.setOnAction(this :: ToBeSaved);
    }
}

