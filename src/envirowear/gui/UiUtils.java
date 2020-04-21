package src.envirowear.gui;

import src.envirowear.publishersubscriber.Event;
import src.envirowear.thermostat.dto.Alert;
import src.envirowear.thermostat.dto.AlertType;

import java.util.List;
import java.util.stream.Collectors;

public class UiUtils {

    public static List<Alert> getAlertForType(final List<Event> events, final AlertType type) {
        return events.stream().map(event -> (Alert) event.getEvent()).filter(a -> a.getAlertType() == type)
                .collect(Collectors.toList());
    }

    public static String formatAlerts(List<Alert> alerts) {
        String output = alerts.stream().map(Alert::getMessage).distinct().collect(Collectors.joining("\n"));
        System.out.println(output);
        return output;
    }
}
