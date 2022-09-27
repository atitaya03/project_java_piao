package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.Account;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;


import java.io.IOException;

public class StaffController {
    Account staff;
    @FXML
    private Circle staffimage;
    @FXML private Label nameLabel;
    @FXML private Label editLabel;
    @FXML private Button test;


    public void initialize(){
        staff = (Account) com.github.saacsos.FXRouter.getData();
        showUserData();
    }
    private void showUserData() {
        nameLabel.setText(staff.getUsername());
        String url = getClass().getResource(staff.getImagePath()).toExternalForm();
        staffimage.setFill(new ImagePattern(new Image(url)));

    }
    @FXML
    public void handleHomepageButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home",staff);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleedit(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("staffedit",staff);
        } catch (IOException e) {
            System.err.println("Cannot reach Dictionary");
        }
    }
    @FXML
    public void handledetail(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("staffdetail",staff);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }



}
