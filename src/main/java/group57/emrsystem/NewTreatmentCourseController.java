package group57.emrsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewTreatmentCourseController {
    private Stage stage;
    @FXML
    private TextField treatment_textfield;
    @FXML
    private TextField start_date_textfield;
    @FXML
    private TextField end_date_textfield;
    @FXML
    public Button save_button;
    @FXML
    public TableView<TreatmentCourse> treatment_admin_table;
    @FXML
    public TableView<TreatmentCourse> treatment_user_table;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_user_treatment;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_user_start_date;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_user_end_date;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_user_actions;
    @FXML
    public Button add_record_button;

}
