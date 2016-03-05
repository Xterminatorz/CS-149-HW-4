/*
 * CS 149 Group 2
 * Homework 4
 */
import java.util.ArrayList;

/**
 *
 * @author Johnny
 */
public class NextFitMemory extends Memory {

    private int lastAssignedIndex;

    @Override
    public int getNextIndex(ArrayList<String> memory, SimulatedProcess proccess) {
        int start = -1; // Index of first free space
        int freeCount = 0; // Keeps count of empty space
        boolean wrapAround = true; // Determines if we should go back to the beginning
        for (int i = lastAssignedIndex; i < memory.size(); i++) { // Start from last assigned index
            if (memory.get(i).equals(".")) { // Check if space is free
                if (start == -1)
                    start = i; // Store starting index if start index is unset
                freeCount++; // Increment free block counter
            } else {
                freeCount = 0; // Reset free block counter
                start = -1; // Reset start Index
            }
            if (freeCount == proccess.getSize()) { // Only return start index if there is enough space
                lastAssignedIndex = i; // Store last assigned index
                return start;
            }
            if (wrapAround && i == memory.size() - 1) {
                i = 0; // Start from beginning
                freeCount = 0;
                start = -1;
                wrapAround = false;
            }
        }
        return -1;
    }
}
