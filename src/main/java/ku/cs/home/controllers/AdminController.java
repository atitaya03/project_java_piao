package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AdminController {
    @FXML
    public void handleHomeButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleLogoutButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleStaffSignUpButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("staffsignup");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าสร้างบัญชีของสตาฟไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
}
