/**
 * The main part of the calculator doing the calculations.
 * 
 * @author: (none yet)
 * @version 0.1 (incomplete)
 */
public class CalcEngine
{
    // Put instance variables here.

    /**
     * Create a CalcEngine.
     */
    public CalcEngine()
    {
    }

    /**
     * @return The value that should currently be displayed
     * on the calculator display.
     */
    public int getDisplayValue()
    {
        return 0;
    }

    /**
     * A number button was pressed.
     * Either start a new operand, or incorporate this number as
     * the least significant digit of an existing one.
     * @param number The number pressed on the calculator.
     */
    public void numberPressed(int number)
    {
    }

    /**
     * The 'plus' button was pressed. 
     */
    public void plus()
    {
    }

    /**
     * The 'minus' button was pressed.
     */
    public void minus()
    {
    }

    /**
     * The '=' button was pressed.
     */
    public void equals()
    {
    }

    /**
     * The 'C' (clear) button was pressed.
     */
    public void clear()
    {
    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "";
    }

    /**
     * @return The author of this engine. This string is displayed as it is,
     * so it should say something like "Written by H. Simpson".
     */
    public String getAuthor()
    {
        return "";
    }

    /**
     * @return The version number of this engine. This string is displayed as 
     * it is, so it should say something like "Version 1.1".
     */
    public String getVersion()
    {
        return "";
    }
}
