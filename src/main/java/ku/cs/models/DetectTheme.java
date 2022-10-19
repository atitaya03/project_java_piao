package ku.cs.models;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class DetectTheme {
    private final String lightModePath = getClass().getResource("/ku/cs/Themes/light.css").toExternalForm();
    private final String darkModePath = getClass().getResource("/ku/cs/Themes/dark.css").toExternalForm();
    @FXML
    private AnchorPane parent;
    private Account student;

    public DetectTheme(AnchorPane parent, Account student) {
        this.parent = parent;
        this.student = student;
        detectTheme();
    }

    private void detectTheme() {
        if (student.getTheme().isLightMode()) {
            setLightMode();
        } else {
            setDarkMode();
        }
    }

    private void setLightMode(){
        parent.getStylesheets().add(lightModePath);
        parent.getStylesheets().remove(darkModePath);
    }

    private void setDarkMode(){
        parent.getStylesheets().add(darkModePath);
        parent.getStylesheets().remove(lightModePath);
    }

}
