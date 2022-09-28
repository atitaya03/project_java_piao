package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;

public class ChangePasswordController {
    private Account account;
    private AccountList accountList;
    private DataSource<AccountList> dataSource;
    @FXML
    private TextField usernameTextField;
    @FXML private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML private Button savePWButton;
    @FXML private Label successLabel;

    public void initialize(){
        readData();
    }

    private void readData() {
        dataSource = new AccountFileDataSource();
        accountList = dataSource.readData();
    }
    @FXML
    private void savePW(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (newPassword.equals("") || confirmPassword.equals("")){
            successLabel.setText("กรอกรหัสผ่านไม่ครบ");
        } else if (!newPassword.equals(confirmPassword)){
            successLabel.setText("รหัสผ่านไม่ตรงกัน");
        } else {
            AccountList accountList = dataSource.readData();

            accountList.searchAccountByUsername(username);
            accountList.changePasswordByUsername(username,newPassword);
            dataSource.writeData(accountList,false);
            successLabel.setText("เปลี่ยนรหัสผ่านสำเร็จ");

        } clearPW();

    }

    public void clearPW(){
        newPasswordField.clear();
        confirmPasswordField.clear();
    }
}
