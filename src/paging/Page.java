package paging;

/*
 * CS 149 Group 2
 * Homework 4
 */
/**
 * Represents a page file
 *
 * @author Johnny
 */
public class Page {

    private final int page;
    private int reference;

    /**
     * Constructs a page with the specified number
     *
     * @param pageNum Page number for this Page object
     */
    public Page(int pageNum) {
        page = pageNum;
    }

    /**
     * Gets the page number of this Page object
     *
     * @return the page number
     */
    public int getPageNumber() {
        return page;
    }

    /**
     * Gets the reference count of this page
     *
     * @return the page number
     */
    public int getReferenceCount() {
        return reference;
    }

    /**
     * Sets the reference count of this page
     *
     * @param r the new reference count
     */
    public void setReferenceCount(int r) {
        reference = r;
    }

    @Override
    public String toString() {
        return page + " ";
    }
}
