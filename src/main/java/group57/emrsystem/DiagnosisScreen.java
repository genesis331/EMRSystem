package group57.emrsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DiagnosisScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DiagnosisController.class.getResource("diagnosis-admin.fxml"));
        DiagnosisController controller = new DiagnosisController(stage, true, "admin");
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Diagnosis Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
