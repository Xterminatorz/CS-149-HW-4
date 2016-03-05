
import java.util.ArrayList;
import java.util.List;

/*
 * CS 149 Group 2
 * Homework 4
 */
/**
 *
 * @author Johnny
 */
public class BestFitMemory extends Memory {

    @Override
    public int getNextIndex(ArrayList<String> memory, SimulatedProcess proccess) {
        List<Fitment> startIndices = new ArrayList<>(); // List of possible indexes
        Fitment f = null; // Last stored index
        int start = -1; // Index of first free space
        int freeCount = 0; // Keeps count of empty space
        for (int i = 0; i < memory.size(); i++) {
            if (memory.get(i).equals(".")) { // Check if space is free
                if (start == -1)
                    start = i; // Store starting index if start index is unset
                freeCount++; // Increment free block counter
                if (f != null)
                    f.freeSpaceAfter++; // Increment empty space after storing
            } else {
                f = null;
                freeCount = 0; // Reset free block counter
                start = -1; // Reset start Index
            }
            if (freeCount == proccess.getSize()) { // Store start index if there is enough space
                f = new Fitment(start, freeCount); // Keep track of empty space after allocation at that index
                startIndices.add(f); // Store possible index
            }
        }
        startIndices.sort((f1, f2) -> Integer.compare(f1.freeSpaceAfter, f2.freeSpaceAfter)); // Sort list by least free space after
        return startIndices.isEmpty() ? -1 : startIndices.get(0).start;
    }

    class Fitment {

        int start = 0;
        int freeSpaceAfter = 0;

        public Fitment(int start, int freeSpaceAfter) {
            this.start = start;
            this.freeSpaceAfter = freeSpaceAfter;
        }

    }
}
