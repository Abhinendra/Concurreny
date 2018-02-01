import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader{
    INSTANCE;
    private Semaphore semaphore=new Semaphore(3,true);
    public void DownloadData() throws InterruptedException {
        semaphore.acquire();
        download();
        Thread.sleep(3000);
        semaphore.release();
    }

    private void download() {
        System.out.println("Downloading data from web");
    }
}

public class Semaphores {
    public static void main(String[] args0){
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=0;i<12;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Downloader.INSTANCE.DownloadData();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    
}
