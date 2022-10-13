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
    public void delete(Object del){
        complaints.remove(del);
    }

    public Complaint searchComplaintByTitle(String title){
        for (Complaint c: complaints)
            if (c.getTitle().equals(title))
                return c;
        return null;
    }

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
