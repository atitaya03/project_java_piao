package ku.cs.home.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import ku.cs.models.*;
import ku.cs.services.DataSource;
import ku.cs.services.ReportedAccountFileDataSource;
import ku.cs.services.ReportedComplaintFileDataSource;

import java.io.IOException;

public class ReportListController {
    private Account admin;
    private Account account;
    private DataSource<AccountList> dataSource;
    private AccountList accountList;
    private ReportAccount reportAccount;
    private ReportAccList reportAccList;
    private DataSource<ReportAccList> dataSourceAcc;

    private ReportComplaint reportComplaint;
    private ReportComplaintList reportComplaintList;
    private DataSource<ReportComplaintList> dataSourceComplaint;

    @FXML private ListView showReportList;
    @FXML private Label reportedLabel;
    @FXML private Label reporterLabel;
    @FXML private Label attemptLogin;
    @FXML private Label detailRequest;
//    @FXML private Label banSucceeded;
    @FXML private Label reportedComplaintLabel;
    @FXML private Pane reportAccountPane;
    @FXML private Pane reportComplaintPane;


    public void initialize(){
        admin = (Account) com.github.saacsos.FXRouter.getData();
        reportAccount = (ReportAccount) com.github.saacsos.FXRouter.getData();
        reportComplaint = (ReportComplaint ) com.github.saacsos.FXRouter.getData();

        dataSourceAcc = new ReportedAccountFileDataSource("executablefiles_csv/csv/", "reportedAccount.csv");
        dataSourceComplaint = new ReportedComplaintFileDataSource("executablefiles_csv/csv/", "reportedComplaint.csv");
        reportAccList = dataSourceAcc.readData();
        reportComplaintList = dataSourceComplaint.readData();

        reportComplaintPane.setOpacity(0);
        showAccListView();
        clearSelectedAccount();
        handleSelectedAccountListView();
        handleSelectedComplaintListView();
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
        reportAccountPane.setOpacity(1);
        reportComplaintPane.setOpacity(0);
    }

    @FXML private void handleComplaintButton(){
        showComplaintListView();
        reportAccountPane.setOpacity(0);
        reportComplaintPane.setOpacity(1);
    }
    private void handleSelectedAccountListView() {
        showReportList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<ReportAccount>() {
                    @Override
                    public void changed(ObservableValue<? extends ReportAccount> observableValue,
                                        ReportAccount oldValue, ReportAccount newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedAccount(newValue);
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
                        System.out.println(newValue + " is selected");
                        showSelectedComplaint(newValue);
                        reportComplaint = newValue;
                    }
                });

    }
    private void showSelectedAccount(ReportAccount reportAccount){
        clearSelectedAccount();
        reportedLabel.setText(reportAccount.getReportedAccountUsername());
        reporterLabel.setText(reportAccount.getReporterAccount());
        if(account.isBanned()){
            attemptLogin.setText(account.getLoginAttempt()+"");
            attemptLogin.setStyle("-fx-text-fill: #f61e1e");
            detailRequest.setText(account.getUnbanRequest());
            detailRequest.setStyle("-fx-text-fill: #f61e1e");
        }
    }

    private void showSelectedComplaint(ReportComplaint reportComplaint){
        clearSelectedComplaint();
        reportedComplaintLabel.setText(reportComplaint.getSubject());
    }
    private void clearSelectedAccount() {
        reportedLabel.setText("");
        reporterLabel.setText("");
    }





    private void clearSelectedComplaint(){
        reportedComplaintLabel.setText("");
    }

    private void clearData(){
        showReportList.getItems().clear();
    }




//    @FXML
//    public void handleBanButton(ActionEvent actionEvent){
//        admin.ban(account);
//        banSucceeded.setText("ระงับการใช้งานสำเร็จ");
//        banSucceeded.setStyle("-fx-text-fill: #f61e1e");
//        dataSource.writeData(accountList,false);
//
//
//    }
//    @FXML
//    public void handleUnBanButton(ActionEvent actionEvent){
//        if(account.isBanned()){admin.unBan(account);
//            banSucceeded.setText("คืนการใช้งานสำเร็จ");
//            banSucceeded.setStyle("-fx-text-fill: #03bd00");
//            dataSource.writeData(accountList,false);}
//
//
//    }







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
            com.github.saacsos.FXRouter.goTo("staffsignup");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าสร้างบัญชีของสตาฟไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
}
