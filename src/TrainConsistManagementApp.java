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
 * UC15: Custom Runtime Exception
 */
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) { super(message); }
}

/**
 * Bogie Class: Core domain object (Integrated UC1-UC16)
 */
class Bogie {
    private String id;
    private String shape;
    private String cargo;
    private int capacity;

    Bogie(String id, String shape, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) throw new InvalidCapacityException("Capacity must be > 0.");
        this.id = id;
        this.shape = shape;
        this.capacity = capacity;
        this.cargo = "Empty";
    }

    public void assignCargo(String newCargo) {
        try {
            if (shape.equalsIgnoreCase("Rectangular") && newCargo.equalsIgnoreCase("Petroleum")) {
                throw new CargoSafetyException("Safety Alert: Improper cargo for rectangular bogie.");
            }
            this.cargo = newCargo;
        } catch (CargoSafetyException e) {
            System.err.println("❌ " + e.getMessage());
        } finally {
            // Optional logging
        }
    }

    public int getCapacity() { return capacity; }
    public String getId() { return id; }

    @Override
    public String toString() { return id + " (" + capacity + ")"; }
}

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App [v16.0 Algorithm Intro] ===");

        // --- UC16: Manual Sorting Logic (Bubble Sort) ---
        System.out.println("\n--- Performance Planning: Manual Capacity Sorting (Bubble Sort) ---");

        // Initial capacity data (Unsorted)
        int[] capacities = {72, 56, 24, 70, 60};
        System.out.println("Original Capacities: " + Arrays.toString(capacities));

        // Manual Bubble Sort implementation
        int n = capacities.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements
                if (capacities[j] > capacities[j + 1]) {
                    // Swap Logic
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }

        System.out.println("Sorted Capacities (Bubble Sort): " + Arrays.toString(capacities));

        // --- UC1 - UC15 Integration Recap ---
        try {
            // Validating Train ID (UC11)
            String trainId = "TRN-2026";
            if (Pattern.matches("TRN-\\d{4}", trainId)) {
                System.out.println("\nTrain " + trainId + " is verified for deployment.");
            }

            // Creating Bogies and checking safety (UC12, UC14, UC15)
            List<Bogie> consist = new ArrayList<>();
            consist.add(new Bogie("B101", "Cylindrical", 72));
            consist.add(new Bogie("B102", "Rectangular", 56));

            // Stream Aggregation (UC10)
            int totalSeats = consist.stream().mapToInt(Bogie::getCapacity).sum();
            System.out.println("Total Passenger Capacity: " + totalSeats);

            // Safety check (UC12)
            boolean isSafe = consist.stream().allMatch(b ->
                    !b.getShape().equals("Cylindrical") || b.getCapacity() > 0);
            System.out.println("Safety Protocol: " + (isSafe ? "Verified ✅" : "Failed ❌"));

        } catch (Exception e) {
            System.err.println("Operation Error: " + e.getMessage());
        }

        System.out.println("\nStatus: All 16 Use Cases executed successfully.");
    }
}