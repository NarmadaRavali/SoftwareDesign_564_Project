package src.envirowear.thermostat;

import src.envirowear.Clock;
import src.envirowear.gui.UIReceiver;
import src.envirowear.thermostat.dto.Alert;
import src.envirowear.thermostat.dto.AlertType;
import src.envirowear.thermostat.dto.ReceiverReading;
import src.envirowear.thermostat.dto.RegulatorReading;
import src.envirowear.thermostat.dto.RegulatorState;
import src.envirowear.thermostat.dto.SensorType;
import src.envirowear.thermostat.dto.Signal;
import src.envirowear.gui.DesiredSuitTemperature;
import src.envirowear.publishersubscriber.Event;
import src.envirowear.thermostat.dto.ThermostatMetaData;
import src.envirowear.thermostat.receivers.EnvirowearReceiver;
import src.envirowear.thermostat.receivers.Receiver;
import src.envirowear.thermostat.regulator.Regulator;
import src.envirowear.thermostat.sensors.EnvirowearSensor;
import src.envirowear.thermostat.sensors.Sensor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Thermostat implements Clock {

    final double MIN_USER_SET_TEMP = 60;
    final double MAX_USER_SET_TEMP = 78;
    private final List<Sensor> bodySensors;
    private final List<Sensor> suitSensors;
    private final List<Receiver> receivers;
    private final Regulator regulator;
    private DesiredSuitTemperature desiredSuitTemperature = new DesiredSuitTemperature(72);

    public Thermostat() {
        this.bodySensors = createComponents(5, (Integer i) -> new EnvirowearSensor(SensorType.BODY_SENSOR,
                String.valueOf(i)));
        this.suitSensors = createComponents(5, (Integer i) -> new EnvirowearSensor(SensorType.SUIT_SENSOR,
                String.valueOf(i)));
        this.receivers = createComponents(3, (Integer i) -> new EnvirowearReceiver(String.valueOf(i)));
        this.regulator = new Regulator();
        subscribeReceiversToSensors(bodySensors, receivers);
        subscribeReceiversToSensors(suitSensors, receivers);
    }

    public double getUserDesiredSuitTemperature() {
        return Double.valueOf(desiredSuitTemperature.getTextField().getText());
    }

    public DesiredSuitTemperature getDesiredSuitTemperature() {
        return desiredSuitTemperature;
    }

    public List<Receiver> getReceivers() {
        return receivers;
    }

    public List<Sensor> getBodySensors() {
        return bodySensors;
    }

    public List<Sensor> getSuitSensors() {
        return suitSensors;
    }

    private boolean validateUserReading(double userDesiredTemperature) {
        return MIN_USER_SET_TEMP <= userDesiredTemperature && userDesiredTemperature <= MAX_USER_SET_TEMP;
    }

    private void subscribeReceiversToSensors(final List<Sensor> sensors,
                                             final List<Receiver> receivers) {
        sensors.forEach(sensor -> receivers.forEach(sensor::addSubscriber));
    }

    private <T> List<T> createComponents(int count, Function<Integer, T> componentSupplier) {
        return IntStream.range(1, count + 1).mapToObj(componentSupplier::apply).collect(Collectors.toList());
    }


    @Override
    public ThermostatMetaData onClockSignal(Signal signal) {
        this.receivers.forEach(Receiver::clearReadings);
        this.suitSensors.forEach(Sensor::notifySubscribers);
        this.bodySensors.forEach(Sensor::notifySubscribers);
        // validate desired suit temperature.
        if (!validateUserReading(getUserDesiredSuitTemperature())) {
            return new ThermostatMetaData(Arrays.asList(
                    new Event(new Alert(String.format("User Desired temperature should be between %s and %s",
                            MIN_USER_SET_TEMP, MAX_USER_SET_TEMP), AlertType.VALIDATION))), null);
        }



        List<Event> allAlerts = new ArrayList<>();

        // receiver validation
        validateReceiver().ifPresent(allAlerts::add);

        // validate readings from sensor
        allAlerts.addAll(getWarningsForBodySensors());
        allAlerts.addAll(getWarningsForSuitSensors());

        return failIfThermostatHasSeverWarnings(allAlerts)
                .orElseGet(() -> {
                    ReceiverReading receiverReading = fetchTrustedValue();
                    return new ThermostatMetaData(allAlerts,
                            regulator.getRegulatorReading(receiverReading.getAvgBodySensorReading(),
                                    getUserDesiredSuitTemperature(), receiverReading.getAvgSuitSensorReading(),
                                    suitSensors,
                                    receivers));
                });
    }

    List<Event> getWarningsForBodySensors() {
        return receivers.stream().map(receiver -> receiver.validateBodySensor()).collect(Collectors.toList());
    }


    List<Event> getWarningsForSuitSensors() {
        return receivers.stream().map(receiver -> receiver.validateSuitSensor()).collect(Collectors.toList());
    }

    Optional<ThermostatMetaData> failIfThermostatHasSeverWarnings(List<Event> alerts) {
        if (alerts.stream().map(e -> (Alert) e.getEvent()).anyMatch(a -> a.getAlertType() == AlertType.SEVERE)) {
            return Optional.of(new ThermostatMetaData(alerts, null));
        }
        return Optional.empty();
    }

    private Optional<Event> validateReceiver() {
        final List<EnvirowearReceiver> workingReceivers = receivers.stream().map(r -> (EnvirowearReceiver) r)
                .filter(r -> r.getUiReceiver().getCheckBox().isSelected())
                .collect(Collectors.toList());

        if (workingReceivers.size() < 2) {
            return Optional.of(new Event(new Alert("Receivers are not working", AlertType.SEVERE)));
        } else if (workingReceivers.size() == 2) {
            return Optional.of(new Event(new Alert("Few receivers are not working", AlertType.WARNING)));
        } else return Optional.empty();
    }


    private ReceiverReading fetchTrustedValue() {
        // returning one of the values from receiver for now
        return receivers.get(0).getReading();
    }
}
