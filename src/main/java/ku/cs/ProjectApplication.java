package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "caramelCase", 800, 600);
        configRoute();
        FXRouter.goTo("staff");
    }
    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("home", packageStr+"home.fxml");
        FXRouter.when("credits", packageStr+"credits.fxml");
        FXRouter.when("staff", packageStr+"staff.fxml");
        FXRouter.when("student", packageStr+"student.fxml");
        FXRouter.when("admin", packageStr+"admin.fxml");
        FXRouter.when("signUp", packageStr+"signUp.fxml");
        FXRouter.when("staffsignup", packageStr+"staffsignup.fxml");
    }
    public static void main(String[] args) {
        launch();
    }
}
