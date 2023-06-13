package group57.emrsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TreatmentCourseController {
    @FXML
    private TextField treatment_textfield;

    @FXML
    private TextField start_date_textfield;

    @FXML
    private TextField end_date_textfield;

    @FXML
    private Button save_button;

    @FXML
    private TableView treatment_admin_table;

    @FXML
    private Button add_record_button;

    @FXML
    private TableView treatment_user_table;
}
