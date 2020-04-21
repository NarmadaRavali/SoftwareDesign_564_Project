package src.envirowear.gui;


import src.envirowear.thermostat.Thermostat;
import src.envirowear.thermostat.dto.Alert;
import src.envirowear.thermostat.dto.AlertType;
import src.envirowear.thermostat.dto.RegulatorState;
import src.envirowear.thermostat.dto.ThermostatMetaData;

import javax.swing.*;
import java.awt.*;

import static src.envirowear.gui.UiUtils.getAlertForType;

public class RegulatorFrame {

    private static CircleFill currentActionSymbol = new CircleFill();
    private final JFrame jFrame;
    JTextArea alertMessage = new JTextArea();
    JTextField desiredSuitTemperatureTextField = new JTextField();
    JTextField currentSuitTemperatureTextField = new JTextField();
    JTextField currentBodyTemperatureTextField = new JTextField();
    JLabel currentActionLabel = new JLabel("Current Action");


    public RegulatorFrame() {
        this.jFrame = buildJFrame();
    }

    private JFrame buildJFrame() {

        JFrame frame1 = new JFrame();

        frame1.setTitle("Regulator");
        frame1.setSize(400, 400);  // size of frame
        frame1.setVisible(true);  // to able to see frame
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        alertMessage.setBounds(120, 10, 350, 90);

        frame1.add(alertMessage);

        JLabel desiredSuitTemperatureLabel = new JLabel("Desired Suit Temperature");
        desiredSuitTemperatureLabel.setBounds(10, 100, 160, 20);
        frame1.add(desiredSuitTemperatureLabel);

        desiredSuitTemperatureTextField.setBounds(170, 100, 50, 20);
        frame1.add(desiredSuitTemperatureTextField);

        JLabel currentSuitTemperatureLabel = new JLabel("Current Suit Temperature");
        currentSuitTemperatureLabel.setBounds(250, 100, 160, 20);
        frame1.add(currentSuitTemperatureLabel);


        currentSuitTemperatureTextField.setBounds(420, 100, 50, 20);
        frame1.add(currentSuitTemperatureTextField);

        JLabel currentBodyTemperatureLabel = new JLabel("Current Body Temperature");
        currentBodyTemperatureLabel.setBounds(250, 150, 170, 20);
        frame1.add(currentBodyTemperatureLabel);


        currentBodyTemperatureTextField.setBounds(420, 150, 50, 20);
        frame1.add(currentBodyTemperatureTextField);



        currentActionLabel.setBounds(10, 200, 100, 20);
        frame1.add(currentActionLabel);

        frame1.add(currentActionSymbol);

        frame1.setSize(500, 300);  // size of frame
        frame1.setVisible(true);  // to able to see frame
        frame1.setLayout(null);


        return frame1;

    }

    public void viewRegulatorFrame(ThermostatMetaData currentMetaData) {

        clear();

        final java.util.List<Alert> warningAlerts = getAlertForType(currentMetaData.getEvents(), AlertType.WARNING);

        final java.util.List<Alert> severeAlerts = getAlertForType(currentMetaData.getEvents(), AlertType.SEVERE);

        if (!severeAlerts.isEmpty()) {
            CircleFill.alertColor = Color.RED;
            currentActionSymbol.repaint();
            alertMessage.setText(UiUtils.formatAlerts(severeAlerts));
            disableAllOtherComponents();
            return;
        } else {
            enableAllOtherComponents();
        }
        if (!warningAlerts.isEmpty()) {

                CircleFill.alertColor = Color.YELLOW;
                currentActionSymbol.repaint();
                alertMessage.setText(UiUtils.formatAlerts(warningAlerts));
        }
        desiredSuitTemperatureTextField.setText(
                String.valueOf(currentMetaData.getRegulatorReading().getDesiredSuitTemperature()));

        currentBodyTemperatureTextField.setText(
                String.valueOf(currentMetaData.getRegulatorReading().getCurrentBodyTemperature()));

        currentSuitTemperatureTextField.setText(
                String.valueOf(currentMetaData.getRegulatorReading().getCurrentSuitTemperature()));


        if (currentMetaData.getRegulatorReading().getRegulatorState() == RegulatorState.HEATING) {
            CircleFill.currentActionColor = Color.RED;
            currentActionSymbol.repaint();
            currentActionLabel.setText(RegulatorState.HEATING.toString());
        } else if (currentMetaData.getRegulatorReading().getRegulatorState() == RegulatorState.COOLING) {
            CircleFill.currentActionColor = Color.BLUE;
            currentActionSymbol.repaint();
            currentActionLabel.setText(RegulatorState.COOLING.toString());
        } else if (currentMetaData.getRegulatorReading().getRegulatorState() == RegulatorState.IDLE) {
            CircleFill.currentActionColor = Color.GREEN;
            currentActionSymbol.repaint();
            currentActionLabel.setText(RegulatorState.IDLE.toString());
        }
    }


    private void clear() {
        CircleFill.alertColor = Color.WHITE;
        currentActionSymbol.repaint();
        alertMessage.setText("");
    }

    private void disableAllOtherComponents() {
        desiredSuitTemperatureTextField.setEnabled(false);
        currentBodyTemperatureTextField.setEnabled(false);
        currentSuitTemperatureTextField.setEnabled(false);
        CircleFill.currentActionColor = Color.WHITE;
        currentActionSymbol.repaint();
        currentActionSymbol.setEnabled(false);
        currentActionLabel.setEnabled(false);

    }

    private void enableAllOtherComponents() {
        desiredSuitTemperatureTextField.setEnabled(true);
        currentBodyTemperatureTextField.setEnabled(true);
        currentSuitTemperatureTextField.setEnabled(true);
        CircleFill.currentActionColor = Color.WHITE;
        currentActionSymbol.repaint();
        currentActionSymbol.setEnabled(true);
        currentActionLabel.setEnabled(true);

    }
}

