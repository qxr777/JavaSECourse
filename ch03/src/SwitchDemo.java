import java.util.Scanner;

public class SwitchDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input your score:");
		double score = 0;
		
		do {
			System.out.println("有效百分制分数范围[0, 100]");
			score = scanner.nextDouble();
		} while (score < 0 || score > 100);

		int score_int = (int) score;

		if (score_int >= 0 && score_int <= 100) {

			score_int /= 10;
			String grade = "";

			switch (score_int) {
			case 10:
			case 9:
				grade = "A";
				break;
			case 8:
				grade = "B";
				break;
			case 7:
				grade = "C";
				break;
			case 6:
				grade = "D";
				break;
			default:
				grade = "E";
			}

			System.out.println("your level is " + grade);
		}

	}

}
