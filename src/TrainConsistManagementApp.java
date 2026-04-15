import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Bogie Class: Models a railway unit with attributes (UC7 - UC13)
 */
class Bogie {
    private String id;
    private String type;
    private String cargo;
    private int capacity;

    Bogie(String id, String type, String cargo, int capacity) {
        this.id = id;
        this.type = type;
        this.cargo = cargo;
        this.capacity = capacity;
    }

    public String getType() { return type; }
    public String getCargo() { return cargo; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return type + " [" + capacity + "]";
    }
}

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        // --- UC1 - UC11 Recap (Brief Initialization) ---
        System.out.println("=== Train Consist Management App ===");

        // UC11: Regex Validation
        String trainId = "TRN-2026";
        if (Pattern.matches("TRN-\\d{4}", trainId)) {
            System.out.println("Validated Train: " + trainId);
        }

        // --- UC13: Performance Comparison (Loops vs Streams) ---
        System.out.println("\n--- Performance Benchmarking (Loops vs Streams) ---");

        // 1. Prepare a larger dataset for meaningful measurement
        List<Bogie> largeConsist = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeConsist.add(new Bogie("B"+i, "Sleeper", "Passengers", 72));
            largeConsist.add(new Bogie("C"+i, "Cylindrical", "Petroleum", 3000));
        }

        int threshold = 60;

        // 2. Benchmarking Traditional Loop
        long loopStart = System.nanoTime();
        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : largeConsist) {
            if (b.getCapacity() > threshold) {
                loopResult.add(b);
            }
        }
        long loopEnd = System.nanoTime();
        long loopDuration = loopEnd - loopStart;

        // 3. Benchmarking Stream API
        long streamStart = System.nanoTime();
        List<Bogie> streamResult = largeConsist.stream()
                .filter(b -> b.getCapacity() > threshold)
                .collect(Collectors.toList());
        long streamEnd = System.nanoTime();
        long streamDuration = streamEnd - streamStart;

        // 4. Comparison Results
        System.out.println("Loop Filtering Time   : " + loopDuration + " ns");
        System.out.println("Stream Filtering Time : " + streamDuration + " ns");
        System.out.println("Result Count (Loop)   : " + loopResult.size());
        System.out.println("Result Count (Stream) : " + streamResult.size());

        if (loopDuration < streamDuration) {
            System.out.println(">> Analysis: Traditional loop was faster in this run.");
        } else {
            System.out.println(">> Analysis: Stream API was faster/equivalent in this run.");
        }

        // --- UC12: Safety Compliance (Final Check) ---
        boolean isSafe = largeConsist.stream()
                .allMatch(b -> !b.getType().equals("Cylindrical") || b.getCargo().equals("Petroleum"));
        System.out.println("\nSafety Audit Result: " + (isSafe ? "PASS ✅" : "FAIL ❌"));
    }
}