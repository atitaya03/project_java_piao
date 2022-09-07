package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.Account;

import java.io.IOException;

public class StudentEditController {
    Account student;
    @FXML
    private Circle circle;
    @FXML
    private Label nameLabel;

    public void initialize(){
        student = (Account) com.github.saacsos.FXRouter.getData();
        String url = getClass().getResource("/ku/cs/images/billkin1.jpg").toExternalForm();
        circle.setFill(new ImagePattern(new Image(url)));
        showUserData();
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
    private void showUserData() {
        nameLabel.setText(student.getUsername());

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
