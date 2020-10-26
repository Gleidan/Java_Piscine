import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = 0;

        int count = 1;

        boolean isPrime = true;

        int i = 2;

        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
        }
        else {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        if (number <= 1) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        while ((i * i) <= number) {
            if (number % i == 0) {
                isPrime = false;
                break ;
            }
            i++;
            count++;
        }

        System.out.println(isPrime + " " + count);

        scanner.close();
    }
}
