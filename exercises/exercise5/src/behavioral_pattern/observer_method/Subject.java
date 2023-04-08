package behavioral_pattern.observer_method;

public interface Subject {
    void subscribe(Subscriber sub);
    void unsubscribe(Subscriber sub);
    void notifyObserver();
}
