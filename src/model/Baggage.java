package model;

import java.io.Serializable;

public class Baggage implements Comparable<Baggage>, Serializable {
    private static final long serialVersionUID = 1L;
    private int baggageId;
    private int passengerId;
    private String baggageTag;
    private String description;
    private String color;
    private String currentStatus;
    private String currentLocation;

    public Baggage(int baggageId, int passengerId, String baggageTag,
                   String description, String color) {
        this.baggageId = baggageId;
        this.passengerId = passengerId;
        this.baggageTag = baggageTag;
        this.description = description;
        this.color = color;
        this.currentStatus = "Registered";
        this.currentLocation = "Check-in Counter";
    }

    public int getBaggageId() { return baggageId; }
    public int getPassengerId() { return passengerId; }
    public String getBaggageTag() { return baggageTag; }
    public String getDescription() { return description; }
    public String getColor() { return color; }
    public String getCurrentStatus() { return currentStatus; }
    public String getCurrentLocation() { return currentLocation; }

    public void updateStatus(String status, String location) {
        this.currentStatus = status;
        this.currentLocation = location;
    }

    public void displayBaggage() {
        System.out.println(this);
    }

    @Override
    public int compareTo(Baggage other) {
        return Integer.compare(this.baggageId, other.baggageId);
    }

    @Override
    public String toString() {
        return "Baggage{ID=" + baggageId + ", PassengerID=" + passengerId
                + ", Tag='" + baggageTag + "', Description='" + description
                + "', Color='" + color + "', Status='" + currentStatus
                + "', Location='" + currentLocation + "'}";
    }
}
