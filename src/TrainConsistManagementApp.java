import java.util.ArrayList;
import java.util.List;

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        // --- UC1: Welcome Message ---
        System.out.println("=== Train Consist Management App ===");

        // Initialize the list
        List<String> trainConsist = new ArrayList<>();

        // --- UC2: Add Passenger Bogies (ArrayList Operations) ---

        // 1. Adding Bogies (Create)
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");
        System.out.println("Adding bogies: Sleeper, AC Chair, First Class...");

        // 2. Display List (Read)
        System.out.println("Current Consist: " + trainConsist);
        System.out.println("Total bogies: " + trainConsist.size());

        // 3. Remove a Bogie (Delete)
        System.out.println("\nRemoving bogie: AC Chair...");
        trainConsist.remove("AC Chair");

        // 4. Check Existence (Search)
        System.out.print("Checking if 'Sleeper' exists: ");
        if (trainConsist.contains("Sleeper")) {
            System.out.println("Yes, Sleeper is attached.");
        } else {
            System.out.println("No, Sleeper is missing.");
        }

        // 5. Final State
        System.out.println("\nFinal Consist Summary: " + trainConsist);
        System.out.println("Final bogie count: " + trainConsist.size());
    }
}