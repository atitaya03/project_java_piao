package ku.cs.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;

public class Complaint {
    private String category;
    private String title;
    private String detail;
    private int voted;
    private String user;
    private String status;

    private String time;
//    private AccountList votedList;
    public Complaint(String category, String title, String detail, String user) {
        this.category = category;
        this.title = title;
        this.detail = detail;
        this.voted = 0;
        this.user = user;
        this.status = "ยังไม่ดำเนินการ";
        this.time = "00";
    }

    public Complaint(String category, String title, String detail, int voted, String user, String status, String time) {
        this.category = category;
        this.title = title;
        this.detail = detail;
        this.voted = voted;
        this.user = user;
        this.status = status;
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public int getVoted() {
        return voted;
    }

    public String getUser() {
        return user;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public void updateStatus(String s){
        this.status = s;
    }
    public void vote(){
        this.voted++;
    }


    public String toCSV() {
        return
                 category + ',' + title + ',' + detail + ',' + voted + "," + user + ',' + status  + ',' + time;
    }


    public void initialCreateTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Before Formatting: " + now);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.time = now.format(format);
    }


}
