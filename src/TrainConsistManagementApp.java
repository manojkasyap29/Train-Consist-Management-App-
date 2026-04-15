import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        // --- UC1 & UC2: Consist Initialization ---
        System.out.println("=== Train Consist Management App ===");
        List<String> trainConsist = new ArrayList<>();

        // --- UC3: Track Unique Bogie IDs (HashSet) ---
        System.out.println("\n--- Initializing Bogie ID Tracking (Uniqueness Check) ---");

        // Using Set interface with HashSet implementation
        Set<String> bogieIds = new HashSet<>();

        // Adding Bogie IDs (including an intentional duplicate)
        System.out.println("Registering Bogie IDs: B101, B102, B103, B101 (duplicate)...");
        bogieIds.add("B101");
        bogieIds.add("B102");
        bogieIds.add("B103");

        // Attempting to add a duplicate
        boolean isAdded = bogieIds.add("B101");

        // 1. Check if the duplicate was accepted
        if (!isAdded) {
            System.out.println("Alert: Bogie ID 'B101' is already registered. Duplicate rejected.");
        }

        // 2. Display Unique IDs
        System.out.println("Unique Bogie IDs in System: " + bogieIds);
        System.out.println("Total Unique Bogies: " + bogieIds.size());

        System.out.println("\nStatus: Data integrity verified. No duplicate bogies detected.");
    }
}