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
 *
 * @author Johnny
 */
public class Process {

    private final Memory mem;
    private int lastPageIndex = -1;
    private final Random r = new Random();
    private int pageRefs;
    private final List<Integer> stats = new ArrayList<>();

    public Process(Memory m) {
        mem = m;
    }

    private int getPageFromMemory() {
        if (lastPageIndex == -1) {
            lastPageIndex = r.nextInt(mem.getPagesOnDisk());
            return lastPageIndex;
        }
        int p = r.nextInt(10);
        if (p < 7) { // 70% chance of locality of reference
            lastPageIndex += r.nextInt(3) - 1; // -1 0 +1 from last page
            if (lastPageIndex < 0)
                lastPageIndex = 0;
        } else {
            lastPageIndex = r.nextInt(7) + 2; // random page from 2 - 8
        }
        return lastPageIndex;
    }

    public void run() {
        while (pageRefs < 100) {
            mem.requestPage(getPageFromMemory());
            pageRefs++;
        }
        stats.add(mem.getPageHits());
        System.out.println("Page hit ratio: " + mem.getPageHits() / 100.0);
    }

    public void reset() {
        pageRefs = 0;
        lastPageIndex = -1;
        mem.reset();
    }

    public void printAverageHitRatio() {
        OptionalDouble avgSwap = stats.stream().mapToDouble(a -> a).average();
        System.out.println("Average hit ratio: " + (avgSwap.isPresent() ? avgSwap.getAsDouble() / 100.0 : 0));
    }
}
