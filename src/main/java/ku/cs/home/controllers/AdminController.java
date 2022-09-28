package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import ku.cs.models.Account;

import java.io.IOException;

public class AdminController {
    Account admin;
    @FXML private ImageView adminicon;
    @FXML
    public void initialize(){
        admin = (Account) com.github.saacsos.FXRouter.getData();
        String url = getClass().getResource("/ku/cs/images/adminicon.png").toExternalForm();
        adminicon.setImage(new Image(url));
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
    public void handleChangePassword(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("changepassword");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าสร้างบัญชีของสตาฟไม่ได้");
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
