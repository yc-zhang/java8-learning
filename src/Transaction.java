import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Transaction {



    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brain = new Trader("Brain", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brain, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("--------------------------------");
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        System.out.println("--------------------------------");
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        System.out.println("--------------------------------");
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .map(Trader::getName)
                .forEach(System.out::println);

        System.out.println("--------------------------------");
        String nameString = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (nameA, nameB) -> nameA + nameB);
        System.out.println(nameString);

        System.out.println("--------------------------------");
        System.out.println(transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan")));

        System.out.println("--------------------------------");
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println("--------------------------------");
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        max.ifPresent(System.out::println);

        System.out.println("--------------------------------");
        Optional<Transaction> min = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        min.ifPresent(transaction -> System.out.println(transaction.getValue()));
    }

    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}

class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}
