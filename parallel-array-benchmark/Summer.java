public class Summer implements Runnable {

    private int[] arr;
    private int start, end;
    private long partialSum = 0;

    public Summer(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        partialSum = ArraySum.sumRange(arr, start, end);
    }

    public long getSum() {
        return partialSum;
    }
}
