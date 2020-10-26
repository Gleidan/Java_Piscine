package ex03;

import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        User user1 = new User("Sergey", 500);
        User user2 = new User("Vovan", 300);
        User user3 = new User("Lolka", 200);
        User user4 = new User("Sergey", 500);
        User user5 = new User("Vovan", 300);
        User user6 = new User("Lolka", 200);
        User user7 = new User("Sergey", 500);
        User user8 = new User("Vovan", 300);
        User user9 = new User("Lolka", 200);
        User user10 = new User("Sergey", 500);
        User user11 = new User("Vovan", 300);
        User user12 = new User("Lolka", 200);

        Transaction transaction1 = new Transaction(user1, user2, 1, MoneyOrder.DEBITS);
        Transaction transaction2 = new Transaction(user1, user2, 2, MoneyOrder.DEBITS);
        Transaction transaction3 = new Transaction(user1, user2, 3, MoneyOrder.DEBITS);
        Transaction transaction4 = new Transaction(user1, user2, 4, MoneyOrder.DEBITS);

        TransactionsList listOfTrans = new TransactionsLinkedList();

        listOfTrans.addTransaction(transaction1);
        listOfTrans.addTransaction(transaction2);
        listOfTrans.addTransaction(transaction3);
        listOfTrans.addTransaction(transaction4);

        Transaction[] arrTrans = listOfTrans.toArray();

        for (int i = 0; i < arrTrans.length; i++) {
            System.out.println(arrTrans[i].toString());
        }

        System.out.println();

        listOfTrans.deleteTransaction(transaction2.getId());

        arrTrans = listOfTrans.toArray();

        for (int i = 0; i< arrTrans.length; i++) {
            System.out.println(arrTrans[i].toString());
        }
    }
}
