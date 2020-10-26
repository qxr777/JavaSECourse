package cn.edu.whut.cs.java.dome;

import java.util.ArrayList;

/**
 * The database class provides a facility to store CD and video 
 * objects. A list of all CDs and videos can be printed to the
 * terminal.
 * 
 * This version does not save the data to disk, and it does not
 * provide any search functions.
 * 
 */
public class Database
{
    private ArrayList<Item> items;

    /**
     * Construct an empty Database.
     */
    public Database()
    {
        items = new ArrayList<Item>();
    }

    public void addItem(Item item)
    {
    	items.add(item);
    }

    /**
     * Print a list of all currently stored CDs and DVDs to the
     * text terminal.
     */
    public void list()
    {
    	for (Item item : items) {
    		item.print();
    		System.out.println();
    	}
    }
}
