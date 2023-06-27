package group57.emrsystem;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewAnalysisController {
        private Stage stage;
        @FXML
        private TextField date_textfield;
        @FXML
        private TextField type_of_test_textfield;
        @FXML
        private TextField result_textfield;
        @FXML
        public Button save_button;
        @FXML
        public TableView<Analysis> analysis_admin_table;
        @FXML
        public TableView<Analysis> analysis_user_table;
        @FXML
        public TableColumn<Analysis, String> analysis_user_date;
        @FXML
        public TableColumn<Analysis, String> analysis_user_type_of_test;
        @FXML
        public TableColumn<Analysis, String> analysis_user_result;
        @FXML
        public TableColumn<Analysis, String> analysis_user_action;
        @FXML
        public Button add_record_button;

}
