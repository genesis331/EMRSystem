package group57.emrsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProcedureAndMedicineScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProcedureAndMedicineController.class.getResource("procedureandmedicine-admin.fxml"));
        fxmlLoader.setController(new ProcedureAndMedicineController(stage, true, "admin"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Procedure & Medicine Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
