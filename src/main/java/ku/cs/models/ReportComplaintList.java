package ku.cs.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class ReportComplaintList {
    private ArrayList<ReportComplaint> reportComplaints;
    public ReportComplaintList() {
        this.reportComplaints = new ArrayList<>();
    }

    public void newAccReport(String reportedComplaintTitle, String subject, String detail, String reporterUsername, String reportTime,String reportDate){
        reportComplaints.add(new ReportComplaint(reportedComplaintTitle,subject, detail, reporterUsername,reportTime,reportDate));
    }

    public void addComplaintReport(ReportComplaint report){
        reportComplaints.add(report);
    }

    public ArrayList<ReportComplaint> getAllReport(){
        return reportComplaints;
    }
    public void delete(Object del){
        reportComplaints.remove(del);
    }

//    public ArrayList<ReportComplaint> sortByTime(){
//        ArrayList<ReportComplaint> temp = reportComplaints;
//        temp.sort(new Comparator<ReportComplaint>() {
//            @Override
//            public int compare(ReportComplaint o1, ReportComplaint o2) {
//                if (o1.getTime().isBefore(o2.getTime())) return 1;
//                if (o1.getTime().isAfter(o2.getTime())) return -1;
//                return 0;
//            }
//        });
//        return temp;
//    }
}

