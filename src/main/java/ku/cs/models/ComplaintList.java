package ku.cs.models;

import java.util.ArrayList;

public class ComplaintList {
    private ArrayList<Complaint> complaints;

    public ComplaintList(){

        complaints = new ArrayList<>();
    }
    public void addComplaint(Complaint complaint){
        complaints.add(complaint);
    }
    public ArrayList<Complaint> getAllComplaints(){return complaints;}

    public Complaint getComplaint(Complaint complaint){
        Complaint comp= null;
        for(Complaint c : complaints){
            if(complaint.getTitle().equals(c.getTitle()) && complaint.getUser().equals(c.getUser()) && complaint.getTime().equals(c.getTime())){
                comp = c;
            }
        }
        return comp;
    }
}
