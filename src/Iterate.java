import java.util.function.Supplier;
import java.util.stream.Stream;

public class Iterate {
    public static void main(String [] args) {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        Stream.iterate(new int[]{0, 1}, a -> new int[]{a[1], a[0] + a[1]})
                .limit(20)
                .forEach(a -> System.out.println("(" + a[0] + ", " + a[1] + ") "));

        Stream.generate(() -> (int) (Math.random() * 100))
                .limit(5)
                .forEach(System.out::println);
    }
}
