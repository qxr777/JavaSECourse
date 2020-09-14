
public class TicketMachine {
	private int price;
	private int balance;
	private int total;
	
	public TicketMachine(int ticketCost) {
		balance = 0;
		total = 0;
		if (ticketCost > 0) {
			price = ticketCost;
		} else {
			System.out.println("ticket must be positive!");
		}
		
	}
	
	public void insertMoney(int amount) {
		if (amount < 0) {
			System.out.println("You must use a positive value!");
		} else {
			balance += amount;
		}
		
	}
	
	public void printTicket() {
		if (balance < price) {
			System.out.println("balance is not enough!");
		} else {
			System.out.println("########################");
			System.out.println("This is a ticket");
			System.out.println("price:" + price + "Yuan");
			System.out.println("########################");
			balance = balance - price;
			total += price;
		}

	}
	
	public int refundBalance() {
		int amountRefunded = balance;
		balance = 0;
		return amountRefunded;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getBalance() {
		return balance;
	}

	public int getTotal() {
		return total;
	}
	
	public static void main(String[] args) {
//		TicketMachine ticketMachine1 = new TicketMachine(2);
//		ticketMachine1.insertMoney(5);
//		ticketMachine1.printTicket();
//		ticketMachine1.printTicket();
//		System.out.println(ticketMachine1.refundBalance());
		
		TicketMachine ticketMachine2 = new TicketMachine(2);
		ticketMachine2.insertMoney(1);
		ticketMachine2.printTicket();
	}

}
