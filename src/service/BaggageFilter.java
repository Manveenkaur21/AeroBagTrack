package service;

import model.Baggage;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BaggageFilter {
    public static List<Baggage> filter(List<Baggage> list, Predicate<Baggage> condition) {
        return list.stream().filter(condition).collect(Collectors.toList());
    }

    public static List<Baggage> filterByStatus(List<Baggage> list, String status) {
        return filter(list, b -> b.getCurrentStatus().equalsIgnoreCase(status));
    }

    public static List<Baggage> filterByColor(List<Baggage> list, String color) {
        return filter(list, b -> b.getColor().equalsIgnoreCase(color));
    }
}
