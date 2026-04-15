import java.util.*;
import java.util.stream.Collectors;

/**
 * Bogie Class representing a physical unit of the train (Introduced in UC7)
 */
class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() { return name; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return name + " [Capacity: " + capacity + "]";
    }
}

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        // --- UC1: Initialize Train and Display Consist Summary ---
        System.out.println("=== Train Consist Management App ===");
        List<String> trainConsist = new ArrayList<>();
        System.out.println("Initial bogie count: " + trainConsist.size());

        // --- UC2: Add/Remove Passenger Bogies (ArrayList Operations) ---
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");
        trainConsist.remove("AC Chair");

        // --- UC3 & UC5: Track Unique IDs & Preserve Insertion Order (LinkedHashSet) ---
        Set<String> uniqueIds = new LinkedHashSet<>();
        uniqueIds.add("B101");
        uniqueIds.add("B102");
        uniqueIds.add("B101"); // Duplicate - will be ignored

        // --- UC4: Maintain Ordered Bogie IDs (LinkedList) ---
        LinkedList<String> physicalSequence = new LinkedList<>();
        physicalSequence.addFirst("Engine");
        physicalSequence.addLast("Sleeper");
        physicalSequence.add(1, "Pantry Car");

        // --- UC6: Map Bogie to Capacity (HashMap) ---
        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 56);
        capacityMap.put("First Class", 24);

        // --- UC7: Sort Bogies by Capacity (Comparator) ---
        List<Bogie> passengerBogies = new ArrayList<>();
        passengerBogies.add(new Bogie("Sleeper", 72));
        passengerBogies.add(new Bogie("AC Chair", 56));
        passengerBogies.add(new Bogie("First Class", 24));
        passengerBogies.add(new Bogie("Special Coach", 80));

        passengerBogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        // --- UC8: Filter Passenger Bogies Using Streams ---
        System.out.println("\n--- High-Capacity Bogie Filtering (Stream API) ---");

        // Defining the threshold
        int capacityThreshold = 60;

        // Using Stream Pipeline: stream() -> filter() -> collect()
        List<String> highCapacityNames = passengerBogies.stream()
                .filter(b -> b.getCapacity() > capacityThreshold) // Threshold Filtering Logic
                .map(Bogie::getName)                              // Transform to names
                .collect(Collectors.toList());                    // Materialize to new list

        System.out.println("Filtering bogies with capacity > " + capacityThreshold + "...");

        if (highCapacityNames.isEmpty()) {
            System.out.println("No matching bogies found.");
        } else {
            System.out.println("High-Capacity Bogies: " + highCapacityNames);
        }

        // Demonstrating Original List Integrity
        System.out.println("\nVerification: Original list size remains " + passengerBogies.size());
        System.out.println("Status: Functional processing complete.");
    }
}