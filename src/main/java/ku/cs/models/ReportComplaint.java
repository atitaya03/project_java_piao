package ku.cs.models;

public class ReportComplaint {
    private Complaint complaint;
    private String reportedComplaintTitle;
    private String subject;
    private String detail;
    private String reporterAccount;

    public ReportComplaint(String reportedComplaintTitle, String subject, String detail, String reporterAccount) {
        this.reportedComplaintTitle = reportedComplaintTitle;
        this.subject = subject;
        this.detail = detail;
        this.reporterAccount = reporterAccount;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
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
}
