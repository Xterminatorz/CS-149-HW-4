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
    private int lastUsedCounter; //counter to check when the page was last used
    /**
     * Constructs a page with the specified number
     *
     * @param pageNum Page number for this Page object
     */
    public Page(int pageNum) {
        page = pageNum;
        lastUsedCounter = 0;
    }

    /**
     * Gets the page number of this Page object
     *
     * @return the page number
     */
    public int getPageNumber() {
        return page;
    }

    @Override
    public String toString() {
        return page + " ";
    }
    
    public int getLastUsed(){
    	return lastUsedCounter;
    }
    
    public void resetLastUsed(){
    	lastUsedCounter = 0;
    }
    public void incrementLastUsed(){
    	lastUsedCounter += 1;
    }
}
