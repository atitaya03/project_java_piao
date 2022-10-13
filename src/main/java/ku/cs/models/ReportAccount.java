package ku.cs.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportAccount {
    private Account reportedAccount;
    private String getReportedAccountUsername;
    private String subject;
    private String reporterAccount;
    private String detail;
    private String reportTime;
    private String reportDate;

    public ReportAccount(String reportedAccountName, String subject,
                         String detail,String reporterAccount,String reportTime,String reportDate) {
        this.getReportedAccountUsername = reportedAccountName;
        this.subject = subject;
        this.reporterAccount = reporterAccount;
        this.detail = detail;
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


    public void setReportedAccount(Account reportedAccount) {
        this.reportedAccount = reportedAccount;
    }

    public String getReportedAccountUsername() {
        return getReportedAccountUsername;
    }

    public Account getReportedAccount() {
        return reportedAccount;
    }

    public String getSubject() {
        return subject;
    }

    public String getReporterAccount() {
        return reporterAccount;
    }

    public String getDetail() {
        return detail;
    }

    public String getReportTime() {
        return reportTime;
    }

    public String getReportDate() {
        return reportDate;
    }

    @Override
    public String toString() {
        return "รายงานโดย: " + reporterAccount + " [หมวดหมู่: " + subject +"]\n" +
                "วันที่รายงาน: " + reportDate;
    }
}

