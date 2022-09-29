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

public class StaffEditController {
    Account staff;
    @FXML private Circle staffimage;
    @FXML private Circle newStaffImage;

    @FXML private Label nameLabel;
    @FXML private ImageView homeicon;


    public void initialize(){
        staff = (Account) com.github.saacsos.FXRouter.getData();

        String url = getClass().getResource("/ku/cs/images/home.png").toExternalForm();
        homeicon.setImage(new Image(url));
        showUserData();

    }

    private void showUserData() {
        nameLabel.setText(staff.getDisplayname());
        String url = getClass().getResource(staff.getImagePath()).toExternalForm();
        staffimage.setFill(new ImagePattern(new Image(url)));

    }
    @FXML
    public void handleStaffButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("staff",staff);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า staff ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleHomepageButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าแรกไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleChangePW(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("changepassword",staff);
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
            String imagePath = imageFile.getAbsolutePath();
            newStaffImage.setFill(new ImagePattern(new Image(imagePath)));
        }else{
            System.out.println("file is not valid!!");
        }

        /*TODO set image url in changeProfileButton
            add newProfile image in to resource instead of using absolute path*/


    }




}
