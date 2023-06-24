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

public class AnalysisController implements Initializable {
    private Stage stage;
    @FXML
    private TextField date_textfield;
    @FXML
    private TextField type_of_test_textfield;
    @FXML
    private TextField result_textfield;
    @FXML
    private Button save_button;
    @FXML
    private TableView analysis_admin_table;
    @FXML
    private Button add_record_button;
    @FXML
    private TableView analysis_user_table;
    @FXML
    private TableColumn analysis_user_date;
    @FXML
    private TableColumn analysis_user_type_of_test;
    @FXML
    private TableColumn analysis_user_result;
    @FXML
    private TableColumn analysis_user_action;
    @FXML
    private TableColumn analysis_admin_date;
    @FXML
    private TableColumn analysis_admin_type_of_test;
    @FXML
    private TableColumn analysis_admin_result;
    @FXML
    private TableColumn analysis_admin_action;

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

    public AnalysisController(Stage stage) {
        this.stage = stage;
    }

    public static List<Analysis> UserReadCSV(String fileName){
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Analysis> data = new ArrayList<Analysis>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    Analysis analysis = new Analysis (String.valueOf(Integer.parseInt(tokens[0])), tokens[1], tokens[2], tokens[3]);
                    data.add(analysis);
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

    public static List<Analysis> AdminReadCSV(String fileName){
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Analysis> data = new ArrayList<Analysis>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    Analysis analysis = new Analysis (String.valueOf(Integer.parseInt(tokens[0])), tokens[1], tokens[2], tokens[3]);
                    data.add(analysis);
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
        List<Analysis> data = UserReadCSV(Objects.requireNonNull(DemoController.class.getResource("analysis.csv")).getPath());
        ObservableList<Analysis> list = FXCollections.observableArrayList(data);
        analysis_user_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        analysis_user_type_of_test.setCellValueFactory(new PropertyValueFactory<>("type_of_test"));
        analysis_user_result.setCellValueFactory(new PropertyValueFactory<>("result"));
        analysis_user_action.setCellValueFactory(new PropertyValueFactory<>("actions"));
        analysis_user_table.setItems(list);
    }

    public void AdminRenderData(){
        List<Analysis> data = AdminReadCSV(Objects.requireNonNull(DemoController.class.getResource("analysis.csv")).getPath());
        ObservableList<Analysis> list = FXCollections.observableArrayList(data);
        analysis_admin_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        analysis_admin_type_of_test.setCellValueFactory(new PropertyValueFactory<>("type_of_test"));
        analysis_admin_result.setCellValueFactory(new PropertyValueFactory<>("result"));
        analysis_admin_action.setCellValueFactory(new PropertyValueFactory<>("actions"));
        analysis_admin_table.setItems(list);
    }

    public void ToAddRecord() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/group57.emrsystem/newanalysis.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

