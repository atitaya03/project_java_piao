package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.models.Complaint;
import ku.cs.models.ComplaintList;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.ComplaintFileDataSource;
import ku.cs.services.DataSource;

import java.io.IOException;

public class StudentReportController {
    private Account student;
    @FXML
    private ComboBox categoryComboBox;
    @FXML
    private Circle circle;
    @FXML
    private TextField titleAddTextField;
    @FXML
    private TextField detailAddTextField;
    @FXML
    private Label nameLabel;

    public void initialize(){
        //categoryComboBox.getItems().addAll("หน่วย1", "หน่วย2", "หน่วย3", "หน่วย4");
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
    public void handleSubmitButton(){
        ComplaintList complaints = new ComplaintList();
        if(titleAddTextField.getText() != "" &&  detailAddTextField.getText() != "") {
            String title = titleAddTextField.getText();
            String detail = detailAddTextField.getText();
            Complaint c = new Complaint("category",title,detail,student.getUsername());
            complaints.addComplaint(c);
            DataSource<ComplaintList> write;
            write = new ComplaintFileDataSource("executablefiles+csv/csv/","complaintData.csv");
            write.writeData(complaints);

            }
        else
        {
            System.err.println("Error");
        }

    }


}


