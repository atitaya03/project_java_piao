package ku.cs.home.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.models.Complaint;
import ku.cs.models.ComplaintList;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.ComplaintFileDataSource;
import ku.cs.services.DataSource;

import java.io.IOException;

public class StudentController {
    Account student;
    @FXML
    private Label nameLabel;
    @FXML private Label editLabel;
    @FXML
    private Circle circle;

    @FXML
    private TableView<Complaint> table;

    @FXML
    private TableColumn<Complaint, String> categoryTable;

    @FXML
    private TableColumn<Complaint, String> topicTable;

    @FXML
    private TableColumn<Complaint, String> voteTable;

    @FXML
    private TableColumn<Complaint, String> statusTable;

    @FXML
    private TableColumn<Complaint, String> userTable;

    private ComplaintFileDataSource complaintFileDataSource;




    public void initialize(){
        student = (Account) com.github.saacsos.FXRouter.getData();
        showUserData();

        complaintFileDataSource = new ComplaintFileDataSource("executablefiles_csv/csv/", "complaintData.csv");
        ComplaintList list = complaintFileDataSource.readData();
        showTable(list);


    }

    public void showTable(ComplaintList complaintList){
        ObservableList<Complaint> data = FXCollections.observableArrayList(complaintList.getAllAccount());
        categoryTable.setCellValueFactory(new PropertyValueFactory<>("category"));
        topicTable.setCellValueFactory(new PropertyValueFactory<>("title"));
        voteTable.setCellValueFactory(new PropertyValueFactory<>("voted"));
        statusTable.setCellValueFactory(new PropertyValueFactory<>("status"));
        userTable.setCellValueFactory(new PropertyValueFactory<>("user"));


        table.setItems(data);
    }


    @FXML
    public void handleedit(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("edit",student);
        } catch (IOException e) {
            System.err.println("Cannot reach Dictionary");
        }
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
    private void showUserData() {
        nameLabel.setText(student.getDisplayname());
        String url = getClass().getResource(student.getImagePath()).toExternalForm();
        circle.setFill(new ImagePattern(new Image(url)));

    }

    public void handleStudentHomeButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("student");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า student ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    public void handleStudentReportButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("studentreport",student);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า studentreport ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }


}




