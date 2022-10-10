package ku.cs.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportAccount {
    private Account reportedAccount;
    private String getReportedAccountUsername;
    private String subject;
    private String reporterAccount;
    private String detail;

    public ReportAccount(String reportedAccountName, String subject, String detail,String reporterAccount) {
        this.getReportedAccountUsername = reportedAccountName;
        this.subject = subject;
        this.reporterAccount = reporterAccount;
        this.detail = detail;
    }

    public void setReportedAccount(Account reportedAccount) {
        this.reportedAccount = reportedAccount;
    }

    public String getReportedAccountUsername() {
        return getReportedAccountUsername;
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

}

