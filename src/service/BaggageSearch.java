package service;

import model.Baggage;
import java.util.List;

public class BaggageSearch {
    public static Baggage searchById(List<Baggage> list, int id) {
        return list.stream().filter(b -> b.getBaggageId() == id).findFirst().orElse(null);
    }

    public static Baggage searchByTag(List<Baggage> list, String tag) {
        return list.stream().filter(b -> b.getBaggageTag().equalsIgnoreCase(tag))
                .findFirst().orElse(null);
    }

    public static void searchByColor(List<Baggage> list, String color) {
        list.stream().filter(b -> b.getColor().equalsIgnoreCase(color))
                .forEach(System.out::println);
    }
}
