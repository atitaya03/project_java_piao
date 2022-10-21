package ku.cs.home.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.*;
import ku.cs.services.ComplaintFileDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Parse;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StudentComplaintController {
    private Account student;
    @FXML
    private ComboBox categoryComboBox = null;
    @FXML
    private Circle circle;
    @FXML
    private TextField titleAddTextField;
    @FXML
    private TextArea detailAddTextField;
    @FXML
    private TextField detailFeatureTextField;
    @FXML
    private Label nameLabel;
    @FXML
    private Label categoryFeaturePrompt;
    @FXML
    private Label aleartLabel;

    private String category;
    private Parse parse;
    private boolean theme;
    @FXML
    private AnchorPane parent;

    private ComplaintList complaintList;
    private DataSource<ComplaintList> complaintsDataSource;

    public void initialize(){
        student = (Account) com.github.saacsos.FXRouter.getData();
       // parse.showAllObject();
//        theme = (Boolean) parse.getObject("theme");
//        student = (Account) parse.getObject("student");
//        parse.add("theme", theme);


        categoryComboBox.getItems().add("ความปลอดภัย");
        categoryComboBox.getItems().add("ความสะอาด");
        categoryComboBox.getItems().add("อาคารชำรุด");
        categoryComboBox.getItems().add("ถนน ทางเท้า");
        categoryComboBox.getItems().add("ยานพาหนะ");

        complaintsDataSource = new ComplaintFileDataSource();
        complaintList = complaintsDataSource.readData();
        detailAddTextField.setWrapText(true);
        showUserData();
        handleChoose();
        DetectTheme detectTheme = new DetectTheme(parent,student);


    }


    private void showUserData() {
        nameLabel.setText(student.getDisplayname());
        File image = new File(student.getImagePath());
        circle.setFill(new ImagePattern(new Image(image.toURI().toString())));
    }

    @FXML
    public void handleLogoutButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleedit(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("edit",student);
        } catch (IOException e) {
            System.err.println("Cannot reach Dictionary");
        }
    }



    @FXML

    public void handleStudentHomeButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("student",student);
            //parse.showAllObject();
        } catch (IOException e) {
            System.err.println("ไปที่หน้า studenthome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    public void handleStudentReportButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("studentreport");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า studentreport ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    public void handleChoose(){
        categoryComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                category = (String) categoryComboBox.getValue();
                if(category == null){
                    System.err.println("category is null");
                }
                else if(category!=""){
                    aleartLabel.setText("เลือกหมวดหมู่ "+category);
                    aleartLabel.setStyle("-fx-text-fill: #03bd00");
                    if(category.equals("ความสะอาด")||category.equals("ความปลอดภัย")||category.equals("ถนน ทางเท้า")) categoryFeaturePrompt.setText("สถานที่");
                    else if (category.equals("ยานพาหนะ")) categoryFeaturePrompt.setText("ประเภทรถ");
                    else if (category.equals("อาคารชำรุด")) categoryFeaturePrompt.setText("อาคาร");
                    else categoryFeaturePrompt.setText("อื่นๆ");}
                else {aleartLabel.setText("โปรดเลือกหมวดหมู่");aleartLabel.setStyle("-fx-text-fill: #f61e1e");}

            }});}
    public void clear(){
        titleAddTextField.clear();
        detailAddTextField.clear();
        categoryComboBox.valueProperty().set(null);
        detailFeatureTextField.clear();
        aleartLabel.setText("");
    }
    public void handleSubmitButton(){
        if(titleAddTextField.getText() != "" &&  detailAddTextField.getText() != ""&& category!= ""&&detailFeatureTextField.getText()!="") {
            String title = titleAddTextField.getText();
            String detail = detailAddTextField.getText();
            String detailFeature = detailFeatureTextField.getText();
            Complaint c = new Complaint(category,title,detail,student.getUsername(),detailFeature);
            c.initialCreateTime();
            complaintList.addComplaint(c);
            complaintsDataSource.writeData(complaintList, false);

            clear();

            }
        else {
            aleartLabel.setText("โปรดกรอกรายละเอียดให้ครบ");
            aleartLabel.setStyle("-fx-text-fill: #f61e1e");
        }

    }

    @FXML
    public void handleUserComplaintButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("studentselfcomplaint",student);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า usercomplaint ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

    }
    @FXML public void handleHowToUseButton(ActionEvent actionEvent){
        try {
            Desktop.getDesktop().open(new File("data" + File.separator + "manual.pdf"));
        } catch (IOException e) {
            System.out.println("Cannot open manual.pdf");
            e.printStackTrace();
        }
    }

}


