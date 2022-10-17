package ku.cs.home.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.ProjectApplication;
import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.models.Complaint;
import ku.cs.models.ComplaintList;
import ku.cs.models.*;
import ku.cs.services.AccountFileDataSource;
import ku.cs.services.ComplaintFileDataSource;
import ku.cs.services.DataSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StudentController {
    private Account student;
    @FXML
    private Label nameLabel;
    @FXML private Label editLabel;
    @FXML
    private Circle circle;
    @FXML private ComboBox sortByCategoryBox;
    @FXML private ComboBox sortByStatusBox;
    @FXML private TextField minTextField;
    @FXML private TextField maxTextField;


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
    @FXML
    private TableColumn<Complaint, String> timeTable;
    @FXML
    private AnchorPane parent;

    private ComplaintFileDataSource complaintFileDataSource;
    private ComplaintList list;
    private ArrayList<Object> dataList;
    private ComplaintFilterer filterer;
    private final ObservableList<String> sortByCategoryBoxList = FXCollections.observableArrayList("ALL","ความปลอดภัย", "ความสะอาด","อาคารชำรุด","ถนน ทางเท้า","ยานพาหนะ");
    private final ObservableList<String> sortByStatusList = FXCollections.observableArrayList("ALL","ยังไม่ดำเนินการ","อยู่ระหว่างการดำเนินการ","ดำเนินการเสร็จสิ้น");

    private boolean isLightMode = true;
    private final String lightModePath = getClass().getResource("/ku/cs/Themes/light.css").toExternalForm();
    private final String darkModePath = getClass().getResource("/ku/cs/Themes/dark.css").toExternalForm();





    public void initialize(){
        student = (Account) com.github.saacsos.FXRouter.getData();
        dataList = new ArrayList<>();
        showUserData();
        dataList.add(student);
        filterer = new ComplaintFilterer();
        complaintFileDataSource = new ComplaintFileDataSource("executablefiles_csv/csv/", "complaintData.csv");
         list = complaintFileDataSource.readData();
        sortByCategoryBox.setItems(sortByCategoryBoxList);
        sortByStatusBox.setItems(sortByStatusList);
        showTable(list);
        handleSelectedTableView();
        detectTheme();
        handleSortByCategory();
        handleSortByStatus();
        


    }
    @FXML private void handleSortByVoteButton(){
        //
    }

    private void handleSortByCategory(){
        sortByCategoryBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(sortByCategoryBox.getValue());
                if(((String)(sortByCategoryBox.getValue())).equals("ALL")){filterer.setCategory(null);}
                else if(sortByCategoryBox.getValue()!=null){
                    filterer.setCategory((String) sortByCategoryBox.getValue());
                }
                else {filterer.setCategory(null);}
                ComplaintList filtered = list.filterBy(filterer);
                showTable(filtered);
            }

        });
    }
    private void handleSortByStatus(){
        sortByStatusBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(sortByStatusBox.getValue());
                if(((String)(sortByStatusBox.getValue())).equals("ALL")){filterer.setStatus(null);}
                else if(sortByStatusBox.getValue()!=null){
                    filterer.setStatus((String) sortByStatusBox.getValue());
                }
                else {filterer.setStatus(null);}
                ComplaintList filtered = list.filterBy(filterer);
                showTable(filtered);
            }
        });
    }




    private void detectTheme() {
        if (ProjectApplication.getUserAgentStylesheet() == null || ProjectApplication.getUserAgentStylesheet().equals(lightModePath)) {
            isLightMode = true;
            setLightMode();
        } else {
            isLightMode = false;
            setDarkMode();
        }
    }

    public void changeMode(ActionEvent actionevent) {
        if (isLightMode) {
            setDarkMode();
        } else {
            setLightMode();
        }
        isLightMode = !isLightMode;
    }

    private void setLightMode(){
//        System.out.println(parent.getStylesheets());
        parent.getStylesheets().add(lightModePath);
        parent.getStylesheets().remove(darkModePath);
    }

    private void setDarkMode(){
        parent.getStylesheets().add(darkModePath);
        parent.getStylesheets().remove(lightModePath);
    }



    public void showTable(ComplaintList complaintList){
        ObservableList<Complaint> data = FXCollections.observableArrayList(complaintList.getAllComplaints());
        categoryTable.setCellValueFactory(new PropertyValueFactory<>("category"));
        topicTable.setCellValueFactory(new PropertyValueFactory<>("title"));
        voteTable.setCellValueFactory(new PropertyValueFactory<>("voted"));
        statusTable.setCellValueFactory(new PropertyValueFactory<>("status"));
        userTable.setCellValueFactory(new PropertyValueFactory<>("user"));
        timeTable.setCellValueFactory(new PropertyValueFactory<>("time"));

        table.setItems(data);
    }
    private void handleSelectedTableView(){
        table.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Complaint>() {
                    @Override
                    public void changed(ObservableValue<? extends Complaint> observableValue, Complaint oldValue, Complaint newValue) {
                        System.out.println(newValue + " is selected");
                        dataList.add(newValue);
                        try {
                            com.github.saacsos.FXRouter.goTo("studentdetail",dataList);
                        } catch (IOException e) {
                            System.err.println("ไปที่หน้า detail ไม่ได้");
                            System.err.println("ให้ตรวจสอบการกําหนด route");
                        }
                    }
                });

    }
    @FXML
    public void handleStudentReportButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("studentreport",student);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleStudentHomeButton(MouseEvent mouseEvent){
        try {
            com.github.saacsos.FXRouter.goTo("student",student);
        } catch (IOException e) {
            System.err.println("Cannot reach Dictionary");
        }
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
        File image = new File(student.getImagePath());
        circle.setFill(new ImagePattern(new Image(image.toURI().toString())));

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





}




