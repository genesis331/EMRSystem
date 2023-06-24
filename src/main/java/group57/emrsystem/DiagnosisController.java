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

public class DiagnosisController implements Initializable{

    private Stage stage;
    @FXML
    private TextField DiagnosisDateTextField;
    private TextField DiagnosisNameTextField;
    private TextField DiagnosisDiagnosedSicknessTextField;
    private TableView DiagnosisAdminTableView;
    private TableView DiagnosisUserTableView;

    private TableColumn DiagnosisUserDateColumn;

    private TableColumn DiagnosisUserNameColumn;
    private TableColumn DiagnosisUserDiagnosedSicknessColumn;

    private Button DiagnosisSaveButton;
    private Button DiagnosisAdminAddRecordButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserRenderData();
        AdminRenderData();
        DiagnosisAdminAddRecordButton.setOnAction(e -> {
            try {
                ToAddRecord();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        DiagnosisSaveButton.setOnAction(e->ToBeSaved());
    }



    public DiagnosisController(Stage stage)
    {
        this.stage = stage;
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
    public void UserRenderData() {
        List<Diagnosis> data = UserReadCSV(Objects.requireNonNull(DemoController.class.getResource("diagnosis.csv")).getPath());
        ObservableList<Diagnosis> list = FXCollections.observableArrayList(data);
        DiagnosisUserDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        DiagnosisUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DiagnosisUserDiagnosedSicknessColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosed_sickness"));
        DiagnosisUserTableView.setItems(list);
    }

    public void AdminRenderData() {
        List<Diagnosis> data = AdminReadCSV(Objects.requireNonNull(DemoController.class.getResource("diagnosis.csv")).getPath());
        ObservableList<Diagnosis> list = FXCollections.observableArrayList(data);
        DiagnosisUserDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        DiagnosisUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DiagnosisUserDiagnosedSicknessColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosed_sickness"));
        DiagnosisUserTableView.setItems(list);
    }

    public void ToAddRecord() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/group57.emrsystem/newdiagnosis.fxml"));
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

