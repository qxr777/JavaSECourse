package cn.edu.whut.cs.java.dome;

public class Main {

	public static void main(String[] args) {
		CD cd1 = new CD("cd1 title","artist1",12,60);
		cd1.setOwn(true);
		CD cd2 = new CD("cd2 title","artist2",12,60);
		CD cd3 = new CD("cd3 title","artist3",12,60);
		
		DVD dvd1 = new DVD("dvd1 title","director1", 90);
		DVD dvd2 = new DVD("dvd2 title","director2", 90);
		
		Database database = new Database();
		database.addItem(cd1);
		database.addItem(cd2);
		database.addItem(cd3);
		database.addItem(dvd1);
		database.addItem(dvd2);
		
		database.list();
		
	}

}
