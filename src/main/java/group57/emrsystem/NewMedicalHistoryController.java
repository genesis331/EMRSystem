package group57.emrsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewMedicalHistoryController implements Initializable {
    private Stage stage;
    private String delimiter = ",";
    private Boolean isAdmin = false;
    @FXML
    private TextField MedHisDateTextField;
    private TextField MedHisTimeTextField;
    private TextField MedHisWardTextField;
    private TextField MedHisTreatmentResultsTextField;
    private TextField MedHisObservationsTextField;
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
    private String newline = "\n";
    private String header = "id,date,time,ward,treatmentresults,observations,majorcomplication,attendingdoctor,actions";


    public void writeCSV(List<MedicalHistory> MedicalHistorys, String fileName) {
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            fileWriter.append(header);
            fileWriter.append(newline);

            // process content line by line
            for (MedicalHistory MedicalHistory : MedicalHistorys) {
                fileWriter.append(String.valueOf(MedicalHistory.getDate()));
                fileWriter.append(delimiter);
                fileWriter.append(MedicalHistory.getName());
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(MedicalHistory.getName()));
                fileWriter.append(delimiter);
                fileWriter.append(String.valueOf(MedicalHistory.getDiagnosedSickness()));
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


    public List<MedicalHistory> readCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<MedicalHistory> students = new ArrayList<MedicalHistory>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {

                    MedicalHistory medicalhistory = new MedicalHistory(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]);
                    students.add(medicalhistory);
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


    /*public void create(MedicalHistory medicalhistory) {
        List<MedicalHistory> d = this.readCSV(MedicalHistory.fileName);
        d.add(MedicalHistory);
        writeCSV(d, MedicalHistory.fileName);

    }*/

    @FXML
    private void ToBeSaved(ActionEvent event){
        MedHisSaveButton.setOnAction(e-> {
            ;
            String id = "0";
            String date = MedHisDateTextField.getText();
            String time = MedHisTimeTextField.getText();
            String ward = MedHisWardTextField.getText();
            String treatment_results = MedHisTreatmentResultsTextField.getText();
            String observations = MedHisObservationsTextField.getText();
            String major_complications = MedHisMajorComplicationsTextField.getText();
            String attendingdoctor = MedHisAttendingDoctorTextField.getText();
            String Actions = "something I guess";
            MedicalHistory medicalhistory = new MedicalHistory(id, date, time, ward, treatment_results, observations, major_complications, attendingdoctor, actions);
            CSVHandler csv = new CSVHandler();
            NewMedicalHistoryController n = new NewMedicalHistoryController();
            //n.create(medicalhistory);
        });
        //I think we need a create method, what do you suggest?



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        MedHisSaveButton.setOnAction(this :: ToBeSaved);

    }
}

