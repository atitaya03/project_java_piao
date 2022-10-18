package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

    @FXML private Label nameLabel,statusLabel,voteTotalLabel,dateLabel,detailInEachCategory,FeatureInEachCategoryPrompt;
    @FXML private Label titleLabel;
    @FXML private Label orgLabel;
    @FXML private Label vote;
    @FXML private Circle studentImage;
    @FXML private TextArea detailTextArea;
    @FXML private TextArea managementTextArea;

    private Account student;
    private Complaint complaint;
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
        detailTextArea.setText(complaint.getDetail());
        detailTextArea.setWrapText(true);
        detailTextArea.setEditable(false);
        statusLabel.setText(complaint.getStatus());
        if (complaint.getStatus().equals("ยังไม่ดำเนินการ")){
            statusLabel.setStyle("-fx-text-fill: #f61e1e");
        } else if (complaint.getStatus().equals("อยู่ระหว่างการดำเนินการ")){
            statusLabel.setStyle("-fx-text-fill: #f5bd20");
        } else {
            statusLabel.setStyle("-fx-text-fill: #01a57a");
        }

        voteTotalLabel.setText(complaint.getVoted()+"");
        managementTextArea.setText(complaint.getManagement());
        managementTextArea.setWrapText(true);
        managementTextArea.setEditable(false);
        dateLabel.setText(complaint.getTime());
        orgLabel.setText(complaint.getCategory());
        FeatureInEachCategoryPrompt.setText(complaint.getCategoryFeature());
        detailInEachCategory.setText(complaint.getCategoryDetail());
    }


    @FXML public void handleStudentComplaintButton(ActionEvent actionEvent) {
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

    public void in(MouseEvent mouseEvent){
        vote.setStyle("-fx-text-fill: #f61e1e");
    }

    public void out(MouseEvent mouseEvent){
        vote.setStyle("-fx-text-fill: #4d4d4d");
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
    public void handleVote(MouseEvent mouseEvent) {
        complaint.vote();
        complaintListDataSource.writeData(complaintList, false);
        voteTotalLabel.setText(complaint.getVoted()+"");
    }
    @FXML
    public void handleReportInDetail(MouseEvent mouseEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("reportindetail",dataList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า reportindetail ไม่ได้");
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

    @FXML
    public void handleUserComplaintButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("studentselfcomplaint",student);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า usercomplaint ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

    }
    @FXML
    public void handleHowToUseButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("howtostudent",student);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า howtostudent ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
}

