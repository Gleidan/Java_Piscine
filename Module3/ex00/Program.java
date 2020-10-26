import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Program {

    public static void main(String[] args) throws Exception {
        int count = getCount(args);

        EggOrHen egg = new EggOrHen("Egg", count);
        Thread eggThread = new Thread(egg);

        EggOrHen hen = new EggOrHen("Hen", count);
        Thread henThread = new Thread(hen);

        eggThread.start();
        henThread.start();

        eggThread.join();
        henThread.join();

        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }

    private static int getCount(String[] args) {
        int rt;

        String subString;

        if (args.length != 1 || !args[0].contains("--count=")) {
            System.err.println("Usage: Program --count=*number*");
            System.exit(-1);
        }

        subString = args[0].replaceAll("[^0-9]", "");
        rt = Integer.parseInt(subString);

        return rt;
    }
}
