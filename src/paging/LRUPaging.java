package paging;

/*
 * CS 149 Group 2
 * Homework 4
 */
import java.util.LinkedList;

/**
 *
 * @author Johnny
 */
public class LRUPaging extends Memory {

    private final LinkedList<Page> lruCache = new LinkedList<>();

    /**
     * Assigns disk access to memory
     *
     * @param d the disk this memory has access to
     */
    public LRUPaging(Disk d) {
        super(d);
    }

    /**
     * Picks the oldest recently used page which should be first in the list and
     * removes from cache
     *
     * @return page index to remove
     */
    @Override
    public int getPageIndexToRemove() {
        return getPageFrames().indexOf(lruCache.poll());
    }

    /**
     * Override default requestPage function by keeping track of what pages were
     * referenced after calling the requestPage method in its super class. LRU
     * Cache order is oldest recently used in front to recently used in back
     *
     * @param page index of page to retrieve
     * @param refsMade current amount of references made
     * @return the page being requested
     */
    @Override
    public Page requestPage(int page, int refsMade) {
        Page p = super.requestPage(page, refsMade);
        if (lruCache.contains(p)) // Checks if page is already in cache
            lruCache.remove(p); // Move page to end of list to signify recent usage by removing and adding
        lruCache.addLast(p); // Add page to end of LRU cache (most recent)
        return p;
    }

    /**
     * Clears LRU cache
     */
    @Override
    public void reset() {
        super.reset();
        lruCache.clear();
    }
}
