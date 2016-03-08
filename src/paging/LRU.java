package paging;

import java.util.List;
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
public class LRU extends Memory {

    /**
     * Assigns disk access to memory
     *
     * @param d the disk this memory has access to
     */
    public LRU(Disk d) {
        super(d);
    }

    /**
     * Picks a random index from the memory to remove
     *
     * @return page index to remove
     */
    @Override
    public int getPageIndexToRemove() {
    	int LFU = 0;
    	int LFUindex = 0;
    	List<Page> pageFrames = getPageFrames();
    	for(int i = 0; i < pageFrames.size(); i++){
    		if( pageFrames.get(i).getLastUsed() >= LFU){
    			LFUindex = i;
    			LFU = pageFrames.get(i).getLastUsed();
    		}
    	}
		return LFUindex;
       
    }

}
