import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Custom Exceptions (UC14 & UC15)
 */
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) { super(message); }
}

class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) { super(message); }
}

/**
 * Bogie Class (Integrated UC1-UC19)
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
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App [v19.0 Optimized Search] ===");

        // --- UC19: Binary Search for Bogie ID ---
        System.out.println("\n--- Optimized Bogie Locator (Binary Search) ---");

        // Step 1: Data must be sorted for Binary Search
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        Arrays.sort(bogieIds); // Ensure natural ordering

        String searchKey = "BG412";
        System.out.println("Consist IDs (Sorted): " + Arrays.toString(bogieIds));
        System.out.println("Searching for: " + searchKey);

        // Step 2: Binary Search Logic (Divide and Conquer)
        int low = 0;
        int high = bogieIds.length - 1;
        int foundAt = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = searchKey.compareTo(bogieIds[mid]);

            if (comparison == 0) {
                foundAt = mid;
                break; // Match found
            } else if (comparison > 0) {
                low = mid + 1; // Search right half
            } else {
                high = mid - 1; // Search left half
            }
        }

        if (foundAt != -1) {
            System.out.println("✔ Result: Bogie found at index " + foundAt + " using Binary Search.");
        } else {
            System.out.println("❌ Result: Bogie not found.");
        }

        // --- UC13: Performance Note ---
        // Binary Search complexity: O(log n) vs Linear Search: O(n)

        // --- UC11: Regex Validation ---
        if (Pattern.matches("TRN-\\d{4}", "TRN-2026")) {
            System.out.println("\nTrain authorization verified.");
        }

        // --- UC10: Stream Aggregation ---
        try {
            List<Bogie> consist = new ArrayList<>();
            consist.add(new Bogie("BG101", "Sleeper", 72));
            consist.add(new Bogie("BG205", "AC Chair", 56));

            int totalSeats = consist.stream().mapToInt(Bogie::getCapacity).sum();
            System.out.println("Total Seating: " + totalSeats);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nStatus: UC19 complete. System optimized for high-speed lookup.");
    }
}