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

public class DiagnosisController implements Initializable{
    private Stage stage;
    private Boolean isAdmin;
    private String username;

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
    public TableColumn<Diagnosis, String> DiagnosisAdminDateColumn;
    @FXML
    public TableColumn<Diagnosis, String> DiagnosisAdminNameColumn;
    @FXML
    public TableColumn<Diagnosis, String> DiagnosisAdminDiagnosedSicknessColumn;
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
    }

    public DiagnosisController(Stage stage, boolean isAdmin, String username)
    {
        this.stage = stage;
        this.isAdmin = isAdmin;
        this.username = username;
    }

    public List<Diagnosis> UserReadCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Diagnosis> data = new ArrayList<>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    if (tokens[0].equals(username)) {
                        Diagnosis diagnosis = new Diagnosis(tokens[1], tokens[2], tokens[3], tokens[4]);
                        data.add(diagnosis);
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

    public List<Diagnosis> AdminReadCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Diagnosis> data = new ArrayList<>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            if (username.equals("admin")) {
                while ((line = bReader.readLine()) != null) {
                    String[] tokens = line.split(delimiter);
                    if (tokens.length > 0) {
                        Diagnosis diagnosis = new Diagnosis(tokens[1], tokens[2], tokens[3], tokens[4]);
                        data.add(diagnosis);
                    }
                }
            } else {
                while ((line = bReader.readLine()) != null) {
                    String[] tokens = line.split(delimiter);
                    if (tokens.length > 0) {
                        if (tokens[1].equals(username)) {
                            Diagnosis diagnosis = new Diagnosis(tokens[1], tokens[2], tokens[3], tokens[4]);
                            data.add(diagnosis);
                        }
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

    Callback<TableColumn<Diagnosis, Void>, TableCell<Diagnosis, Void>> cellFactory = new Callback<>() {
        @Override
        public TableCell<Diagnosis, Void> call(final TableColumn<Diagnosis, Void> param) {
            return new TableCell<>() {

                private final Button btn = new Button("Delete");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Diagnosis data = getTableView().getItems().get(getIndex());
                        ToDeleteRecord(data.getId());
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

    private void ToDeleteRecord(String target) {
        List<String> data = new ArrayList<>();
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(Objects.requireNonNull(PatientController.class.getResource("diagnosis.csv")).getPath());

        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    if (!tokens[1].equals(target)) {
                        data.add(line + "\n");
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

        List<String> stringArrays = new ArrayList<>();
        stringArrays.add("username,id,date,name,diagnosedsickness\n");
        stringArrays.addAll(data);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Objects.requireNonNull(NewDiagnosisController.class.getResource("diagnosis.csv")).getPath()))) {
            for (String stringArray : stringArrays) {
                writer.write(stringArray);
            }
            System.out.println("Data has been written to the file.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Record has been deleted successfully!");
            alert.showAndWait();
            AdminRenderData();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void UserRenderData() {
        List<Diagnosis> data = UserReadCSV(Objects.requireNonNull(DiagnosisController.class.getResource("diagnosis.csv")).getPath());
        ObservableList<Diagnosis> list = FXCollections.observableArrayList(data);
        DiagnosisUserDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        DiagnosisUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        DiagnosisUserDiagnosedSicknessColumn.setCellValueFactory(new PropertyValueFactory<>("DiagnosedSickness"));
        DiagnosisUserTableView.setItems(list);
    }

    public void AdminRenderData() {
        if (DiagnosisAdminTableView.getColumns().size() > 3) {
            DiagnosisAdminTableView.getColumns().remove(DiagnosisAdminTableView.getColumns().size() - 1);
        }
        TableColumn<Diagnosis, Void> colBtn = new TableColumn<>("Actions");
        List<Diagnosis> data = AdminReadCSV(Objects.requireNonNull(DiagnosisController.class.getResource("diagnosis.csv")).getPath());
        ObservableList<Diagnosis> list = FXCollections.observableArrayList(data);
        DiagnosisAdminDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        DiagnosisAdminNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        DiagnosisAdminDiagnosedSicknessColumn.setCellValueFactory(new PropertyValueFactory<>("DiagnosedSickness"));
        colBtn.setCellFactory(cellFactory);
        DiagnosisAdminTableView.getColumns().add(colBtn);
        DiagnosisAdminTableView.setItems(list);
    }

    public void ToAddRecord() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newdiagnosis.fxml"));
        NewDiagnosisController controller = new NewDiagnosisController();
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        controller.setStage(stage);
        controller.setParentController(this);
        stage.setScene(scene);
        stage.show();
    }
}

