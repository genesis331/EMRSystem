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

public class TreatmentCourseController  implements Initializable {
    private Stage stage;
    private Boolean isAdmin;
    private String username;
    @FXML
    private TextField treatment_textfield;
    @FXML
    private TextField start_date_textfield;
    @FXML
    private TextField end_date_textfield;
    @FXML
    public Button save_button;
    @FXML
    public TableView<TreatmentCourse> treatment_admin_table;
    @FXML
    public Button add_record_button;
    @FXML
    public TableView<TreatmentCourse> treatment_user_table;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_user_treatment;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_user_start_date;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_user_end_date;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_admin_treatment;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_admin_start_date;
    @FXML
    public TableColumn<TreatmentCourse, String> treatment_admin_end_date;

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

    public TreatmentCourseController(Stage stage, boolean isAdmin, String username) {
        this.stage = stage;
        this.isAdmin = isAdmin;
        this.username = username;
    }

    public List<TreatmentCourse> UserReadCSV(String fileName){
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<TreatmentCourse> data = new ArrayList<>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    if (tokens[0].equals(username)) {
                        TreatmentCourse treatmentCourse = new TreatmentCourse(String.valueOf(Integer.parseInt(tokens[1])), tokens[2], tokens[3], tokens[4]);
                        data.add(treatmentCourse);
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

    public List<TreatmentCourse> AdminReadCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<TreatmentCourse> data = new ArrayList<>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            if (username.equals("admin")) {
                while ((line = bReader.readLine()) != null) {
                    String[] tokens = line.split(delimiter);
                    if (tokens.length > 0) {
                        TreatmentCourse treatmentCourse = new TreatmentCourse(String.valueOf(Integer.parseInt(tokens[1])), tokens[2], tokens[3], tokens[4]);
                        data.add(treatmentCourse);
                    }
                }
            } else {
                while ((line = bReader.readLine()) != null) {
                    String[] tokens = line.split(delimiter);
                    if (tokens.length > 0) {
                        if (tokens[1].equals(username)) {
                            TreatmentCourse treatmentCourse = new TreatmentCourse(String.valueOf(Integer.parseInt(tokens[1])), tokens[2], tokens[3], tokens[4]);
                            data.add(treatmentCourse);
                        }
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

    private void ToDeleteRecord(String target) {
        List<String> data = new ArrayList<>();
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(Objects.requireNonNull(PatientController.class.getResource("treatmentcourse.csv")).getPath());

        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    if (!tokens[1].equals(target)) {
                        data.add(line + "\n");
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

        List<String> stringArrays = new ArrayList<>();
        stringArrays.add("username,id,treatment,start_date,end_date\n");
        stringArrays.addAll(data);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Objects.requireNonNull(NewDiagnosisController.class.getResource("treatmentcourse.csv")).getPath()))) {
            for (String stringArray : stringArrays) {
                writer.write(stringArray);
            }
            System.out.println("Data has been written to the file.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Record has been deleted successfully!");
            alert.showAndWait();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    Callback<TableColumn<TreatmentCourse, Void>, TableCell<TreatmentCourse, Void>> cellFactory = new Callback<>() {
        @Override
        public TableCell<TreatmentCourse, Void> call(final TableColumn<TreatmentCourse, Void> param) {
            return new TableCell<>() {

                private final Button btn = new Button("Delete");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        TreatmentCourse data = getTableView().getItems().get(getIndex());
                        ToDeleteRecord(data.getId());
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

    public void UserRenderData(){
        List<TreatmentCourse> data = UserReadCSV(Objects.requireNonNull(TreatmentCourseController.class.getResource("treatmentcourse.csv")).getPath());
        ObservableList<TreatmentCourse> list = FXCollections.observableArrayList(data);
        treatment_user_treatment.setCellValueFactory(new PropertyValueFactory<>("Treatment"));
        treatment_user_start_date.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        treatment_user_end_date.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
        treatment_user_table.setItems(list);
    }

    public void AdminRenderData(){
        TableColumn<TreatmentCourse, Void> colBtn = new TableColumn<>("Actions");
        List<TreatmentCourse> data = AdminReadCSV(Objects.requireNonNull(TreatmentCourseController.class.getResource("treatmentcourse.csv")).getPath());
        ObservableList<TreatmentCourse> list = FXCollections.observableArrayList(data);
        treatment_admin_treatment.setCellValueFactory(new PropertyValueFactory<>("Treatment"));
        treatment_admin_start_date.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        treatment_admin_end_date.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
        colBtn.setCellFactory(cellFactory);
        treatment_admin_table.getColumns().add(colBtn);
        treatment_admin_table.setItems(list);
    }

    public void ToAddRecord() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("newtreatmentcourse.fxml")));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
