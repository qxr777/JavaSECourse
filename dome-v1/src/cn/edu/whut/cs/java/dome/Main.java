package cn.edu.whut.cs.java.dome;

/**
 * @author: qixin created
 */
public class Main {

	public static void main(String[] args) {
		CD cd1 = new CD("cd1 title","artist1",12,60);
		cd1.setOwn(true);
		CD cd2 = new CD("cd2 title","artist2",12,60);
		CD cd3 = new CD("cd3 title","artist3",12,60);
		
		DVD dvd1 = new DVD("dvd1 title","director1", 90);
		DVD dvd2 = new DVD("dvd2 title","director2", 90);
		
		Database database = new Database();
		database.addCD(cd1);
		database.addCD(cd2);
		database.addCD(cd3);
		database.addDVD(dvd1);
		database.addDVD(dvd2);
		
		database.list();
		
	}

}
