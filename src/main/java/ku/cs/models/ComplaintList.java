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
    public ArrayList<Complaint> getAllAccount(){return complaints;}
}
