package group57.emrsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AnalysisController {
    private Stage stage;

    @FXML
    private TextField date_textfield;

    @FXML
    private TextField type_of_test_textfield;

    @FXML
    private TextField result_textfield;

    @FXML
    private Button save_button;

    @FXML
    private TableView analysis_admin_table;

    @FXML
    private Button add_record_button;

    @FXML
    private TableView analysis_user_table;

    public AnalysisController(Stage stage)
    {
        this.stage = stage;
    }
}
