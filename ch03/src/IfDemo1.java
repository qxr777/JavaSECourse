import java.util.Scanner;

public class IfDemo1 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input one number");
		double number3 = scanner.nextDouble();
		if (number3 > 10) {
			System.out.println(number3 + " is greater than 10");
		}
	}

}
