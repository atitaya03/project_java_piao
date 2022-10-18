package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "caramelCase", 800, 600);
        configRoute();
        FXRouter.goTo("login");

    }
    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("login", packageStr+"login.fxml");
        FXRouter.when("banned", packageStr+"banned.fxml");
        FXRouter.when("credits", packageStr+"credits.fxml");
        FXRouter.when("staff", packageStr+"staff.fxml");
        FXRouter.when("student", packageStr+"student.fxml");
        FXRouter.when("admin", packageStr+"admin.fxml");
        FXRouter.when("signUp", packageStr+"signUp.fxml");
        FXRouter.when("staffsignup", packageStr+"staffsignup.fxml");
        FXRouter.when("edit", packageStr+"edit.fxml");
        FXRouter.when("staffdetail", packageStr+"staffdetail.fxml");
        FXRouter.when("studentreport", packageStr+"studentreport.fxml");
        FXRouter.when("reportindetail", packageStr+"reportindetail.fxml");
        FXRouter.when("changepassword", packageStr+"changepassword.fxml");
        FXRouter.when("reportinadmin", packageStr+"reportinadmin.fxml");
        FXRouter.when("studentdetail", packageStr+"studentdetail.fxml");
        FXRouter.when("studentselfcomplaint", packageStr+"studentselfcomplaint.fxml");
        FXRouter.when("howtostudent", packageStr+"howtousestudent.fxml");
        FXRouter.when("howtostaff", packageStr+"howtousestaff.fxml");


    }

    public static void main(String[] args) {
        launch();
    }
}
