import java.io.*;
import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        int count = getCount(args);

        ArrayList<String> getUrl = getUrlFromFile();

        Storage storage = new Storage(getUrl);

        Producer producer = new Producer(storage, count);

        new Thread(producer).start();

        for (int i = 0; i < count; i++) {
            new Thread(new Consumer(storage, i + 1)).start();
        }
    }

    private static ArrayList<String> getUrlFromFile() {
        try {

            File file = new File("files_urls.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();

            ArrayList<String> list = new ArrayList<>();

            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
            return list;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int getCount(String[] args) {
        int rt;

        String subString;

        if (args.length != 1 || !args[0].contains("--threadsCount=")) {
            System.err.println("Usage: Program --threadsCount=*number*");
            System.exit(-1);
        }

        subString = args[0].replaceAll("[^0-9]", "");
        rt = Integer.parseInt(subString);

        return rt;
    }
}
