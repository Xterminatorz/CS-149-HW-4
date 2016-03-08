package paging;

/*
 * CS 149 Group 2
 * Homework 4
 */
import java.util.Random;

/**
 * Represents the random paging algorithm
 *
 * @author Johnny
 */
public class RandomPaging extends Memory {

    private final Random r = new Random();

    /**
     * Assigns disk access to memory
     *
     * @param d the disk this memory has access to
     */
    public RandomPaging(Disk d) {
        super(d);
    }

    /**
     * Picks a random index from the memory to remove
     *
     * @return page index to remove
     */
    @Override
    public int getPageIndexToRemove() {
        return r.nextInt(getPageFrames().size() - 1);
    }

}
