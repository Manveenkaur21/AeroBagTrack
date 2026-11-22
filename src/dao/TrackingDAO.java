package dao;

import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackingDAO {

    public boolean addEvent(int baggageId, String event) {
        String sql = "INSERT INTO tracking_history (baggage_id, event) VALUES (?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, baggageId);
            ps.setString(2, event);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Tracking Insert Error: " + e.getMessage());
            return false;
        }
    }

    public List<String> getHistory(int baggageId) {
        List<String> events = new ArrayList<>();

        String sql = "SELECT event FROM tracking_history WHERE baggage_id = ? ORDER BY event_time, tracking_id";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, baggageId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    events.add(rs.getString("event"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Tracking Read Error: " + e.getMessage());
        }

        return events;
    }
}