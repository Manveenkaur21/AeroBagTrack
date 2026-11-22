package dao;

import model.Complaint;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {

    public boolean addComplaint(Complaint complaint) {
        String sql = "INSERT INTO complaints (complaint_id, passenger_id, baggage_id, complaint_description, complaint_status, resolution) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, complaint.getComplaintId());
            ps.setInt(2, complaint.getPassengerId());
            ps.setInt(3, complaint.getBaggageId());
            ps.setString(4, complaint.getComplaintDescription());
            ps.setString(5, complaint.getComplaintStatus());
            ps.setString(6, complaint.getResolution());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Complaint Insert Error: " + e.getMessage());
            return false;
        }
    }

    public Complaint getComplaintById(int id) {
        String sql = "SELECT * FROM complaints WHERE complaint_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Complaint complaint = new Complaint(
                            rs.getInt("complaint_id"),
                            rs.getInt("passenger_id"),
                            rs.getInt("baggage_id"),
                            rs.getString("complaint_description")
                    );

                    if ("Resolved".equals(rs.getString("complaint_status"))) {
                        complaint.resolveComplaint(rs.getString("resolution"));
                    } else {
                        complaint.updateComplaintStatus(rs.getString("complaint_status"));
                    }

                    return complaint;
                }
            }

        } catch (SQLException e) {
            System.out.println("Complaint Search Error: " + e.getMessage());
        }

        return null;
    }

    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT * FROM complaints";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Complaint complaint = new Complaint(
                        rs.getInt("complaint_id"),
                        rs.getInt("passenger_id"),
                        rs.getInt("baggage_id"),
                        rs.getString("complaint_description")
                );

                if ("Resolved".equals(rs.getString("complaint_status"))) {
                    complaint.resolveComplaint(rs.getString("resolution"));
                } else {
                    complaint.updateComplaintStatus(rs.getString("complaint_status"));
                }

                complaints.add(complaint);
            }

        } catch (SQLException e) {
            System.out.println("Complaint Read Error: " + e.getMessage());
        }

        return complaints;
    }

    public boolean updateComplaintStatus(int id, String status) {
        String sql = "UPDATE complaints SET complaint_status = ? WHERE complaint_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Complaint Update Error: " + e.getMessage());
            return false;
        }
    }

    public boolean resolveComplaint(int id, String resolution) {
        String sql = "UPDATE complaints SET resolution = ?, complaint_status = 'Resolved' WHERE complaint_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, resolution);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Complaint Resolution Error: " + e.getMessage());
            return false;
        }
    }
}