
package service;

import model.Baggage;
import java.util.LinkedList;
import java.util.Queue;

public class BaggageQueue {

    private Queue<Baggage> baggageQueue;

    public BaggageQueue() {
        baggageQueue = new LinkedList<>();
    }

    public void addBaggage(Baggage baggage) {
        if (baggage != null) {
            baggageQueue.offer(baggage);
            System.out.println("Baggage added to queue successfully!");
        }
    }

    public Baggage processNextBaggage() {
        Baggage baggage = baggageQueue.poll();

        if (baggage == null) {
            System.out.println("No baggage available in queue.");
        } else {
            System.out.println("Processing Baggage ID: "
                    + baggage.getBaggageId());
        }

        return baggage;
    }

    public void displayQueue() {
        if (baggageQueue.isEmpty()) {
            System.out.println("Baggage queue is empty.");
            return;
        }

        System.out.println("\n===== BAGGAGE QUEUE =====");

        for (Baggage baggage : baggageQueue) {
            System.out.println("Baggage ID: "
                    + baggage.getBaggageId());
        }
    }

    public int getQueueSize() {
        return baggageQueue.size();
    }
}
