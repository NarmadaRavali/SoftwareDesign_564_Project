package src.envirowear.thermostat.dto;

public class ReceiverReading {
    final double avgBodySensorReading;
    final double avgSuitSensorReading;
    final String receiverId;

    public ReceiverReading(double avgBodySensorReading, double avgSuitSensorReading, String receiverId) {
        this.avgBodySensorReading = avgBodySensorReading;
        this.avgSuitSensorReading = avgSuitSensorReading;
        this.receiverId = receiverId;
    }

    public double getAvgSuitSensorReading() {
        return avgSuitSensorReading;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public double getAvgBodySensorReading() {
        return avgBodySensorReading;
    }
}
