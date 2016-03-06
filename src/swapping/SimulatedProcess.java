package swapping;

/*
 * CS 149 Group 2
 * Homework 4
 */
/**
 *
 * @author Johnny
 */
public class SimulatedProcess {

    public static int nextpID = 0;
    private final int pId;
    private final char name;
    private final int size;
    private int duration;
    private boolean isFinished;
    private static final String NAMES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * Returns a process object with the specified parameters
     *
     * @param size Size of the process
     * @param duration How long the process should run
     */
    public SimulatedProcess(int size, int duration) {
        pId = nextpID++;
        this.name = NAMES.charAt(pId % NAMES.length());
        this.size = size;
        this.duration = duration;
    }

    /**
     * Returns name of the process
     *
     * @return name of process
     */
    public String getName() {
        return String.valueOf(name);
    }

    /**
     * Returns the size of the process
     *
     * @return process size
     */
    public int getSize() {
        return size;
    }

    /**
     * Simulates an execution of a process. Only decreases time remaining
     */
    public void executing() {
        duration -= 1;
        if (duration <= 0) {
            isFinished = true;
        }
    }

    /**
     * Returns whether the process has finished execution
     *
     * @return whether process has finished
     */
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public String toString() {
        return "Name=" + name + "/Size=" + size + "/Duration=" + duration;
    }

}
