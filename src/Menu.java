import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    public static void main(String args[]) {
        List<Dish> dishList = Arrays.asList(new Dish(300, "Pork"), new Dish(100, "Fish"), new Dish(0, "Vegetables"));
        dishList.stream().filter(d -> d.getCalories() > 100).forEach(d -> System.out.println(d.getName()));
    }
}

class Dish {
    private int calories;
    private String name;

    public Dish(int calories, String name) {
        this.calories = calories;
        this.name = name;
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
}
