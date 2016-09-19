package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
//        Equals to the following:
//        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//        new Thread(() -> {
//            double price = calculatePrice(product);
//            futurePrice.complete(price);
//        }).start();
//        return futurePrice;
    }

    private double calculatePrice(String product) {
        delay(); // as a example to deplay
        return Math.random() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Shop shop = new Shop("My test shop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("first stuff");

        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " ms");

        System.out.println("Do some other stuffs here please.");
        try {
            double price = futurePrice.get();
            System.out.println("The price is " + price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
