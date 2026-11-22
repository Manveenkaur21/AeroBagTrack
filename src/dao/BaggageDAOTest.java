package dao;

import model.Baggage;

public class BaggageDAOTest {

    public static void main(String[] args) {

        BaggageDAO dao = new BaggageDAO();

        Baggage baggage = new Baggage(
                101,
                1,
                "TAG101",
                "Black trolley bag",
                "Black"
        );

        boolean inserted = dao.addBaggage(baggage);

        if (inserted) {
            System.out.println("Baggage inserted successfully!");
        } else {
            System.out.println("Baggage insertion failed!");
        }

        Baggage result = dao.getBaggageById(101);

        if (result != null) {
            System.out.println("Baggage retrieved successfully!");
            result.displayBaggage();
        }
    }
}