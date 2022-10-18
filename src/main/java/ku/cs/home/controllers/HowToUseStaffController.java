package ku.cs.home.controllers;

import javafx.fxml.FXML;
import ku.cs.models.Account;

import java.io.IOException;

public class HowToUseStaffController {
    private Account staff = (Account) com.github.saacsos.FXRouter.getData();
    @FXML
    public void handleBackButton() throws IOException {
        try {
            com.github.saacsos.FXRouter.goTo("staff", staff);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า หลัก ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
