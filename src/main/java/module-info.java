module group57.emrsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens group57.emrsystem to javafx.fxml;
    exports group57.emrsystem;
}