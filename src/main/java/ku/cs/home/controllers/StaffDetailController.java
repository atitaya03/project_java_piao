package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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

public class StaffDetailController {
    private Account staff;
    @FXML
    private Circle staffimage;
    private Complaint complaint;
    private ComplaintList complaintList;
    private DataSource<ComplaintList> complaintListDataSource;

    @FXML private Label nameLabel;
    @FXML private Label statusLabel;
    @FXML private Label titleLabel;
    @FXML private Label detailLabel;
//    @FXML private Label timeLabel;
    @FXML private Label orgLabel;
    @FXML private TextArea managementTextArea;
    @FXML private ComboBox<String> statusComboBox;


    private ArrayList<Object> dataList;


    public void initialize(){
        dataList = new ArrayList<>();
        dataList = (ArrayList<Object>) com.github.saacsos.FXRouter.getData();
        staff = (Account) dataList.get(0);
        complaint = (Complaint) dataList.get(1);

        complaintListDataSource = new ComplaintFileDataSource();
        complaintList = complaintListDataSource.readData();
        complaint = complaintList.getComplaint(complaint);

        System.out.println(complaint);
        managementTextArea.setWrapText(true);
        statusComboBox.getItems().addAll("ยังไม่ดำเนินการ","อยู่ระหว่างการดำเนินการ","ดำเนินการเสร็จสิ้น");
        showComplaint();
        showUserData();
    }
    private void showUserData() {
        nameLabel.setText(staff.getDisplayname());
        File image = new File(staff.getImagePath());
        staffimage.setFill(new ImagePattern(new Image(image.toURI().toString())));

    }
    private void showComplaint(){
        titleLabel.setText(complaint.getTitle());
        detailLabel.setText(complaint.getDetail());
        statusLabel.setText(complaint.getStatus());
        if (complaint.getStatus().equals("ยังไม่ดำเนินการ")){
            statusLabel.setStyle("-fx-text-fill: #f61e1e");
        } else if (complaint.getStatus().equals("อยู่ระหว่างการดำเนินการ")){
            statusLabel.setStyle("-fx-text-fill: #f5bd20");
        } else {
            statusLabel.setStyle("-fx-text-fill: #01a57a");
        }
        orgLabel.setText(complaint.getCategory());
//        timeLabel.setText(complaint.getTime());
    }
    @FXML
    public void handleStaffButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("staff",staff);
        } catch (IOException e) {
            System.err.println("ไปที่หน้าหลักไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleLogoutButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าแรกไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleedit(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("staffedit",staff);
        } catch (IOException e) {
            System.err.println("ไปหน้าแก้ไขโปรไฟล์ไม่ได้");
        }
    }
    @FXML
    public void handleManagementButton(ActionEvent actionEvent){
        String management = managementTextArea.getText();
        String status = statusComboBox.getValue();
        if(status == null){
            System.out.println("please select status first");
            status = complaint.getStatus();
        }
        if(management == ""){
            complaint.setManagement("-");
        }else{
        complaint.setManagement(management);}

        complaint.setStatus(status);
        complaintListDataSource.writeData(complaintList, false);

        managementTextArea.clear();
        statusComboBox.valueProperty().set(null);
        try {
            com.github.saacsos.FXRouter.goTo("staff",staff);
        } catch (IOException e) {
            System.err.println("ไปที่หน้าหลักไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

    }



}
