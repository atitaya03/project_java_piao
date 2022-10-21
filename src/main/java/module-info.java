module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ku.cs to javafx.fxml;
    exports ku.cs;
    exports ku.cs.home.controllers;
    opens ku.cs.home.controllers to javafx.fxml;

    exports ku.cs.models;
    opens ku.cs.models to javafx.fxml;

}