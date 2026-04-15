import java.util.ArrayList;
import java.util.List;

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        // Application Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // Dynamic Initialization using List Interface and ArrayList Implementation
        // This stores our bogies dynamically as the train grows
        List<String> trainConsist = new ArrayList<>();

        // Displaying Consist Summary
        System.out.println("Initializing train consist...");
        System.out.println("Initial bogie count: " + trainConsist.size());

        System.out.println("System ready.");
    }
}