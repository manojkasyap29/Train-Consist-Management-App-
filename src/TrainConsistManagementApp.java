import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Bogie Class: Represents both Passenger and Goods units (UC7 - UC12)
 */
class Bogie {
    private String id;
    private String type; // e.g., "Sleeper", "Cylindrical", "Rectangular"
    private String cargo; // e.g., "Passengers", "Petroleum", "Coal"
    private int capacity;

    // Constructor for UC12 logic
    Bogie(String id, String type, String cargo, int capacity) {
        this.id = id;
        this.type = type;
        this.cargo = cargo;
        this.capacity = capacity;
    }

    public String getType() { return type; }
    public String getCargo() { return cargo; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return String.format("[%s | %s | %s]", type, cargo, capacity);
    }
}

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        // --- UC1 - UC5: Consist Management ---
        System.out.println("=== Train Consist Management App ===");

        // --- UC11: Regex Validation ---
        String trainId = "TRN-9876";
        if (Pattern.matches("TRN-\\d{4}", trainId)) {
            System.out.println("Train ID " + trainId + " validated successfully.");
        }

        // --- UC7 - UC10: Collection & Aggregation Logic ---
        List<Bogie> trainConsist = new ArrayList<>();
        trainConsist.add(new Bogie("B001", "Sleeper", "Passengers", 72));
        trainConsist.add(new Bogie("B002", "AC Chair", "Passengers", 56));

        // --- UC12: Safety Compliance Check for Goods Bogies ---
        System.out.println("\n--- Safety Compliance Verification (allMatch) ---");

        // Adding Goods Bogies for UC12 scenario
        trainConsist.add(new Bogie("G001", "Cylindrical", "Petroleum", 3000));
        trainConsist.add(new Bogie("G002", "Rectangular", "Coal", 5000));
        // trainConsist.add(new Bogie("G003", "Cylindrical", "Coal", 3000)); // Uncomment to trigger safety violation

        System.out.println("Current Goods Consist: " + trainConsist.stream()
                .filter(b -> !b.getCargo().equals("Passengers"))
                .collect(Collectors.toList()));

        /**
         * SAFETY RULE:
         * If type is 'Cylindrical', cargo MUST be 'Petroleum'.
         * Non-cylindrical bogies can carry anything.
         */
        boolean isSafetyCompliant = trainConsist.stream()
                .allMatch(b -> {
                    if (b.getType().equalsIgnoreCase("Cylindrical")) {
                        return b.getCargo().equalsIgnoreCase("Petroleum");
                    }
                    return true; // Non-cylindrical bogies pass by default
                });

        System.out.println("\nExecuting Safety Audit...");
        if (isSafetyCompliant) {
            System.out.println("✅ RESULT: Train is SAFETY COMPLIANT. Ready for dispatch.");
        } else {
            System.out.println("❌ RESULT: SAFETY VIOLATION! Cylindrical bogies detected with unauthorized cargo.");
        }

        // --- UC10 Aggregation (Final Metric) ---
        int totalCapacity = trainConsist.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);
        System.out.println("Total Train Load/Seat Capacity: " + totalCapacity);
    }
}