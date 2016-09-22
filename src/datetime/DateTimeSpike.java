package datetime;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

public class DateTimeSpike {

    public static void main(String... args) {
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        System.out.println(dayOfWeek);
        System.out.println(today);

        Period p1 = Period.between(LocalDate.of(2014, 3, 8), LocalDate.of(2014, 3, 18));
        System.out.println(p1);
    }

}
