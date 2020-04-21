package src.envirowear.thermostat.regulator;

import src.envirowear.EnvirowearUtils;
import src.envirowear.thermostat.dto.ReceiverReading;
import src.envirowear.thermostat.dto.RegulatorReading;
import src.envirowear.thermostat.dto.RegulatorState;
import src.envirowear.thermostat.receivers.Receiver;
import src.envirowear.thermostat.sensors.EnvirowearSensor;
import src.envirowear.thermostat.sensors.Sensor;

import java.util.List;

public class Regulator {
    public static final double DELTA = 0.1;

    public RegulatorReading getRegulatorReading(double bodyTemp,
                                                double desiredTemp,
                                                double suitTemp,
                                                List<Sensor> envirowearSensors,
                                                List<Receiver> envirowearReceivers) {
        if (97.7 <= bodyTemp && bodyTemp <= 99.5) {
            // body tep is normal.. monitoring suit.
            if (desiredTemp > suitTemp) {
                return heat(envirowearSensors, envirowearReceivers, desiredTemp);
            } else if ( desiredTemp < suitTemp) {
                return cool(envirowearSensors, envirowearReceivers, desiredTemp);
            } else {
                return new RegulatorReading(suitTemp, bodyTemp, desiredTemp, RegulatorState.IDLE);
            }
        } else if (bodyTemp > 99.5) {
            return cool(envirowearSensors, envirowearReceivers, desiredTemp);
        } else {
            // heat

            return heat(envirowearSensors, envirowearReceivers, desiredTemp);
        }
    }

    private ReceiverReading fetchTrustedValue(List<Receiver> receivers) {
        // returning one of the values from receiver for now
        return receivers.get(0).getReading();
    }

    private RegulatorReading cool(List<Sensor> envirowearSensors, List<Receiver> envirowearReceivers, double desiredTemp) {
        //cool
        envirowearSensors.forEach(
                r -> {
                    EnvirowearSensor envirowearSensor = (EnvirowearSensor) r;
                    double currentVal = Double.valueOf(envirowearSensor.getSensorReading().getTextField().getText());
                    envirowearSensor.getSensorReading().getTextField().setText(
                            EnvirowearUtils.toTwoDecimalPlacesInStringFormat(currentVal - DELTA));
                }
        );
        ReceiverReading receiverReading = fetchTrustedValue(envirowearReceivers);
        return new RegulatorReading(receiverReading.getAvgSuitSensorReading(),
                receiverReading.getAvgBodySensorReading(), desiredTemp, RegulatorState.COOLING);
    }

    private RegulatorReading heat(List<Sensor> envirowearSensors, List<Receiver> envirowearReceivers, double desiredTemp) {
        //cool
        envirowearSensors.forEach(
                r -> {
                    EnvirowearSensor envirowearSensor = (EnvirowearSensor) r;
                    double currentVal = Double.valueOf(envirowearSensor.getSensorReading().getTextField().getText());
                    envirowearSensor.getSensorReading().getTextField().setText(
                            EnvirowearUtils.toTwoDecimalPlacesInStringFormat(currentVal + DELTA));
                }
        );
        ReceiverReading receiverReading = fetchTrustedValue(envirowearReceivers);
        return new RegulatorReading(receiverReading.getAvgSuitSensorReading(),
                receiverReading.getAvgBodySensorReading(), desiredTemp, RegulatorState.HEATING);
    }
}
