package group57.emrsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProcedureAndMedicineController implements Initializable {
    private Stage stage;
    private Boolean isAdmin;
    private String username;
    @FXML
    public Button addbuttonadmin;
    @FXML
    private TextField text_date_procedure;

    @FXML
    private TextField text_time_procedure;

    @FXML
    private TextField text_procedure_type_procedure;

    @FXML
    private TextField text_medication_procedure;

    @FXML
    private TextField text_amount_procedure;

    @FXML
    private TextField text_frequency_procedure;

    @FXML
    private Button save_button_admin;

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
    private TextField textdateprocedure;

    @FXML
    private TextField texttimeprocedure;

    @FXML
    private TextField textproceduretypeprocedure;

    @FXML
    private TextField textmedicationprocedure;

    @FXML
    private TextField textamountprocedure;

    @FXML
    private TextField textfrequencyprocedure;

    @FXML
    private Button savebuttonprocedure;

    public ProcedureAndMedicineController(Stage stage, boolean isAdmin, String username)
    {
        this.stage = stage;
        this.isAdmin = isAdmin;
        this.username = username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isAdmin) {
            AdminRenderData();
            addbuttonadmin.setOnAction(e -> {
                try {
                    ToAddRecord();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } else {
            UserRenderData();
        }
    }

    public List<ProcedureAndMedicine> UserReadCSV(String fileName){
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<ProcedureAndMedicine> data = new ArrayList<>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    if (tokens[0].equals(username)) {
                        ProcedureAndMedicine procedure = new ProcedureAndMedicine(String.valueOf(Integer.parseInt(tokens[1])), tokens[2], tokens[3], tokens[4], tokens[5],tokens[6], tokens[7]);
                        data.add(procedure);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bReader != null)
                    bReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public List<ProcedureAndMedicine> AdminReadCSV(String fileName){
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<ProcedureAndMedicine> data = new ArrayList<>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    ProcedureAndMedicine procedure = new ProcedureAndMedicine(String.valueOf(Integer.parseInt(tokens[1])), tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]);
                    data.add(procedure);
                }
            }
        } catch (FileNotFoundException e) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bReader != null)
                    bReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public void UserRenderData(){
        List<ProcedureAndMedicine> data = UserReadCSV(Objects.requireNonNull(ProcedureAndMedicineController.class.getResource("procedureandmedicine.csv")).getPath());
        ObservableList<ProcedureAndMedicine> list = FXCollections.observableArrayList(data);
        table_date_procedure.setCellValueFactory(new PropertyValueFactory<>("Date"));
        table_time_procedure.setCellValueFactory(new PropertyValueFactory<>("Time"));
        table_type_procedure.setCellValueFactory(new PropertyValueFactory<>("TypeOfProcedure"));
        table_medication_procedure.setCellValueFactory(new PropertyValueFactory<>("Medication"));
        table_amount_procedure.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        table_frequency_procedure.setCellValueFactory(new PropertyValueFactory<>("Frequency"));
        tableuser.setItems(list);
    }

    Callback<TableColumn<ProcedureAndMedicine, Void>, TableCell<ProcedureAndMedicine, Void>> cellFactory = new Callback<>() {
        @Override
        public TableCell<ProcedureAndMedicine, Void> call(final TableColumn<ProcedureAndMedicine, Void> param) {
            return new TableCell<>() {

                private final Button btn = new Button("Delete");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        ProcedureAndMedicine data = getTableView().getItems().get(getIndex());
                        System.out.println("selectedData: " + data);
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
        }
    };

    public void AdminRenderData(){
        TableColumn<ProcedureAndMedicine, Void> colBtn = new TableColumn<>("Actions");
        List<ProcedureAndMedicine> data = AdminReadCSV(Objects.requireNonNull(ProcedureAndMedicineController.class.getResource("procedureandmedicine.csv")).getPath());
        ObservableList<ProcedureAndMedicine> list = FXCollections.observableArrayList(data);
        table_date_procedure.setCellValueFactory(new PropertyValueFactory<>("Date"));
        table_time_procedure.setCellValueFactory(new PropertyValueFactory<>("Time"));
        table_type_procedure.setCellValueFactory(new PropertyValueFactory<>("TypeOfProcedure"));
        table_medication_procedure.setCellValueFactory(new PropertyValueFactory<>("Medication"));
        table_amount_procedure.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        table_frequency_procedure.setCellValueFactory(new PropertyValueFactory<>("Frequency"));
        colBtn.setCellFactory(cellFactory);
        tableadmin.getColumns().add(colBtn);
        tableadmin.setItems(list);
    }

    public void ToAddRecord() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newprocedure.fxml"));
        fxmlLoader.setController(new NewProcedureAndMedicineController());
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
