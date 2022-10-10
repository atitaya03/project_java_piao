package ku.cs.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report {
    private String reporterUsername;
    private String reportedUsername;
    private String reportTime;
    private String reportDate;
    private String detail;
    private String title;
    private String category;

    public Report(String reporterUsername, String reportedUsername, String reportTime, String reportDate, String detail, String title, String category) {
        this.reporterUsername = reporterUsername;
        this.reportedUsername = reportedUsername;
        this.reportTime = reportTime;
        this.reportDate = reportDate;
        this.detail = detail;
        this.title = title;
        this.category = category;
    }

    public Report(String reporterUsername, String reportedUsername,String detail, String title, String category) {
    }


    public void ReportTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String loginDateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String[] time = loginDateTime.split(" ");
        reportDate = time[0];
        reportTime = time[1];
    }
    public String getReporterUsername() {
        return reporterUsername;
    }

    public String getReportedUsername() {
        return reportedUsername;
    }

    public String getReportTime() {
        return reportTime;
    }

    public String getReportDate() {
        return reportDate;
    }

    public String getDetail() {
        return detail;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "รายงานโดย: " + reporterUsername + " [หมวดหมู่: " + category + "/หัวข้อ: " + title +"]\n" +
                "วันที่รายงาน: " + reportDate;
    }
}

