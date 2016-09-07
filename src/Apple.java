import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.Comparator;
import java.util.function.Predicate;

public class Apple {
    private String color;
    private Integer weight;

    public Apple(String color, Integer height) {
        this.color = color;
        this.weight = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public static void process(Runnable r) {
        r.run();
    }
    public static void main(String args[]) {
        Comparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        Predicate<Apple> greenApple = a -> a.getColor().equals("Green");
    }
}
