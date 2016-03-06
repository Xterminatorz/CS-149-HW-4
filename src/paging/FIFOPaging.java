package paging;

/*
 * CS 149 Group 2
 * Homework 4
 */

/**
 *
 * @author Frank
 * An implementation of FIFO Paging.  Should cycle through removing the first, second, third, then
 * fourth page from memory.
 */
public class FIFOPaging extends Memory {

	private Integer index = 0;
	
    public FIFOPaging(Disk d) {
        super(d);
    }

    @Override
    public int getPageIndexToRemove() {
    	try{
    		return index;
    	}
    	finally{
    		index = new Integer((index+1)%4);
    	}
    }

}