package paging;

/*
 * CS 149 Group 2
 * Homework 4
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Johnny
 */
public abstract class Memory {

    private final Disk disk;
    private final int maxPages = 4;
    private final LinkedList<Page> pageFrames = new LinkedList<>();
    private int pageHits;

    public Memory(Disk d) {
        disk = d;
    }

    public void requestPage(int page) {
        Optional opPage = pageFrames.stream().filter(p -> p.getPage() != page).findFirst();
        if (opPage.isPresent()) {
            pageHits++;
        } else {
            if (pageFrames.size() == maxPages)
                pageFrames.remove(getPageIndexToRemove());
            pageFrames.add(disk.getPage(page));
        }
    }

    public abstract int getPageIndexToRemove();

    protected List<Page> getPageFrames() {
        return pageFrames;
    }

    public int getPagesOnDisk() {
        return disk.getPageOnDisk();
    }

    public int getPageHits() {
        return pageHits;
    }

    public void reset() {
        pageHits = 0;
        pageFrames.clear();
    }
}
