package group57.emrsystem;
import java.io.IOException;
import java.util.*;

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

public class NewDiagnosisController {
    private Stage stage;
    private String delimiter = ",";

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
    private String newline = "\n";
    private String header = "id,date,name,diagnosed_sickness";


    public void writeCSV(List<Diagnosis> diagnosiss, String fileName) {
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            fileWriter.append(header);
            fileWriter.append(newline);

            // process content line by line
            for (Diagnosis diagnosis : diagnosiss) {
                fileWriter.append(String.valueOf(diagnosis.getDate()));
                fileWriter.append(delimiter);
                fileWriter.append(diagnosis.getName());
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(diagnosis.getName()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(diagnosis.getDiagnosedSickness()));
                fileWriter.append(newline);
            }
        } catch (Exception e) {
            // handle exception
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                // handle exception
                e.printStackTrace();
            }
        }
    }


    public List<Diagnosis> readCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Diagnosis> students = new ArrayList<Diagnosis>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {

                    Diagnosis diagnosis = new Diagnosis(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3]);
                    students.add(diagnosis);
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
            // handle exception
            e.printStackTrace();
        } finally {
            try {
                if (bReader != null)
                    bReader.close();
            } catch (IOException e) {
                // handle exception
                e.printStackTrace();
            }
        }
        return students;
    }


    public void create(Diagnosis diagnosis) {
        List<Diagnosis> d = this.readCSV(Diagnosis.fileName);
        d.add(diagnosis);
        writeCSV(d, Diagnosis.fileName);

    }

    public void ToBeSaved(){
        DiagnosisSaveButton.setOnAction(e-> {
                    ;
                    int id = 0;
                    String date = DiagnosisDateTextField.getText();
                    String name = DiagnosisNameTextField.getText();
                    String diagnosed_sickness = DiagnosisDiagnosedSicknessTextField.getText();
                    Diagnosis diagnosis = new Diagnosis(id, date, name, diagnosed_sickness);
                    CSVHandler csv = new CSVHandler();
                    NewDiagnosisController n = new NewDiagnosisController();
                    n.create(diagnosis);
                });
        //I think we need a create method, what do you suggest?

    }


}
