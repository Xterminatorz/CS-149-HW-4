package paging;

/*
 * CS 149 Group 2
 * Homework 4
 */

/**
 *
 * @author Frank
 * An implementation of FIFO Paging.  FIFO removes the first (oldest) page.
 */
public class FIFOPaging extends Memory {
	
    public FIFOPaging(Disk d) {
        super(d);
    }

    @Override
    public int getPageIndexToRemove() {
    	return 0;
    }

}