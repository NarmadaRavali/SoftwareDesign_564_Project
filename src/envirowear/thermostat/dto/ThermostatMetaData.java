package src.envirowear.thermostat.dto;

import src.envirowear.publishersubscriber.Event;

import java.util.List;

public class ThermostatMetaData {

    final List<Event> events;
    final RegulatorReading regulatorReading;

    public ThermostatMetaData(List<Event> events, RegulatorReading regulatorReading) {
        this.events = events;
        this.regulatorReading = regulatorReading;
    }

    public List<Event> getEvents() {
        return events;
    }

    public RegulatorReading getRegulatorReading() {
        return regulatorReading;
    }
}
