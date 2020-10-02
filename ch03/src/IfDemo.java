import java.util.Scanner;

public class IfDemo {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input two numbers");
		double n1 = scanner.nextDouble();
		double n2 = scanner.nextDouble();
		
		double max;
		if (n1 > n2) {
			max = n1;
		} else {
			max = n2;
		}
//		max = n1 > n2 ? n1 : n2;
		System.out.println("max is " + max);

	}

}
