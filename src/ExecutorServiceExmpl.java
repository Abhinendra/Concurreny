import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Workers implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("In the worker method : "+i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            
    }
}

public class ExecutorServiceExmpl {
  public static void main(String[] args){
      ExecutorService executorService=  Executors.newFixedThreadPool(5);
      for(int i=0;i<6;i++){
          executorService.execute(new Workers());
      }
      
  }
    
}
