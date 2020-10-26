package ex00;

public class Program {

    public static void main(String[] args) {
        User user1 = new User("Sergey", 500);

        User user2 = new User("Vovan", 300);

        Transaction transaction1 = new Transaction(user1, user2, 150, MoneyOrder.DEBITS);

        Transaction transaction2 = new Transaction(user2, user1, -150, MoneyOrder.CREDITS);

        System.out.println(transaction1.toString());

        System.out.println(transaction2.toString());

        System.out.println(user1.toString());

        System.out.println(user2.toString());
    }
}
