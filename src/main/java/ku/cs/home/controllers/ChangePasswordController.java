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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;

import java.io.IOException;

public class ChangePasswordController {
    Account staff;

    private AccountList accountList;
    private DataSource<AccountList> dataSource;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label successLabel;
    @FXML private ImageView homeicon;
    @FXML private Circle staffimage;
    @FXML private Label nameLabel;


    public void initialize(){
        staff = (Account) com.github.saacsos.FXRouter.getData();
        readData();
        showUserData();

        String url1 = getClass().getResource("/ku/cs/images/home.png").toExternalForm();
        homeicon.setImage(new Image(url1));


    }

    private void readData() {
        dataSource = new AccountFileDataSource();
        accountList = dataSource.readData();

    }

    private void showUserData() {
        nameLabel.setText(staff.getDisplayname());
        String url = getClass().getResource(staff.getImagePath()).toExternalForm();
        staffimage.setFill(new ImagePattern(new Image(url)));
    }

    @FXML
    private void savePW(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (newPassword.equals("") || confirmPassword.equals("")){
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
    }

    @FXML
    private void handleStaffButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("staff");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า staff ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

    }
    @FXML
    private void handleHomepageButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

    }

    @FXML
    private void handleChangePF(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("staffedit");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

    }
}
