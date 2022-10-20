package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.models.DetectTheme;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;

import java.io.File;
import java.io.IOException;

public class ChangePasswordController {
    private Account account;

    private AccountList accountList;
    private DataSource<AccountList> dataSource;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private PasswordField oldPasswordField;
    @FXML private Label successLabel;

    @FXML private Circle staffimage;
    @FXML private Label nameLabel;
    @FXML
    private AnchorPane parent;




    public void initialize(){
        account = (Account) com.github.saacsos.FXRouter.getData();
        readData();
        showUserData();
        DetectTheme detectTheme = new DetectTheme(parent,account);

    }

    private void readData() {
        dataSource = new AccountFileDataSource();
        accountList = dataSource.readData();

    }

    private void showUserData() {
        nameLabel.setText(account.getDisplayname());
        File image = new File(account.getImagePath());
        staffimage.setFill(new ImagePattern(new Image(image.toURI().toString())));
    }

    @FXML
    private void savePW(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String oldPassword = oldPasswordField.getText();

        if (!account.getUsername().equals(username)){
            successLabel.setText("ชื่อบัญชีผู้ใช้ไม่ถูกต้อง");
            successLabel.setStyle("-fx-text-fill: #f61e1e");
        }
        else if (!account.getPassword().equals(oldPassword)){
            successLabel.setText("รหัสผ่านเดิมไม่ถูกต้อง");
            successLabel.setStyle("-fx-text-fill: #f61e1e");
        }
        else if (newPassword.equals("") || confirmPassword.equals("")){
            successLabel.setText("กรอกรหัสผ่านไม่ครบ");
            successLabel.setStyle("-fx-text-fill: #f61e1e");
        } else if (!newPassword.equals(confirmPassword)){
            successLabel.setText("รหัสผ่านไม่ตรงกัน");
            successLabel.setStyle("-fx-text-fill: #f61e1e");
        } else {
            AccountList accountList = dataSource.readData();

            accountList.searchAccountByUsername(username);
            accountList.changePasswordByUsername(username,newPassword);
            dataSource.writeData(accountList,false);
            successLabel.setText("เปลี่ยนรหัสผ่านสำเร็จ");
            successLabel.setStyle("-fx-text-fill: #076b51");

        } clearPW();

    }

    public void clearPW(){
        newPasswordField.clear();
        confirmPasswordField.clear();
        usernameTextField.clear();
        oldPasswordField.clear();
    }

    @FXML
    private void handleHomeButton(ActionEvent actionEvent){
        try {
            if(account.isStaff()) com.github.saacsos.FXRouter.goTo("staff",account);
            else if (account.isAdmin()) com.github.saacsos.FXRouter.goTo("admin",account);
            else  com.github.saacsos.FXRouter.goTo("student",account);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

    }
    @FXML
    private void handleLogoutButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

    }

    @FXML
    private void handleChangePF(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("edit",account);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

    }
}
