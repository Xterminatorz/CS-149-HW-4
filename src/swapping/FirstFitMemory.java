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
public class FirstFitMemory extends Memory {

    @Override
    public int getNextIndex(ArrayList<String> memory, SimulatedProcess proccess) {
        int start = -1; // Index of first free space
        int freeCount = 0; // Keeps count of empty space
        for (int i = 0; i < memory.size(); i++) {
            if (memory.get(i).equals(".")) { // Check if space is free
                if (start == -1)
                    start = i; // Store starting index if start index is unset
                freeCount++; // Increment free block counter
            } else {
                freeCount = 0; // Reset free block counter
                start = -1; // Reset start Index
            }
            if (freeCount == proccess.getSize()) // Only return start index if there is enough space
                return start;
        }
        return -1;
    }
}
