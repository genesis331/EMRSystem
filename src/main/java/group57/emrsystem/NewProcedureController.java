package group57.emrsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewProcedureController {
        private Stage stage;
        @FXML
        private TextField textdateprocedure;
        @FXML
        private TextField texttimeprocedure;
        @FXML
        private TextField textproceduretypeprocedure;
        @FXML
        public TextField textmedicationprocedure;
        @FXML
        public TextField textamountprocedure;
        @FXML
        public TextField textfrequencyprocedure;
        @FXML
        public TableView<ProcedureAndMedicine> tableadmin;
        @FXML
        public TableView<ProcedureAndMedicine> tableuser;
        @FXML
        public TableColumn<ProcedureAndMedicine, String> table_date_procedure;
        @FXML
        public TableColumn<ProcedureAndMedicine, String> table_time_procedure;
        @FXML
        public TableColumn<ProcedureAndMedicine, String> table_type_procedure;
        @FXML
        public TableColumn<ProcedureAndMedicine, String> table_medication_procedure;
        @FXML
        public TableColumn<ProcedureAndMedicine, String> table_amount_procedure;
        @FXML
        public TableColumn<ProcedureAndMedicine, String> table_frequency_procedure;
        @FXML
        public TableColumn<ProcedureAndMedicine, String> admin_table_action_procedure;
        @FXML
        public Button savebuttonprocedure;

}
