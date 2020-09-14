
public class TicketMachine {
	
	int price;
	int balance;
	int total;
	
	void setPrice(int ticketPrice) {
		price = ticketPrice;
	}
	
	void insertMoney(int amount) {
		balance += amount;
	}
	
	void printTicket() {
		System.out.println("=================================");
		System.out.println("This is a ticket");
		System.out.println("price: " + price + " Yuan");
		System.out.println("=================================");
		balance -= price;
		total += price;
	}
	
	int getBalance() {
		return balance;
	}
	
	int refund() {
		int result = balance;
		balance = 0;
		return result;
	}
	
	int getTotal() {
		return total;
	}
	
	void reset() {
		total += balance;
		System.out.println("total : " + total); 
		balance = 0;
		total = 0;
	}

}
