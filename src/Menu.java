import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Menu {
    public static void main(String args[]) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 530, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", true, 450, Dish.Type.FISH)
        );

        System.out.println("--------------------------------");

        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(threeHighCaloricDishNames);

        System.out.println("--------------------------------");
        List<String> names = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println(names);

        System.out.println("--------------------------------");
        names = menu.stream()
                .filter(dish -> {
                    System.out.println("filtering: " + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping: " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(names);

        System.out.println("--------------------------------");
        long count = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .distinct()
                .limit(3)
                .count();
        System.out.println(count);

        System.out.println("--------------------------------");
        List<Dish> vegetarianDishes = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        vegetarianDishes.stream().forEach(dish -> System.out.println(dish.getName()));

        System.out.println("--------------------------------");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(integer -> integer % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        System.out.println("--------------------------------");
        List<Dish> dishes = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());

        dishes.stream().forEach(dish -> System.out.println(dish.getName()));

        System.out.println("--------------------------------");
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println(dishNames);

        System.out.println("--------------------------------");
        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(dishNameLengths);

        System.out.println("--------------------------------");
        List<String> words = Arrays.asList("Hello", "World");
        words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

        System.out.println("--------------------------------");
        Function<Integer, Integer> t = d -> {
            return d * d;
        };
        Consumer<Integer> c = s -> {
            s += 10;
            System.out.println(s);
        };
        Arrays.asList(1, 2, 3, 4, 5).stream()
                .map(t)
                .forEach(c);

        System.out.println("--------------------------------");
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        Function<Integer, Stream<Integer[]>> f2 = d -> numbers2.stream().map(j -> new Integer[]{d, j});

        List<Integer[]> results = numbers1.stream()
                .flatMap(myF2(numbers2))
                .collect(Collectors.toList());

        results.stream().flatMap(val -> Arrays.asList(val).stream()).forEach(System.out::println);

        System.out.println("--------------------------------");
        System.out.println("All dishes' calories are less than 1000: " + menu.stream().allMatch(dish -> dish.getCalories() < 1000));

        System.out.println("--------------------------------");
        System.out.println("None dish's calories are greater or equal than 1000: " + menu.stream().noneMatch(dish -> dish.getCalories() >= 1000));

        System.out.println("--------------------------------");
        Optional<Dish> vegetarianDish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();

        if (vegetarianDish.isPresent()) {
            System.out.println(vegetarianDish.get().getName());
        } else {
            System.out.println("None found.");
        }

        System.out.println("--------------------------------");
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish -> System.out.println(dish.getName()));

        System.out.println("--------------------------------");
        numbers = Arrays.asList(2, 3, 5, 12, 1, 2, 5, 6);
        System.out.println(numbers.stream().reduce(0, (a, b) -> a + b));
        System.out.println(numbers.stream().reduce(1, (a, b) -> a * b));
        System.out.println(numbers.stream().reduce(0, (a, b) -> Integer.sum(a, b)));
        System.out.println(numbers.stream().reduce(0, Integer::sum));
        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        System.out.println(sum.isPresent() ? sum.get() : null);

        System.out.println("--------------------------------");
        Optional<Integer> max = numbers.stream().reduce((a, b) -> a > b ? a : b);
        Integer max2 = numbers.stream().reduce(0, (a, b) -> a > b ? a : b);
        System.out.println(max.isPresent() ? max.get() : null);
        System.out.println(numbers.stream().reduce(Integer::max));

        System.out.println("--------------------------------");
        System.out.println("Dishes count : " + menu.stream().map(dish -> 1).reduce(Integer::sum));
    }

    public static Function<Integer, Stream<Integer[]>> myF2(List<Integer> numbers) {
        return d -> numbers.stream().map(j -> new Integer[]{d, j});
    }
}

class Dish {
    private int calories;
    private String name;
    private boolean vegetarian;
    private Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.calories = calories;
        this.name = name;
        this.vegetarian = vegetarian;
        this.type = type;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Type {MEAT, FISH, OTHER}
}
