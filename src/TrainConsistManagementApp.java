import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}


class Bogie {
    private String id;
    private String type;
    private String cargo;
    private int capacity;

    
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

        String trainId = "TRN-5566";
        if (Pattern.matches("TRN-\\d{4}", trainId)) {
            System.out.println("Validated Train ID: " + trainId);
        }

        List<Bogie> trainConsist = new ArrayList<>();

        System.out.println("\n--- Attempting Bogie Creation (Exception Handling) ---");

        try {
            Bogie b1 = new Bogie("B001", "Sleeper", "Passengers", 72);
            trainConsist.add(b1);
            System.out.println("✔ Successfully added: " + b1);

            System.out.println("Attempting to add bogie with zero capacity...");
            Bogie b2 = new Bogie("B002", "Sleeper", "Passengers", 0);
            trainConsist.add(b2);

        } catch (InvalidCapacityException e) {
            System.err.println("❌ FAILED: " + e.getMessage());
        }

        try {
            System.out.println("Attempting to add bogie with negative capacity...");
            Bogie b3 = new Bogie("B003", "AC Chair", "Passengers", -10);
            trainConsist.add(b3);
        } catch (InvalidCapacityException e) {
            System.err.println("❌ FAILED: " + e.getMessage());
        }

        System.out.println("\n--- Post-Validation Consist State ---");
        int totalCapacity = trainConsist.stream().mapToInt(Bogie::getCapacity).sum();
        System.out.println("Total Valid Seating: " + totalCapacity);

        boolean isSafe = trainConsist.stream()
                .allMatch(b -> !b.getType().equals("Cylindrical") || b.getCargo().equals("Petroleum"));
        System.out.println("Safety Audit: " + (isSafe ? "PASS ✅" : "FAIL ❌"));

        System.out.println("\nStatus: UC14 Validation Complete.");
    }
}
