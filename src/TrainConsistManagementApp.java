import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * UC14: Custom Exception for Domain-Specific Errors
 */
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

/**
 * Bogie Class: Updated with Fail-Fast Validation (UC14)
 */
class Bogie {
    private String id;
    private String type;
    private String cargo;
    private int capacity;

    // UC14: Constructor with Capacity Validation
    Bogie(String id, String type, String cargo, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero. Provided: " + capacity);
        }
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
        return String.format("[%s | %s | %d]", id, type, capacity);
    }
}

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        // UC11: Regex Validation
        String trainId = "TRN-5566";
        if (Pattern.matches("TRN-\\d{4}", trainId)) {
            System.out.println("Validated Train ID: " + trainId);
        }

        List<Bogie> trainConsist = new ArrayList<>();

        // --- UC14: Handling Invalid Bogie Capacity ---
        System.out.println("\n--- Attempting Bogie Creation (Exception Handling) ---");

        try {
            // Case 1: Valid Bogie
            Bogie b1 = new Bogie("B001", "Sleeper", "Passengers", 72);
            trainConsist.add(b1);
            System.out.println("✔ Successfully added: " + b1);

            // Case 2: Invalid Bogie (This will trigger the catch block)
            System.out.println("Attempting to add bogie with zero capacity...");
            Bogie b2 = new Bogie("B002", "Sleeper", "Passengers", 0);
            trainConsist.add(b2);

        } catch (InvalidCapacityException e) {
            System.err.println("❌ FAILED: " + e.getMessage());
        }

        try {
            // Case 3: Negative Capacity
            System.out.println("Attempting to add bogie with negative capacity...");
            Bogie b3 = new Bogie("B003", "AC Chair", "Passengers", -10);
            trainConsist.add(b3);
        } catch (InvalidCapacityException e) {
            System.err.println("❌ FAILED: " + e.getMessage());
        }

        // --- UC10 & UC12: Analytics & Safety Audit ---
        System.out.println("\n--- Post-Validation Consist State ---");
        int totalCapacity = trainConsist.stream().mapToInt(Bogie::getCapacity).sum();
        System.out.println("Total Valid Seating: " + totalCapacity);

        boolean isSafe = trainConsist.stream()
                .allMatch(b -> !b.getType().equals("Cylindrical") || b.getCargo().equals("Petroleum"));
        System.out.println("Safety Audit: " + (isSafe ? "PASS ✅" : "FAIL ❌"));

        System.out.println("\nStatus: UC14 Validation Complete.");
    }
}