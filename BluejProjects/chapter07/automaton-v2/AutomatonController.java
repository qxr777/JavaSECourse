/**
 * Set up and control an elementary cellular automaton.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version  2016.02.29
 */
public class AutomatonController
{
    // The automaton.
    private Automaton auto;
    
    /**
     * Create an AutomatonController.
     * @param numberOfCells The number of cells in the automaton.
     */
    public AutomatonController(int numberOfCells)
    {
        auto = new Automaton(numberOfCells);
        auto.print();
    }
    
    /**
     * Create an AutomatonController with
     * a default number of cells.
     */
    public AutomatonController()
    {
        this(50);
    }
    
    /**
     * Run the automaton for the given number of steps.
     * @param numSteps The number of steps.
     */
    public void run(int numSteps)
    {
        for(int step = 1; step <= numSteps; step++) {
            step();
        }
    }
    
    /**
     * Run the automaton for a single step.
     */
    public void step()
    {
        auto.update();
        auto.print();
    }
    
    /**
     * Reset the automaton.
     */
    public void reset()
    {
        auto.reset();
        auto.print();
    }
    
}
