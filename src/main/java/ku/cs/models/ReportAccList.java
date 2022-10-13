package ku.cs.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class ReportAccList {
    private ArrayList<ReportAccount> reportAcctList;
    public ReportAccList() {
        this.reportAcctList = new ArrayList<>();
    }

    public void newAccReport(String reportedUsername, String subject, String detail, String reporterUsername, String reportTime, String reportDate){
        reportAcctList.add(new ReportAccount(reportedUsername,subject, detail, reporterUsername,reportTime,reportDate));
    }

    public void addAccReport(ReportAccount report){
        reportAcctList.add(report);
    }
    public ArrayList<ReportAccount> getAllReport(){
        return reportAcctList;
    }
    public void delete(Object del){
        reportAcctList.remove(del);
    }

    /// อย่าลืมใช้นะโว้ย
//    public ArrayList<ReportAccount> sortByTime(){
//        ArrayList<ReportAccount> temp = reportAcctList;
//        temp.sort(new Comparator<ReportAccount>() {
//            @Override
//            public int compare(ReportAccount o1, ReportAccount o2) {
//                if (o1.getTime().isBefore(o2.getTime())) return 1;
//                if (o1.getTime().isAfter(o2.getTime())) return -1;
//                return 0;
//            }
//        });
//        return temp;
//    }
}

