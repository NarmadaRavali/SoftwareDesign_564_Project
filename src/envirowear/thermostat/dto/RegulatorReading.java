package src.envirowear.thermostat.dto;

public class RegulatorReading {


    final double currentSuitTemperature;
    final double currentBodyTemperature;
    final double desiredSuitTemperature;
    final RegulatorState regulatorState;

    public double getCurrentSuitTemperature() {
        return currentSuitTemperature;
    }

    public double getCurrentBodyTemperature() {
        return currentBodyTemperature;
    }

    public double getDesiredSuitTemperature() {
        return desiredSuitTemperature;
    }

    public RegulatorState getRegulatorState() {
        return regulatorState;
    }

    public RegulatorReading(double currentSuitTemperature, double currentBodyTemperature,
                            double desiredSuitTemperature, RegulatorState regulatorState) {
        this.currentSuitTemperature = currentSuitTemperature;
        this.currentBodyTemperature = currentBodyTemperature;
        this.desiredSuitTemperature = desiredSuitTemperature;
        this.regulatorState = regulatorState;
    }
}
