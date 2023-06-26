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
    private Boolean isAdmin = false;
    @FXML
    private TextField date_textfield;
    @FXML
    private TextField type_of_test_textfield;
    @FXML
    private TextField result_textfield;
    @FXML
    public Button save_button;
    @FXML
    public TableView<Analysis> analysis_admin_table;
    @FXML
    public Button add_record_button;
    @FXML
    public TableView<Analysis> analysis_user_table;
    @FXML
    public TableColumn<Analysis, String> analysis_user_date;
    @FXML
    public TableColumn<Analysis, String> analysis_user_type_of_test;
    @FXML
    public TableColumn<Analysis, String> analysis_user_result;
    @FXML
    public TableColumn<Analysis, String> analysis_user_action;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isAdmin) {
            AdminRenderData();
            add_record_button.setOnAction(e -> {
                try {
                    ToAddRecord();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } else {
            UserRenderData();
        }
//        save_button.setOnAction(e->ToBeSaved());
    }

    public AnalysisController(Stage stage, boolean isAdmin) {
        this.stage = stage;
        this.isAdmin = isAdmin;
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
        analysis_user_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        analysis_user_type_of_test.setCellValueFactory(new PropertyValueFactory<>("TypeOfTest"));
        analysis_user_result.setCellValueFactory(new PropertyValueFactory<>("Result"));
//        analysis_user_action.setCellValueFactory(new PropertyValueFactory<>("Actions"));
        analysis_user_table.setItems(list);
    }

    public void AdminRenderData(){
        List<Analysis> data = AdminReadCSV(Objects.requireNonNull(DemoController.class.getResource("analysis.csv")).getPath());
        ObservableList<Analysis> list = FXCollections.observableArrayList(data);
        analysis_user_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        analysis_user_type_of_test.setCellValueFactory(new PropertyValueFactory<>("TypeOfTest"));
        analysis_user_result.setCellValueFactory(new PropertyValueFactory<>("Result"));
//        analysis_user_action.setCellValueFactory(new PropertyValueFactory<>("actions"));
        analysis_admin_table.setItems(list);
    }

    public void ToAddRecord() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("newanalysis.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ToBeSaved(){
        String id = "0";
        String date = date_textfield.getText();
        String name = type_of_test_textfield.getText();
        String result = result_textfield.getText();
        Analysis analysis = new Analysis(id, date, name, result);
        CSVHandler csv = new CSVHandler();
        //csv.create(diagnosis);
        //I think we need a create method, what do you suggest?

    }
}

