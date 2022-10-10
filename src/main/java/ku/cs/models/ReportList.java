package ku.cs.models;

import java.util.ArrayList;

public class ReportList {
    private ArrayList<Report> reportList;
    public ReportList() {
        this.reportList = new ArrayList<>();
    }

    public void newReport(String reporterUsername, String reportedUsername,String detail, String title, String category){
        reportList.add(new Report(reporterUsername, reportedUsername,detail, title, category));
    }

    public void addReport(Report report){
        reportList.add(report);
    }

    public ArrayList<Report> getAllReport(){
        return reportList;
    }



}

