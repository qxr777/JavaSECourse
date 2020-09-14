import java.util.*;

/**
 * Model a 1D elementary cellular automaton.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version  2016.02.29 - version 3
 */
public class Automaton
{
    // The number of cells.
    private final int numberOfCells;
    // The state of the cells.
    private int[] state;
    
    /**
     * Create a 1D automaton consisting of the given number of cells.
     * @param numberOfCells The number of cells in the automaton.
     */
    public Automaton(int numberOfCells)
    {
        this.numberOfCells = numberOfCells;
        // Allow an extra element to avoid 'fencepost' errors.
        state = new int[numberOfCells + 1];
        // Seed the automaton with a single 'on' cell.
        state[numberOfCells / 2] = 1;
    }
    
    /**
     * Print the current state of the automaton.
     */
    public void print()
    {
        for(int cellValue : state) {
            System.out.print(cellValue == 1 ? "*" : " ");
        }
        System.out.println();
    }   
    
    /**
     * Update the automaton to its next state.
     */
    public void update()
    {
        // Build the new state in a separate array.
        int[] nextState = new int[state.length];
        // Use 0 for the non-existent value to the left of
        // the first cell.
        int left = 0;
        int center = state[0];
        for(int i = 0; i < numberOfCells; i++) {
            int right = state[i + 1];
            nextState[i] = calculateNextState(left, center, right);
            left = center;
            center = right;
        }
        state = nextState;
    }
    
    /**
     * Reset the automaton.
     */
    public void reset()
    {
        Arrays.fill(state, 0);
        // Seed the automaton with a single 'on' cell in the middle.
        state[numberOfCells / 2] = 1;
    }

    /**
     * Calculate the next state of the center cell
     * given current left, center and right cell
     * values.
     * This implements Wolfram code 110.
     * @see https://en.wikipedia.org/wiki/Wolfram_code
     * @param left The state of the cell to the left of center.
     * @param center The state of the center cell.
     * @param right The state of the cell to the right of center.
     * @return The new value of center (0 or 1).
     */
    private int calculateNextState(int left, int center, int right)
    {
        return (center + right + center * right + left * center * right) % 2;
    }

}
