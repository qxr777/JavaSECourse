import java.util.Scanner;

public class IncomeTax {

	public static void main(String[] args) {
		
		int TAX_THRESHHOLD = 5000;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入您的月收入：");
		double income = scanner.nextDouble();
		double amount = income - TAX_THRESHHOLD;
		
		double rate = 0;
		double deduction = 0;
		
		if (amount <= 1500) {
			rate = 0.03;
			deduction = 0;
		} else if (amount > 1500 && amount <= 4500) {
			rate = 0.1;
			deduction = 105;
		} else if (amount > 4500 && amount <= 9000) {
			rate = 0.2;
			deduction = 555;
		} else if (amount > 9000 && amount <= 35000) {
			rate = 0.25;
			deduction = 1005;
		} else if (amount > 35000 && amount <= 55000) {
			rate = 0.3;
			deduction = 2755;
		} else if (amount > 55000 && amount <= 80000) {
			rate = 0.35;
			deduction = 5055;
		}  else if (amount > 80000) {
			rate = 0.4;
			deduction = 13505;
		}
		
		double tax = amount * rate - deduction;
		System.out.println("您应该缴纳的个人所得税为" + tax + "元");

	}

}
