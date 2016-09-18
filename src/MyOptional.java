import java.util.Optional;

public class MyOptional {

    public static void main(String args[]) {
        Insurance insurance1 = new Insurance("insurance 1");
        Insurance insurance2 = null;

        Car car = new Car("FIAT Panda", insurance1);
        Person person = new Person("Someone", car);

        Optional<Insurance> insuranceOptional = Optional.of(insurance1);
        Optional<Person> personOptional = Optional.of(person);

        insuranceOptional.ifPresent(System.out::println);

        Optional<String> name = insuranceOptional.map(Insurance::getName);
        name.ifPresent(System.out::println);

        String outPut = personOptional.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("");

        System.out.println("The name of out put " + outPut);
    }
}

class Person {
    private String name;
    private Optional<Car> car;

    public Person(String name, Car car) {
        this.name = name;
        this.car = Optional.ofNullable(car);
    }

    public String getName() {
        return name;
    }

    public Optional<Car> getCar() {
        return car;
    }
}

class Car {
    private Optional<Insurance> insurance;
    private String name;

    public Car(String name, Insurance insurance) {
        this.name = name;
        this.insurance = Optional.ofNullable(insurance);
    }

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public String getName() {
        return name;
    }
}

class Insurance {
    private String name;

    public Insurance(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}