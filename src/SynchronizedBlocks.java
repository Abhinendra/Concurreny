public class SynchronizedBlocks {
    private static int counter1=0;
    private static int counter2=0;
    private static Object lock1=new Object();
    private static Object lock2=new Object();

    public static void add1(){
        synchronized (lock1){
          counter1++;
        }
    }

    public static void add2(){
        synchronized (lock2){
            counter2++;
        }
    }
    public static void compute(){
        for(int i=0;i<100;i++){
            add1();
            add2();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
            }
        });
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Counter1 : "+counter1+" Counter2 : "+counter2);
    }
}
