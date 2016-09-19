package future;

import java.util.concurrent.*;

public class SimpleFutureSpike {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(() -> {
            System.out.println("Executing");
            return 0d;
        });

        System.out.println("Hi");
        try {
            Double d = future.get(1, TimeUnit.SECONDS);
            System.out.println(d);
            executorService.shutdown();
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
