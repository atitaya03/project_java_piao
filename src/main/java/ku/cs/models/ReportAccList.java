package ku.cs.models;

import java.util.ArrayList;

public class ReportAccList {
    private ArrayList<ReportAccount> reportAcctList;
    public ReportAccList() {
        this.reportAcctList = new ArrayList<>();
    }

    public void newAccReport(String reportedUsername,String subject, String detail, String reporterUsername){
        reportAcctList.add(new ReportAccount(reportedUsername,subject, detail, reporterUsername));
    }

    public void addAccReport(ReportAccount report){
        reportAcctList.add(report);
    }

    public ArrayList<ReportAccount> getAllReport(){
        return reportAcctList;
    }


}

