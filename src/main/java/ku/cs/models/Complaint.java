package ku.cs.models;

public class Complaint {
    private String category;
    private String title;
    private String detail;
    private int voted;
    private String user;
    private String status;

    public Complaint(String category, String title, String detail, String user) {
        this.category = category;
        this.title = title;
        this.detail = detail;
        this.voted = 0;
        this.user = user;
        this.status = "ยังไม่ดำเนินการ";
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
    public void updateStatus(String s){
        this.status = s;
    }
    public void vote(){
        this.voted++;
    }


    public String toCSV() {
        return
                 category + ',' + title + ',' + detail + ',' + voted + "," + user + ',' + status + ',' ;
    }
}
