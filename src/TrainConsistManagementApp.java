import java.util.*;
import java.util.stream.Collectors;

/**
 * Bogie Class: Models a railway unit with attributes for categorization and sorting.
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
        return "Bogie{name='" + name + "', capacity=" + capacity + "}";
    }
}

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        // --- UC1: Initialize ---
        System.out.println("=== Train Consist Management App ===");

        // --- UC2: Basic Operations ---
        List<String> simpleList = new ArrayList<>();
        simpleList.add("Sleeper");
        simpleList.add("AC Chair");
        simpleList.remove("AC Chair");

        // --- UC3 & UC5: Uniqueness & Order ---
        Set<String> uniqueFormation = new LinkedHashSet<>();
        uniqueFormation.add("B101");
        uniqueFormation.add("B102");
        uniqueIds.add("B101"); // Duplicate ignored

        // --- UC4: Chaining ---
        LinkedList<String> physicalSequence = new LinkedList<>();
        physicalSequence.addFirst("Engine");
        physicalSequence.addLast("Guard");

        // --- UC6: Capacity Mapping ---
        Map<String, Integer> staticCapacity = new HashMap<>();
        staticCapacity.put("Sleeper", 72);

        // --- UC7: Custom Objects & Sorting ---
        List<Bogie> passengerBogies = new ArrayList<>();
        passengerBogies.add(new Bogie("Sleeper", 72));
        passengerBogies.add(new Bogie("Sleeper", 72)); // Second sleeper unit
        passengerBogies.add(new Bogie("AC Chair", 56));
        passengerBogies.add(new Bogie("First Class", 24));
        passengerBogies.add(new Bogie("First Class", 24)); // Second first class unit

        // --- UC8: Stream Filtering ---
        List<Bogie> highCap = passengerBogies.stream()
                .filter(b -> b.getCapacity() > 50)
                .collect(Collectors.toList());

        // --- UC9: Group Bogies by Type (Collectors.groupingBy) ---
        System.out.println("\n--- Categorizing Bogies by Type (groupingBy) ---");

        // Using groupingBy to create a Map where key = name, and value = List of bogies
        Map<String, List<Bogie>> groupedBogies = passengerBogies.stream()
                .collect(Collectors.groupingBy(Bogie::getName));

        // Displaying the structured output
        groupedBogies.forEach((type, list) -> {
            System.out.println("Category: " + type + " | Units: " + list.size());
            list.forEach(bogie -> System.out.println("   -> " + bogie));
        });

        System.out.println("\nSummary: " + groupedBogies.keySet().size() + " distinct categories identified.");
        System.out.println("Original list integrity check (size): " + passengerBogies.size());
    }
}