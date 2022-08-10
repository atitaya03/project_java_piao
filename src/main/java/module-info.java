module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;


    opens ku.cs to javafx.fxml;
    exports ku.cs;
    exports ku.cs.home.controllers;
    opens ku.cs.home.controllers to javafx.fxml;

}