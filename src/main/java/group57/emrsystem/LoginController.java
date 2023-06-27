package group57.emrsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    private void login() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.equals("admin") && password.equals("admin")) {
            stage.close();
            PatientScreen patientScreen = new PatientScreen();
            patientScreen.hackedStart(new Stage(), true, username);
        } else {
            stage.close();
            PatientScreen patientScreen = new PatientScreen();
            patientScreen.hackedStart(new Stage(), false, username);
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
