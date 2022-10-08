package ku.cs.home.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;

import java.io.File;
import java.io.IOException;

public class ReportListController {
    private Account admin;
    private Account account;
    private AccountList accountList; // ต้องทำ ReportList
    private DataSource<AccountList> dataSource;
    @FXML private ListView showReportAccList;

    public void initialize() {
        admin = (Account) com.github.saacsos.FXRouter.getData();
        account = (Account) com.github.saacsos.FXRouter.getData();

    }

    private void showAccListView() {
        showReportAccList.getItems().addAll(accountList.getAllAccount());
        showReportAccList.refresh();
    }

    private void handleSelectedReportedListView() {
        showReportAccList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Account>() {
                    @Override
                    public void changed(ObservableValue<? extends Account> observableValue,
                                        Account oldValue, Account newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedAccount(newValue);
                        account = newValue;
                    }
                });

    }

    private void showSelectedAccount(Account account) {
        clearSelectedAccount();

    }

    private void clearSelectedAccount() {
    }

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
