package service;

import model.Baggage;
import java.util.List;

public class BaggageStatistics {
    public static long total(List<Baggage> list) { return list.stream().count(); }

    public static long countByStatus(List<Baggage> list, String status) {
        return list.stream().filter(b -> b.getCurrentStatus().equalsIgnoreCase(status)).count();
    }

    public static void display(List<Baggage> list) {
        System.out.println("Total baggage: " + total(list));
        list.stream().map(Baggage::getCurrentStatus).distinct()
                .forEach(s -> System.out.println(s + ": " + countByStatus(list, s)));
    }
}
