package service;

import model.Complaint;
import dao.ComplaintDAO;
import java.util.List;
import Exception.InvalidComplaintException;

public class ComplaintService {

    private final ComplaintDAO complaintDAO = new ComplaintDAO();

    public boolean registerComplaint(Complaint complaint)
            throws InvalidComplaintException {

        if (complaint == null) {
            throw new InvalidComplaintException("Complaint cannot be null.");
        }

        if (complaint.getComplaintId() <= 0) {
            throw new InvalidComplaintException("Invalid complaint ID.");
        }

        if (complaint.getPassengerId() <= 0) {
            throw new InvalidComplaintException("Invalid passenger ID.");
        }

        if (complaint.getBaggageId() <= 0) {
            throw new InvalidComplaintException("Invalid baggage ID.");
        }

        if (complaint.getComplaintDescription() == null ||
                complaint.getComplaintDescription().isBlank()) {
            throw new InvalidComplaintException("Complaint details are required.");
        }

        return complaintDAO.addComplaint(complaint);
    }

    public Complaint searchComplaint(int id) {
        return complaintDAO.getComplaintById(id);
    }

    public void displayAllComplaints() {
        List<Complaint> complaints = complaintDAO.getAllComplaints();

        if (complaints.isEmpty()) {
            System.out.println("No complaints found.");
        } else {
            complaints.forEach(Complaint::displayComplaint);
        }
    }

    public boolean updateComplaintStatus(int id, String status) {
        return complaintDAO.updateComplaintStatus(id, status);
    }

    public boolean resolveComplaint(int id, String resolution) {
        return complaintDAO.resolveComplaint(id, resolution);
    }

    public int getTotalComplaints() {
        return complaintDAO.getAllComplaints().size();
    }
}