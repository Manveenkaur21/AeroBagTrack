
package service;

import model.Baggage;
import java.util.ArrayDeque;
import java.util.Deque;

public class BaggageDeque {

    private Deque<Baggage> baggageDeque;

    public BaggageDeque() {
        baggageDeque = new ArrayDeque<>();
    }

    public void addAtFront(Baggage baggage) {
        if (baggage != null) {
            baggageDeque.addFirst(baggage);
            System.out.println("Baggage added at front!");
        }
    }

    public void addAtRear(Baggage baggage) {
        if (baggage != null) {
            baggageDeque.addLast(baggage);
            System.out.println("Baggage added at rear!");
        }
    }

    public void removeFromFront() {
        Baggage baggage = baggageDeque.pollFirst();

        if (baggage == null) {
            System.out.println("Deque is empty.");
        } else {
            System.out.println("Removed Baggage ID: "
                    + baggage.getBaggageId());
        }
    }

    public void removeFromRear() {
        Baggage baggage = baggageDeque.pollLast();

        if (baggage == null) {
            System.out.println("Deque is empty.");
        } else {
            System.out.println("Removed Baggage ID: "
                    + baggage.getBaggageId());
        }
    }

    public void displayDeque() {
        if (baggageDeque.isEmpty()) {
            System.out.println("Deque is empty.");
            return;
        }

        System.out.println("\n===== BAGGAGE DEQUE =====");

        for (Baggage baggage : baggageDeque) {
            System.out.println("Baggage ID: "
                    + baggage.getBaggageId());
        }
    }

    public int getDequeSize() {
        return baggageDeque.size();
    }
}
