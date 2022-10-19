package ku.cs.home.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.*;
import ku.cs.services.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReportInDetailController {
    private Account reporter;
    private Account student;
    @FXML
    private Label nameLabel;
    @FXML
    private Label alertTypeReport,alertDetailReport;
    @FXML private Circle studentImage;
    @FXML private ComboBox reportTypeBox;
    @FXML private ComboBox subjectBox;
    @FXML private TextArea detailTextArea;


    private String type;
    private Complaint complaint;
    private ArrayList<Object> dataList;
    private ReportAccList reportedAccList;
    private ReportComplaintList reportComplaintList;
    private ComplaintList complaintList;
    private AccountList accountList;
    private String reportTime;
    private String reportDate;
    private DataSource<ComplaintList> complaintListDataSource;
    private DataSource<AccountList> accountListDataSource;
    private DataSource<ReportAccList> reportAccListDataSource;
    private DataSource<ReportComplaintList> reportComplaintListDataSource;
    private final ObservableList<String> reportTypeBoxList = FXCollections.observableArrayList("ผู้ใช้งาน", "เนื้อหา");
    private final ObservableList<String> reportSubjectContentBoxList = FXCollections.observableArrayList("เนื้อหาไม่เหมาะสม", "เนื้อหาปลอม", "เนื้อหาล่อแหลม", "เนื้อหามีความรุนแรง", "เนื้อหาแสดงความเกลียดชัง", "อื่นๆ");
    private final ObservableList<String> reportSubjectUserBoxList = FXCollections.observableArrayList("บัญชีปลอม","แอบอ้าง","โพสเนื้อหาไม่เหมาะสม","ชื่อปลอม","อื่นๆ");


    public void initialize(){
        clear();
        dataList = (ArrayList<Object>) com.github.saacsos.FXRouter.getData();
        reporter = (Account) dataList.get(0);
        complaint = (Complaint) dataList.get(1);
        showData();
        reportTypeBox.setItems(reportTypeBoxList);
        readData();
        handleSelectType();
    }
    public void readData(){
        accountListDataSource = new AccountFileDataSource("executablefiles_csv/csv/", "userData.csv");
        accountList = accountListDataSource.readData();
        complaintListDataSource = new ComplaintFileDataSource("executablefiles_csv/csv/", "complaintData.csv");
        complaintList = complaintListDataSource.readData();
        reportAccListDataSource = new ReportedAccountFileDataSource();
        reportedAccList = reportAccListDataSource.readData();
        reportComplaintListDataSource = new ReportedComplaintFileDataSource();
        reportComplaintList = reportComplaintListDataSource.readData();

    }

    private void showData(){
        nameLabel.setText(reporter.getDisplayname());
        File image = new File(reporter.getImagePath());
        studentImage.setFill(new ImagePattern(new Image(image.toURI().toString())));
    }

    @FXML public void handleStudentComplaintButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("studentreport",reporter);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า studentreport ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("studentdetail",dataList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า reportindetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
     public void handleSelectType() {
         reportTypeBox.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 type = (String) reportTypeBox.getValue();
                 if(!type.equals("")){
                     alertTypeReport.setText("รายงาน "+type);
                     alertTypeReport.setStyle("-fx-text-fill: #03bd00");
                     if(type.equals("ผู้ใช้งาน")) subjectBox.setItems(reportSubjectUserBoxList);
                     else subjectBox.setItems(reportSubjectContentBoxList);}
                 else {alertTypeReport.setText("โปรดเลือกประเภทการรายงาน");alertTypeReport.setStyle("-fx-text-fill: #f61e1e");}
                                       };
                                   });}

    @FXML public void handleReportButton(ActionEvent actionEvent) {
        String detail = detailTextArea.getText();
        String subject = (String)subjectBox.getValue();
        if(subject.equals("")||detail.equals("")) {alertDetailReport.setText("โปรดกรอกข้อมูลให้ครบ");alertDetailReport.setStyle("-fx-text-fill: #f61e1e");}
        else{
            if(type.equals("ผู้ใช้งาน")){
                Account reported = accountList.searchAccountByUsername(complaint.getUser());
                ReportAccount reportedAccount = new ReportAccount
                        (reported.getUsername(), subject,detail,reporter.getUsername(),reportTime,reportDate);
                reportedAccount.setReportedAccount(reported);
                reportedAccList.addAccReport(reportedAccount);
                reportedAccount.initializeReportTime();
                reportAccListDataSource.writeData(reportedAccList,false);
            }
            else {
                ReportComplaint reportedComplaint = new ReportComplaint
                        (complaint.getTitle(),subject,detail,reporter.getUsername(),reportTime,reportDate);
                reportedComplaint.setComplaint(complaint);
                reportComplaintList.addComplaintReport(reportedComplaint);
                reportedComplaint.initializeReportTime();
                reportComplaintListDataSource.writeData(reportComplaintList,false);
            }
            clear();
        }

    }

    @FXML
    public void handleedit(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("edit",reporter);
        } catch (IOException e) {
            System.err.println("ไปหน้าแก้ไขโปรไฟล์ไม่ได้");
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
    public void handleStudentHomeButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("student");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าหลักไม่ได้");
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
    public void clear(){
        reportTypeBox.setValue("");
        subjectBox.setValue("");
        detailTextArea.setText("");
        alertTypeReport.setText("");
        alertDetailReport.setText("");
    }
}
