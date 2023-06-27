package group57.emrsystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MedicalHistoryController implements Initializable {
    private Stage stage;
    private Boolean isAdmin = false;
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
    public TableColumn MedHisUserObservationsColumn;
    public TableColumn MedHisUserMajorComplicationsColumn;
    public TableColumn MedHisUserAttendingDoctorColumn;
    public TableColumn MedHisUserActionsColumn;
    public TableColumn MedHisAdminDateColumn;
    public TableColumn MedHisAdminTimeColumn;
    public TableColumn MedHisAdminWardColumn;
    public TableColumn MedHisAdminTreatmentResultsColumn;
    public TableColumn MedHisAdminObservationColumn;
    public TableColumn MedHisAdminMajorComplicationsColumn;
    public TableColumn MedHisAdminAttendingDoctorColumn;
    public TableColumn MedHisAdminActionsColumn;

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
//        DiagnosisSaveButton.setOnAction(e->ToBeSaved());
    }



    public static List<MedicalHistory> UserReadCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<MedicalHistory> data = new ArrayList<MedicalHistory>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    MedicalHistory medicalhistory = new MedicalHistory(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]);
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

    public static List<MedicalHistory> AdminReadCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<MedicalHistory> data = new ArrayList<MedicalHistory>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    MedicalHistory medicalhistory = new MedicalHistory(tokens[0], tokens[1], tokens[2], tokens[3], tokens [4], tokens[5], tokens[6], tokens[7]);
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
        List<MedicalHistory> data = UserReadCSV(Objects.requireNonNull(DemoController.class.getResource("medicalhistory.csv")).getPath());
        ObservableList<MedicalHistory> list = FXCollections.observableArrayList(data);
        MedHisAdminDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        MedHisAdminTimeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
        MedHisAdminWardColumn.setCellValueFactory(new PropertyValueFactory<>("Ward"));
        MedHisAdminTreatmentResultsColumn.setCellValueFactory(new PropertyValueFactory<>("TreatmentResults"));
        MedHisAdminObservationColumn.setCellValueFactory(new PropertyValueFactory<>("Observation"));
        MedHisAdminMajorComplicationsColumn.setCellValueFactory(new PropertyValueFactory<>("MajorComplications"));
        MedHisAdminAttendingDoctorColumn.setCellValueFactory(new PropertyValueFactory<>("AttendingDoctor"));
        MedHisAdminTableView.setItems(list);
    }

    public void AdminRenderData() {
        List<MedicalHistory> data = AdminReadCSV(Objects.requireNonNull(DemoController.class.getResource("medicalhistory.csv")).getPath());
        ObservableList<MedicalHistory> list = FXCollections.observableArrayList(data);
        MedHisAdminDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        MedHisAdminTimeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
        MedHisAdminWardColumn.setCellValueFactory(new PropertyValueFactory<>("Ward"));
        MedHisAdminTreatmentResultsColumn.setCellValueFactory(new PropertyValueFactory<>("TreatmentResults"));
        MedHisAdminObservationColumn.setCellValueFactory(new PropertyValueFactory<>("Observation"));
        MedHisAdminMajorComplicationsColumn.setCellValueFactory(new PropertyValueFactory<>("MajorComplications"));
        MedHisAdminAttendingDoctorColumn.setCellValueFactory(new PropertyValueFactory<>("AttendingDoctor"));
        MedHisAdminTableView.setItems(list);
    }

    public void ToAddRecord() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("newdiagnosis.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ToBeSaved(){
        String id = "0";
        String date = MedHisDateTextField.getText();
        String time = MedHisTimeTextField.getText();
        String ward = MedHisTimeTextField.getText();
        String treatment_results = MedHisTreatmentResultsTextField.getText();
        String observations = MedHisObervationTextField.getText();
        String major_complications = MedHisMajorComplicationsTextField.getText();
        String attending_doctors = MedHisAttendingDoctorTextField.getText();
        MedicalHistory medicalhistory = new MedicalHistory(id, date, time,ward,treatment_results, observations, major_complications, attending_doctors);
        CSVHandler csv = new CSVHandler();
        //csv.create(diagnosis);
        //I think we need a create method, what do you suggest?

    }

    public MedicalHistoryController(Stage stage, boolean isAdmin)
    {
        this.stage = stage;
        this.isAdmin = isAdmin;
    }
}