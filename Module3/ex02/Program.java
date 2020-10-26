import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        int threadCount;

        int arraySize;

        SumOfArray arrayClass = null;

        ArrayList<Integer> arrList = new ArrayList<Integer>();

        if (args.length != 2 || !args[0].contains("--arraySize=") || !args[1].contains("--threadsCount=")) {
            System.err.println("Usage: Program --arraySize=*number* --threadsCount=*number*");
            System.exit(-1);
        }

        arraySize = parsingArguments(args[0]);
        threadCount = parsingArguments(args[1]);
        fillArrayList(arrList, arraySize);

        arrayClass = new SumOfArray(arrList);

        getSumSimple(arrList);
        createThread(threadCount, arraySize, arrayClass);
        System.out.println("Sum by threads: " + arrayClass.getSum());

    }

    private static void getSumSimple(ArrayList<Integer> list) {
        long result = 0L;

        for (int i = 0; i < list.size(); i++) {
            result += list.get(i);
        }
        System.out.println("Sum: " + result);
    }

    private static void createThread(int threadCont, int arrSize, SumOfArray arrayClass) {
        int start = 0;

        int step = arrSize / threadCont + arrSize % threadCont;

        int end = 0;

        ArrayList<Thread> listOfThread = new ArrayList<>();

        for (int i = 0; i < threadCont; i++) {
            start = end;
            end = start + step;
            if (end > arrSize) {
                end = arrSize;
            }
            listOfThread.add(new Thread(new CalculateThread(start, end - 1, arrayClass, i + 1)));
        }

        for (Thread temp : listOfThread) {
            temp.start();
        }

        try {
            for (Thread temp : listOfThread) {
                temp.join();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fillArrayList(ArrayList<Integer> list, int arraySize) {
        int temp;

        for (int i = 0; i < arraySize; i++) {
            temp = (int)(Math.random() * (2000 + 1)) - 1000;
            list.add(temp);
        }
    }

    private static int parsingArguments(String args) {
        int rt;

        String subString;

        subString = args.replaceAll("[^0-9]", "");
        rt = Integer.parseInt(subString);

        return rt;
    }
}
