package swapping;

/*
 * CS 149 Group 2
 * Homework 2
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
        Memory[] algs = {new FirstFitMemory(), new NextFitMemory(), new BestFitMemory()};
        String[] algNames = {"First Fit", "Next Fit", "Best Fit"};
        for (int algIndex = 0; algIndex < algs.length; algIndex++) {
            System.out.println(algNames[algIndex]);
            CPUSystem s = new CPUSystem(algs[algIndex]);
            for (int i = 0; i < 5; i++) {
                s.generateProcesses();
                s.start();
                s.reset();
                System.out.println();
            }
            s.printStats();
            System.out.println();
        }
    }

}
