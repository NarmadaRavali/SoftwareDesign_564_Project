package src.envirowear.thermostat.receivers;

import src.envirowear.thermostat.dto.ReceiverReading;
import src.envirowear.publishersubscriber.Event;
import src.envirowear.publishersubscriber.EventSubscriber;

public interface Receiver extends EventSubscriber {

    //public void observeReading(SensorReading sensorReading);

    void clearReadings();

    ReceiverReading getReading();

    Event validateBodySensor();

    Event validateSuitSensor();
}
