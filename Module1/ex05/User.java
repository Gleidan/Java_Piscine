package ex05;

public class User {

    private Integer id;

    private String name;

    private Integer balance;

    private TransactionsList transactions;

    public User(String name, Integer balance) {
        this.id = UserIdsGenerator.getInstance().generateId();

        this.name = name;

        if (balance < 0) {
            this.balance = 0;
        }
        else {
            this.balance = balance;
        }

        transactions = new TransactionsLinkedList();
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance += balance;
    }

    public Integer getId() {
        return id;
    }

    public TransactionsList getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
