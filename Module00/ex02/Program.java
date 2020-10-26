import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();

        int count = 0;

        while (number != 42) {
            if (isPrime(sumOfDigits(number))) {
                count++;
            }
            number = scanner.nextInt();
        }

        System.out.println("Count of coffee-request - " + count);

        scanner.close();
    }

    public static int sumOfDigits(int number) {
        int result = 0;

        while (number != 0) {
            result += number % 10;
            number /= 10;
        }

        return result;
    }

    public static boolean isPrime(int number) {
        boolean isPrime = true;

        int i = 2;

        while ((i * i) <= number) {
            if (number % i == 0) {
                isPrime = false;
                break ;
            }
            i++;
        }
        return isPrime;
    }
}
