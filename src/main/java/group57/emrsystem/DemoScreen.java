package group57.emrsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DemoScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DemoController.class.getResource("patient-user.fxml"));
        fxmlLoader.setController(new PatientController(stage, false, "A12345"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Test Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
