package dao;

import model.FoundBaggage;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoundBaggageDAO {

    public boolean addFoundBaggage(FoundBaggage baggage) {
        String sql = "INSERT INTO found_baggage (found_id, baggage_tag, description, color, found_location, recovery_status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, baggage.getFoundId());
            ps.setString(2, baggage.getBaggageTag());
            ps.setString(3, baggage.getDescription());
            ps.setString(4, baggage.getColor());
            ps.setString(5, baggage.getFoundLocation());
            ps.setString(6, baggage.getRecoveryStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Found Baggage Insert Error: " + e.getMessage());
            return false;
        }
    }

    public List<FoundBaggage> getAllFoundBaggage() {
        List<FoundBaggage> list = new ArrayList<>();
        String sql = "SELECT * FROM found_baggage";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                FoundBaggage baggage = new FoundBaggage(
                        rs.getInt("found_id"),
                        rs.getString("baggage_tag"),
                        rs.getString("description"),
                        rs.getString("color"),
                        rs.getString("found_location")
                );

                baggage.updateRecoveryStatus(rs.getString("recovery_status"));
                list.add(baggage);
            }

        } catch (SQLException e) {
            System.out.println("Found Baggage Read Error: " + e.getMessage());
        }

        return list;
    }
}