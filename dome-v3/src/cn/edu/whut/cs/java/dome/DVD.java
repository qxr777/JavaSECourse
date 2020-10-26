package cn.edu.whut.cs.java.dome;

public class DVD extends Item
{
    private String director;

    public DVD(String theTitle, String theDirector, int time)
    {
        super(theTitle, time);
        director = theDirector;
    }
    
	public void print() {
		System.out.print("DVD");
		super.print();
		System.out.println("    " + director);
	}

}
