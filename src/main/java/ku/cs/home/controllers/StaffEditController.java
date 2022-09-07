package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class StaffEditController {
    @FXML
    public void handleStaffButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("staff");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า staff ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleHomepageButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleStaffDetailButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("staffdetail");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า staffdetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    private Circle staffimage;


}
