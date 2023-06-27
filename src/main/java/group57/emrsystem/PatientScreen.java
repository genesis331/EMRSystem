package group57.emrsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource("patient-admin.fxml"));
        fxmlLoader.setController(new PatientController(stage, true, "admin"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Patient Form");
        stage.setScene(scene);
        stage.show();
    }

    public void hackedStart(Stage stage, boolean isAdmin, String username) throws IOException {
        String fxml = isAdmin ? "patient-admin.fxml" : "patient-user.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(PatientController.class.getResource(fxml));
        fxmlLoader.setController(new PatientController(stage, isAdmin, username));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Patient Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
