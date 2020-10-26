package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();

        if (args.length == 0) {
            userMode(service);
        }
        else {
            if (args[0].equals("--profile=dev")) {
                devMode(service);
            }
        }
    }

    public static void devMode(TransactionsService service) {
        Scanner scanner = new Scanner(System.in);

        Integer command;

        User tempUser;

        Transaction[] tempTransArr;

        while (true) {
            printMenu(true);
            command = scanner.nextInt();
            if (command.equals(7)) {
                break ;
            }
            if (command.equals(1)) {
                System.out.println("Введите имя и баланс пользователя");
                tempUser = new User(scanner.next(), scanner.nextInt());
                service.addUser(tempUser);
                System.out.println("Пользователь добавлен с id = " + tempUser.getId());
                printEndLine();
            }
            if (command.equals(2)) {
                System.out.println("Введите id пользователя");
                try {
                    System.out.println(service.getUserBalance(scanner.nextInt()));
                }
                catch (UserNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                printEndLine();
            }
            if (command.equals(3)) {
                System.out.println("Введите id-отправителя, id-получателя и сумму перевода");
                try {
                    service.makeTransaction(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                    System.out.println("Перевод осуществлен");
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                printEndLine();
            }
            if (command.equals(4)) {
                System.out.println("Введите id пользователя");
                try {
                    tempTransArr = service.getUserTransactions(scanner.nextInt());
                    for (int i = 0; i < tempTransArr.length; i++) {
                        System.out.println(tempTransArr[i].toString());
                    }
                }
                catch (UserNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                printEndLine();
            }
            if (command.equals(5)) {
                System.out.println("Введите id пользователя и id перевода");
                try {
                    service.removeUserTransaction(scanner.nextInt(), UUID.fromString(scanner.next()));
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                printEndLine();
            }
            if (command.equals(6)) {
                System.out.println("Результаты проверки:");
                tempTransArr = service.checkTransactions();
                for (int i = 0; i < tempTransArr.length; i++) {
                    tempTransArr[i].ifUnconfirmed();
                }
                printEndLine();
            }
        }
        scanner.close();
    }

    public static void userMode(TransactionsService service) {
        Scanner scanner = new Scanner(System.in);

        Integer command;

        User tempUser;

        Transaction[] tempTransArr;

        while (true) {
            printMenu(false);
            command = scanner.nextInt();
            if (command.equals(5)) {
                break ;
            }
            if (command.equals(1)) {
                System.out.println("Введите имя и баланс пользователя");
                tempUser = new User(scanner.next(), scanner.nextInt());
                service.addUser(tempUser);
                System.out.println("Пользователь добавлен с id = " + tempUser.getId());
                printEndLine();
            }
            if (command.equals(2)) {
                System.out.println("Введите id пользователя");
                try {
                    System.out.println(service.getUserBalance(scanner.nextInt()));
                }
                catch (UserNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                printEndLine();
            }
            if (command.equals(3)) {
                System.out.println("Введите id-отправителя, id-получателя и сумму перевода");
                try {
                    service.makeTransaction(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                    System.out.println("Перевод осуществлен");
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                printEndLine();
            }
            if (command.equals(4)) {
                System.out.println("Введите id пользователя");
                try {
                    tempTransArr = service.getUserTransactions(scanner.nextInt());
                    for (int i = 0; i < tempTransArr.length; i++) {
                        System.out.println(tempTransArr[i].toString());
                    }
                }
                catch (UserNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                printEndLine();
            }
        }
        scanner.close();
    }

    public static void printMenu(boolean devMode) {
        System.out.println("1. Добавить пользователя");
        System.out.println("2. Просмотреть баланс пользователя");
        System.out.println("3. Осуществить перевод");
        System.out.println("4. Посмотреть все переводы конкретного пользователя");
        if (devMode) {
            System.out.println("5. DEV - удалить перевод по ID");
            System.out.println("6. DEV - проверить корректность переводов");
            System.out.println("7. Завершить выполнение");
        }
        else {
            System.out.println("5. Завершить выполнение");
        }
    }

    public static void printEndLine() {
        for (int i = 0; i < 15; i++) {
            System.out.print("_");
        }
        System.out.println();
    }
}
