package src.envirowear.thermostat.dto;

public class Alert {

    private final String message;

    public String getMessage() {
        return message;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    private final AlertType alertType;

    public Alert(String message, AlertType alertType) {
        this.message = message;
        this.alertType = alertType;
    }
}
