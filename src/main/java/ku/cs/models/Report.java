package ku.cs.models;

public class Report {
    private String reporterUsername;
    private String reportedUsername;
    private String reportTime;
    private String reportDate;
    private String detail;
    private String title;

    public Report(String reporterUsername, String reportedUsername, String reportTime, String reportDate, String detail, String title) {
        this.reporterUsername = reporterUsername;
        this.reportedUsername = reportedUsername;
        this.reportTime = reportTime;
        this.reportDate = reportDate;
        this.detail = detail;
        this.title = title;
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

    @Override
    public String toString() {
        return "Report{" +
                "reporterUsername='" + reporterUsername + '\'' +
                ", reportedUsername='" + reportedUsername + '\'' +
                ", reportTime='" + reportTime + '\'' +
                ", reportDate='" + reportDate + '\'' +
                ", detail='" + detail + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
