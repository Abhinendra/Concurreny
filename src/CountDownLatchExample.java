import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker2 implements Runnable {
    private int id;
    private CountDownLatch countDownLatch;
    private Random random;

    public Worker2(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doWork();
        countDownLatch.countDown();
    }

    public void doWork() {
        System.out.println("Starts working  ..."+id);
    }
}


public class CountDownLatchExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 15; i++) {
            executorService.execute(new Worker2(i+1, countDownLatch));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All the thread visits are done");
        executorService.shutdown();
    }


}
