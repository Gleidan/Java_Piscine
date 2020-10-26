package ex00;

public class User {

    private Integer id;

    private String name;

    private Integer balance;

    public User(String name, Integer balance) {
        this.name = name;

        if (balance < 0) {
            this.balance = 0;
        }
        else {
            this.balance = balance;
        }
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public Integer getId() {
        return id;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
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
