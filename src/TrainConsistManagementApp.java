import java.util.*;

public class TrainConsistApp {
    public static void main(String[] args) {
        // --- UC1, UC2, UC3: Summary of previous steps ---
        System.out.println("=== Train Consist Management App ===");

        // --- UC4: Maintain Ordered Bogie Sequence (LinkedList) ---
        System.out.println("\n--- Forming Train Sequence (LinkedList Operations) ---");

        // Initializing LinkedList to model the physical chaining of bogies
        LinkedList<String> trainSequence = new LinkedList<>();

        // 1. Adding bogies to the ends
        trainSequence.addFirst("Engine");
        trainSequence.addLast("Sleeper");
        trainSequence.addLast("AC Chair");
        trainSequence.addLast("Cargo");
        trainSequence.addLast("Guard Coach");

        System.out.println("Initial Sequence: " + trainSequence);

        // 2. Inserting in the middle (Positional Insertion)
        // Inserting 'Pantry Car' at index 2 (between Sleeper and AC Chair)
        System.out.println("Inserting 'Pantry Car' at position 2...");
        trainSequence.add(2, "Pantry Car");
        System.out.println("After Insertion: " + trainSequence);

        // 3. Removing First and Last bogies (Detaching)
        System.out.println("\nDetaching Engine and Guard Coach for maintenance...");
        trainSequence.removeFirst();
        trainSequence.removeLast();

        // 4. Final Ordered State
        System.out.println("Final Ordered Consist: " + trainSequence);
        System.out.println("Current length: " + trainSequence.size() + " coaches.");
    }
}