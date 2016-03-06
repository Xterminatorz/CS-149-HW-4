package swapping;

/*
 * CS 149 Group 2
 * Homework 4
 */
import java.util.ArrayList;

/**
 *
 * @author Johnny
 */
public abstract class Memory {

    private final ArrayList<String> memory = new ArrayList<>();

    /**
     * Initialize memory with 100 free spaces
     */
    public Memory() {
        for (int i = 0; i < 100; i++) {
            memory.add(".");
        }
    }

    /**
     * Returns starting index of where to allocate memory for the specified
     * process.
     *
     * @param memory current memory
     * @param proccess process to allocate memory for
     * @return index of where to start allocating
     */
    public abstract int getNextIndex(ArrayList<String> memory, SimulatedProcess proccess);

    /**
     * Allocates memory for a process
     *
     * @param process process to allocate
     * @return true if process was successfully allocated into memory, false
     * otherwise
     */
    public boolean allocateMemory(SimulatedProcess process) {
        int startIndex = getNextIndex(memory, process);
        if (startIndex != -1) {
            for (int i = startIndex; i < startIndex + process.getSize(); i++) {
                memory.set(i, process.getName());
            }
            System.out.print("Allocated " + process.getName() + ": ");
            printMemoryMap();
            return true;
        }
        return false;
    }

    /**
     * Deallocate the process from memory
     *
     * @param process process to deallocate
     */
    public void deallocateMemory(SimulatedProcess process) {
        for (int i = 0; i < memory.size(); i++) {
            if (memory.get(i).equals(process.getName())) {
                memory.set(i, ".");
            }
        }
        System.out.print("Deallocated " + process.getName() + ": ");
        printMemoryMap();
    }

    /**
     * Prints current memory map
     */
    public void printMemoryMap() {
        memory.stream().forEach(System.out::print);
        System.out.println();
    }

    /**
     * Resets memory state
     */
    public void reset() {
        for (int i = 0; i < memory.size(); i++) {
            memory.set(i, ".");
        }
    }
}
