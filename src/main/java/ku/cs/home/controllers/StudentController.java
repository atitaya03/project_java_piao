package ku.cs.home.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import ku.cs.services.Parse;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class StudentController {
    private Account student;
    @FXML
    private Label nameLabel;
    @FXML
    private Circle circle;
    @FXML private ComboBox sortByCategoryBox;
    @FXML private ComboBox sortByStatusBox;
    @FXML private ComboBox<String> sortByTimeAndVotes;
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

    @FXML
    private ImageView imgMode;

    private ComplaintFileDataSource complaintFileDataSource;
    private ComplaintList list;
    private ComplaintList filtered;
    private ArrayList<Object> dataList;
    private ComplaintFilterer filterer;
    private final ObservableList<String> sortByCategoryBoxList = FXCollections.observableArrayList("ALL","ความปลอดภัย", "ความสะอาด","อาคารชำรุด","ถนน ทางเท้า","ยานพาหนะ");
    private final ObservableList<String> sortByStatusList = FXCollections.observableArrayList("ALL","ยังไม่ดำเนินการ","อยู่ระหว่างการดำเนินการ","ดำเนินการเสร็จสิ้น");
    private final ObservableList<String> sortByTimeAndVotesList= FXCollections.observableArrayList("คะแนนโหวตจากมากที่สุด","คะแนนโหวตจากน้อยที่สุด", "เวลาแจ้งล่าสุด","เวลาแจ้งเก่าที่สุด");

    @FXML private Button modeBtn;
   // private Parse parse;
    //private boolean theme;
    private final String lightModePath = getClass().getResource("/ku/cs/Themes/light.css").toExternalForm();
    private final String darkModePath = getClass().getResource("/ku/cs/Themes/dark.css").toExternalForm();





    public void initialize(){

        student = (Account) com.github.saacsos.FXRouter.getData();


        dataList = new ArrayList<>();
        showUserData();
        dataList.add(student);
        filterer = new ComplaintFilterer();
        complaintFileDataSource = new ComplaintFileDataSource();
         list = complaintFileDataSource.readData();
        sortByCategoryBox.setItems(sortByCategoryBoxList);
        sortByStatusBox.setItems(sortByStatusList);
        sortByTimeAndVotes.setItems(sortByTimeAndVotesList);
        filtered = list;
        showTable(list);
        handleSelectedTableView();
        detectTheme();
        handleSortByCategory();
        handleSortByStatus();
        handleSortByTimeandVotes();

        


    }
    @FXML private void handleSortByVoteButton(){
        int min ;
        int max ;
        if(minTextField.getText().equals("")&&maxTextField.getText().equals("")){
            filterer.setMin(-1);
            filterer.setMax(-1);
        }
        else if(!minTextField.getText().equals("")&&!maxTextField.getText().equals("")) {
            System.out.println("case1");
            min  = Integer.parseInt((minTextField.getText()));
            max = Integer.parseInt((maxTextField.getText()));
            filterer.setMin(min);
            filterer.setMax(max);}
        else if (!minTextField.getText().equals("")) {
            System.out.println("case2");
            min  = Integer.parseInt((minTextField.getText()));
            filterer.setMin(min);
            filterer.setMax(-1);
        }
        else {
            System.out.println("case3");
            max = Integer.parseInt((maxTextField.getText()));
            filterer.setMin(-1);
            filterer.setMax(max);
        }
        filtered = list.filterBy(filterer);
        showTable(filtered);

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
                filtered = list.filterBy(filterer);
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
                filtered = list.filterBy(filterer);
                showTable(filtered);
            }
        });
    }



    public void handleSortByTimeandVotes(){
        sortByTimeAndVotes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(filtered == null){
                    filtered = list;
                }
                System.out.println(sortByTimeAndVotes.getValue());
                if(sortByTimeAndVotes.getValue().equals("คะแนนโหวตจากมากที่สุด")){
                    filtered.sortByVotes(-1);
                }else if(sortByTimeAndVotes.getValue().equals("คะแนนโหวตจากน้อยที่สุด")){
                    filtered.sortByVotes(1);
                } else if (sortByTimeAndVotes.getValue().equals("เวลาแจ้งล่าสุด")) {
                    filtered.sortByTime(-1);
                } else if (sortByTimeAndVotes.getValue().equals("เวลาแจ้งเก่าที่สุด")) {
                    filtered.sortByTime(1);

                } else{
                    System.out.println("Hello world");
                }
                showTable(filtered);
            }
        });
    }
    private void detectTheme() {
        if (student.getTheme().isLightMode()) {
            setLightMode();
        } else {
            setDarkMode();
        }
    }

    public void changeMode(ActionEvent actionevent) {
        if (student.getTheme().isLightMode()) {
            setDarkMode();
        } else {
            setLightMode();
        }

        student.getTheme().setLightMode(!student.getTheme().isLightMode());

    }

    private void setLightMode(){
//        System.out.println(parent.getStylesheets());
        modeBtn.setText("Dark Mode");
        String path = getClass().getResource("/ku/cs/images/darkMode.png").toExternalForm();
        imgMode.setImage(new Image(path));
        parent.getStylesheets().add(lightModePath);
        parent.getStylesheets().remove(darkModePath);
    }

    private void setDarkMode(){

        modeBtn.setText("Light Mode");
        String path = getClass().getResource("/ku/cs/images/lightMode.png").toExternalForm();
        imgMode.setImage(new Image(path));
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
            //parse.showAllObject();
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเขียนร้องเรียน ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            throw new RuntimeException();
        }
    }



    @FXML
    public void handleedit(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("edit",student);
        } catch (IOException e) {
            System.err.println("ไปหน้าแก้ไขโปรไฟล์ไม่ได้");
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
    @FXML public void handleHowToUseButton(ActionEvent actionEvent){
        try {
            Desktop.getDesktop().open(new File("data" + File.separator + "manual.pdf"));
        } catch (IOException e) {
            System.out.println("Cannot open manual.pdf");
            e.printStackTrace();
        }
    }





}




