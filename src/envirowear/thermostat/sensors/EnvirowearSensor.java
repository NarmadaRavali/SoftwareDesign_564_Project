package src.envirowear.thermostat.sensors;

import src.envirowear.EnvirowearUtils;
import src.envirowear.thermostat.dto.SensorReading;
import src.envirowear.thermostat.dto.SensorType;
import src.envirowear.gui.UISensor;
import src.envirowear.publishersubscriber.Event;
import src.envirowear.publishersubscriber.EventSubscriber;
import src.envirowear.thermostat.receivers.Receiver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static src.envirowear.EnvirowearUtils.generateRandomDouble;

public class EnvirowearSensor implements Sensor {

    private static Random RANDOM = new Random();

    private final List<Receiver> subscribers;
    private final SensorType sensorType;
    private final String id;

    public UISensor getSensorReading() {
        return sensorReading;
    }

    private final UISensor sensorReading;

    public EnvirowearSensor(final SensorType sensorType, final String id) {
        this.subscribers = new ArrayList<>();
        this.sensorType = sensorType;
        this.id = id;
        this.sensorReading = new UISensor(this.id, this.sensorType.toString().toLowerCase(),
                this.sensorType == SensorType.BODY_SENSOR
                        ? generateRandomDouble(97.7, 97.8) : generateRandomDouble(73.1, 73.2));
    }

    @Override
    public void addSubscriber(EventSubscriber eventSubscriber) {
        subscribers.add((Receiver) eventSubscriber);
    }

    @Override
    public void notifySubscribers() {
        subscribers.forEach(subscriber -> subscriber.react(
                new Event(new SensorReading(this.sensorType,
                        Double.parseDouble(this.sensorReading.getTextField().getText()),
                        this.id))
                ));
    }


}
