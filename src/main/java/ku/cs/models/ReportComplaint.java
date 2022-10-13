package ku.cs.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportComplaint {
    private Complaint complaint;
    private String reportedComplaintTitle;
    private String subject;
    private String detail;
    private String reporterAccount;
    private String reportTime;
    private String reportDate;

    public ReportComplaint(String reportedComplaintTitle, String subject,
                           String detail, String reporterAccount,String reportTime, String reportDate) {
        this.reportedComplaintTitle = reportedComplaintTitle;
        this.subject = subject;
        this.detail = detail;
        this.reporterAccount = reporterAccount;
        this.reportTime = reportTime;
        this.reportDate = reportDate;
    }

    public void initializeReportTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String reportDateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        String [] time = reportDateTime.split(" ");
        reportDate = time[0];
        reportTime = time[1];

    }

    public LocalDateTime getTime(){
        String[] data = reportDate.split("/");
        int year = Integer.parseInt(data[2]);
        int month = Integer.parseInt(data[1]);
        int day = Integer.parseInt(data[0]);
        data = reportTime.split(":");
        int hour = Integer.parseInt(data[0]);
        int minute = Integer.parseInt(data[1]);
        int sec = Integer.parseInt(data[2]);
        return LocalDateTime.of(year, month, day, hour, minute, sec);
    }
    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public String getReportedComplaintTitle() {
        return reportedComplaintTitle;
    }

    public String getSubject() {
        return subject;
    }

    public String getDetail() {
        return detail;
    }

    public String getReporterAccount() {
        return reporterAccount;
    }

    public String getReportTime() {
        return reportTime;
    }

    public String getReportDate() {
        return reportDate;
    }

    @Override
    public String toString() {
        return "เรื่อง: "+reportedComplaintTitle+" รายงานโดย: " + reporterAccount + " [หมวดหมู่: " + subject +"]\n" +
                "วันที่รายงาน: " + reportDate;
    }
}
