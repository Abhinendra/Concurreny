import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocks {
    private static final Lock lock=new ReentrantLock();
    private  static int counter=0;

    public static void increment(){
        lock.lock();
        try{
            for(int i=0;i<100;i++){
                counter++;
            }
        }finally {
            lock.unlock();
        }
    }

    public static void main(String [] args) throws InterruptedException {
        Thread thread1=new Thread(new Runnable() {
            public void run() {
              increment();
            }
        });
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
            increment();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Final Counter value : "+counter);
    }
}
