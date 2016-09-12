import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Collector {

    public static void main(String[] args) {
        List<Dish> menu = Menu.getDishes();

        Comparator<Dish> dishComparator = Comparator.comparing(Dish::getCalories);
        Optional<Dish> mostCaloriesDish = menu.stream().collect(maxBy(dishComparator));
        mostCaloriesDish.ifPresent(dish -> System.out.println(dish.getName()));

        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(avgCalories);

        String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenu);

        totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories);

        totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(totalCalories);

        menu.stream().map(Dish::getName).collect(reducing((a, b) -> a + " " + b)).ifPresent(System.out::println);

        Map<Dish.Type, List<Dish>> dishMap = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishMap);

        Map<CaloricLevel, List<Dish>> caloricLevelListMap = menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            return CaloricLevel.FAT;
        }));
        System.out.println(caloricLevelListMap);
        caloricLevelListMap.entrySet().stream()
                .filter(e -> e.getKey() == CaloricLevel.NORMAL)
                .flatMap(e -> e.getValue().stream())
                .forEach(dish -> System.out.println(dish.getName()));

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeAndCaloricLevel = menu.stream()
                .collect(groupingBy(Dish::getType, groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    return CaloricLevel.FAT;
                })));

        dishesByTypeAndCaloricLevel.entrySet().forEach(e -> {
            System.out.println("--------------------------");
            System.out.println(e.getKey());
            e.getValue().entrySet().stream().forEach(f -> {
                System.out.println("*************************");
                System.out.println(f.getKey());
                f.getValue().stream().forEach(dish -> {
                    System.out.println(dish.getName());
                });
            });
        });

        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));

        partitionedMenu.entrySet().stream().forEach(t -> {
            System.out.println("-----------------------");
            System.out.println(t.getKey());
            t.getValue().stream().forEach(dish -> System.out.println(dish.getName()));
        });

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println(vegetarianDishesByType);

        Map<Boolean, List<Dish>> vegetarianDishList = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, toList()));
        System.out.println(vegetarianDishList);

        Map<Boolean, Optional<Dish>> mostCaloricOptionalPartitionedByVegetarian = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricOptionalPartitionedByVegetarian);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(dishComparator), Optional::get)));

        System.out.println(mostCaloricPartitionedByVegetarian);
    }

    private enum CaloricLevel {DIET, NORMAL, FAT}
}
