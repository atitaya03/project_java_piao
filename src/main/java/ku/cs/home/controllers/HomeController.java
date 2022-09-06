package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.AccountList;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;

import java.io.IOException;

public class HomeController {
    @FXML private ImageView logo;
    @FXML private ImageView image1;
    @FXML private TextField inputUsernameTextField;
    @FXML private TextField inputPasswordTextField;
    @FXML private Label failedLabel;

    private DataSource<AccountList> dataSource = new AccountFileDataSource();
    private AccountList accountList = dataSource.readData();


    public void initialize(){
        String url = getClass().getResource("/ku/cs/images/logo.png").toExternalForm();
        logo.setImage(new Image(url));
        String url1 = getClass().getResource("/ku/cs/images/imagehome1.jpg").toExternalForm();
        image1.setImage(new Image(url1));
    }
    @FXML
    public void handleCreditsButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("credits");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า credits ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }


    public void handleSignUpButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("signUp");
        } catch (IOException e) {
            System.err.println("ไปทีหน้า signUp ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    public void handleSignInButton(ActionEvent actionEvent){
        String username = inputUsernameTextField.getText();
        String password = inputPasswordTextField.getText();
        if (!accountList.loginSuccess(username,password)){
            failedLabel.setText("ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง");
        } else
        try {
            dataSource.writeData(accountList);
            com.github.saacsos.FXRouter.goTo("staff");
        } catch (IOException e) {
            System.err.println("ไปทีหน้า signUp ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }


    }


}
