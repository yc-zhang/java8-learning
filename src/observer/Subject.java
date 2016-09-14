package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;

public interface Subject {

    void addObserver(Observer observer);

    void notifyObservers(String message);

}

interface Observer {
    void notify(String message);
}

class SMSObserver implements Observer {
    @Override
    public void notify(String message) {
        System.out.println("SMS: " + message);
    }
}

class SNSObserver implements Observer {

    @Override
    public void notify(String message) {
        System.out.println("SNS: " + message);
    }
}

class TingtingObServer implements Observer {
    @Override
    public void notify(String message) {
        System.out.println("call Tingting with this message: " + message);
    }
}

class PagerDuty implements Subject {

    private List<Observer> observerList;

    public PagerDuty(List<Observer> observerList) {
        this.observerList = observerList;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void notifyObservers(String message) {
        this.observerList.stream().forEach(observer -> observer.notify(message));
    }

    public static void main(String args[]) {
        PagerDuty pager = new PagerDuty(new ArrayList<>());
        Observer tingting = message -> System.out.println("Tingting: " + message);

        pager.addObserver(tingting);
        pager.addObserver(message -> {
            System.out.println("Yuchen is a good man and with message: " + message);
        });

        pager.notifyObservers("The service is down!");
        pager.notifyObservers("The service is unreachable!");
        MyLogger.log(Level.ALL, () -> {
            // xxxx
            // xxxx
            /// xxx
            // xxxx
            // xxx
            return "xxx";
        });
    }

}

class MyLogger {
    public static void log(Level level, Supplier<String> logMessageSupplier) {
        if (level != Level.CONFIG) {
            System.out.println(logMessageSupplier.get());
        }
    }
}
