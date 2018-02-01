class Runner1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner 1: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Runner2 extends  Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            System.out.println("Runner 2: " + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

 class Worker implements Runnable{
    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }

    private boolean isTerminated=false;
    @Override
    public void run() {
        while(!isTerminated){
            System.out.println("Sleeping ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Process{
    private static int counter=0;
    public synchronized static void  increment(){
        counter++;
    }
    public static void process() throws InterruptedException {
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;++i){
                    increment();
                }
            }
        });

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;++i){
                    increment();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Counter Value"+counter);
    }
}



public class App {

    public static void main(String[] args) throws InterruptedException {
        Process.process();
    }
}
