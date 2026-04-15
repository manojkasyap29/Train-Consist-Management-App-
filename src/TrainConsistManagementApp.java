import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Custom Exceptions from UC14 & UC15
 */
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) { super(message); }
}

class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) { super(message); }
}

/**
 * Bogie Class (Integrated UC1-UC18)
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
    public String getType() { return type; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() { return id + " [" + type + "]"; }
}

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App [v18.0 Search Integration] ===");

        // --- UC18: Linear Search for Bogie ID ---
        System.out.println("\n--- Bogie Locator (Linear Search) ---");

        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        String searchKey = "BG309";
        boolean found = false;
        int position = -1;

        System.out.println("Searching for Bogie ID: " + searchKey + "...");

        // Linear Search Logic: Sequential Traversal
        for (int i = 0; i < bogieIds.length; i++) {
            if (bogieIds[i].equals(searchKey)) {
                found = true;
                position = i;
                break; // Early Termination once match is found
            }
        }

        if (found) {
            System.out.println("✔ Bogie " + searchKey + " found at position: " + position);
        } else {
            System.out.println("❌ Bogie " + searchKey + " not found in the consist.");
        }

        // --- UC17 & UC16: Sorting Recap ---
        String[] types = {"Sleeper", "AC Chair", "General"};
        Arrays.sort(types);
        System.out.println("\nSorted Bogie Types (UC17): " + Arrays.toString(types));

        // --- UC1 - UC15 Integration Recap ---
        try {
            // UC11: Regex Validation
            if (Pattern.matches("TRN-\\d{4}", "TRN-2026")) {
                System.out.println("Train ID Format Verified.");
            }

            // UC10: Stream Aggregation
            List<Bogie> consist = new ArrayList<>();
            consist.add(new Bogie("BG101", "Sleeper", 72));
            consist.add(new Bogie("BG205", "AC Chair", 56));

            int totalLoad = consist.stream().mapToInt(Bogie::getCapacity).sum();
            System.out.println("Consolidated Capacity: " + totalLoad + " units.");

        } catch (Exception e) {
            System.err.println("Operational Alert: " + e.getMessage());
        }

        System.out.println("\nStatus: Linear search complete. All 18 Use Cases active.");
    }
}