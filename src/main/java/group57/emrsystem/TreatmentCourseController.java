package group57.emrsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TreatmentCourseController  implements Initializable {
    private Stage stage;
    @FXML
    private TextField treatment_textfield;
    @FXML
    private TextField start_date_textfield;
    @FXML
    private TextField end_date_textfield;
    @FXML
    private Button save_button;
    @FXML
    private TableView treatment_admin_table;
    @FXML
    private Button add_record_button;
    @FXML
    private TableView treatment_user_table;
    @FXML
    private TableColumn treatment_admin_treatment;
    @FXML
    private TableColumn treatment_admin_start_date;
    @FXML
    private TableColumn treatment_admin_end_date;
    @FXML
    private TableColumn treatment_admin_actions;
    @FXML
    private TableColumn treatment_user_treatment;
    @FXML
    private TableColumn treatment_user_start_date;
    @FXML
    private TableColumn treatment_user_end_date;
    @FXML
    private TableColumn treatment_user_actions;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserRenderData();
        AdminRenderData();
        add_record_button.setOnAction(event ->{
            try {
                ToAddRecord();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        save_button.setOnAction(event -> {});
    }

    public TreatmentCourseController(Stage stage) {
        this.stage = stage;
    }

    public static List<TreatmentCourse> UserReadCSV(String fileName){
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<TreatmentCourse> data = new ArrayList<TreatmentCourse>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    TreatmentCourse treatmentCourse = new TreatmentCourse (String.valueOf(Integer.parseInt(tokens[0])), tokens[1], tokens[2]);
                    data.add(treatmentCourse);
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

    public static List<TreatmentCourse> AdminReadCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<TreatmentCourse> data = new ArrayList<TreatmentCourse>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    TreatmentCourse treatmentCourse = new TreatmentCourse(String.valueOf(Integer.parseInt(tokens[0])), tokens[1], tokens[2]);
                    data.add(treatmentCourse);
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
        List<TreatmentCourse> data = UserReadCSV(Objects.requireNonNull(DemoController.class.getResource("treatmentcourse.csv")).getPath());
        ObservableList<TreatmentCourse> list = FXCollections.observableArrayList(data);
        treatment_user_treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        treatment_user_start_date.setCellValueFactory(new PropertyValueFactory<>("start_end"));
        treatment_user_end_date.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        treatment_user_actions.setCellValueFactory(new PropertyValueFactory<>("actions"));
        treatment_user_table.setItems(list);
    }

    public void AdminRenderData(){
        List<TreatmentCourse> data = AdminReadCSV(Objects.requireNonNull(DemoController.class.getResource("treatmentcourse.csv")).getPath());
        ObservableList<TreatmentCourse> list = FXCollections.observableArrayList(data);
        treatment_admin_treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        treatment_admin_start_date.setCellValueFactory(new PropertyValueFactory<>("start_end"));
        treatment_admin_end_date.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        treatment_admin_actions.setCellValueFactory(new PropertyValueFactory<>("actions"));
        treatment_admin_table.setItems(list);
    }

    public void ToAddRecord() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/group57.emrsystem/newtreatmentcourse.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
