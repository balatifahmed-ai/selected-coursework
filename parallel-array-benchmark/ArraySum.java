import java.util.Random;

public class ArraySum {

    static int[] createArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100);
        }
        return arr;
    }

    static long sumRange(int[] arr, int min, int max) {
        long sum = 0;
        for (int i = min; i < max; i++) {
            sum += (long) arr[i] * arr[i] * arr[i];
        }
        return sum;
    }

    static long singleSum(int[] arr) {
        long sum = 0;
        for (int v : arr) {
            sum += (long) v * v * v;
        }
        return sum;
    }

    static class Worker extends Thread {
        int[] arr;
        int start, end;
        long partialSum = 0;

        Worker(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        public void run() {
            for (int i = start; i < end; i++) {
                partialSum += (long) arr[i] * arr[i] * arr[i];
            }
        }
    }

    static long parallelSum(int[] arr, int threadCount) throws InterruptedException {
        Worker[] workers = new Worker[threadCount];
        int chunk = arr.length / threadCount;
        for (int i = 0; i < threadCount; i++) {
            int start = i * chunk;
            int end = (i == threadCount - 1) ? arr.length : start + chunk;
            workers[i] = new Worker(arr, start, end);
            workers[i].start();
        }
        long total = 0;
        for (Worker w : workers) {
            w.join();
            total += w.partialSum;
        }
        return total;
    }

    public static void main(String[] args) throws Exception {
        int size = 50_000_000;
        int[] data = createArray(size);

        // Baseline: single-threaded
        long t1 = System.currentTimeMillis();
        long correct = singleSum(data);
        long t2 = System.currentTimeMillis();
        long baseTime = t2 - t1;

        System.out.println("=================================================");
        System.out.println(" Array size: " + size + " elements");
        System.out.println("=================================================");
        System.out.printf("Threads: %-5d Time: %-5d ms  Speedup: 1.00x  Improvement: 0.00%%%n",
                1, baseTime);

        // Expanded thread counts: covers scaling, saturation, and overhead regions
        int[] counts = {2, 4, 6, 8, 16, 32, 64, 128, 256, 512, 1000};

        for (int tc : counts) {
            long start = System.currentTimeMillis();
            long result = parallelSum(data, tc);
            long end = System.currentTimeMillis();
            long elapsed = end - start;

            double speedup = (elapsed == 0) ? 0 : (double) baseTime / elapsed;
            double improvement = ((double)(baseTime - elapsed) / baseTime) * 100.0;

            System.out.printf("Threads: %-5d Time: %-5d ms  Speedup: %.2fx  Improvement: %.2f%%%n",
                    tc, elapsed, speedup, improvement);

            if (result != correct) {
                System.out.println("Result mismatch!");
            }
        }
    }
}
