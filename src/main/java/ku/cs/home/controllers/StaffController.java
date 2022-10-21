package ku.cs.home.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.ProjectApplication;
import ku.cs.models.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import ku.cs.services.ComplaintFileDataSource;
import ku.cs.services.DataSource;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SplittableRandom;

public class StaffController {
    private Account staff;
    @FXML
    private Circle staffimage;
    @FXML private Label nameLabel;
    @FXML private ComboBox <String> selectStatusCombobox;
    @FXML private TableView<Complaint> complaintsTable;
    private ComplaintList complaintList;
    private  ComplaintList filtered;
    private ComplaintList sorted;
    private ComplaintFilterer filterer;
    private DataSource<ComplaintList> complaintListDataSource;
    private ArrayList<Object> dataList;

    @FXML private Button modeBtn;
    private boolean isLightMode = true;
    private final String lightModePath = getClass().getResource("/ku/cs/Themes/light.css").toExternalForm();
    private final String darkModePath = getClass().getResource("/ku/cs/Themes/dark.css").toExternalForm();

    @FXML
    private AnchorPane parent;

    @FXML
    private ImageView imgMode;



    public void initialize(){
        staff = (Account) com.github.saacsos.FXRouter.getData();
        dataList = new ArrayList<>();
        dataList.add(staff);
        complaintListDataSource = new ComplaintFileDataSource();
        complaintList = complaintListDataSource.readData();
        filterer = new ComplaintFilterer();
        filtered = new ComplaintList();
        sorted = sortCategory();
        selectStatusCombobox.getItems().addAll("ALL","ยังไม่ดำเนินการ","อยู่ระหว่างการดำเนินการ","ดำเนินการเสร็จสิ้น");
//        DetectTheme detectTheme = new DetectTheme(parent,staff);
        showUserData();
        showComplaintsData(sorted);
        handleSelectedTableView();
        detectTheme();
        handleSortByStatus();
    }
    private void handleSortByStatus(){
        selectStatusCombobox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(selectStatusCombobox.getValue());
                if(((String)(selectStatusCombobox.getValue())).equals("ALL")){filterer.setStatus(null);}
                else if(selectStatusCombobox.getValue()!=null){
                    filterer.setStatus((String) selectStatusCombobox.getValue());
                }
                else {filterer.setStatus(null);}
                filtered = sorted.filterBy(filterer);
                showComplaintsData(filtered);
            }
        });
    }

    private void detectTheme() {
        if (staff.getTheme().isLightMode()) {
            setLightMode();
        } else {
            setDarkMode();
        }
    }

    public void changeMode(ActionEvent actionevent) {
        if (staff.getTheme().isLightMode()) {
            setDarkMode();
        } else {
            setLightMode();
        }

        staff.getTheme().setLightMode(!staff.getTheme().isLightMode());

    }

    private void setLightMode(){
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
    private void showComplaintsData(ComplaintList sorted){

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
    
    @FXML public void handleHowToUseButton(ActionEvent actionEvent){
        try {
            Desktop.getDesktop().open(new File("data" + File.separator + "manual.pdf"));
        } catch (IOException e) {
            System.out.println("Cannot open manual.pdf");
            e.printStackTrace();
        }
    }

    




}
