package model;

public class Complaint {
    private int complaintId;
    private int passengerId;
    private int baggageId;
    private String complaintDescription;
    private String complaintStatus = "Pending";
    private String resolution = "Not Resolved";

    public Complaint(int complaintId, int passengerId, int baggageId, String complaintDescription) {
        this.complaintId = complaintId;
        this.passengerId = passengerId;
        this.baggageId = baggageId;
        this.complaintDescription = complaintDescription;
    }

    public int getComplaintId() { return complaintId; }
    public int getPassengerId() { return passengerId; }
    public int getBaggageId() { return baggageId; }
    public String getComplaintDescription() { return complaintDescription; }
    public String getComplaintStatus() { return complaintStatus; }
    public String getResolution() { return resolution; }

    public void updateComplaintStatus(String status) { complaintStatus = status; }
    public void resolveComplaint(String details) {
        resolution = details;
        complaintStatus = "Resolved";
    }

    public void displayComplaint() {
        System.out.println("Complaint ID: " + complaintId + ", Passenger ID: " + passengerId
                + ", Baggage ID: " + baggageId + ", Status: " + complaintStatus
                + ", Resolution: " + resolution + ", Details: " + complaintDescription);
    }
}
