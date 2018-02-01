import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallablesAndFutures {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService=Executors.newFixedThreadPool(2);
        List<Future<String>> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            Future<String> future=executorService.submit(new Processor(i+1));
            list.add(future);
        }
        executorService.shutdown();
        
        for(Future<String> future:list){
            System.out.println(" Values "+future.get());
        }
    }
}

class Processor implements Callable<String>{
    private int id;
    public Processor(int id){
       this.id=id;
    }
    @Override
    public String call() throws Exception {
       Thread.sleep(1000);
       return "id : "+id;
    }
}
