package paging;

/*
 * CS 149 Group 2
 * Homework 4
 */
import java.util.ArrayList;

/**
 *
 * @author John
 */
public class LFUPaging extends Memory {

    private final ArrayList<Page> lfuCache = new ArrayList<>();
    

    /**
     * Assigns disk access to memory
     *
     * @param d the disk this memory has access to
     */
    public LFUPaging(Disk d) {
        super(d);
    }

    /**
     * Picks the least frequently used page which should be first in the list and
     * removes from cache
     *
     * @return page index to remove
     */
    @Override
    public int getPageIndexToRemove() {
        return getPageFrames().indexOf(lfuCache.remove(0));
    }

    /**
     * Override default requestPage function by keeping track of what pages were
     * referenced after calling the requestPage method in its super class. LFU
     * Cache order is most frequently used in front to least used in back
     *
     * @param page index of page to retrieve
     * @param refsMade current amount of references made
     * @return the page being requested
     */
    @Override
    public Page requestPage(int page, int refsMade) {
        Page p = super.requestPage(page, refsMade);
        if (lfuCache.contains(p)) // Checks if page is already in cache
        {
        	int currentPlace = lfuCache.indexOf(p);
        	lfuCache.remove(currentPlace); // remove p from current position
        	lfuCache.add(currentPlace+1,p);
        }
        lfuCache.add(0, p); // Add page to beginning of LFU cache ( frequent)
        return p;
    }

    /**
     * Clears LFU cache
     */
    @Override
    public void reset() {
        super.reset();
        lfuCache.clear();
    }
}
