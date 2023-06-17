package group57.emrsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DiagnosisController {
    private Stage stage;
    @FXML
    private TextField DateTextField;
    private TextField NameTextField;
    private TextField DiagnosedSicknessTextField;
    private TableView AdminTableView;
    private TableView PrivateTableView;
    private Button SaveButton;
    private Button AdminAddRecordButton;

    public DiagnosisController(Stage stage)
    {
        this.stage = stage;
    }
}

