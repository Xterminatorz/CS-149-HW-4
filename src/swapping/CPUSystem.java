package swapping;

/*
 * CS 149 Group 2
 * Homework 4
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;

/**
 *
 * @author Johnny
 */
public class CPUSystem {

    private final Memory mem;
    private final LinkedList<SimulatedProcess> processes = new LinkedList<>();
    private final ArrayList<SimulatedProcess> runningProcesses = new ArrayList<>();
    private int currentTime = 0;
    private final Random R = new Random(0);
    private static final int[] POSSIBLE_SIZES = {5, 11, 17, 31};
    private static final int[] POSSIBLE_DURATIONS = {1, 2, 3, 4, 5};
    private final List<Integer> stats = new ArrayList<>();

    /**
     * Amount of seconds to run
     */
    public static final int SECONDS_TO_RUN = 60;

    /**
     * Sets up a new CPU scheduler with the given algorithm
     *
     * @param alg The algorithm to run
     */
    public CPUSystem(Memory alg) {
        this.mem = alg;
    }

    /**
     * Resets system state
     */
    public void reset() {
        processes.clear();
        runningProcesses.clear();
        currentTime = 0;
        mem.reset();
        SimulatedProcess.nextpID = 0;
    }

    /**
     * Generates new processes to put on scheduler
     */
    public void generateProcesses() {
        List<Integer> usedSizes = new ArrayList<>();
        List<Integer> usedDuration = new ArrayList<>();
        int size_idx = R.nextInt(POSSIBLE_SIZES.length), duration_idx = R.nextInt(POSSIBLE_DURATIONS.length);
        for (int i = 0; i < 160; i++) { // Evenly distribute processes durations and sizes
            while (usedDuration.contains(duration_idx)) { // Loop until unused index found
                duration_idx = R.nextInt(POSSIBLE_DURATIONS.length);
            }
            usedDuration.add(duration_idx); // Add to used index
            if (usedDuration.size() == POSSIBLE_DURATIONS.length) {
                usedDuration.clear(); // Clear used list if list contains all possible indexes
            }
            while (usedSizes.contains(size_idx)) { // Loop until unused index found
                size_idx = R.nextInt(POSSIBLE_SIZES.length);
            }
            usedSizes.add(size_idx); // Add to used index
            if (usedSizes.size() == POSSIBLE_SIZES.length) {
                usedSizes.clear(); // Clear used list if list contains all possible indexes
            }
            processes.add(new SimulatedProcess(POSSIBLE_SIZES[size_idx], POSSIBLE_DURATIONS[duration_idx]));
        }
    }

    /**
     * Starts the system
     */
    public void start() {
        int procsSwappedIn = 0; // Keeps track number of processes swapped in
        // Each loop represents 1 second
        while (currentTime <= SECONDS_TO_RUN) {
            Iterator<SimulatedProcess> iter = runningProcesses.iterator();
            while (iter.hasNext()) { // Execute all running processes
                SimulatedProcess p = iter.next();
                p.executing(); // Execute Process
                if (p.isFinished()) {
                    mem.deallocateMemory(p); // Deallocate memory if finished
                    iter.remove(); // Remove from running processes list
                }
            }
            SimulatedProcess proc = processes.peek(); // Gets first process
            if (mem.allocateMemory(proc)) { // Attemps to allocate memory
                System.out.println("Process added: " + proc);
                runningProcesses.add(processes.poll()); // Adds to running processes list if memory was successfully allocated
                procsSwappedIn++;
            }

            currentTime += 1; // Increase CPU time by 1 second
        }
        stats.add(procsSwappedIn);
    }

    /**
     * Print average amount of processes swapped
     */
    public void printStats() {
        OptionalDouble avgSwap = stats.stream().mapToDouble(a -> a).average();
        System.out.println("Average processes swapped in: " + (avgSwap.isPresent() ? avgSwap.getAsDouble() : 0));
    }

}
