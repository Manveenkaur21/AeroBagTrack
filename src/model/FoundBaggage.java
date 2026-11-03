package model;

public class FoundBaggage {
    private int foundId;
    private String baggageTag;
    private String description;
    private String color;
    private String foundLocation;
    private String recoveryStatus = "Unclaimed";

    public FoundBaggage(int foundId, String baggageTag, String description,
                        String color, String foundLocation) {
        this.foundId = foundId;
        this.baggageTag = baggageTag;
        this.description = description;
        this.color = color;
        this.foundLocation = foundLocation;
    }

    public int getFoundId() { return foundId; }
    public String getBaggageTag() { return baggageTag; }
    public String getDescription() { return description; }
    public String getColor() { return color; }
    public String getFoundLocation() { return foundLocation; }
    public String getRecoveryStatus() { return recoveryStatus; }
    public void updateRecoveryStatus(String status) { recoveryStatus = status; }

    public void displayFoundBaggage() {
        System.out.println("Found ID: " + foundId + ", Tag: " + baggageTag
                + ", Description: " + description + ", Color: " + color
                + ", Location: " + foundLocation + ", Status: " + recoveryStatus);
    }
}
