import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueImpl {
    public static void main(String[] args){
        BlockingQueue<Integer> blockingQueue=new ArrayBlockingQueue<Integer>(10);
        Worker1 worker1=new Worker1(blockingQueue);
        Worker4 worker4=new Worker4(blockingQueue);
        new Thread(worker1).start();
        new Thread(worker4).start();
    }

}


class Worker1 implements Runnable{
    private BlockingQueue<Integer> blockingQueue;
    Worker1(BlockingQueue blockingQueue){
        this.blockingQueue= blockingQueue;
    }

    @Override
    public void run() {
     doWork();
    }
    private void doWork() {
        int counter=0;
        while (true){
            try {
                blockingQueue.put(counter);
                System.out.println("Putting "+counter);
                counter++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}

class Worker4 implements Runnable{
    private BlockingQueue<Integer> blockingQueue;
    Worker4(BlockingQueue blockingQueue){
        this.blockingQueue= blockingQueue;
    }

    @Override
    public void run() {
        doWork();
    }
    private void doWork() {
        while (true){
            try {
                System.out.println("Taking out ..."+blockingQueue.take());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}