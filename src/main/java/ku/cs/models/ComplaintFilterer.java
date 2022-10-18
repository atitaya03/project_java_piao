package ku.cs.models;

public class ComplaintFilterer implements Filterer<Complaint>
{
    private String category,status;
    private int min,max;

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public boolean filter(Complaint complaint) {
        if(category!=null && status!=null){

            if(min>0 && max>0)  return complaint.getCategory().equals(category) && complaint.getStatus().equals(status)
                    &&complaint.getVoted()>=min && complaint.getVoted()<=max;
            if(max<0) return complaint.getCategory().equals(category) && complaint.getStatus().equals(status)
                    && complaint.getVoted()>=min;
            if(min<0) return complaint.getCategory().equals(category) && complaint.getStatus().equals(status)
                    && complaint.getVoted()<=max;
            return complaint.getCategory().equals(category) && complaint.getStatus().equals(status);
        }

        if(category!=null) //return complaint.getCategory().equals(category);
        {

            if(min>0 && max>0)  return  complaint.getCategory().equals(category)
                    &&complaint.getVoted()>=min && complaint.getVoted()<=max;
            if(max<0) return  complaint.getCategory().equals(category)
                    && complaint.getVoted()>=min;
            if(min<0) return complaint.getCategory().equals(category)
                    && complaint.getVoted()<=max;
            return  complaint.getCategory().equals(category);
        }
        if(status!=null) //return  complaint.getStatus().equals(status);
        {

            if(min>0 && max>0)  return  complaint.getStatus().equals(status)
                    &&complaint.getVoted()>=min && complaint.getVoted()<=max;
            if(max<0) return  complaint.getStatus().equals(status)
                    && complaint.getVoted()>=min;
            if(min<0) return complaint.getStatus().equals(status)
                    && complaint.getVoted()<=max;
            return  complaint.getStatus().equals(status);
        }
        if(min>0 && max>0) return complaint.getVoted()>=min && complaint.getVoted()<=max;
        if(max<0) return complaint.getVoted()>=min;
        if(min<0) return complaint.getVoted()<=max;

        return true;
    }
}
