package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.Account;

import java.io.IOException;

public class StudentReportController {
    Account student;
    @FXML
    private Circle circle;
    @FXML
    private Label nameLabel;

    public void initialize(){
        student = (Account) com.github.saacsos.FXRouter.getData();
        showUserData();
    }
    private void showUserData() {
        nameLabel.setText(student.getUsername());
        String url = getClass().getResource(student.getImagePath()).toExternalForm();
        circle.setFill(new ImagePattern(new Image(url)));

    }

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
    public void handleStudentEditButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("studentedit");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า studentedit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    public void handleStudentHomeButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("student");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า studenthome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    public void handleStudentReportButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("studentreport");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า studentreport ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }


}

