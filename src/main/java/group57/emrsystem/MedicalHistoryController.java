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

public class MedicalHistoryController implements Initializable {
    private Stage stage;
    private Boolean isAdmin = false;
    private String username;
    @FXML
    public TextField MedHisDateTextField;
    public TextField MedHisTimeTextField;
    public TextField MedHisWardTextField;
    public TextField MedHisTreatmentResultsTextField;
    public TextField MedHisObervationTextField;
    public TextField MedHisMajorComplicationsTextField;
    public TextField MedHisAttendingDoctorTextField;
    public Button MedHisSaveButton;
    public TableView MedHisUserTableView;
    public TableView MedHisAdminTableView;
    public Button MedHisAdminAddRecordButton;
    public TableColumn MedHisUserDateColumn;
    public TableColumn MedHisUserTimeColumn;
    public TableColumn MedHisUserWardColumn;
    public TableColumn MedHisUserTreatmentResultsColumn;
    public TableColumn MedHisUserObservationColumn;
    public TableColumn MedHisUserMajorComplicationsColumn;
    public TableColumn MedHisUserAttendingDoctorColumn;
    public TableColumn MedHisAdminDateColumn;
    public TableColumn MedHisAdminTimeColumn;
    public TableColumn MedHisAdminWardColumn;
    public TableColumn MedHisAdminTreatmentResultsColumn;
    public TableColumn MedHisAdminObservationColumn;
    public TableColumn MedHisAdminMajorComplicationsColumn;
    public TableColumn MedHisAdminAttendingDoctorColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isAdmin) {
            AdminRenderData();
            MedHisAdminAddRecordButton.setOnAction(e -> {
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

    public List<MedicalHistory> UserReadCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<MedicalHistory> data = new ArrayList<>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    if (tokens[0].equals(username)) {
                        MedicalHistory medicalhistory = new MedicalHistory(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], tokens[8]);
                        data.add(medicalhistory);
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

    public List<MedicalHistory> AdminReadCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<MedicalHistory> data = new ArrayList<>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    MedicalHistory medicalhistory = new MedicalHistory(tokens[1], tokens[2], tokens[3], tokens[4], tokens [5], tokens[6], tokens[7], tokens[8]);
                    data.add(medicalhistory);
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
    public void UserRenderData() {
        List<MedicalHistory> data = UserReadCSV(Objects.requireNonNull(MedicalHistoryController.class.getResource("medicalhistory.csv")).getPath());
        ObservableList<MedicalHistory> list = FXCollections.observableArrayList(data);
        MedHisUserDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        MedHisUserTimeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
        MedHisUserWardColumn.setCellValueFactory(new PropertyValueFactory<>("Ward"));
        MedHisUserTreatmentResultsColumn.setCellValueFactory(new PropertyValueFactory<>("TreatmentResults"));
        MedHisUserObservationColumn.setCellValueFactory(new PropertyValueFactory<>("Observation"));
        MedHisUserMajorComplicationsColumn.setCellValueFactory(new PropertyValueFactory<>("MajorComplications"));
        MedHisUserAttendingDoctorColumn.setCellValueFactory(new PropertyValueFactory<>("AttendingDoctor"));
        MedHisUserTableView.setItems(list);
    }

    Callback<TableColumn<MedicalHistory, Void>, TableCell<MedicalHistory, Void>> cellFactory = new Callback<TableColumn<MedicalHistory, Void>, TableCell<MedicalHistory, Void>>() {
        @Override
        public TableCell<MedicalHistory, Void> call(final TableColumn<MedicalHistory, Void> param) {
            return new TableCell<MedicalHistory, Void>() {

                private final Button btn = new Button("Delete");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        MedicalHistory data = getTableView().getItems().get(getIndex());
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

    public void AdminRenderData() {
        TableColumn<MedicalHistory, Void> colBtn = new TableColumn<>("Actions");
        List<MedicalHistory> data = AdminReadCSV(Objects.requireNonNull(MedicalHistoryController.class.getResource("medicalhistory.csv")).getPath());
        ObservableList<MedicalHistory> list = FXCollections.observableArrayList(data);
        MedHisAdminDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        MedHisAdminTimeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
        MedHisAdminWardColumn.setCellValueFactory(new PropertyValueFactory<>("Ward"));
        MedHisAdminTreatmentResultsColumn.setCellValueFactory(new PropertyValueFactory<>("TreatmentResults"));
        MedHisAdminObservationColumn.setCellValueFactory(new PropertyValueFactory<>("Observation"));
        MedHisAdminMajorComplicationsColumn.setCellValueFactory(new PropertyValueFactory<>("MajorComplications"));
        MedHisAdminAttendingDoctorColumn.setCellValueFactory(new PropertyValueFactory<>("AttendingDoctor"));
        colBtn.setCellFactory(cellFactory);
        MedHisAdminTableView.getColumns().add(colBtn);
        MedHisAdminTableView.setItems(list);
    }

    public void ToAddRecord() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newdiagnosis.fxml"));
        fxmlLoader.setController(new NewMedicalHistoryController());
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public MedicalHistoryController(Stage stage, boolean isAdmin, String username)
    {
        this.stage = stage;
        this.isAdmin = isAdmin;
        this.username = username;
    }
}