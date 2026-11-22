package service;

import model.FoundBaggage;
import dao.FoundBaggageDAO;
import java.util.List;

public class FoundBaggageService {

    private final FoundBaggageDAO foundBaggageDAO = new FoundBaggageDAO();

    public boolean registerFoundBaggage(FoundBaggage baggage) {
        if (baggage == null) {
            return false;
        }
        return foundBaggageDAO.addFoundBaggage(baggage);
    }

    public List<FoundBaggage> getFoundList() {
        return foundBaggageDAO.getAllFoundBaggage();
    }

    public void displayAllFoundBaggage() {
        List<FoundBaggage> list = getFoundList();

        if (list.isEmpty()) {
            System.out.println("No found baggage records.");
        } else {
            list.forEach(FoundBaggage::displayFoundBaggage);
        }
    }
}