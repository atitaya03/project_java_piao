package ku.cs.models;

import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
    public ComplaintList filterBy(Filterer filterer){
        ComplaintList filtered = new ComplaintList();
        for(Complaint c : complaints){
            Complaint found  = c;
            if(filterer.filter(c)){
                filtered.addComplaint(c);
            }
        }
        return filtered;
    }
    public ComplaintList sortByVotes(int n){
        ComplaintList sort = new ComplaintList();
        Collections.sort(complaints, new Comparator<Complaint>() {
            @Override
            public int compare(Complaint o1, Complaint o2) {
                return (o1.getVoted() - o2.getVoted())*n ;
            }
        });
        return sort;
    }
    public ComplaintList sortByTime(int n){
        ComplaintList sort = new ComplaintList();
        Collections.sort(complaints, new Comparator<Complaint>() {
            @Override
            public int compare(Complaint o1, Complaint o2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                LocalDateTime t1 = LocalDateTime.parse(o1.getTime(), formatter);
                LocalDateTime t2 = LocalDateTime.parse(o2.getTime(), formatter);
                return t1.compareTo(t2)*n;
            }
        });
        return  sort;
    }
}
