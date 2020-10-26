package ex02;

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

        UsersArrayList listOfUser = new UsersArrayList();

        listOfUser.userAdd(user1);
        listOfUser.userAdd(user2);
        listOfUser.userAdd(user3);
        listOfUser.userAdd(user4);
        listOfUser.userAdd(user5);
        listOfUser.userAdd(user6);
        listOfUser.userAdd(user7);
        listOfUser.userAdd(user8);
        listOfUser.userAdd(user9);
        listOfUser.userAdd(user10);
        listOfUser.userAdd(user11);
        listOfUser.userAdd(user12);

        System.out.println(listOfUser.countOfUser());
        System.out.println(listOfUser.getUserInd(2).getName());
        System.out.println(listOfUser.getUserId(3).getName());
        System.out.println(listOfUser.getUserId(15).getName());
    }
}
