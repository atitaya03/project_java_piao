package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.models.DetectTheme;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class StaffSignUpController {
    @FXML private Rectangle newStaffImage;
    @FXML private TextField inputDisplaynameTextField;
    @FXML private TextField inputUsernameTextField;
    @FXML private PasswordField inputPasswordTextField;
    @FXML private PasswordField confirmPasswordTextField;
    @FXML private ComboBox organizationComboBox;
    @FXML private Label failed;
    private File imageFile;
    @FXML private AnchorPane parent;
    private Account admin;
    private AccountList accountList;
    private DataSource<AccountList> dataSource;
    public void initialize(){
        admin = (Account) com.github.saacsos.FXRouter.getData();
        organizationComboBox.getItems().add("ความปลอดภัย");
        organizationComboBox.getItems().add("ความสะอาด");
        organizationComboBox.getItems().add("อาคารชำรุด");
        organizationComboBox.getItems().add("ถนน ทางเท้า");
        organizationComboBox.getItems().add("ยานพาหนะ");
        readData();
        DetectTheme detectTheme = new DetectTheme(parent,admin);
    }
    private void readData() {
        dataSource = new AccountFileDataSource();
        accountList = dataSource.readData();
    }

    public void handleHomeButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("admin",admin);
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
        if (displayname == "" || password == "" || confirmPass == "" || username == ""){
            failed.setText("กรอกข้อมูลให้ครบ");
            failed.setStyle("-fx-text-fill: #f61e1e");
        } else if (organization == null){
            failed.setText("โปรดเลือกหน่วยงาน");
            failed.setStyle("-fx-text-fill: #f61e1e");}
        else if (accountList.usernameIsUsed(username)) {
            failed.setText("มีชื่อผู้ใช้บัญชีนี้แล้ว");
            failed.setStyle("-fx-text-fill: #f61e1e");}
        else if (!(password).equals(confirmPass)) {
            failed.setText("รหัสผ่านไม่ตรงกัน");
            failed.setStyle("-fx-text-fill: #f61e1e");}
        else {
            Account newStaff = new Account(displayname,username, password, "staff",organization);
            staffregis.addAccount(newStaff);
            String imageFilePath;
            if(imageFile != null){
                System.out.println("image not null");
                File tempImagePNG = new File("data/profileUsers"+ File.separator+ "temp.png");
                String staffImage = username+".png";
                File renameImage = new File("data/profileUsers" + File.separator + staffImage);
                if (tempImagePNG.renameTo(renameImage)) {
                    System.out.println(renameImage.getPath());
                    imageFilePath = "data/profileUsers" + File.separator +staffImage;
                    newStaff.setImagePath(imageFilePath);
                }
            }
            DataSource<AccountList> write;
            write = new AccountFileDataSource("data/csv/", "userData.csv");
            write.writeData(staffregis,true);
            try {
                com.github.saacsos.FXRouter.goTo("admin");
            } catch (IOException e) {
                System.err.println("ไปหน้าแรกไม่ได้");
            }

        }
    }
    public void handleUploadImageButton(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        //***can upload only image type file***
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        imageFile = fileChooser.showOpenDialog(null);


        if(imageFile != null){
            try {
                String imagePath = imageFile.getAbsolutePath();
                File tempImagePNG = new File("data/profileUsers"+ File.separator+ "temp.png");
                Path pathOut = (Path) Paths.get(tempImagePNG.getAbsolutePath());
                Files.copy(imageFile.toPath(), pathOut, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(imagePath);
                newStaffImage.setFill(new ImagePattern(new Image(tempImagePNG.toURI().toString())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.err.println("Can't upload image");
        }
        /*TODO set image url in changeProfileButton
            add newProfile image in to resource instead of using absolute path*/

    }
}
