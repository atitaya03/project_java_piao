package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.models.Complaint;
import ku.cs.models.ComplaintList;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.ComplaintFileDataSource;
import ku.cs.services.DataSource;

import java.io.File;
import java.io.IOException;

public class StudentReportController {
    private Account student;
    @FXML
    private ComboBox categoryComboBox = null;
    @FXML
    private Circle circle;
    @FXML
    private TextField titleAddTextField;
    @FXML
    private TextArea detailAddTextField;
    @FXML
    private Label nameLabel;

    private ComplaintList complaintList;
    private DataSource<ComplaintList> complaintsDataSource;

    public void initialize(){
        categoryComboBox.getItems().add("หน่วย 1");
        categoryComboBox.getItems().add("หน่วย 2");
        categoryComboBox.getItems().add("หน่วย 3");
        categoryComboBox.getItems().add("หน่วย 4");
        categoryComboBox.getItems().add("หน่วย 5");
        student = (Account) com.github.saacsos.FXRouter.getData();
        complaintsDataSource = new ComplaintFileDataSource();
        complaintList = complaintsDataSource.readData();

        showUserData();
    }
    private void showUserData() {
        nameLabel.setText(student.getDisplayname());
        File image = new File(student.getImagePath());
        circle.setFill(new ImagePattern(new Image(image.toURI().toString())));

    }

    @FXML
    public void handleLogoutButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleedit(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("studentedit",student);
        } catch (IOException e) {
            System.err.println("Cannot reach Dictionary");
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
        if(titleAddTextField.getText() != "" &&  detailAddTextField.getText() != "") {
            String category = (String) categoryComboBox.getValue();
            String title = titleAddTextField.getText();
            String detail = detailAddTextField.getText();
            Complaint c = new Complaint(category,title,detail,student.getUsername());
            c.initialCreateTime();
            complaintList.addComplaint(c);
            complaintsDataSource.writeData(complaintList, false);

            titleAddTextField.clear();
            detailAddTextField.clear();
            categoryComboBox.valueProperty().set(null);

            }
        else
        {
            System.err.println("Error");
        }

    }


}


