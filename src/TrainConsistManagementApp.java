import java.util.*;

/**
 * Train Consist Management App
 * UC1 - UC6: Comprehensive Train Management
 */
public class TrainConsistApp {
    public static void main(String[] args) {
        // --- UC1: Welcome Message & Initialization ---
        System.out.println("=== Train Consist Management App ===");
        List<String> trainConsist = new ArrayList<>();

        // --- UC2: Basic List Operations (Add/Remove) ---
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");
        trainConsist.remove("AC Chair");

        // --- UC3 & UC5: Unique & Ordered Formation (LinkedHashSet) ---
        // We use LinkedHashSet to ensure no duplicates while keeping the sequence
        Set<String> uniqueFormation = new LinkedHashSet<>();
        uniqueFormation.add("Engine");
        uniqueFormation.add("Sleeper");
        uniqueFormation.add("Cargo");
        uniqueFormation.add("Guard");

        // --- UC4: Chaining Logic (LinkedList) ---
        LinkedList<String> chain = new LinkedList<>(uniqueFormation);

        // --- UC6: Map Bogie to Capacity (HashMap) ---
        System.out.println("\n--- Bogie Capacity Mapping (HashMap) ---");

        // Key: Bogie Name (String), Value: Capacity (Integer)
        Map<String, Integer> bogieCapacityMap = new HashMap<>();

        // 1. Assigning capacities using put()
        bogieCapacityMap.put("Sleeper", 72);
        bogieCapacityMap.put("AC Chair", 56);
        bogieCapacityMap.put("First Class", 24);
        bogieCapacityMap.put("Cargo", 5000); // 5000kg load

        // 2. Iterating through the Map using entrySet()
        // This allows us to see both the Bogie and its associated data
        System.out.println("Bogie-Capacity Details:");
        for (Map.Entry<String, Integer> entry : bogieCapacityMap.entrySet()) {
            String unit = (entry.getKey().equals("Cargo")) ? " kg" : " Seats";
            System.out.println("Bogie: " + entry.getKey() + " | Capacity: " + entry.getValue() + unit);
        }

        // 3. Fast Lookup Example
        String searchBogie = "Sleeper";
        if (bogieCapacityMap.containsKey(searchBogie)) {
            System.out.println("\nQuick Search: " + searchBogie + " can hold " + bogieCapacityMap.get(searchBogie) + " passengers.");
        }

        System.out.println("\nStatus: Mapping complete. System ready for load validation.");
    }
}