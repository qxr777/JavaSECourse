import java.util.Calendar;

/**
 * Store the data from a single line of a
 * web-server log file.
 * Individual fields are made available via
 * accessors such as getHour() and getMinute().
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogEntry implements Comparable<LogEntry>
{
    // Where the data values extracted from a single
    // log line are stored.
    private int[] dataValues;
    // The equivalent Calendar object for the log time.
    private Calendar when;
    
    // At which index in dataValues the different fields
    // from a log line are stored.
    private static final int YEAR = 0, MONTH = 1, DAY = 2,
                             HOUR = 3, MINUTE = 4;
    // The number of fields. If more fields are added, e.g. for
    // seconds or a status code, then this value must be increased
    // to match.
    private static final int NUMBER_OF_FIELDS = 5;
                      
    /**
     * Decompose a log line so that the individual fields
     * are available.
     * @param logline A single line from the log.
     *                This should be in the format:
     *                year month day hour minute etc.
     */
    public LogEntry(String logline)
    {
        // The array to store the data for a single line.
        dataValues = new int[NUMBER_OF_FIELDS];
        // Break up the log line.
        LoglineTokenizer tokenizer = new LoglineTokenizer();
        tokenizer.tokenize(logline,dataValues);
        setWhen();
    }
    
    /**
     * Create a LogEntry from the individual components.
     * @param year The year
     * @param month The month (1-12)
     * @param day The day (1-31)
     * @param hour The hour (0-23)
     * @param minute The minute (0-59)
     */
    public LogEntry(int year, int month, int day, int hour, int minute)
    {
        // The array to store the data for a single line.
        dataValues = new int[NUMBER_OF_FIELDS];
        dataValues[YEAR] = year;
        dataValues[MONTH] = month;
        dataValues[DAY] = day;
        dataValues[HOUR] = hour;
        dataValues[MINUTE] = minute;
        setWhen();
    }
    
    /**
     * Return the hour.
     * @return The hour field from the log line.
     */
    public int getHour()
    {
        return dataValues[HOUR];
    }

    /**
     * Return the minute.
     * @return The minute field from the log line.
     */
    public int getMinute()
    {
        return dataValues[MINUTE];
    }
    
    /**
     * Create a string representation of the data.
     * This is not necessarily identical with the
     * text of the original log line.
     * @return A string representing the data of this entry.
     */
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        for(int value : dataValues) {
           // Prefix a leading zero on single digit numbers.
            if(value < 10) {
                buffer.append('0');
            }
            buffer.append(value);
            buffer.append(' ');
        }
        // Drop any trailing space.
        return buffer.toString().trim();
    }
    
    /**
     * Compare the date/time combination of this log entry
     * with another.
     * @param otherEntry The other entry to compare against.
     * @return A negative value if this entry comes before the other.
     *         A positive value if this entry comes after the other.
     *         Zero if the entries are the same.
     */
    public int compareTo(LogEntry otherEntry)
    {
        // Use the equivalent Calendars comparison method.
        return when.compareTo(otherEntry.getWhen());
    }
    
    /**
     * Return the Calendar object representing this event.
     * @return The Calendar for this event.
     */
    private Calendar getWhen()
    {
        return when;
    }

    /**
     * Create an equivalent Calendar object from the data values.
     */
    private void setWhen()
    {
        when = Calendar.getInstance();
        // Adjust from 1-based month and day to 0-based.
        when.set(dataValues[YEAR],
                 dataValues[MONTH] - 1, dataValues[DAY] - 1,
                 dataValues[HOUR], dataValues[MINUTE]);
    }
    
}