package group57.emrsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Stage stage;
    @FXML
    public TextField usernameField;
    @FXML
    public TextField passwordField;
    @FXML
    public Button loginButton;

    public LoginController(Stage stage)
    {
        this.stage = stage;
    }

    public List<Patient> readCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Patient> data = new ArrayList<>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    Patient patient = new Patient (String.valueOf(Integer.parseInt(tokens[0])), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                    data.add(patient);
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

    private void login() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.equals("admin") && password.equals("admin")) {
            stage.close();
            PatientScreen patientScreen = new PatientScreen();
            patientScreen.hackedStart(new Stage(), true, username);
        } else {
            List<Patient> data = readCSV(Objects.requireNonNull(LoginController.class.getResource("patient.csv")).getPath());
            boolean found = false;
            for (Patient patient : data) {
                if (patient.getNationalID().equals(username)) {
                    found = true;
                }
            }
            if (!found) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Patient not found!");
                alert.showAndWait();
                usernameField.clear();
                passwordField.clear();
            } else {
                stage.close();
                PatientScreen patientScreen = new PatientScreen();
                patientScreen.hackedStart(new Stage(), false, username);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(actionEvent -> {
            try {
                login();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
