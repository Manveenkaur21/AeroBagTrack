package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackingService {
    private final Map<Integer, List<String>> history = new HashMap<>();

    public void initializeTracking(int baggageId) {
        history.putIfAbsent(baggageId, new ArrayList<>());
        updateTracking(baggageId, "Registered at Check-in Counter");
    }

    public void updateTracking(int baggageId, String event) {
        history.computeIfAbsent(baggageId, key -> new ArrayList<>()).add(event);
    }

    public void trackBaggage(int baggageId) {
        List<String> events = history.get(baggageId);
        if (events == null) {
            System.out.println("No tracking history found.");
            return;
        }
        displayTrackingHistory(baggageId);
    }

    public void displayTrackingHistory(int baggageId) {
        List<String> events = history.get(baggageId);
        if (events == null || events.isEmpty()) {
            System.out.println("No tracking history found.");
            return;
        }
        System.out.println("Tracking history for baggage " + baggageId + ":");
        for (String event : events) System.out.println("- " + event);
    }
}
