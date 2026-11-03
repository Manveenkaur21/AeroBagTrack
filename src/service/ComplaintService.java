package service;

import model.Complaint;
import java.util.ArrayList;
import java.util.List;

public class ComplaintService {
    private final List<Complaint> complaints = new ArrayList<>();

    public boolean registerComplaint(Complaint complaint) {
        if (complaint == null || searchComplaint(complaint.getComplaintId()) != null) return false;
        complaints.add(complaint);
        return true;
    }

    public Complaint searchComplaint(int id) {
        return complaints.stream().filter(c -> c.getComplaintId() == id).findFirst().orElse(null);
    }

    public void displayAllComplaints() {
        if (complaints.isEmpty()) System.out.println("No complaints found.");
        else complaints.forEach(Complaint::displayComplaint);
    }

    public boolean updateComplaintStatus(int id, String status) {
        Complaint c = searchComplaint(id);
        if (c == null) return false;
        c.updateComplaintStatus(status);
        return true;
    }

    public boolean resolveComplaint(int id, String resolution) {
        Complaint c = searchComplaint(id);
        if (c == null) return false;
        c.resolveComplaint(resolution);
        return true;
    }

    public int getTotalComplaints() { return complaints.size(); }
}
