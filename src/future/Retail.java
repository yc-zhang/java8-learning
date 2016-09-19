package future;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Retail {
    List<Shop> shopList = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("ButItAll"));

    public static void main(String args[]) {
        Retail retail = new Retail();
        long start = System.nanoTime();
        System.out.println(retail.findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " ms");

        start = System.nanoTime();
        System.out.println(retail.findPricesWithParallelStream("myPhone27S"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " ms");


        start = System.nanoTime();
        List<CompletableFuture<String>> futurePrices = retail.findFuturePrices("myPhone27S");
        futurePrices.stream().map(CompletableFuture::join).collect(Collectors.toList());
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " ms");
    }

    public List<String> findPrices(String product) {
        return internalFindPrices(product, shopList.stream());
    }

    public List<String> findPricesWithParallelStream(String product) {
        return internalFindPrices(product, shopList.parallelStream());
    }

    private List<String> internalFindPrices(String product, Stream<Shop> stream) {
        return stream.map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
    }

    public List<CompletableFuture<String>> findFuturePrices(String product) {
        return shopList.stream().map(shop ->
                CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))))
                .collect(Collectors.toList());
    }
}
