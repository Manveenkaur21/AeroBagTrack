package service;

import model.Baggage;
import model.FoundBaggage;
import java.util.List;
import java.util.stream.Collectors;

public class MatchingService {
    public List<FoundBaggage> findMatches(Baggage lost, List<FoundBaggage> foundList) {
        if (lost == null || foundList == null) return List.of();
        return foundList.stream().filter(found ->
                lost.getBaggageTag().equalsIgnoreCase(found.getBaggageTag())
                || (lost.getColor().equalsIgnoreCase(found.getColor())
                && lost.getDescription().equalsIgnoreCase(found.getDescription())))
                .collect(Collectors.toList());
    }

    public void displayMatches(List<FoundBaggage> matches) {
        if (matches.isEmpty()) System.out.println("No matching baggage found.");
        else matches.forEach(FoundBaggage::displayFoundBaggage);
    }
}
