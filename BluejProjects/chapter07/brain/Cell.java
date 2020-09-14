import java.util.*;

/**
 * A cell in a 2D cellular automaton.
 * The cell has multiple possible states.
 * This is an implementation of the rules for Brian's Brain.
 * @see https://en.wikipedia.org/wiki/Brian%27s_Brain
 * 
 * @author David J. Barnes and Michael Kölling
 * @version  2016.02.29
 */
public class Cell
{
    // The possible states.
    public static final int ALIVE = 0, DEAD = 1, DYING = 2;
    // The number of possible states.
    public static final int NUM_STATES = 3;

    // The cell's state.
    private int state;
    // The cell's neighbors.
    private Cell[] neighbors;

    /**
     * Set the initial state to be DEAD.
     */
    public Cell()
    {
        this(DEAD);
    }
    
    /**
     * Set the initial state.
     * @param initialState The initial state
     */
    public Cell(int initialState)
    {
        state = initialState;
        neighbors = new Cell[0];
    }
    
    /**
     * Determine this cell's next state, based on the
     * state of its neighbors.
     * This is an implementation of the rules for Brian's Brain.
     * @return The next state.
     */
    public int getNextState()
    {
        if(state == DEAD) {
            // Count the number of neighbors that are alive.
            int aliveCount = 0;
            for(Cell n : neighbors) {
                if(n.getState() == ALIVE) {
                    aliveCount++;
                }
            }
            return aliveCount == 2 ? ALIVE : DEAD;
        }
        else if(state == DYING) {
            return DEAD;
        }
        else {
            return DYING;
        }
    }
    
    /**
     * Receive the list of neighboring cells and take
     * a copy.
     * @param neighborList Neighboring cells.
     */
    public void setNeighbors(ArrayList<Cell> neighborList)
    {
        neighbors = new Cell[neighborList.size()];
        neighborList.toArray(neighbors);
    }

    /**
     * Get the state of this cell.
     * @return The state.
     */
    public int getState()
    {
        return state;
    }
    
    /**
     * Set the state of this cell.
     * @param The state.
     */
    public void setState(int state)
    {
        this.state = state;
    }   
    
}
