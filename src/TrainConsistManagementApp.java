import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * UC14: Custom Checked Exception
 */
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) { super(message); }
}

/**
 * UC15: Custom Runtime Exception for Operational Safety
 */
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) { super(message); }
}

/**
 * Bogie Class: The core domain object (Integrated UC1-UC15)
 */
class Bogie {
    private String id;
    private String shape; // e.g., "Cylindrical", "Rectangular"
    private String cargo;
    private int capacity;

    Bogie(String id, String shape, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) throw new InvalidCapacityException("Capacity must be > 0.");
        this.id = id;
        this.shape = shape;
        this.capacity = capacity;
        this.cargo = "Empty";
    }

    // UC15: Safe Cargo Assignment Logic
    public void assignCargo(String newCargo) {
        System.out.println(">>> Attempting to load " + newCargo + " into " + id + " (" + shape + ")...");
        try {
            if (shape.equalsIgnoreCase("Rectangular") && newCargo.equalsIgnoreCase("Petroleum")) {
                throw new CargoSafetyException("SAFETY ALERT: Petroleum cannot be loaded into Rectangular bogies!");
            }
            this.cargo = newCargo;
            System.out.println("✅ SUCCESS: " + newCargo + " assigned to " + id);
        } catch (CargoSafetyException e) {
            System.err.println("❌ ERROR: " + e.getMessage());
        } finally {
            System.out.println("--- Loading Audit Log: Operation completed for " + id + " ---");
        }
    }

    public String getShape() { return shape; }
    public String getCargo() { return cargo; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return String.format("[%s | %s | %s | %d]", id, shape, cargo, capacity);
    }
}

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App [v15.0 Final] ===");

        // UC11: Regex Validation for Train ID
        if (Pattern.matches("TRN-\\d{4}", "TRN-2026")) {
            System.out.println("System online: Train TRN-2026 authorized.");
        }

        List<Bogie> trainConsist = new ArrayList<>();

        try {
            // UC14: Creating Bogies (Checked Exception Handling)
            Bogie g1 = new Bogie("G101", "Cylindrical", 3000);
            Bogie g2 = new Bogie("G102", "Rectangular", 5000);
            trainConsist.add(g1);
            trainConsist.add(g2);

            // UC15: Dynamic Cargo Assignment (Runtime Exception Handling)
            System.out.println("\n--- Starting Cargo Loading Operations ---");

            // Valid Assignment
            g1.assignCargo("Petroleum");

            System.out.println();

            // Invalid Assignment (Handled by try-catch-finally inside the method)
            g2.assignCargo("Petroleum");

            // Valid Assignment
            g2.assignCargo("Coal");

        } catch (InvalidCapacityException e) {
            System.err.println("Fatal Creation Error: " + e.getMessage());
        }

        // UC10: Final Analytics using Streams
        System.out.println("\n--- Final Train Consist Status ---");
        trainConsist.forEach(System.out::println);

        int totalCap = trainConsist.stream().mapToInt(Bogie::getCapacity).sum();
        System.out.println("\nTotal Operational Capacity: " + totalCap);
        System.out.println("System Status: All operations logged and completed.");
    }
}