package paging;

/*
 * CS 149 Group 2
 * Homework 4
 */

import java.util.Random;

/**
 *
 * @author Johnny
 */
public class RandomPaging extends Memory {

    public RandomPaging(Disk d) {
        super(d);
    }

    @Override
    public int getPageIndexToRemove() {
        return new Random().nextInt(super.getPageFrames().size() - 1);
    }

}
