import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String letters = scanner.nextLine();

        char[] lettersArr = letters.toCharArray();

        int[] countOfLetters = new int[65536];

        int[][] arrayLetters;

        int count;

        for (int i = 0; i < lettersArr.length; i++) {
            countOfLetters[(int)lettersArr[i]]++;
        }

        count = getNumberOfLetters(countOfLetters);

        if (count == 0) {
            System.exit(0);
        }

        arrayLetters = fillArrayLetters(countOfLetters, count);

        if (arrayLetters[0][0] > 10) {
            printStatisticsCorrect(arrayLetters);
        }
        else {
            printStatistics(arrayLetters);
        }

	System.out.println();

        scanner.close();
    }

    public static void printStatisticsCorrect(int[][] arrayLetters) {
        int iter = 10;

        int count;

        if (arrayLetters[0].length > 10) {
            count = 10;
        }
        else {
            count = arrayLetters[0].length;
        }

        for (int i = 0; i < count; i++) {
            if (arrayLetters[2][i] == iter) {
                System.out.printf("%3d ", arrayLetters[0][i]);
            }
        }

        System.out.println();

        while (arrayLetters[2][0] > 0) {
            System.out.printf("%3c ", '#');
            for (int i = 1; i < count; i++) {
                if (arrayLetters[2][i] + 1 == iter) {
                    System.out.printf("%3d ", arrayLetters[0][i]);
                }
                if (arrayLetters[2][0] == arrayLetters[2][i]) {
                    System.out.printf("%3c ", '#');
                    arrayLetters[2][i]--;
                }
            }
            System.out.println();
            arrayLetters[2][0]--;
            iter--;
        }

        for (int i = 0; i < count; i++) {
            System.out.printf("%3c ", (char)arrayLetters[1][i]);
        }
    }

    public static int[][] fillArrayLetters(int[] countOfLetters, int count) {
        int[][] lettersAndFrequency = new int[3][count];

        double multiplier;

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < countOfLetters.length; j++) {
                if (lettersAndFrequency[0][i] < countOfLetters[j]) {
                    lettersAndFrequency[0][i] = countOfLetters[j];
                    lettersAndFrequency[1][i] = j;
                }
            }
            countOfLetters[lettersAndFrequency[1][i]] = 0;
        }

        multiplier = lettersAndFrequency[0][0] / 10.0;

        for (int i = 0; i < count; i++) {
            lettersAndFrequency[2][i] = (int)(lettersAndFrequency[0][i] / multiplier);
        }

        return lettersAndFrequency;
    }

    public static int getNumberOfLetters(int[] letters) {
        int count = 0;

        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > 0) {
                count++;
            }
        }

        return count;
    }

    public static void printStatistics(int[][] arrayLetters) {
        int iter = arrayLetters[0][0];

        int count;

        if (arrayLetters[0].length > 10) {
            count = 10;
        }
        else {
            count = arrayLetters[0].length;
        }

        for (int i = 0; i < count; i++) {
            if (arrayLetters[0][i] == iter) {
                System.out.printf("%3d ", arrayLetters[0][i]);
            }
        }

        System.out.println();

        while (arrayLetters[0][0] > 0) {
            System.out.printf("%3c ", '#');
            for (int i = 1; i < count; i++) {
                if (arrayLetters[0][i] + 1 == iter) {
                    System.out.printf("%3d ", arrayLetters[0][i]);
                }
                if (arrayLetters[0][i] == arrayLetters[0][0]) {
                    System.out.printf("%3c ", '#');
                    arrayLetters[0][i]--;
                }
            }
            System.out.println();
            arrayLetters[0][0]--;
            iter--;
        }

        for (int i = 0; i < count; i++) {
            System.out.printf("%3c ", (char)arrayLetters[1][i]);
        }
    }
}
