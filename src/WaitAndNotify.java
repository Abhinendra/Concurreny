import java.util.ArrayList;
import java.util.List;

public class WaitAndNotify {
    private List<Integer> list=new ArrayList<Integer>();
    private int BOTTOM=0;
    private int LIMIT=5;
    private int value=0;
    private final Object lock=new Object();
    public void produce() throws InterruptedException {
        synchronized (lock) {
           while (true){
               if(list.size()==LIMIT){
                   System.out.println("Size limit reached, Waiting for consume thread");
                   lock.wait();
               }else{
                   System.out.println("Adding : "+value);
                    list.add(value);
                    value++;
                    lock.notify();
               }
               Thread.sleep(500);
           }
           
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock) {
            while (true){
                if(list.size()==BOTTOM){
                    System.out.println("Bottom reached, waiting for thread produce");
                    lock.wait();
                }else {
                    System.out.println("Removed : "+list.remove(--value));
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify waitAndNotify=new WaitAndNotify();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitAndNotify.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitAndNotify.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
