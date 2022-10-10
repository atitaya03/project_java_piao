package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.Account;
import ku.cs.models.Complaint;
import ku.cs.models.ComplaintList;
import ku.cs.services.ComplaintFileDataSource;
import ku.cs.services.DataSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StudentDetailController {
    private Account student;
    private Complaint complaint;

    @FXML
    private Label nameLabel,statusLabel,voteTotalLabel,dateLabel,detailInEachCategory,FeatureInEachCategoryPrompt,managementLabel;
    @FXML private Label titleLabel;
    @FXML private Label detailLabel;
    @FXML
    private Circle studentImage;
    private ComplaintList complaintList;
    private DataSource<ComplaintList> complaintListDataSource;
    private ArrayList<Object> dataList;


    public void initialize(){
        dataList = (ArrayList<Object>) com.github.saacsos.FXRouter.getData();
        student = (Account) dataList.get(0);
        complaint = (Complaint) dataList.get(1);
        complaintListDataSource = new ComplaintFileDataSource();
        complaintList = complaintListDataSource.readData();
        complaint = complaintList.getComplaint(complaint);
        showData();

    }
    private void showData() {
        nameLabel.setText(student.getDisplayname());
        File image = new File(student.getImagePath());
        studentImage.setFill(new ImagePattern(new Image(image.toURI().toString())));
        titleLabel.setText(complaint.getTitle());
        detailLabel.setText(complaint.getDetail());
        statusLabel.setText(complaint.getStatus());
        voteTotalLabel.setText(complaint.getVoted()+"");
        managementLabel.setText(complaint.getManagement());
        dateLabel.setText(complaint.getTime());
        FeatureInEachCategoryPrompt.setText(complaint.getCategoryFeature());
        detailInEachCategory.setText(complaint.getCategoryDetail());
    }


    @FXML public void handleStudentReportButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("studentreport",student);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า studentreport ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleedit(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("edit",student);
        } catch (IOException e) {
            System.err.println("Cannot reach Dictionary");
        }
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
    public void handleVoteButton(ActionEvent actionEvent) {
        complaint.vote();
        complaintListDataSource.writeData(complaintList, false);
        voteTotalLabel.setText(complaint.getVoted()+"");
    }
    @FXML
    public void handleReportComplaintButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("studentreport",student);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleStudentHomeButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("student");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า studenthome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

}
