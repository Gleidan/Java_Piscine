import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String week = scanner.next();

        int numberOfWeek = scanner.nextInt();

        long result = 0;

        if (!week.equals("Week") || numberOfWeek != 1) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        while (true) {
            result = result * 10 + getMinimumScore(scanner);
            week = scanner.next();
            if (week.equals("42")) {
                break ;
            }
            if (!week.equals("Week") || numberOfWeek + 1 != scanner.nextInt()) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            numberOfWeek++;
        }

        result = reverseScore(result);

        printStatistics(result);

        scanner.close();
    }

    public static int getMinimumScore(Scanner scanner) {
        int i = 0;

        int minimum = 9;

        int temp;

        while (i < 5) {
            temp = scanner.nextInt();
            if (minimum > temp) {
                minimum = temp;
            }
            i++;
        }
        return minimum;
    }

    public static long reverseScore(long score) {
        long result = 0;

        while (score != 0) {
            result = result * 10 + score % 10;
            score /= 10;
        }

        return result;
    }

    public static void printStatistics(long stat) {
        int i;

        int numberOfWeek = 1;

        while (stat != 0) {
            i = 1;
            System.out.print("Week " + numberOfWeek + " ");
            while (i <= stat % 10) {
                System.out.print("=");
                i++;
            }
            System.out.println(">");
            stat /= 10;
            numberOfWeek++;
        }
    }
}
