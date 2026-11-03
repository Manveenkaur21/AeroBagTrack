package dao;

import model.Baggage;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaggageDAO {
    public boolean addBaggage(Baggage b) {
        String sql = "INSERT INTO baggage (baggage_id, passenger_id, baggage_tag, description, color, current_status, current_location) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, b.getBaggageId());
            ps.setInt(2, b.getPassengerId());
            ps.setString(3, b.getBaggageTag());
            ps.setString(4, b.getDescription());
            ps.setString(5, b.getColor());
            ps.setString(6, b.getCurrentStatus());
            ps.setString(7, b.getCurrentLocation());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
