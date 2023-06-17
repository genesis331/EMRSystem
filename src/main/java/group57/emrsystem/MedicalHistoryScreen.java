package group57.emrsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MedicalHistoryScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MedicalHistoryController.class.getResource("medicalhistory-admin.fxml"));
        fxmlLoader.setController(new MedicalHistoryController(stage));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Medical History Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
