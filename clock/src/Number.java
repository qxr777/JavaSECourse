public class Number {
	
	private int limit;
	private int value;
	
	public Number() {
		limit = 0;
		value = 0;
	}
	
	public Number(int limit) {
		this.limit = limit;
	}
	
	public Number(int limit, int value) {
		this(limit);
		this.value = value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String getShowValue() {
		if (value < 10) {
			return "0" + value;
		} else {
			return "" + value;
		}
	}
	
	public int increase() {
		value++;
		if (value == limit) {
			value = 0;
		}
		return value;
	}
	
}
