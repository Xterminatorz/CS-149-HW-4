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
public class Disk {

    private final List<Page> pages = new ArrayList<>();

    public void addPage(Page p) {
        pages.add(p);
    }

    public Page getPage(int p) {
        return p < pages.size() ? pages.get(p) : null;
    }

    public int getPageOnDisk() {
        return pages.size();
    }
}
