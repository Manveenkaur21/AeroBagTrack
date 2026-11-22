package util;

import model.Baggage;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class SerializationHandler {

    private static final String FILE_NAME = "baggage_data.ser";

    public static void saveBaggage(List<Baggage> baggageList) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {

            out.writeObject(new ArrayList<>(baggageList));
            System.out.println("Baggage data serialized successfully!");

        } catch (IOException e) {
            System.out.println("Serialization error: " + e.getMessage());
        }
    }

    public static void readBaggage() {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(FILE_NAME))) {

            List<Baggage> baggageList =
                    (List<Baggage>) in.readObject();

            for (Baggage baggage : baggageList) {
                System.out.println(baggage);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization error: " + e.getMessage());
        }
    }
}