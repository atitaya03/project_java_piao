package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class CreditsController {
    @FXML
    public void handleLogoutButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML private ImageView nokkokImage;
    @FXML private ImageView atitayaImage;

    @FXML private ImageView lemonImage;
    @FXML private ImageView chanittaImage;

    public void initialize(){
        String url = getClass().getResource("/ku/cs/images/nokkok.jpg").toExternalForm();
        nokkokImage.setImage(new Image(url));
        String url1 = getClass().getResource("/ku/cs/images/atitaya.jpg").toExternalForm();
        atitayaImage.setImage(new Image(url1));
        String url2 = getClass().getResource("/ku/cs/images/lemon.jpg").toExternalForm();
        lemonImage.setImage(new Image(url2));
        String url3 = getClass().getResource("/ku/cs/images/chanitta.jpg").toExternalForm();
        chanittaImage.setImage(new Image(url3));
    }
}
