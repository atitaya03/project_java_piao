package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.DataSource;

import java.io.IOException;

public class BannedController {
    @FXML
    private Label statusLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField detailTextField;

    private DataSource<AccountList> dataSource = new AccountFileDataSource(); ;
    private AccountList accountList = dataSource.readData();;


    public void initialize() {


    }

    @FXML
    private void handleConfirmButton(ActionEvent actionEvent){
        String username = usernameTextField.getText();
        String request = detailTextField.getText();
        Account banned = accountList.searchAccountByUsername(username);
        if(banned.isBanned()&&request!=null)
        {

            banned.unBanRequest(request);
            dataSource.writeData(accountList,false);
            statusLabel.setText("     ส่งคำขอสำเร็จ");
            statusLabel.setStyle("-fx-text-fill: #03bd00");
            clear();}
        else if (request==null) {
            statusLabel.setText("    โปรดกรอกรายละเอียด");
            statusLabel.setStyle("-fx-text-fill: #f61e1e");}

        else {statusLabel.setText("    ชื่อบัญชีไม่ถูกต้อง");
        statusLabel.setStyle("-fx-text-fill: #f61e1e");}}


    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที7หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    private void clear() {
        detailTextField.clear();
        usernameTextField.clear();

    }
    }


