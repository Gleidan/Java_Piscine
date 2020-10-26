package ex04;

public class Program {

    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();

        service.addUser(new User("Vlad", 500));
        service.addUser(new User("Kate", 1500));


        System.out.println(service.getUserBalance(1));
        System.out.println(service.getUserBalance(2));

        service.makeTransaction(1, 2, 300);

        System.out.println(service.getUserBalance(1));
        System.out.println(service.getUserBalance(2));

        service.addUser(new User("Mom", 2000));
        service.makeTransaction(3, 1, 500);
        service.removeUserTransaction(1, service.getUserTransactions(1)[1].getId());
        Transaction[] checked = service.checkTransactions();

        System.out.println(checked[0]);
    }
}
