package service;

import dao.TrackingDAO;
import java.util.List;

public class TrackingService {

    private final TrackingDAO trackingDAO = new TrackingDAO();

    public void initializeTracking(int baggageId) {
        trackingDAO.addEvent(baggageId, "Registered at Check-in Counter");
    }

    public void updateTracking(int baggageId, String event) {
        trackingDAO.addEvent(baggageId, event);
    }

    public void trackBaggage(int baggageId) {
        displayTrackingHistory(baggageId);
    }

    public void displayTrackingHistory(int baggageId) {
        List<String> events = trackingDAO.getHistory(baggageId);

        if (events.isEmpty()) {
            System.out.println("No tracking history found.");
            return;
        }

        System.out.println("Tracking history for baggage " + baggageId + ":");

        for (String event : events) {
            System.out.println("- " + event);
        }
    }
}