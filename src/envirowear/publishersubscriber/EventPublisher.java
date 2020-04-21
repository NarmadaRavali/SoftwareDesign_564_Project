package src.envirowear.publishersubscriber;

public interface EventPublisher {

    void addSubscriber(EventSubscriber eventSubscriber);
    void notifySubscribers();
}
