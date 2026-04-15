import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Bogie Class: Models a railway unit with attributes (UC7 - UC11)
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
        // --- UC1 - UC10: Previous Operations (Summarized Execution) ---
        System.out.println("=== Train Consist Management App ===");

        // Setup base data for the train
        List<Bogie> passengerBogies = new ArrayList<>(Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24)
        ));

        // --- UC11: Validate Train ID & Cargo Codes (Regex) ---
        System.out.println("\n--- Input Validation Layer (Regex) ---");

        // 1. Define Patterns
        // TRN-\\d{4} : Matches "TRN-" followed by exactly 4 digits
        // PET-[A-Z]{2} : Matches "PET-" followed by exactly 2 uppercase letters
        String trainIdRegex = "TRN-\\d{4}";
        String cargoCodeRegex = "PET-[A-Z]{2}";

        Pattern trainPattern = Pattern.compile(trainIdRegex);
        Pattern cargoPattern = Pattern.compile(cargoCodeRegex);

        // 2. Sample Inputs for Validation
        String[] testTrainIds = {"TRN-1234", "TRAIN12", "TRN-123", "TRN-12345"};
        String[] testCargoCodes = {"PET-AB", "PET-ab", "PET123", "PET-XY"};

        System.out.println("Validating Train IDs:");
        for (String id : testTrainIds) {
            Matcher matcher = trainPattern.matcher(id);
            if (matcher.matches()) {
                System.out.println("✔ Valid Train ID: " + id);
            } else {
                System.out.println("❌ Invalid Train ID: " + id + " (Format must be TRN-XXXX)");
            }
        }

        System.out.println("\nValidating Cargo Codes:");
        for (String code : testCargoCodes) {
            Matcher matcher = cargoPattern.matcher(code);
            if (matcher.matches()) {
                System.out.println("✔ Valid Cargo Code: " + code);
            } else {
                System.out.println("❌ Invalid Cargo Code: " + code + " (Format must be PET-XX)");
            }
        }

        // --- Final Analytics (from UC10) ---
        int totalSeats = passengerBogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        System.out.println("\n========================================");
        System.out.println("VALIDATION COMPLETE | TOTAL SEATS: " + totalSeats);
        System.out.println("========================================");
    }
}