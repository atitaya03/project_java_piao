package ku.cs.home.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.Account;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import ku.cs.models.Complaint;
import ku.cs.models.ComplaintList;
import ku.cs.services.ComplaintFileDataSource;
import ku.cs.services.DataSource;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StaffController {
    private Account staff;
    @FXML
    private Circle staffimage;
    @FXML private Label nameLabel;
    @FXML private Label editLabel;
    @FXML private Button test;
    @FXML private TableView<Complaint> complaintsTable;
    private ComplaintList complaintList;
    private DataSource<ComplaintList> complaintListDataSource;
    private ArrayList<Object> dataList;




    public void initialize(){
        staff = (Account) com.github.saacsos.FXRouter.getData();
        dataList = new ArrayList<>();
        dataList.add(staff);
        complaintListDataSource = new ComplaintFileDataSource();
        complaintList = complaintListDataSource.readData();

        showUserData();
        showComplaintsData();
        handleSelectedTableView();
    }
    private void showUserData() {
        nameLabel.setText(staff.getDisplayname());
        File image = new File(staff.getImagePath());
        staffimage.setFill(new ImagePattern(new Image(image.toURI().toString())));

    }
    private ComplaintList sortCategory(){
        ComplaintList sorted = new ComplaintList();
        for(Complaint c : complaintList.getAllComplaints()){
            if(c.getCategory().equals(staff.getOrganization())){
                sorted.addComplaint(c);
            }
            //TODO ADD sort status "สถานะโสด"
        }

        return sorted;
    }
    private void showComplaintsData(){
        ComplaintList sorted = sortCategory();

        ObservableList<Complaint> data = FXCollections.observableArrayList(sorted.getAllComplaints());

        complaintsTable.getColumns().clear();
        complaintsTable.setEditable(true);
        TableColumn<Complaint, String> titleColumn = new TableColumn<>("หัวข้อ");
        titleColumn.setMinWidth(100);
        titleColumn.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );
        TableColumn detailsColumn = new TableColumn("รายละเอียด");
        detailsColumn.setMinWidth(150);
        detailsColumn.setCellValueFactory(
                new PropertyValueFactory<Complaint,String>("detail")
        );
        TableColumn voteColumn = new TableColumn("โหวต");
        voteColumn.setMinWidth(50);
        voteColumn.setCellValueFactory(
                new PropertyValueFactory<Complaint,String>("voted")
        );
        TableColumn detailColumn= new TableColumn("วิธีการจัดการ");
        detailColumn.setMinWidth(150);
        detailColumn.setCellValueFactory(
                new PropertyValueFactory<Complaint,String>("management")
        );
        TableColumn statusColumn = new TableColumn("สถานะ");
        statusColumn.setMinWidth(100);
        statusColumn.setCellValueFactory(
                new PropertyValueFactory<Complaint,String>("status")
        );
        TableColumn timeColumn = new TableColumn("เวลาร้องเรียน");
        timeColumn.setMinWidth(150);
        timeColumn.setCellValueFactory(
                new PropertyValueFactory<Complaint, String>("time")
        );
        complaintsTable.setItems(data);
        complaintsTable.getColumns().addAll(titleColumn, detailsColumn, voteColumn, detailColumn, statusColumn, timeColumn);

    }
    private void handleSelectedTableView(){
        complaintsTable.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Complaint>() {
                    @Override
                    public void changed(ObservableValue<? extends Complaint> observableValue, Complaint oldValue, Complaint newValue) {
                        System.out.println(newValue + " is selected");
                        dataList.add(newValue);
                        try {
                            com.github.saacsos.FXRouter.goTo("staffdetail",dataList);
                        } catch (IOException e) {
                            System.err.println("ไปที่หน้า detail ไม่ได้");
                            System.err.println("ให้ตรวจสอบการกําหนด route");
                        }
                    }
                });

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
    public void handleedit(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("edit",staff);
        } catch (IOException e) {
            System.err.println("Cannot reach Dictionary");
        }
    }
    @FXML
    public void handledetail(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("staffdetail",staff);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleHowToUseButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("howtostaff",staff);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า howtostudent ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }



}
