package util;

import model.Baggage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "baggage_records.txt";

    public static void saveBaggageRecords(List<Baggage> baggageList) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Baggage b : baggageList) writer.write(b.toString() + System.lineSeparator());
            System.out.println("Baggage records saved.");
        } catch (IOException e) {
            System.out.println("File save error: " + e.getMessage());
        }
    }

    public static void readBaggageRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) System.out.println(line);
        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        }
    }
}
