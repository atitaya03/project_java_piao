package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    @FXML private TextField inputUsernameTextField;
    @FXML private PasswordField inputPasswordTextField;
    @FXML private PasswordField confirmPasswordTextField;
    @FXML private Label failedPassword;
    public void handleHomeButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    public void handleSignUpButton(ActionEvent actionEvent) {
        AccountList staffregis = new AccountList();
        if (!(inputPasswordTextField.getText()).equals(confirmPasswordTextField.getText()))
            failedPassword.setText("รหัสผ่านไม่ตรงกัน");
        else {
            staffregis.addAccount(new Account(inputUsernameTextField.getText(), inputPasswordTextField.getText(), "staff"));
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
