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
        for (Memory alg : algs) {
            CPUSystem s = new CPUSystem(alg);
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
