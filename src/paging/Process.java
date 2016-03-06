package paging;

/*
 * CS 149 Group 2
 * Homework 4
 */
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;

/**
 * Represents a process making memory references
 *
 * @author Johnny
 */
public class Process {

    private final Memory mem;
    private int lastPageIndex = -1;
    private final Random r = new Random();
    private int pageRefs;
    private final List<Integer> stats = new ArrayList<>();

    /**
     * Creates a process specifying the memory that it belongs to
     *
     * @param m the memory this process has access to
     */
    public Process(Memory m) {
        mem = m;
    }

    private int getPageToRequest() {
        if (lastPageIndex == -1) {
            lastPageIndex = r.nextInt(mem.getPagesOnDisk());
            return lastPageIndex;
        }
        int p = r.nextInt(10);
        if (p < 7) { // 70% chance of locality of reference
            lastPageIndex = (lastPageIndex + (r.nextInt(3) - 1)) % 10; // -1 0 +1 from last page
        } else {
            lastPageIndex = (lastPageIndex + (r.nextInt(7) + 2)) % 10; // delta-i from 2 - 8
        }
        return lastPageIndex;
    }

    /**
     * Runs for 100 page references
     */
    public void run() {
        while (pageRefs < 100) {
            mem.requestPage(getPageToRequest());
            pageRefs++;
        }
        stats.add(mem.getPageHits());
        System.out.println("Page hit ratio: " + mem.getPageHits() / 100.0);
    }

    /**
     * Resets process state
     */
    public void reset() {
        pageRefs = 0;
        lastPageIndex = -1;
        mem.reset();
    }

    /**
     * Prints average hit ratio from all runs
     */
    public void printAverageHitRatio() {
        OptionalDouble avgSwap = stats.stream().mapToDouble(a -> a).average();
        System.out.println("Average hit ratio: " + (avgSwap.isPresent() ? avgSwap.getAsDouble() / 100.0 : 0));
    }
}
