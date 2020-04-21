package src.envirowear;

import src.envirowear.gui.MainFrame;
import src.envirowear.gui.UIReceiver;
import src.envirowear.gui.UISensor;
import src.envirowear.thermostat.Thermostat;
import src.envirowear.thermostat.receivers.EnvirowearReceiver;
import src.envirowear.thermostat.sensors.EnvirowearSensor;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class EnvirowearSuit {
    final Thermostat thermostat;

    public EnvirowearSuit() {
        this.thermostat = new Thermostat();
    }

    public List<UISensor> getBodySensorTextFields() {
        return this.thermostat.getBodySensors().stream().map(s -> (EnvirowearSensor) s)
                .map(EnvirowearSensor::getSensorReading).collect(Collectors.toList());
    }

    public List<UISensor> getSuitSensorTextFields() {
        return this.thermostat.getSuitSensors().stream().map(s -> (EnvirowearSensor) s)
                .map(EnvirowearSensor::getSensorReading).collect(Collectors.toList());
    }

    public List<UIReceiver> getSuitReceiverCheckBoxes() {
        return this.thermostat.getReceivers().stream().map(s -> (EnvirowearReceiver) s)
                .map(EnvirowearReceiver::getUiReceiver).collect(Collectors.toList());
    }

    public Thermostat getThermostat() {
        return thermostat;
    }

    public static void main(String[] args) {
        EnvirowearSuit suit = new EnvirowearSuit();
//        suit.getThermostat().onClockSignal(new Signal());

        JFrame mainFrame = MainFrame.buildFrame(suit.getBodySensorTextFields(),
                suit.getSuitSensorTextFields(),
                suit.getSuitReceiverCheckBoxes(),
                suit.getThermostat().getDesiredSuitTemperature(), suit.getThermostat());

    }
}
