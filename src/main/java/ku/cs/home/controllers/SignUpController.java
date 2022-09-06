package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;

import java.io.IOException;

public class SignUpController {

    @FXML private TextField inputUsernameTextField;
    @FXML private TextField inputPasswordTextField;
    @FXML private TextField confirmPasswordTextField;

    public void handleHomeButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    public void handleSignUpButton(ActionEvent actionEvent) {
        AccountList regis = new AccountList();
        regis.addAccount(new Account(inputUsernameTextField.getText(),inputPasswordTextField.getText(),confirmPasswordTextField.getText()));
        DataSource<AccountList> write;
        write = new AccountFileDataSource("data","userData.csv");
        write.writeData(regis);
        try {
            com.github.saacsos.FXRouter.goTo("home");

        } catch (IOException e){
            System.err.println("ไปหน้าแรกไม่ได้");
        }



    }
    public void handleUploadImageButton(ActionEvent actionEvent) {

    }
}
