public class Program {
    public static void main(String[] args) {
        int number = 479598;

        int res = 0;

        res += number % 10;

        res += number / 10 % 10;

        res += number / 100 % 10;

        res += number / 1000 % 10;

        res += number / 10000 % 10;

        res += number / 100000 % 10;

        System.out.println(res);
    }
}
