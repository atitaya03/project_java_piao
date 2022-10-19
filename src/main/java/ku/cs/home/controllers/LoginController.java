package ku.cs.home.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import ku.cs.ProjectApplication;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Effect;

import java.io.IOException;

public class LoginController {
//    @FXML private ImageView logo;
    @FXML private ImageView image1;
    @FXML private TextField inputUsernameTextField;


    @FXML private PasswordField inputPasswordTextField;
    @FXML private Label failedLabel;

    private DataSource<AccountList> dataSource = new AccountFileDataSource();
    private AccountList accountList = dataSource.readData();

    private boolean isLightMode;
    private final String lightModePath = getClass().getResource("/ku/cs/Themes/light.css").toExternalForm();
    private final String darkModePath = getClass().getResource("/ku/cs/Themes/dark.css").toExternalForm();

    @FXML
    private AnchorPane parent;






    public void initialize(){
//        String url = getClass().getResource("/ku/cs/images/logo.png").toExternalForm();
//        logo.setImage(new Image(url));
        isLightMode = true;
        if (FXRouter.getData() != null) {
            isLightMode = (boolean) FXRouter.getData();
        }

        String url1 = getClass().getResource("/ku/cs/images/1.png").toExternalForm();
        image1.setImage(new Image(url1));
//        String lightModePath = getClass().getResource("/ku/cs/Themes/light.css").toExternalForm();
//        ProjectApplication.setUserAgentStylesheet(lightModePath);
        detectTheme();


    }
    private void detectTheme() {
            if (isLightMode) {
                setLightMode();
            } else {
                setDarkMode();
            }
        }


    private void setLightMode(){
//        System.out.println(parent.getStylesheets());
        parent.getStylesheets().add(lightModePath);
        parent.getStylesheets().remove(darkModePath);
    }

    private void setDarkMode(){
        parent.getStylesheets().add(darkModePath);
        parent.getStylesheets().remove(lightModePath);
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
        Account user = accountList.searchAccountByUsername(username);
        if(username == "" && password == ""){
            failedLabel.setText("โปรดกรอกข้อมูลให้ครบ");
            failedLabel.setStyle("-fx-text-fill: #f61e1e");

        } else if (!accountList.loginSuccess(username,password)){
            failedLabel.setText("ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง");
            failedLabel.setStyle("-fx-text-fill: #f61e1e");
        } else if (user.isBanned()) {
            user.updateLoginAttempt();
            dataSource.writeData(accountList, false);
            try {
                FXRouter.goTo("banned");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            try {
                user.initialLoginTime();
                if (user.isStudent())
                    com.github.saacsos.FXRouter.goTo("student", user);
                else if (user.isStaff())
                    com.github.saacsos.FXRouter.goTo("staff", user);
                else if (user.isAdmin())
                    com.github.saacsos.FXRouter.goTo("admin", user);
                dataSource.writeData(accountList, false);

            } catch (IOException e) {
                System.err.println("ไปที่หน้าหลัก ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนด route");
                e.printStackTrace();
            }
        }



    }


}
