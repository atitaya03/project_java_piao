package ku.cs.home.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class AdminController {
    Account admin;
    Account account;
    private DataSource<AccountList> dataSource;
    private AccountList accountList;
    @FXML
    private ImageView adminicon;
    @FXML
    private ListView<Account> showAccListView;
    @FXML
    private Label userLabel;
    @FXML
    private Label attemptLogin;
    @FXML
    private Label typeLabel;
    @FXML
    private Label logintimeLabel;
    @FXML
    private Label banSucceeded;
    @FXML
    private ImageView accountImageView;


    public void initialize() {
        admin = (Account) com.github.saacsos.FXRouter.getData();
        String url = getClass().getResource("/ku/cs/images/adminicon.png").toExternalForm();
        adminicon.setImage(new Image(url));

        dataSource = new AccountFileDataSource("executablefiles_csv/csv/", "userData.csv");
        accountList = dataSource.readData();


        showAccListView();
        clearSelectedAccount();
          handleSelectedListView();

    }

    private void showAccListView() {
        showAccListView.getItems().addAll(accountList.getAllAccount());
        showAccListView.refresh();
    }


     private void handleSelectedListView() {

        showAccListView.getSelectionModel().selectedItemProperty().addListener(
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
        banSucceeded.setText("");
        userLabel.setText(account.getUsername());
        typeLabel.setText(account.getRole());
        logintimeLabel.setText(account.getLoginTime());
        attemptLogin.setText(account.getLoginAttempt()+"");
//        accountImageView.setImage(new Image(account.getImagePath()));

    }

    private void clearSelectedAccount() {
        userLabel.setText("");
        typeLabel.setText("");
        logintimeLabel.setText("");
        banSucceeded.setText("");
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

    @FXML
    public void handleBanButton(ActionEvent actionEvent){
        admin.Ban(account);
        banSucceeded.setText("ระงับการใช้งานสำเร็จ");
        banSucceeded.setStyle("-fx-text-fill: #f61e1e");
        dataSource.writeData(accountList,false);



    }
    @FXML
    public void handleUnBanButton(ActionEvent actionEvent){
        if(account.isBaned()){admin.unBan(account);
        banSucceeded.setText("คืนการใช้งานสำเร็จ");
        banSucceeded.setStyle("-fx-text-fill: #03bd00");
        dataSource.writeData(accountList,false);}



    }
}