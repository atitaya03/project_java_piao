package ku.cs.models;

import java.util.ArrayList;

public class ReportComplaintList {
    private ArrayList<ReportComplaint> reportComplaints;
    public ReportComplaintList() {
        this.reportComplaints = new ArrayList<>();
    }

    public void newAccReport(String reportedComplaintTitle,String subject, String detail, String reporterUsername){
        reportComplaints.add(new ReportComplaint(reportedComplaintTitle,subject, detail, reporterUsername));
    }

    public void addComplaintReport(ReportComplaint report){
        reportComplaints.add(report);
    }

    public ArrayList<ReportComplaint> getAllReport(){
        return reportComplaints;
    }


}

