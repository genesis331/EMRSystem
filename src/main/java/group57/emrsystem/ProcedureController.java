package group57.emrsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

public class ProcedureController implements Initializable{

    private Boolean isAdmin=true;
    private Stage stage;
    @FXML
    private Button add_button_admin;
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
    public TableView<Procedure> Procedure_admin_table;
    @FXML
    public TableView<Procedure> Procedure_user_table;
    @FXML
    public TableColumn<Procedure, String> table_date_procedure;
    @FXML
    public TableColumn<Procedure, String> table_time_procedure;
    @FXML
    public TableColumn<Procedure, String> table_type_procedure;
    @FXML
    public TableColumn<Procedure, String> table_medication_procedure;
    @FXML
    public TableColumn<Procedure, String> table_amount_procedure;
    @FXML
    public TableColumn<Procedure, String> table_frequency_procedure;
    @FXML
    public TableColumn<Procedure, String> admin_table_action_procedure;




    public ProcedureController(Stage stage) {
        this.stage = stage;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isAdmin) {
            AdminRenderData();
            add_button_admin.setOnAction(e -> {
                try {
                    ToAddRecord();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } else {
            UserRenderData();
        }
//        save_button.setOnAction(e->ToBeSaved());
    }

    public static List<Procedure> UserReadCSV(String fileName){
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Procedure> data = new ArrayList<Procedure>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    Procedure procedure = new Procedure (String.valueOf(Integer.parseInt(tokens[0])), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5],tokens[6]);
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

    public static List<Procedure> AdminReadCSV(String fileName){
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Procedure> data = new ArrayList<Procedure>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    Procedure procedure = new Procedure (String.valueOf(Integer.parseInt(tokens[0])), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
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
        List<Procedure> data = UserReadCSV(Objects.requireNonNull(DemoController.class.getResource("procedure.csv")).getPath());
        ObservableList<Procedure> list = FXCollections.observableArrayList(data);
        table_date_procedure.setCellValueFactory(new PropertyValueFactory<>("Date"));
        table_time_procedure.setCellValueFactory(new PropertyValueFactory<>("Time"));
        table_type_procedure.setCellValueFactory(new PropertyValueFactory<>("TypeOfProcedure"));
        table_medication_procedure.setCellValueFactory(new PropertyValueFactory<>("Medication"));
        table_amount_procedure.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        table_frequency_procedure.setCellValueFactory(new PropertyValueFactory<>("Frequency"));

        Procedure_user_table.setItems(list);
    }

    Callback<TableColumn<Procedure, Void>, TableCell<Procedure, Void>> cellFactory = new Callback<TableColumn<Procedure, Void>, TableCell<Procedure, Void>>() {
        @Override
        public TableCell<Procedure, Void> call(final TableColumn<Procedure, Void> param) {
            return new TableCell<Procedure, Void>() {

                private final Button btn = new Button("Delete");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Procedure data = getTableView().getItems().get(getIndex());
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
        TableColumn<Procedure, Void> colBtn = new TableColumn("Actions");
        List<Procedure> data = AdminReadCSV(Objects.requireNonNull(DemoController.class.getResource("Procedure.csv")).getPath());
        ObservableList<Procedure> list = FXCollections.observableArrayList(data);
        table_date_procedure.setCellValueFactory(new PropertyValueFactory<>("Date"));
        table_time_procedure.setCellValueFactory(new PropertyValueFactory<>("Time"));
        table_type_procedure.setCellValueFactory(new PropertyValueFactory<>("TypeOfProcedure"));
        table_medication_procedure.setCellValueFactory(new PropertyValueFactory<>("Medication"));
        table_amount_procedure.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        table_frequency_procedure.setCellValueFactory(new PropertyValueFactory<>("Frequency"));

        colBtn.setCellFactory(cellFactory);
        admin_table_action_procedure.getColumns().add(colBtn);
        admin_table_action_procedure.setText(list.toString());
    }

    public void ToAddRecord() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("newprocedure.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ToBeSaved(){
        String id = "0";
        String date = table_date_procedure.getText();
        String type = table_time_procedure.getText();
        String proceduretype = table_type_procedure.getText();
        String medication = table_medication_procedure.getText();
        String amountprocedure = table_amount_procedure.getText();
        String frequency = table_frequency_procedure.getText();
        Procedure procedure = new Procedure(id, date, type, proceduretype, medication, amountprocedure, frequency);
        CSVHandler csv = new CSVHandler();
        //csv.create(diagnosis);
        //I think we need a create method, what do you suggest?

    }
}
