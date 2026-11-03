import model.Baggage;
import model.Complaint;
import model.FoundBaggage;
import service.BaggageService;
import service.TrackingService;
import service.ComplaintService;
import service.FoundBaggageService;
import service.MatchingService;
import util.CSVHandler;
import util.FileHandler;

import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final BaggageService baggageService = new BaggageService();
    private static final TrackingService trackingService = new TrackingService();
    private static final ComplaintService complaintService = new ComplaintService();
    private static final FoundBaggageService foundService = new FoundBaggageService();
    private static final MatchingService matchingService = new MatchingService();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n===== AEROBAGTRACK =====");
            System.out.println("1. Register baggage");
            System.out.println("2. Display baggage");
            System.out.println("3. Track baggage");
            System.out.println("4. Update baggage status");
            System.out.println("5. Register complaint");
            System.out.println("6. Display complaints");
            System.out.println("7. Register found baggage");
            System.out.println("8. Display found baggage");
            System.out.println("9. Find lost/found matches");
            System.out.println("10. Save baggage to text file");
            System.out.println("11. Save baggage to CSV");
            System.out.println("12. Read baggage text file");
            System.out.println("0. Exit");
            int choice = readInt("Choose option: ");

            switch (choice) {
                case 1 -> registerBaggage();
                case 2 -> baggageService.displayAllBaggage();
                case 3 -> trackingService.trackBaggage(readInt("Baggage ID: "));
                case 4 -> updateBaggage();
                case 5 -> registerComplaint();
                case 6 -> complaintService.displayAllComplaints();
                case 7 -> registerFound();
                case 8 -> foundService.displayAllFoundBaggage();
                case 9 -> findMatches();
                case 10 -> FileHandler.saveBaggageRecords(baggageService.getBaggageList());
                case 11 -> CSVHandler.saveToCSV(baggageService.getBaggageList());
                case 12 -> FileHandler.readBaggageRecords();
                case 0 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
        System.out.println("Thank you for using AeroBagTrack!");
    }

    private static void registerBaggage() {
        int id = readInt("Baggage ID: ");
        int passengerId = readInt("Passenger ID: ");
        System.out.print("Baggage tag: ");
        String tag = input.nextLine();
        System.out.print("Description: ");
        String description = input.nextLine();
        System.out.print("Color: ");
        String color = input.nextLine();
        Baggage b = new Baggage(id, passengerId, tag, description, color);
        try {
            service.BaggageValidator.validate(b);
            if (baggageService.registerBaggage(b)) {
                trackingService.initializeTracking(id);
                System.out.println("Baggage registered.");
            } else System.out.println("Duplicate baggage ID.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }

    private static void updateBaggage() {
        int id = readInt("Baggage ID: ");
        System.out.print("New status: ");
        String status = input.nextLine();
        System.out.print("Current location: ");
        String location = input.nextLine();
        if (baggageService.updateBaggageStatus(id, status, location)) {
            trackingService.updateTracking(id, status + " at " + location);
            System.out.println("Baggage updated.");
        } else System.out.println("Baggage not found.");
    }

    private static void registerComplaint() {
        int complaintId = readInt("Complaint ID: ");
        int passengerId = readInt("Passenger ID: ");
        int baggageId = readInt("Baggage ID: ");
        System.out.print("Complaint details: ");
        String details = input.nextLine();
        if (complaintService.registerComplaint(
                new Complaint(complaintId, passengerId, baggageId, details)))
            System.out.println("Complaint registered.");
        else System.out.println("Duplicate complaint ID.");
    }

    private static void registerFound() {
        int id = readInt("Found record ID: ");
        System.out.print("Baggage tag: ");
        String tag = input.nextLine();
        System.out.print("Description: ");
        String description = input.nextLine();
        System.out.print("Color: ");
        String color = input.nextLine();
        System.out.print("Found location: ");
        String location = input.nextLine();
        if (foundService.registerFoundBaggage(
                new FoundBaggage(id, tag, description, color, location)))
            System.out.println("Found baggage recorded.");
        else System.out.println("Duplicate record ID.");
    }

    private static void findMatches() {
        int id = readInt("Lost baggage ID: ");
        Baggage lost = baggageService.searchBaggage(id);
        if (lost == null) {
            System.out.println("Baggage not found.");
            return;
        }
        matchingService.displayMatches(
                matchingService.findMatches(lost, foundService.getFoundList()));
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine();
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }
}
