package model;

public class Passenger extends User {
    private String passportNumber;

    public Passenger(int userId, String name, String phone, String passportNumber) {
        super(userId, name, phone);
        this.passportNumber = passportNumber;
    }

    public String getPassportNumber() { return passportNumber; }

    @Override
    public void displayDetails() {
        System.out.println("Passenger ID: " + userId + ", Name: " + name
                + ", Phone: " + phone + ", Passport: " + passportNumber);
    }
}
