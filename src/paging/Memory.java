package paging;

/*
 * CS 149 Group 2
 * Homework 4
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents system memory
 *
 * @author Johnny
 */
public abstract class Memory {

    private final Disk disk;
    private final int maxPages = 4;
    private final List<Page> pageFrames = new ArrayList<>();
    private int pageHits;

    /**
     * Assigns disk access to memory
     *
     * @param d the disk this memory has access to
     */
    public Memory(Disk d) {
        disk = d;
    }

    /**
     * Retrieves a page from memory. If not in memory, retrieves from disk. If
     * memory is full, removes a page based on algorithm used
     *
     * @param page index of page to retrieve
     * @param refsMade current amount of references made
     * @return the page being requested
     */
    public Page requestPage(int page, int refsMade) {
        Optional<Page> opPage = pageFrames.stream().filter(p -> p.getPageNumber() == page).findFirst();
        System.out.print("Ref " + refsMade + ": ");
        pageFrames.stream().forEach(System.out::print);
        System.out.println();
        if (opPage.isPresent()) {
            System.out.println("Page " + page + " is hit");
            pageHits++;
            opPage.get().resetLastUsed();
            return opPage.get();
        }
        System.out.println("Page " + page + " needs to be paged in");
        if (pageFrames.size() == maxPages) {
            int evictedIndex = getPageIndexToRemove();
            Page pageRemoved = pageFrames.remove(evictedIndex);
            System.out.println("Page " + pageRemoved.getPageNumber() + " was evicted");
        }
        Page p = disk.getPage(page);
        pageFrames.add(p);
        // increment the counter of all pages in pageFrames
        for (Page pages : pageFrames) {
            pages.incrementLastUsed();
        }
        return p;
    }

    /**
     * Gets the index of the page to remove
     *
     * @return index of page to remove
     */
    public abstract int getPageIndexToRemove();

    /**
     * Allow subclasses to access memory page frames
     *
     * @return List of pages in memory
     */
    protected List<Page> getPageFrames() {
        return pageFrames;
    }

    /**
     * Retrieves the number of pages on disk
     *
     * @return number of pages on disk
     */
    public int getPagesOnDisk() {
        return disk.getPageSizeOnDisk();
    }

    /**
     * Gets the number of page hits during the current run
     *
     * @return number of page hits
     */
    public int getPageHits() {
        return pageHits;
    }

    /**
     * Resets memory state
     */
    public void reset() {
        pageHits = 0;
        pageFrames.clear();
    }
}
