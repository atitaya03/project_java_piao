package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class HomeController {
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

    }
    @FXML private ImageView logo;
    @FXML private ImageView image1;

    public void initialize(){
        String url = getClass().getResource("/ku/cs/images/logo.png").toExternalForm();
        logo.setImage(new Image(url));
        String url1 = getClass().getResource("/ku/cs/images/imagehome1.jpg").toExternalForm();
        image1.setImage(new Image(url1));
    }

}
