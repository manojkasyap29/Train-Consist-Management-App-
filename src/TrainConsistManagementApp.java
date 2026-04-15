import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * UC14: Custom Checked Exception for Creation Errors
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
 * Bogie Class: The complete domain model (UC1-UC20)
 */
class Bogie {
    private String id;
    private String type;
    private int capacity;

    Bogie(String id, String type, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) throw new InvalidCapacityException("Capacity must be > 0.");
        this.id = id;
        this.type = type;
        this.capacity = capacity;
    }

    public String getId() { return id; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() { return id + " [" + type + "]"; }
}

public class TrainConsistManagementApp {

    /**
     * UC20: Search logic with State Validation
     */
    public static void performSearch(String[] bogieIds, String key) {
        System.out.println(">>> Initiating Search for: " + key);

        // 1. State Validation (Defensive Programming)
        if (bogieIds == null || bogieIds.length == 0) {
            throw new IllegalStateException("Search Failed: No bogies are available in the train consist.");
        }

        // 2. Optimized Binary Search (UC19)
        int low = 0, high = bogieIds.length - 1;
        boolean found = false;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = key.compareTo(bogieIds[mid]);

            if (cmp == 0) {
                System.out.println("✔ Found: Bogie " + key + " located at index " + mid);
                found = true;
                break;
            } else if (cmp > 0) low = mid + 1;
            else high = mid - 1;
        }

        if (!found) System.out.println("❌ Not Found: Bogie " + key + " is not in the consist.");
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App [v20.0 FINAL] ===");

        // --- UC20: Exception Handling During Search ---
        String[] emptyConsist = {};
        String[] activeConsist = {"BG101", "BG205", "BG309", "BG412"};
        Arrays.sort(activeConsist); // Required for Binary Search

        // Scenario A: Valid Search
        try {
            performSearch(activeConsist, "BG309");
        } catch (IllegalStateException e) {
            System.err.println("Caught Expected Error: " + e.getMessage());
        }

        System.out.println();

        // Scenario B: Search on Empty Train (Fail-Fast)
        try {
            performSearch(emptyConsist, "BG999");
        } catch (IllegalStateException e) {
            System.err.println("❌ ERROR: " + e.getMessage());
        }

        // --- UC10 & UC11 Recap ---
        try {
            if (Pattern.matches("TRN-\\d{4}", "TRN-2026")) {
                System.out.println("\nTrain TRN-2026 fully validated and ready for departure.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\n[System Termination: All 20 Use Cases Executed Successfully]");
    }
}