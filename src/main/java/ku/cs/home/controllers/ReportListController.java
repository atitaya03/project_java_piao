package ku.cs.home.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ku.cs.models.*;
import ku.cs.services.*;

import java.io.IOException;

public class ReportListController {
    private Account admin;
    private Account account;

    private DataSource<AccountList> dataSource;
    private DataSource<ComplaintList> dataSourceComplaint;
    private AccountList accountList;
    private ComplaintList complaintList;
    private ReportAccount reportAccount;
    private ReportAccList reportAccList;
    private DataSource<ReportAccList> dataSourceAcc;


    private ReportComplaint reportComplaint;
    private ReportComplaintList reportComplaintList;
    private DataSource<ReportComplaintList> dataSourceReportedComplaint;

    @FXML private ListView showReportList;
    @FXML private Label reportedLabel;
    @FXML private TextArea detailReportTextArea;
    @FXML private Label reporterLabel;
    @FXML private Label reportedTypeLabel;
   @FXML private Label banSucceeded;
    @FXML private Label complaintTitleLabel;
    @FXML private Label ComplaintTypeReportLabel;
    @FXML private Label reporterComplaintLabel;
    @FXML private Label deleteComplaintLabel;
    @FXML private TextArea detailReportedTextAreaComplaint;
    @FXML private Pane reportAccountPane;
    @FXML private Pane reportComplaintPane;
    @FXML private Button delComplaintButton;
    @FXML private Button banButton;
    @FXML
    private AnchorPane parent;


    public void initialize(){
        admin = (Account) com.github.saacsos.FXRouter.getData();

        dataSource = new AccountFileDataSource();
        accountList = dataSource.readData();
        dataSourceComplaint = new ComplaintFileDataSource();
        complaintList = dataSourceComplaint.readData();

        dataSourceAcc = new ReportedAccountFileDataSource();
        dataSourceReportedComplaint = new ReportedComplaintFileDataSource();
        reportAccList = dataSourceAcc.readData();
        reportComplaintList = dataSourceReportedComplaint.readData();

        reportComplaintPane.setOpacity(0);
        showAccListView();
        handleSelectedAccountListView();
        clearSelectedAccount();
        clearSelectedComplaint();
        delComplaintButton.setVisible(false);
        DetectTheme detectTheme = new DetectTheme(parent,admin);


    }

    private void showAccListView() {
        clearData();
        showReportList.getItems().addAll(reportAccList.getAllReport());
        showReportList.refresh();
    }

    private void showComplaintListView(){
        clearData();
        showReportList.getItems().addAll(reportComplaintList.getAllReport());
        showReportList.refresh();
    }

    @FXML private void handleAccButton(){
        showAccListView();
        delComplaintButton.setDisable(true);
        delComplaintButton.setVisible(false);
        banButton.setDisable(false);
        banButton.setVisible(true);
        reportAccountPane.setOpacity(1);
        reportComplaintPane.setOpacity(0);
        handleSelectedAccountListView();


    }

    @FXML private void handleComplaintButton(){
        showComplaintListView();
        banButton.setVisible(false);
        delComplaintButton.setDisable(false);
        delComplaintButton.setVisible(true);
        reportAccountPane.setOpacity(0);
        reportComplaintPane.setOpacity(1);
        handleSelectedComplaintListView();
    }
    private void handleSelectedAccountListView() {
        showReportList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<ReportAccount>() {
                    @Override
                    public void changed(ObservableValue<? extends ReportAccount> observableValue,
                                        ReportAccount oldValue, ReportAccount newValue) {
                        System.out.println(newValue + " is selected acc"+admin.getUsername());
                        if(newValue!=null)showSelectedAccount(newValue);
                        reportAccount = newValue;
                    }
                });

    }

    private void handleSelectedComplaintListView() {
        showReportList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<ReportComplaint>() {
                    @Override
                    public void changed(ObservableValue<? extends ReportComplaint> observableValue,
                                        ReportComplaint oldValue, ReportComplaint newValue) {
                        System.out.println(newValue + " is selected complaint");
                        if(newValue!=null)showSelectedComplaint(newValue);
                        reportComplaint = newValue;
                    }
                });

    }
    private void showSelectedAccount(ReportAccount reportAccount){
        clearSelectedAccount();
        String reportedAccUsername = reportAccount.getReportedAccountUsername();
        reportAccount.setReportedAccount(accountList.searchAccountByUsername(reportedAccUsername));
        reportedLabel.setText(reportAccount.getReportedAccount().getUsername());
        reporterLabel.setText(reportAccount.getReporterAccount());
        reportedTypeLabel.setText(reportAccount.getSubject());
        detailReportTextArea.setText(reportAccount.getDetail());
        detailReportTextArea.setWrapText(true);
        detailReportTextArea.setEditable(false);

    }


    private void showSelectedComplaint(ReportComplaint reportComplaint){
        clearSelectedComplaint();
        String reportedComplaintName = reportComplaint.getReportedComplaintTitle();
        reportComplaint.setComplaint(complaintList.searchComplaintByTitle(reportedComplaintName));
        complaintTitleLabel.setText(reportComplaint.getSubject());
        reporterComplaintLabel.setText(reportComplaint.getReporterAccount());
        ComplaintTypeReportLabel.setText(reportComplaint.getSubject());
        detailReportedTextAreaComplaint.setText(reportComplaint.getDetail());
        detailReportedTextAreaComplaint.setWrapText(true);
        detailReportedTextAreaComplaint.setEditable(false);

    }
    private void clearSelectedAccount() {
        reportedLabel.setText("");
        reporterLabel.setText("");
        detailReportTextArea.clear();
        reportedTypeLabel.setText("");

    }
    private void clearSelectedComplaint(){
        complaintTitleLabel.setText("");
        reporterComplaintLabel.setText("");
        ComplaintTypeReportLabel.setText("");
        detailReportedTextAreaComplaint.clear();
        deleteComplaintLabel.setText("");
    }

    private void clearData(){
        showReportList.getItems().clear();
    }


    @FXML
    public void handleBanButton(ActionEvent actionEvent){
        if(reportAccount == null) banSucceeded.setText("โปรดเลือกหัวข้อการรายงาน");
        else if(reportAccount.getReportedAccount().isBanned()==false){
        admin.ban(reportAccount.getReportedAccount());
        banSucceeded.setText("ระงับการใช้งานสำเร็จ");
        banSucceeded.setStyle("-fx-text-fill: #f61e1e");
        reportAccList.delete(reportAccount);
            dataSource.writeData(accountList,false);
            dataSourceAcc.writeData(reportAccList,false);}
        else banSucceeded.setText("ผู้ใช้งานถูกระงับการใช้งานอยู่แล้ว");
        clearSelectedAccount();
        showAccListView();

    }
    @FXML void handleDelComplaintButton(ActionEvent actionEvent){
        if(reportComplaint == null) deleteComplaintLabel.setText("โปรดเลือกหัวข้อการรายงาน");
        else{ complaintList.delete(reportComplaint.getComplaint());
            reportComplaintList.delete(reportComplaint);
            dataSourceComplaint.writeData(complaintList,false);
            dataSourceReportedComplaint.writeData(reportComplaintList,false);
            deleteComplaintLabel.setText("ลบเรื่องร้องเรีบยสำเร็จ");
            clearSelectedComplaint();
            showComplaintListView();


    }}

    @FXML
    public void handleAccountList(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("admin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
        }
    }
    @FXML
    public void handleLogoutButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
        }
    }

    @FXML
    public void handleChangeProfile(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("edit", admin);
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเปลี่ยนรหัสไม่ได้");
        }
    }

    @FXML
    public void handleStaffSignUpButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("staffsignup",admin);
        } catch (IOException e) {
            System.err.println("ไปที่หน้าสร้างบัญชีของสตาฟไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
}
