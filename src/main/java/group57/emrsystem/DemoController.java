package group57.emrsystem;

import javafx.stage.Stage;

import java.io.IOException;

public class DemoController {
    private Stage stage;

    public DemoController(Stage stage)
    {
        this.stage = stage;
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
}
