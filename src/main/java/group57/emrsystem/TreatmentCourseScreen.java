package group57.emrsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TreatmentCourseScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TreatmentCourseController.class.getResource("treatmentcourse-admin.fxml"));
        fxmlLoader.setController(new TreatmentCourseController(stage, true, "admin"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Treatment Course");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
