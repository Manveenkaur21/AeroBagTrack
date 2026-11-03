package service;

import model.Baggage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaggageService {
    private final Map<Integer, Baggage> baggageMap = new HashMap<>();

    public boolean registerBaggage(Baggage baggage) {
        if (baggage == null || baggageMap.containsKey(baggage.getBaggageId())) return false;
        baggageMap.put(baggage.getBaggageId(), baggage);
        return true;
    }

    public Baggage searchBaggage(int id) { return baggageMap.get(id); }
    public List<Baggage> getBaggageList() { return new ArrayList<>(baggageMap.values()); }
    public int getTotalBaggage() { return baggageMap.size(); }

    public boolean updateBaggageStatus(int id, String status, String location) {
        Baggage baggage = baggageMap.get(id);
        if (baggage == null) return false;
        baggage.updateStatus(status, location);
        return true;
    }

    public void displayAllBaggage() {
        if (baggageMap.isEmpty()) System.out.println("No baggage registered.");
        else getBaggageList().stream().sorted().forEach(System.out::println);
    }
}
