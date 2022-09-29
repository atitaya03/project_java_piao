package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;

import java.io.IOException;

public class StaffSignUpController {
    @FXML private  TextField inputDisplaynameTextField;
    @FXML private TextField inputUsernameTextField;
    @FXML private PasswordField inputPasswordTextField;
    @FXML private PasswordField confirmPasswordTextField;
    @FXML private ComboBox organizationComboBox;
    @FXML private Label failed;
    private AccountList accountList;
    private DataSource<AccountList> dataSource;
    public void initialize(){
        organizationComboBox.getItems().add("หน่วย 1");
        organizationComboBox.getItems().add("หน่วย 2");
        organizationComboBox.getItems().add("หน่วย 3");
        organizationComboBox.getItems().add("หน่วย 4");
        organizationComboBox.getItems().add("หน่วย 5");
        readData();
    }
    private void readData() {
        dataSource = new AccountFileDataSource();
        accountList = dataSource.readData();
    }

    public void handleHomeButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    public void handleSignUpButton(ActionEvent actionEvent) {
        String organization = (String) organizationComboBox.getValue();
        AccountList staffregis = new AccountList();
        String password = inputPasswordTextField.getText();
        String displayname = inputDisplaynameTextField.getText();
        String confirmPass = confirmPasswordTextField.getText();
        String username = inputUsernameTextField.getText();
        if (accountList.usernameIsUsed(username))
        {failed.setText("มีชื่อผู้ใช้บัญชีนี้แล้ว");
            failed.setStyle("-fx-text-fill: #f61e1e");}
        else if (!(password).equals(confirmPass))
        {failed.setText("รหัสผ่านไม่ตรงกัน");
            failed.setStyle("-fx-text-fill: #f61e1e");}
        else {
            staffregis.addAccount(new Account(displayname,username, password, "staff",organization));
            DataSource<AccountList> write;
            write = new AccountFileDataSource("executablefiles_csv/csv/", "userData.csv");
            write.writeData(staffregis,true);
            try {
                com.github.saacsos.FXRouter.goTo("admin");
            } catch (IOException e) {
                System.err.println("ไปหน้าแรกไม่ได้");
            }

        }
    }
    public void handleUploadImageButton(ActionEvent actionEvent) {

    }
}
