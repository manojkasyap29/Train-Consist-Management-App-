import java.util.*;

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        // --- UC1 to UC4 Recap ---
        System.out.println("=== Train Consist Management App ===");

        // --- UC5: Preserve Insertion Order (LinkedHashSet) ---
        System.out.println("\n--- Forming Train with LinkedHashSet (Unique & Ordered) ---");

        // Initializing LinkedHashSet
        Set<String> trainFormation = new LinkedHashSet<>();

        // 1. Attaching bogies in a specific sequence
        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Cargo");
        trainFormation.add("Guard");

        System.out.println("Current Formation: " + trainFormation);

        // 2. Attempting to add a duplicate (Business Rule: One physical bogie per train)
        System.out.println("Attempting to re-attach 'Sleeper'...");
        boolean isAdded = trainFormation.add("Sleeper");

        if (!isAdded) {
            System.out.println("System Alert: Duplicate Bogie detected! 'Sleeper' is already in formation.");
        }

        // 3. Displaying final formation
        // Note: The order will be exactly Engine -> Sleeper -> Cargo -> Guard
        System.out.println("\nFinal Verified Train Formation (Insertion Order):");
        for (String bogie : trainFormation) {
            System.out.println("-> " + bogie);
        }

        System.out.println("Total unique units attached: " + trainFormation.size());
    }
}