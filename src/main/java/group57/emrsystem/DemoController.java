package group57.emrsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DemoController implements Initializable {
    private Stage stage;

    @FXML
    private TableView<Demo> tableView;

    @FXML
    public TableColumn<Demo, String> name_col;

    @FXML
    public TableColumn<Demo, String> addr_col;

    @FXML
    public TableColumn<Demo, String> phonenum_col;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        renderData();
    }

    public DemoController(Stage stage)
    {
        this.stage = stage;
    }

    public static List<Demo> readCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Demo> data = new ArrayList<Demo>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    Demo student = new Demo(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3]);
                    data.add(student);
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

    public void launchNewWindow() throws IOException {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.start(new Stage());
    }

    public void replaceWindow() throws IOException {
        stage.close();
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.start(new Stage());
    }

    public void renderData() {
        List<Demo> data = readCSV(Objects.requireNonNull(DemoController.class.getResource("demo.csv")).getPath());
        ObservableList<Demo> list = FXCollections.observableArrayList(data);
        name_col.setCellValueFactory(new PropertyValueFactory<>("Name"));
        addr_col.setCellValueFactory(new PropertyValueFactory<>("Address"));
        phonenum_col.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        tableView.setItems(list);
    }
}
