package ex01;

public class Program {

    public static void main(String[] args) {
        User user1 = new User("Sergey", 500);

        User user2 = new User("Vovan", 300);

        System.out.println(user1.toString());

        System.out.println(user2.toString());
    }
}
