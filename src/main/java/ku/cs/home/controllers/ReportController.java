package ku.cs.home.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ReportController {
    private ComboBox reportTypeBox;
    private ComboBox subjectBox;
    private TextArea detailBox;
    private Button submitButton;
    private final ObservableList<String> reportTypeBoxList = FXCollections.observableArrayList("account", "content");
    private final ObservableList<String> reportSubjectBoxList = FXCollections.observableArrayList("inappropriate content", "fake content", "precarious content", "violent content", "harassment or bullying content", "spam", "something else");

    public void initialize() {
        reportTypeBox.setValue("");
        reportTypeBox.setItems(reportTypeBoxList);
        subjectBox.setValue("");
        subjectBox.setItems(reportSubjectBoxList);
    }
}
