public class Program {

    public static void main(String[] args) {
        int count = getCount(args);

        Storage storage = new Storage();

        Producer producer = new Producer(storage, count);

        Consumer consumer = new Consumer(storage, "Egg", count);

        Consumer consumer1 = new Consumer(storage, "Hen", count);

        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer1).start();
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
