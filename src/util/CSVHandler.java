package util;

import model.Baggage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVHandler {
    private static final String FILE_NAME = "baggage_records.csv";

    public static void saveToCSV(List<Baggage> baggageList) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("baggageId,passengerId,tag,description,color,status,location\n");
            for (Baggage b : baggageList) {
                writer.write(b.getBaggageId() + "," + b.getPassengerId() + ","
                        + clean(b.getBaggageTag()) + "," + clean(b.getDescription()) + ","
                        + clean(b.getColor()) + "," + clean(b.getCurrentStatus()) + ","
                        + clean(b.getCurrentLocation()) + "\n");
            }
            System.out.println("CSV file saved: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("CSV save error: " + e.getMessage());
        }
    }

    public static void readFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) System.out.println(line);
        } catch (IOException e) {
            System.out.println("CSV read error: " + e.getMessage());
        }
    }

    private static String clean(String value) {
        return value == null ? "" : value.replace(",", " ");
    }
}
