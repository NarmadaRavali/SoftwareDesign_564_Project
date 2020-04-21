package src.envirowear.thermostat.dto;

public class SensorReading {
    private SensorType sensorType;
    private double readingValue;
    private String id;

    public SensorReading(SensorType sensorType, double readingValue, String id) {
        this.sensorType = sensorType;
        this.readingValue = readingValue;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public double getReadingValue() {
        return readingValue;
    }
}
