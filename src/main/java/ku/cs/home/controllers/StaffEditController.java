package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.Account;

import java.io.IOException;

public class StaffEditController {
    Account staff;
    @FXML
    private Circle staffimage;

    @FXML private Label nameLabel;
    @FXML private ImageView homeicon;


    public void initialize(){
        staff = (Account) com.github.saacsos.FXRouter.getData();

        String url = getClass().getResource("/ku/cs/images/home.png").toExternalForm();
        homeicon.setImage(new Image(url));
        showUserData();

    }


    private void showUserData() {
        nameLabel.setText(staff.getUsername());
        String url = getClass().getResource(staff.getImagePath()).toExternalForm();
        staffimage.setFill(new ImagePattern(new Image(url)));

    }
    @FXML
    public void handleStaffButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("staff",staff);
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





}
