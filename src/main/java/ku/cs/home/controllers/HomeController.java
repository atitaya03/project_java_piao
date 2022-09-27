package ku.cs.home.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Effect;

import java.io.IOException;

public class HomeController {
    @FXML private ImageView logo;
    @FXML private ImageView image1;
    @FXML private TextField inputUsernameTextField;
    @FXML private PasswordField inputPasswordTextField;
    @FXML private Label failedLabel;

    @FXML private ComboBox<String> themeComboBox;

    @FXML private AnchorPane root;

    private Effect effect;

    private DataSource<AccountList> dataSource = new AccountFileDataSource();
    private AccountList accountList = dataSource.readData();



    public void initialize(){
        effect = new Effect();
        effect.fadeInPage(root);
        String url = getClass().getResource("/ku/cs/images/logo.png").toExternalForm();
        logo.setImage(new Image(url));
        String url1 = getClass().getResource("/ku/cs/images/imagehome1.jpg").toExternalForm();
        image1.setImage(new Image(url1));
        initializeComboBox();
    }

    private void initializeComboBox() {
        themeComboBox.getItems().addAll("เริ่มต้น", "คริสต์มาส", "พาสเทล", "ชมพูฟลามิงโก");
        themeComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue.equals("เริ่มต้น")) FXRouter.setCssStylePath("default.css");
                else if (newValue.equals("คริสต์มาส")) FXRouter.setCssStylePath("christmas.css");
                else if (newValue.equals("พาสเทล")) FXRouter.setCssStylePath("pastel.css");
                else if (newValue.equals("ชมพูฟลามิงโก")) FXRouter.setCssStylePath("pink.css");
                refreshPage();
            }
        });
    }

    private void refreshPage() {
        dataSource.writeData(accountList);
        effect.changePage(root, "home");
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
            failedLabel.setStyle("-fx-text-fill: #f61e1e");
        } else
        try {
            Account user = accountList.searchAccountByUsername(username);
            if((user.getRole()).equals("student"))
            com.github.saacsos.FXRouter.goTo("student",user);
            else if((user.getRole()).equals("staff"))
                com.github.saacsos.FXRouter.goTo("staff",user);
            else if((user.getRole()).equals("admin"))
                com.github.saacsos.FXRouter.goTo("admin",user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้าหลัก ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }




    }


}
