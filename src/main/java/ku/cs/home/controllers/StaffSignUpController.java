package ku.cs.home.controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

public class StaffSignUpController {
    public void handleHomeButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    public void handleSignUpButton(ActionEvent actionEvent) {

    }
    public void handleUploadImageButton(ActionEvent actionEvent) {

    }
}
