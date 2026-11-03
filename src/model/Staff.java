package model;

public class Staff extends User {
    private String designation;

    public Staff(int userId, String name, String phone, String designation) {
        super(userId, name, phone);
        this.designation = designation;
    }

    public String getDesignation() { return designation; }

    @Override
    public void displayDetails() {
        System.out.println("Staff ID: " + userId + ", Name: " + name
                + ", Phone: " + phone + ", Designation: " + designation);
    }
}
