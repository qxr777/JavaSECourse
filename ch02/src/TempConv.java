
public class TempConv {
	
	public static void main(String[] args) {
		int base = 32;
		double factor = 9.0 / 5.0;
		int celsius = 22;
		double fahrenheit = celsius * factor + base;
		System.out.println("摄氏温度：" + celsius);
		System.out.println("华氏温度：" + fahrenheit);
	}

}
