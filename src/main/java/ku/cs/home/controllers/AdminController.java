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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ku.cs.ProjectApplication;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.models.ReportAccount;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class AdminController {
    private Account admin;
    private Account account;
    private DataSource<AccountList> dataSource;
    private AccountList accountList;
    private ReportAccount reportAccount;
    @FXML private ImageView adminicon;
    @FXML private ListView<Account> showAccListView;
    @FXML private Label userLabel;
    @FXML private Label attemptLogin;
    @FXML private Label typeLabel;
    @FXML private Label logintimeLabel;
    @FXML private Label banSucceeded;
    @FXML private Label attemptLoginPrompt;
    @FXML private Label detailRequestPrompt;
    @FXML private Label detailRequest;
    @FXML private Rectangle accountImageView;
    @FXML private Label orgLabel;

    @FXML private Button modeBtn;
    private boolean isLightMode = true;
    private final String lightModePath = getClass().getResource("/ku/cs/Themes/light.css").toExternalForm();
    private final String darkModePath = getClass().getResource("/ku/cs/Themes/dark.css").toExternalForm();
    @FXML private AnchorPane parent;
    @FXML private ImageView imgMode;

    public void initialize() {
        admin = (Account) com.github.saacsos.FXRouter.getData();
        account = (Account) com.github.saacsos.FXRouter.getData();

        String url = getClass().getResource("/ku/cs/images/adminicon.png").toExternalForm();
        adminicon.setImage(new Image(url));

        dataSource = new AccountFileDataSource("data/csv/", "userData.csv");
        accountList = dataSource.readData();
        accountList.sortByTime();

        showAccListView();
        clearSelectedAccount();
        handleSelectedListView();
        detectTheme();

    }

    private void detectTheme() {
        if (admin.getTheme().isLightMode()) {
            setLightMode();
        } else {
            setDarkMode();
        }
    }

    public void changeMode(ActionEvent actionevent) {
        if (admin.getTheme().isLightMode()) {
            setDarkMode();
        } else {
            setLightMode();
        }

        admin.getTheme().setLightMode(!admin.getTheme().isLightMode());

    }

    private void setLightMode(){
//        System.out.println(parent.getStylesheets());
        modeBtn.setText("Dark Mode");
        String path = getClass().getResource("/ku/cs/images/darkMode.png").toExternalForm();
        imgMode.setImage(new Image(path));
        parent.getStylesheets().add(lightModePath);
        parent.getStylesheets().remove(darkModePath);
    }

    private void setDarkMode(){

        modeBtn.setText("Light Mode");
        String path = getClass().getResource("/ku/cs/images/lightMode.png").toExternalForm();
        imgMode.setImage(new Image(path));
        parent.getStylesheets().add(darkModePath);
        parent.getStylesheets().remove(lightModePath);
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
        clearSelectedAccount();
        if(account.isBanned()){
            attemptLoginPrompt.setText("จำนวนครั้งที่พยายามเข้าสู่ระบบ :");
            attemptLoginPrompt.setStyle("-fx-text-fill: #f61e1e");
            attemptLogin.setText(account.getLoginAttempt()+"");
            attemptLogin.setStyle("-fx-text-fill: #f61e1e");
            detailRequestPrompt.setText("คำขอการคืนสิทธิ์ : ");
            detailRequestPrompt.setStyle("-fx-text-fill: #f61e1e");
            detailRequest.setText(account.getUnbanRequest());
            detailRequest.setStyle("-fx-text-fill: #f61e1e");
            }
        userLabel.setText(account.getUsername());
        typeLabel.setText(account.getRole());
        logintimeLabel.setText(account.getLoginTime());
        if (account.isStaff()) orgLabel.setText(account.getOrganization());
        File image = new File(account.getImagePath());
        accountImageView.setFill(new ImagePattern(new Image(image.toURI().toString())));


    }

    private void clearSelectedAccount() {
        userLabel.setText("");
        typeLabel.setText("");
        logintimeLabel.setText("");
        banSucceeded.setText("");
        detailRequestPrompt.setText("");
        detailRequest.setText("");
        attemptLoginPrompt.setText("");
        attemptLogin.setText("");
        orgLabel.setText("");
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
            com.github.saacsos.FXRouter.goTo("staffsignup",admin);
        } catch (IOException e) {
            System.err.println("ไปที่หน้าสร้างบัญชีของสตาฟไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleReport(){
        try {
            com.github.saacsos.FXRouter.goTo("reportinadmin",admin);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า report ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleUnBanButton(ActionEvent actionEvent){
        if(account.isBanned()){admin.unBan(account);
        banSucceeded.setText("คืนการใช้งานสำเร็จ");
        banSucceeded.setStyle("-fx-text-fill: #03bd00");
        dataSource.writeData(accountList,false);}


    }
}