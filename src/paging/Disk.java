package paging;

/*
 * CS 149 Group 2
 * Homework 4
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a physical disk
 *
 * @author Johnny
 */
public class Disk {

    private final List<Page> pages = new ArrayList<>();

    /**
     * Constructs 10 pages on disk
     */
    public Disk() {
        for (int i = 0; i < 10; i++)
            pages.add(new Page(i));
    }

    /**
     * Retrieves a Page object by index
     *
     * @param p Index of page to get
     * @return Page object of the specified index
     */
    public Page getPage(int p) {
        return p < pages.size() ? pages.get(p) : null;
    }

    /**
     * Returns the number of pages on the disk
     *
     * @return number of pages on disk
     */
    public int getPageSizeOnDisk() {
        return pages.size();
    }
}
