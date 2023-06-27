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
    private TextField MedHisDateTextField;
    private TextField MedHisTimeTextField;
    private TextField MedHisWardTextField;
    private TextField MedHisTreatmentResultsTextField;
    private TextField MedHisObervationTextField;
    private TextField MedHisMajorComplicationsTextField;
    private TextField MedHisAttendingDoctorTextField;
    private Button MedHisSaveButton;
    private TableView MedHisUserTableView;
    private TableView MedHisAdminTableView;
    private Button MedHisAdminAddRecordButton;
    private TableColumn MedHisUserDateColumn;
    private TableColumn MedHisUserTimeColumn;
    private TableColumn MedHisUserWardColumn;
    private TableColumn MedHisUserTreatmentResultsColumn;
    private TableColumn MedHisUserObservationsColumn;
    private TableColumn MedHisUserMajorComplicationsColumn;
    private TableColumn MedHisUserAttendingDoctorColumn;
    private TableColumn MedHisUserActionsColumn;
    private TableColumn MedHisAdminDateColumn;
    private TableColumn MedHisAdminTimeColumn;
    private TableColumn MedHisAdminWardColumn;
    private TableColumn MedHisAdminTreatmentResultsColumn;
    private TableColumn MedHisAdminObservationsColumn;
    private TableColumn MedHisAdminMajorComplicationsColumn;
    private TableColumn MedHisAdminAttendingDoctorColumn;
    private TableColumn MedHisAdminActionsColumn;

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
        MedHisUserDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        MedHisUserTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        MedHisUserWardColumn.setCellValueFactory(new PropertyValueFactory<>("ward"));
        MedHisUserTreatmentResultsColumn.setCellValueFactory(new PropertyValueFactory<>("treatmentresults"));
        MedHisUserObservationsColumn.setCellValueFactory(new PropertyValueFactory<>("observations"));
        MedHisUserMajorComplicationsColumn.setCellValueFactory(new PropertyValueFactory<>("majorcpmplications"));
        MedHisUserAttendingDoctorColumn.setCellValueFactory(new PropertyValueFactory<>("attendingdoctor"));
        MedHisUserActionsColumn.setCellValueFactory(new PropertyValueFactory<>("actions"));
        MedHisUserTableView.setItems(list);
    }

    public void AdminRenderData() {
        List<MedicalHistory> data = AdminReadCSV(Objects.requireNonNull(DemoController.class.getResource("medicalhistory.csv")).getPath());
        ObservableList<MedicalHistory> list = FXCollections.observableArrayList(data);
        MedHisAdminDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        MedHisAdminTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        MedHisAdminWardColumn.setCellValueFactory(new PropertyValueFactory<>("ward"));
        MedHisAdminTreatmentResultsColumn.setCellValueFactory(new PropertyValueFactory<>("treatmentresults"));
        MedHisAdminObservationsColumn.setCellValueFactory(new PropertyValueFactory<>("observations"));
        MedHisAdminMajorComplicationsColumn.setCellValueFactory(new PropertyValueFactory<>("majorcpmplications"));
        MedHisAdminAttendingDoctorColumn.setCellValueFactory(new PropertyValueFactory<>("attendingdoctor"));
        MedHisAdminActionsColumn.setCellValueFactory(new PropertyValueFactory<>("actions"));
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