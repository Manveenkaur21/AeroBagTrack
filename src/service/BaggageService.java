package service;

import model.Baggage;
import dao.BaggageDAO;
import java.util.List;
import Exception.InvalidBaggageException;

public class BaggageService {

    private final BaggageDAO baggageDAO = new BaggageDAO();

    public boolean registerBaggage(Baggage baggage)
            throws InvalidBaggageException {

        if (baggage == null) {
            throw new InvalidBaggageException("Baggage cannot be null.");
        }

        if (baggage.getBaggageId() <= 0) {
            throw new InvalidBaggageException("Baggage ID must be positive.");
        }

        if (baggage.getPassengerId() <= 0) {
            throw new InvalidBaggageException("Passenger ID must be positive.");
        }

        if (baggage.getBaggageTag() == null || baggage.getBaggageTag().isBlank()) {
            throw new InvalidBaggageException("Baggage tag is required.");
        }

        if (baggage.getColor() == null || baggage.getColor().isBlank()) {
            throw new InvalidBaggageException("Baggage color is required.");
        }

        return baggageDAO.addBaggage(baggage);
    }

    public Baggage searchBaggage(int id) {
        return baggageDAO.getBaggageById(id);
    }

    public List<Baggage> getBaggageList() {
        return baggageDAO.getAllBaggage();
    }

    public int getTotalBaggage() {
        return baggageDAO.getAllBaggage().size();
    }

    public boolean updateBaggageStatus(int id, String status, String location) {
        return baggageDAO.updateBaggage(id, status, location);
    }

    public void displayAllBaggage() {
        List<Baggage> baggageList = getBaggageList();

        if (baggageList.isEmpty()) {
            System.out.println("No baggage registered.");
        } else {
            baggageList.stream().sorted().forEach(System.out::println);
        }
    }
}