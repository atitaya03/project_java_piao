package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class HomeController {
    @FXML
    public void handleCreditsButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("credits");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า credits ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    public void handleStaffButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("staff");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า staff ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    public void handleStudentButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("student");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า student ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    public void handleAdminButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }


}
