package src.envirowear.thermostat.receivers;

import src.envirowear.EnvirowearUtils;
import src.envirowear.gui.UIReceiver;
import src.envirowear.publishersubscriber.Event;
import src.envirowear.thermostat.dto.Alert;
import src.envirowear.thermostat.dto.AlertType;
import src.envirowear.thermostat.dto.ReceiverReading;
import src.envirowear.thermostat.dto.SensorReading;
import src.envirowear.thermostat.dto.SensorType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnvirowearReceiver implements Receiver {


    private final List<SensorReading> bodySensorReadings;
    private final List<SensorReading> suitSensorReadings;
    private final String id;
    private final UIReceiver uiReceiver;

    public EnvirowearReceiver(final String id) {
        this.id = id;
        this.bodySensorReadings = new ArrayList<>();
        this.suitSensorReadings = new ArrayList<>();
        this.uiReceiver = new UIReceiver(id);
    }

    public UIReceiver getUiReceiver() {
        return uiReceiver;
    }

    @Override
    public void clearReadings() {
        this.bodySensorReadings.clear();
        this.suitSensorReadings.clear();
    }

    @Override
    public ReceiverReading getReading() {
        List<SensorReading> bodySensorWorkingValues = getWorkingSensorValues(bodySensorReadings);
        List<SensorReading> suitSensorWorkingValues = getWorkingSensorValues(suitSensorReadings);
        return new ReceiverReading(
                EnvirowearUtils.toTwoDecimalPlaces(bodySensorWorkingValues.stream()
                        .mapToDouble(SensorReading::getReadingValue)
                        .sum() / bodySensorWorkingValues.size()),
                EnvirowearUtils.toTwoDecimalPlaces(suitSensorWorkingValues.stream()
                        .mapToDouble(SensorReading::getReadingValue)
                        .sum() / suitSensorWorkingValues.size()),
                this.id);
    }

    @Override
    public Event validateBodySensor() {

        final List<SensorReading> workingSensors = getWorkingSensorValues(this.bodySensorReadings);
        if (workingSensors.size() == 0) {
            return new Event(new Alert("Body sensors are not working", AlertType.SEVERE));
        } else if (workingSensors.size() == this.bodySensorReadings.size()) {
            return new Event(new Alert("", AlertType.WORKING_GREAT));
        } else {
            return new Event(new Alert("Few body sensors are not working", AlertType.WARNING));
        }
    }

    @Override
    public Event validateSuitSensor() {

        final List<SensorReading> workingSensors = getWorkingSensorValues(this.suitSensorReadings);

        if (workingSensors.size() == 0) {
            return new Event(new Alert("Suit sensors are not working", AlertType.SEVERE));
        } else if (workingSensors.size() == this.suitSensorReadings.size()) {
            return new Event(new Alert("", AlertType.WORKING_GREAT));
        } else {
            return new Event(new Alert("Few suit sensors are not working", AlertType.WARNING));
        }
    }

    @Override
    public void react(Event event) {
        final SensorReading reading = (SensorReading) event.getEvent();
        if (reading.getSensorType() == SensorType.BODY_SENSOR) {
            bodySensorReadings.add(reading);
        } else {
            suitSensorReadings.add(reading);
        }
    }

    private List<SensorReading> getWorkingSensorValues(final List<SensorReading> sensorReadings) {
        for (int i = 0; i < sensorReadings.size(); i++) {
            int count = 1;
            final List<SensorReading> matchingSensorReading = new ArrayList<>();
            matchingSensorReading.add(sensorReadings.get(i));
            for (int j = i + 1; j < sensorReadings.size(); j++) {
                double difference = Math.abs(
                        sensorReadings.get(i).getReadingValue() - sensorReadings.get(j).getReadingValue());
                if (0 <= difference && difference <= 0.25) {
                    count++;
                    matchingSensorReading.add(sensorReadings.get(j));
                }
            }
            if (count >= sensorReadings.size() / 2 + 1) {
                return matchingSensorReading;
            }
        }
        return Collections.emptyList();
    }
}
