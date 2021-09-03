public class Clock {
	private Number hour;
	private Number minute;
	private Number second;
	
	public Clock() {
		hour = new Number(24);
		minute = new Number(60);
		second = new Number(60);
	}
	
	public void setTime(int hour, int minute) {
		this.hour.setValue(hour);
		this.minute.setValue(minute);
	}
	
	public void setTime(int hour, int minute, int second) {
		this.setTime(hour, minute);
		this.second.setValue(second);
	}
	
	public String getTime() {
		return hour.getShowValue() + ":" + this.minute.getShowValue() + ":" + this.second.getShowValue();
	}
	
	public void tick() {
		if (second.increase() == 0) {
			if (minute.increase() == 0) {
				hour.increase();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Clock clock = new Clock();
		clock.setTime(23, 59);
		System.out.println(clock.getTime());

		while (true) {
			Thread.sleep(1000);
			clock.tick();
			System.out.println(clock.getTime());
		}
		
	}
	

}
