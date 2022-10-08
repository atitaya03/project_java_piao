package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import ku.cs.models.Account;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class EditController {
    private Account account;
    @FXML private Circle staffimage;
    @FXML private Circle newStaffImage;

    @FXML private Label nameLabel;
    @FXML private ImageView homeicon;
    @FXML private Label usernameLabel;
    @FXML private Label displayLabel;
    @FXML private Label roleLabel;
    @FXML private Label orgLabel;


    public void initialize(){
        account = (Account) com.github.saacsos.FXRouter.getData();

        String url = getClass().getResource("/ku/cs/images/home.png").toExternalForm();
        homeicon.setImage(new Image(url));

        showUserData();

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
        File imageFile = fileChooser.showOpenDialog(null);


        if(imageFile != null){
            try {
                String imagePath = imageFile.getAbsolutePath();
                File tempImagePNG = new File("executablefiles_csv/profileUsers"+ File.separator+ "temp.png");
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
        /*TODO set image url in changeProfileButton
            add newProfile image in to resource instead of using absolute path*/

    }


}
