import java.util.ArrayList;

public class SumOfArray {

    private ArrayList<Integer> arrayOfDigits;
    private long sum;

    public SumOfArray(ArrayList<Integer> arrayOfDigits) {
        this.arrayOfDigits = arrayOfDigits;
        this.sum = 0L;
    }

    public long getSum() {
        return sum;
    }

    public long GetSumOfIndex(int iStart, int iEnd) {
        long tempSum = 0L;
        for (int i = iStart; i <= iEnd; i++) {
            tempSum += arrayOfDigits.get(i);
        }

        synchronized (this) {
            this.sum += tempSum;
        }

        return tempSum;
    }
}
