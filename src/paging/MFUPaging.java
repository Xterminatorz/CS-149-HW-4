package paging;

/*
 * CS 149 Group 2
 * Homework 4
 */
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Johnny
 */
public class MFUPaging extends Memory {

    private final List<Page> referenceCounter = new ArrayList<>();

    /**
     * Assigns disk access to memory
     *
     * @param d the disk this memory has access to
     */
    public MFUPaging(Disk d) {
        super(d);
    }

    /**
     * Picks the most recently used page which should be at index 0. Removes
     * from cache and sets reference count to 0.
     *
     * @return page index to remove
     */
    @Override
    public int getPageIndexToRemove() {
        // Sort list by highest reference count first
        referenceCounter.sort((p1, p2) -> Integer.compare(p2.getReferenceCount(), p1.getReferenceCount()));
        Page p = referenceCounter.remove(0);
        p.setReferenceCount(0);
        return getPageFrames().indexOf(p);
    }

    /**
     * Override default requestPage function by incrementing the reference
     * counter on the requested page
     *
     * @param page index of page to retrieve
     * @param refsMade current amount of references made
     * @return the page being requested
     */
    @Override
    public Page requestPage(int page, int refsMade) {
        Page p = super.requestPage(page, refsMade);
        if (!referenceCounter.contains(p))
            referenceCounter.add(p);
        p.setReferenceCount(p.getReferenceCount() + 1);
        return p;
    }

    /**
     * Resets reference counters in cache and clear
     */
    @Override
    public void reset() {
        super.reset();
        referenceCounter.stream().forEach((p) -> {
            p.setReferenceCount(0);
        });
        referenceCounter.clear();
    }

}
