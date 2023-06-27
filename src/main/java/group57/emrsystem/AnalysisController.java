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

public class AnalysisController implements Initializable {
    private Stage stage;
    private Boolean isAdmin = false;
    private String username;
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
    }

    public AnalysisController(Stage stage, boolean isAdmin, String username) {
        this.stage = stage;
        this.isAdmin = isAdmin;
        this.username = username;
    }

    public List<Analysis> UserReadCSV(String fileName){
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Analysis> data = new ArrayList<>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    if (tokens[0].equals(username)) {
                        Analysis analysis = new Analysis(String.valueOf(Integer.parseInt(tokens[1])), tokens[2], tokens[3], tokens[4]);
                        data.add(analysis);
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

    public List<Analysis> AdminReadCSV(String fileName){
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Analysis> data = new ArrayList<>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    Analysis analysis = new Analysis(String.valueOf(Integer.parseInt(tokens[1])), tokens[2], tokens[3], tokens[4]);
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
        analysis_user_table.setItems(list);
    }

    Callback<TableColumn<Analysis, Void>, TableCell<Analysis, Void>> cellFactory = new Callback<>() {
        @Override
        public TableCell<Analysis, Void> call(final TableColumn<Analysis, Void> param) {
            return new TableCell<>() {

                private final Button btn = new Button("Delete");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Analysis data = getTableView().getItems().get(getIndex());
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

    public void AdminRenderData(){
        TableColumn<Analysis, Void> colBtn = new TableColumn<>("Actions");
        List<Analysis> data = AdminReadCSV(Objects.requireNonNull(DemoController.class.getResource("analysis.csv")).getPath());
        ObservableList<Analysis> list = FXCollections.observableArrayList(data);
        analysis_user_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        analysis_user_type_of_test.setCellValueFactory(new PropertyValueFactory<>("TypeOfTest"));
        analysis_user_result.setCellValueFactory(new PropertyValueFactory<>("Result"));
        colBtn.setCellFactory(cellFactory);
        analysis_admin_table.getColumns().add(colBtn);
        analysis_admin_table.setItems(list);
    }

    public void ToAddRecord() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newanalysis.fxml"));
        fxmlLoader.setController(new NewAnalysisController());
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

