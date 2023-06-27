package group57.emrsystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DiagnosisController implements Initializable{

    private Stage stage;

    private Boolean isAdmin = false;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isAdmin) {
            AdminRenderData();
            DiagnosisAdminAddRecordButton.setOnAction(e -> {
                try {
                    ToAddRecord();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } else {
            UserRenderData();
        }
//        DiagnosisSaveButton.setOnAction(e->ToBeSaved());
    }

    public DiagnosisController(Stage stage, boolean isAdmin)
    {
        this.stage = stage;
        this.isAdmin = isAdmin;
    }

    public static List<Diagnosis> UserReadCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Diagnosis> data = new ArrayList<Diagnosis>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    Diagnosis diagnosis = new Diagnosis(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3]);
                    data.add(diagnosis);
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

    public static List<Diagnosis> AdminReadCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Diagnosis> data = new ArrayList<Diagnosis>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    Diagnosis diagnosis = new Diagnosis(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3]);
                    data.add(diagnosis);
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

    Callback<TableColumn<Diagnosis, Void>, TableCell<Diagnosis, Void>> cellFactory = new Callback<TableColumn<Diagnosis, Void>, TableCell<Diagnosis, Void>>() {
        @Override
        public TableCell<Diagnosis, Void> call(final TableColumn<Diagnosis, Void> param) {
            return new TableCell<Diagnosis, Void>() {

                private final Button btn = new Button("Delete");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Diagnosis data = getTableView().getItems().get(getIndex());
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

    public void UserRenderData() {
        List<Diagnosis> data = UserReadCSV(Objects.requireNonNull(DemoController.class.getResource("diagnosis.csv")).getPath());
        ObservableList<Diagnosis> list = FXCollections.observableArrayList(data);
        DiagnosisUserDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        DiagnosisUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DiagnosisUserDiagnosedSicknessColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosedsickness"));
        DiagnosisUserTableView.setItems(list);
    }

    public void AdminRenderData() {
        TableColumn<Diagnosis, Void> colBtn = new TableColumn("Actions");
        List<Diagnosis> data = AdminReadCSV(Objects.requireNonNull(DemoController.class.getResource("diagnosis.csv")).getPath());
        ObservableList<Diagnosis> list = FXCollections.observableArrayList(data);
        DiagnosisUserDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        DiagnosisUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DiagnosisUserDiagnosedSicknessColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosedsickness"));
        colBtn.setCellFactory(cellFactory);
        DiagnosisAdminTableView.getColumns().add(colBtn);
        DiagnosisAdminTableView.setItems(list);
    }

    public void ToAddRecord() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("newdiagnosis.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ToBeSaved(){
        int id = 0;
        String date = DiagnosisDateTextField.getText();
        String name = DiagnosisNameTextField.getText();
        String diagnosed_sickness = DiagnosisDiagnosedSicknessTextField.getText();
        Diagnosis diagnosis = new Diagnosis(id, date, name, diagnosed_sickness);
        CSVHandler csv = new CSVHandler();
        //csv.create(diagnosis);
        //I think we need a create method, what do you suggest?

    }
}

