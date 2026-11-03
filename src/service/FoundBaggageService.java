package service;

import model.FoundBaggage;
import java.util.ArrayList;
import java.util.List;

public class FoundBaggageService {
    private final List<FoundBaggage> foundList = new ArrayList<>();

    public boolean registerFoundBaggage(FoundBaggage item) {
        if (item == null || searchFoundBaggage(item.getFoundId()) != null) return false;
        foundList.add(item);
        return true;
    }

    public FoundBaggage searchFoundBaggage(int id) {
        return foundList.stream().filter(f -> f.getFoundId() == id).findFirst().orElse(null);
    }

    public List<FoundBaggage> getFoundList() { return new ArrayList<>(foundList); }
    public int getTotalFoundBaggage() { return foundList.size(); }

    public void displayAllFoundBaggage() {
        if (foundList.isEmpty()) System.out.println("No found baggage recorded.");
        else foundList.forEach(FoundBaggage::displayFoundBaggage);
    }
}
