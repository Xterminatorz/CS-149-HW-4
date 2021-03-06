package paging;

/*
 * CS 149 Group 2
 * Homework 4
 */
/**
 *
 * @author Johnny
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StringBuilder averageHRStr = new StringBuilder();
        Disk disk = new Disk();
        Memory[] algs = {new FIFOPaging(disk), new LRUPaging(disk), new LFUPaging(disk), new MFUPaging(disk), new RandomPaging(disk)};
        String[] algNames = {"FIFO Paging", "LRU Paging", "LFU Paging", "MFU Paging", "Random Paging"};
        for (int i = 0; i < algs.length; i++) {
            Process p = new Process(algs[i]);
            System.out.println(algNames[i]);
            for (int j = 0; j < 5; j++) {
                System.out.println("\r\nRun " + (j + 1) + " ");
                p.run();
                p.reset();
            }
            averageHRStr.append(algNames[i]).append(" ").append(p.printAverageHitRatio());
            System.out.println();
        }
        System.out.println(averageHRStr.toString());
    }
}
