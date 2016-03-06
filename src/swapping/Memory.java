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

    public Memory() {
        for (int i = 0; i < 100; i++) {
            memory.add(".");
        }
    }

    public abstract int getNextIndex(ArrayList<String> memory, SimulatedProcess proccess);

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

    public void deallocateMemory(SimulatedProcess process) {
        for (int i = 0; i < memory.size(); i++) {
            if (memory.get(i).equals(process.getName())) {
                memory.set(i, ".");
            }
        }
        System.out.print("Deallocated " + process.getName() + ": ");
        printMemoryMap();
    }

    public void printMemoryMap() {
        memory.stream().forEach(System.out::print);
        System.out.println();
    }

    public void reset() {
        for (int i = 0; i < memory.size(); i++) {
            memory.set(i, ".");
        }
    }
}
