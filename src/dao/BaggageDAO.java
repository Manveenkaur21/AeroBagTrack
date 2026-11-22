package dao;

import model.Baggage;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaggageDAO {

    // INSERT
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
            System.out.println("Insert Error: " + e.getMessage());
            return false;
        }
    }

    // SELECT BY ID
    public Baggage getBaggageById(int id) {

        String sql = "SELECT * FROM baggage WHERE baggage_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Baggage b = new Baggage(
                        rs.getInt("baggage_id"),
                        rs.getInt("passenger_id"),
                        rs.getString("baggage_tag"),
                        rs.getString("description"),
                        rs.getString("color")
                );

                b.updateStatus(
                        rs.getString("current_status"),
                        rs.getString("current_location")
                );

                return b;
            }

        } catch (SQLException e) {
            System.out.println("Select Error: " + e.getMessage());
        }

        return null;
    }

    // SELECT ALL
    public List<Baggage> getAllBaggage() {

        List<Baggage> list = new ArrayList<>();

        String sql = "SELECT * FROM baggage";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Baggage b = new Baggage(
                        rs.getInt("baggage_id"),
                        rs.getInt("passenger_id"),
                        rs.getString("baggage_tag"),
                        rs.getString("description"),
                        rs.getString("color")
                );

                b.updateStatus(
                        rs.getString("current_status"),
                        rs.getString("current_location")
                );

                list.add(b);
            }

        } catch (SQLException e) {
            System.out.println("Select Error: " + e.getMessage());
        }

        return list;
    }

    // UPDATE
    public boolean updateBaggage(int id, String status, String location) {

        String sql = "UPDATE baggage SET current_status = ?, current_location = ? WHERE baggage_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setString(2, location);
            ps.setInt(3, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update Error: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean deleteBaggage(int id) {

        String sql = "DELETE FROM baggage WHERE baggage_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete Error: " + e.getMessage());
            return false;
        }
    }
}