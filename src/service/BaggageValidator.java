package service;

import model.Baggage;

public class BaggageValidator {
    public static void validate(Baggage baggage) {
        if (baggage == null) throw new IllegalArgumentException("Baggage cannot be null.");
        if (baggage.getBaggageId() <= 0) throw new IllegalArgumentException("Baggage ID must be positive.");
        if (baggage.getPassengerId() <= 0) throw new IllegalArgumentException("Passenger ID must be positive.");
        if (baggage.getBaggageTag() == null || baggage.getBaggageTag().isBlank())
            throw new IllegalArgumentException("Baggage tag is required.");
        if (baggage.getColor() == null || baggage.getColor().isBlank())
            throw new IllegalArgumentException("Baggage color is required.");
    }
}
