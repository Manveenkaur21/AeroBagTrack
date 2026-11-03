package service;

import model.Baggage;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BaggageAnalysis {
    public static List<Baggage> filterByStatus(List<Baggage> list, String status) {
        return list.stream()
                .filter(b -> b.getCurrentStatus().equalsIgnoreCase(status))
                .sorted(Comparator.comparingInt(Baggage::getBaggageId))
                .collect(Collectors.toList());
    }

    public static void displayByStatus(List<Baggage> list, String status) {
        List<Baggage> result = filterByStatus(list, status);
        if (result.isEmpty()) System.out.println("No baggage with status: " + status);
        else result.forEach(System.out::println);
    }
}
