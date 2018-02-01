import java.util.Random;
import java.util.concurrent.*;

public class CyclicBarrierExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("All the five threads completed their tasks");
            }
        });
        for (int i = 0; i < 15; i++) {
            executorService.execute(new Worker3(i + 1, cyclicBarrier));
        }
        
        executorService.shutdown();
        System.out.println("Shutting down all threads");
    }
}


class Worker3 implements Runnable {
    private int id;
    private CyclicBarrier cyclicBarrier;
    private Random random;

    public Worker3(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
        this.random = new Random();
    }

    @Override
    public void run() {
        doWork();
        
    }

    public void doWork() {
        System.out.println("Starts working  ..." + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished working... "+id);
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("All the thread visits are done");
    }
}
