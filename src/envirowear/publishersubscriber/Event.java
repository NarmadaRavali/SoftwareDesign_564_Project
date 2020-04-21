package src.envirowear.publishersubscriber;

public class Event<T> {
    final T event;

    public Event(T event) {
        this.event = event;
    }

    public T getEvent() {
        return event;
    }
}
