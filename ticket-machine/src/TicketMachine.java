

public class TicketMachine {
    int price;   // 票价
    int balance;
    int total;

    public int getPrice() {
        return price;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    void setPrice(int price) {
        if(price > 0) {
            this.price = price;
        } else {
            System.out.println("票价必须是正整数");
        }
    }

    /**
     * 投入现金
     * @param amount   现金金额
     */
    void insertMoney(int amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("请投入正常金额");
        }
    }

    /**
     *
     * @return 当前余额
     */
    int getBalance() {
        return balance;
    }

    void print() {
        if (balance >= price) {
            System.out.println("==============================");
            System.out.println("This is a ticket");
            System.out.println("price : " + price);
            System.out.println("==============================");
            balance -= price;
            total += price;
        } else {
            System.out.println("余额不足，请投入现金");
        }

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
        balance = 0;
        total = 0;
    }

    public static void main(String[] args) {
        TicketMachine ticketMachine = new TicketMachine();
        ticketMachine.setPrice(2);
        ticketMachine.insertMoney(10);
        System.out.println("balance : " + ticketMachine.getBalance());
        ticketMachine.print();
        ticketMachine.print();
        System.out.println("balance : " + ticketMachine.getBalance());
        System.out.println("refund : " + ticketMachine.refund());

        System.out.println("total : " + ticketMachine.getTotal());
    }

}
