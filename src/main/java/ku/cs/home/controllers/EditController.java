package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.models.DetectTheme;
import ku.cs.models.Theme;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Parse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class EditController {
    private Account account;
    private AccountList accountList;
    @FXML private Circle staffimage;
    @FXML private Circle newStaffImage;

    @FXML private Label nameLabel;

    @FXML private Label usernameLabel;
    @FXML private Label displayLabel;
    @FXML private Label roleLabel;
    @FXML private Label orgLabel;


    @FXML
    private AnchorPane parent;
    private File imageFile;
    private DataSource<AccountList> dataSource;




    public void initialize(){
        account = (Account) com.github.saacsos.FXRouter.getData();
        dataSource = new AccountFileDataSource();
        accountList = dataSource.readData();

        showUserData();
        DetectTheme detectTheme = new DetectTheme(parent,account);

    }


    private void showUserData() {
        nameLabel.setText(account.getDisplayname());
        File image = new File(account.getImagePath());
        staffimage.setFill(new ImagePattern(new Image(image.toURI().toString())));

        File biggercircle = new File(account.getImagePath());
        newStaffImage.setFill(new ImagePattern(new Image(biggercircle.toURI().toString())));

        usernameLabel.setText(account.getUsername());
        displayLabel.setText(account.getDisplayname());
        roleLabel.setText(account.getRole());

        if (account.isStaff()) orgLabel.setText(account.getOrganization());


    }


    @FXML
    public void handleHomeButton(ActionEvent actionEvent) {
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
    public void handleLogoutButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าแรกไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleChangePW(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("changepassword",account);
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเปลี่ยนรหัสไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleUploadImageButton(ActionEvent actionEvent){
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
        } else {
            System.err.println("Can't upload image");
        }


    }
    @FXML
    public void handleEditButton(ActionEvent actionEvent){
        String imageFilePath;
        if(imageFile != null){
            File tempImagePNG = new File("data/profileUsers"+ File.separator+ "temp.png");
            String imagename = account.getUsername()+".png";
            File renameImage = new File("data/profileUsers" + File.separator + imagename);
            if (tempImagePNG.renameTo(renameImage)) {
                System.out.println(renameImage.getPath());
                imageFilePath = "data/profileUsers" + File.separator +imagename;
                accountList.searchAccountByUsername(account.getUsername()).setImagePath(imageFilePath);
            }

        }
        DataSource<AccountList> write;
        write = new AccountFileDataSource("data/csv/", "userData.csv");
        write.writeData(accountList,false);
        accountList = dataSource.readData();
        account = accountList.searchAccountByUsername(account.getUsername());
        try {
            com.github.saacsos.FXRouter.goTo("edit",account);
        } catch (IOException e) {
            System.err.println("ไปหน้าแรกไม่ได้");
        }
    }



}
