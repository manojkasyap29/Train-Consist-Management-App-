import java.util.*;

/**
 * UC7: Bogie Class to hold properties
 */
class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return name + " (Capacity: " + capacity + ")";
    }
}

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        // --- UC1: Initialization ---
        System.out.println("=== Train Consist Management App ===");

        // --- UC2 & UC4: List & LinkedList Operations ---
        LinkedList<String> trainSequence = new LinkedList<>();
        trainSequence.add("Engine");
        trainSequence.add("Sleeper");
        trainSequence.add("Cargo");
        trainSequence.add("Guard");

        // --- UC3 & UC5: Uniqueness & Order (LinkedHashSet) ---
        Set<String> uniqueFormation = new LinkedHashSet<>(trainSequence);

        // --- UC6: Key-Value Mapping (HashMap) ---
        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 56);
        capacityMap.put("First Class", 24);

        // --- UC7: Custom Sorting with Comparator ---
        System.out.println("\n--- Sorting Bogies by Capacity (Comparator) ---");

        // 1. Create a list of Custom Objects
        List<Bogie> passengerBogies = new ArrayList<>();
        passengerBogies.add(new Bogie("Sleeper", 72));
        passengerBogies.add(new Bogie("AC Chair", 56));
        passengerBogies.add(new Bogie("First Class", 24));

        System.out.println("Before Sorting: " + passengerBogies);

        // 2. Sorting using Comparator (Ascending order)
        // We use a Lambda expression to define the comparison logic
        passengerBogies.sort(Comparator.comparingInt(b -> b.capacity));

        // 3. Displaying sorted results
        System.out.println("After Sorting (Lowest to Highest Capacity):");
        for (Bogie b : passengerBogies) {
            System.out.println(">> " + b);
        }

        // Optional: Sorting in Descending order for identifying high-capacity bogies
        passengerBogies.sort((b1, b2) -> b2.capacity - b1.capacity);
        System.out.println("\nRanked by High-Capacity (Descending):");
        passengerBogies.forEach(b -> System.out.println("Rank: " + b));
    }
}