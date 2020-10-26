public class CalculateThread implements Runnable {

    private int iStart;
    private int iEnd;
    private SumOfArray arrayClass;
    private int id;

    public CalculateThread(int iStart, int iEnd, SumOfArray arrayClass, int id) {
        this.iStart = iStart;
        this.iEnd = iEnd;
        this.arrayClass = arrayClass;
        this.id = id;
    }

    @Override
    public void run() {
        long sum = arrayClass.GetSumOfIndex(iStart, iEnd);
        System.out.println("Thread " + id + ": " + "from " + iStart + " to " + iEnd + " sum is " + sum);
    }
}
