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
        CPUSystem s = new CPUSystem(new FirstFitMemory());
        for (int i = 0; i < 5; i++) {
            s.generateProcesses();
            s.start();
            s.reset();
            System.out.println();
        }
        s.printStats();
        System.out.println();

        s = new CPUSystem(new NextFitMemory());
        for (int i = 0; i < 5; i++) {
            s.generateProcesses();
            s.start();
            s.reset();
            System.out.println();
        }
        s.printStats();
        System.out.println();

        s = new CPUSystem(new BestFitMemory());
        for (int i = 0; i < 5; i++) {
            s.generateProcesses();
            s.start();
            s.reset();
            System.out.println();
        }
        s.printStats();
    }

}
