import java.util.*;
import java.util.stream.Collectors;

/**
 * Bogie Class: Models a railway unit with attributes (UC7 - UC10)
 */
class Bogie {
    private String name;
    private int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() { return name; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return name + " (Seats: " + capacity + ")";
    }
}

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        // --- UC1: Initialize ---
        System.out.println("=== Train Consist Management App ===");

        // --- UC2: Basic List Operations ---
        List<String> simpleList = new ArrayList<>();
        simpleList.add("Sleeper");
        simpleList.remove("Sleeper");

        // --- UC3 & UC5: Uniqueness & Order (LinkedHashSet) ---
        Set<String> uniqueIds = new LinkedHashSet<>();
        uniqueIds.add("B101");
        uniqueIds.add("B102");

        // --- UC4: Chaining (LinkedList) ---
        LinkedList<String> sequence = new LinkedList<>();
        sequence.addFirst("Engine");
        sequence.addLast("Guard");

        // --- UC6: Key-Value Mapping (HashMap) ---
        Map<String, Integer> staticCapMap = new HashMap<>();
        staticCapMap.put("AC Chair", 56);

        // --- UC7: Custom Objects & Sorting ---
        List<Bogie> passengerBogies = new ArrayList<>();
        passengerBogies.add(new Bogie("Sleeper", 72));
        passengerBogies.add(new Bogie("Sleeper", 72));
        passengerBogies.add(new Bogie("AC Chair", 56));
        passengerBogies.add(new Bogie("First Class", 24));

        passengerBogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        // --- UC8: Stream Filtering ---
        List<Bogie> filtered = passengerBogies.stream()
                .filter(b -> b.getCapacity() > 50)
                .collect(Collectors.toList());

        // --- UC9: Grouping By Type ---
        Map<String, List<Bogie>> grouped = passengerBogies.stream()
                .collect(Collectors.groupingBy(Bogie::getName));

        // --- UC10: Count Total Seats in Train (reduce) ---
        System.out.println("\n--- Calculating Total Train Capacity (Stream Reduction) ---");

        // Pipeline: stream() -> map() -> reduce()
        // reduce(identity, accumulator)
        int totalSeats = passengerBogies.stream()
                .map(Bogie::getCapacity)        // Extract numeric capacity (UC10 map)
                .reduce(0, Integer::sum);       // Sum all values (UC10 reduce)

        System.out.println("Extracting capacities from all bogies...");
        System.out.println("Applying reduction using Integer::sum...");

        System.out.println("\n========================================");
        System.out.println("TOTAL SEATING CAPACITY: " + totalSeats + " Seats");
        System.out.println("========================================");

        // Verification of UC10 requirements
        System.out.println("Original bogie count verified: " + passengerBogies.size());
        System.out.println("Status: Analytics aggregate calculated successfully.");
    }
}