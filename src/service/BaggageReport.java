package service;

import model.Baggage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaggageReport {
    public static void displayStatusReport(List<Baggage> list) {
        Map<String, Integer> counts = new HashMap<>();
        for (Baggage b : list)
            counts.merge(b.getCurrentStatus(), 1, Integer::sum);
        System.out.println("===== BAGGAGE STATUS REPORT =====");
        counts.forEach((status, count) -> System.out.println(status + ": " + count));
    }
}
