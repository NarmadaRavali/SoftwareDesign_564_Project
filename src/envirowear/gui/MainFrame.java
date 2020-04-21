package src.envirowear.gui;

import src.envirowear.publishersubscriber.Event;
import src.envirowear.thermostat.dto.Alert;
import src.envirowear.thermostat.dto.AlertType;
import src.envirowear.thermostat.dto.Signal;
import src.envirowear.thermostat.Thermostat;
import src.envirowear.thermostat.dto.ThermostatMetaData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static src.envirowear.gui.UiUtils.getAlertForType;

public class MainFrame {
    static JFrame frame;
    static JLabel b1, b2, b3, b4, b5, s1, s2, s3, s4, s5, l, receivers_status, power_label;
    static JCheckBox r1, r2, r3, power_on_off;
    static JTextField tb1, tb2, tb3, tb4, tb5, ts1, ts2, ts3, ts4, ts5, suit_sensors_status, body_sensors_status;
    static JButton b;

    public static void createLabels() {

        Font f = new Font("Lucida Grande", Font.BOLD, 13);

        power_label = new JLabel("Power On");
        power_label.setFont(f);
        power_label.setBounds(30, 20, 90, 20);

        power_on_off = new JCheckBox();
        power_on_off.setSelected(true);
        power_on_off.setBounds(150, 20, 50, 20);

        power_on_off.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (power_on_off.isSelected() == false) {
                    tb1.setEnabled(false);
                    tb2.setEnabled(false);
                    tb3.setEnabled(false);
                    tb4.setEnabled(false);
                    tb5.setEnabled(false);
                    ts1.setEnabled(false);
                    ts2.setEnabled(false);
                    ts3.setEnabled(false);
                    ts4.setEnabled(false);
                    ts5.setEnabled(false);
                } else {
                    tb1.setEnabled(true);
                    tb2.setEnabled(true);
                    tb3.setEnabled(true);
                    tb4.setEnabled(true);
                    tb5.setEnabled(true);
                    ts1.setEnabled(true);
                    ts2.setEnabled(true);
                    ts3.setEnabled(true);
                    ts4.setEnabled(true);
                    ts5.setEnabled(true);
                }

            }
        });


        b1 = new JLabel("Body sensor 1");
        b1.setFont(f);
        b1.setBounds(10, 60, 100, 20);


        b2 = new JLabel("Body sensor 2");
        b2.setFont(f);
        b2.setBounds(10, 90, 100, 20);

        b3 = new JLabel("Body sensor 3");
        b3.setFont(f);
        b3.setBounds(10, 120, 100, 20);

        b4 = new JLabel("Body sensor 4");
        b4.setFont(f);
        b4.setBounds(10, 150, 100, 20);

        b5 = new JLabel("Body sensor 5");
        b5.setFont(f);
        b5.setBounds(10, 180, 100, 20);

        s1 = new JLabel("Suit sensor 1");
        s1.setFont(f);
        s1.setBounds(200, 60, 100, 20);

        s2 = new JLabel("Suit sensor 2");
        s2.setFont(f);
        s2.setBounds(200, 90, 100, 20);

        s3 = new JLabel("Suit sensor 3");
        s3.setFont(f);
        s3.setBounds(200, 120, 100, 20);

        s4 = new JLabel("Suit sensor 4");
        s4.setFont(f);
        s4.setBounds(200, 150, 100, 20);

        s5 = new JLabel("Suit sensor 5");
        s5.setFont(f);
        s5.setBounds(200, 180, 100, 20);

        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(b5);
        frame.add(s1);
        frame.add(s2);
        frame.add(s3);
        frame.add(s4);
        frame.add(s5);

        frame.add(power_label);
        frame.add(power_on_off);
    }


    public static void createTextBoxes() {

        tb1 = new JTextField("97.7");
        tb1.setBounds(120, 60, 50, 20);

        tb2 = new JTextField("97.8");
        tb2.setBounds(120, 90, 50, 20);

        tb3 = new JTextField("97.7");
        tb3.setBounds(120, 120, 50, 20);

        tb4 = new JTextField("97.9");
        tb4.setBounds(120, 150, 50, 20);

        tb5 = new JTextField("97.8");
        tb5.setBounds(120, 180, 50, 20);

        ts1 = new JTextField("65.5");
        ts1.setBounds(300, 60, 50, 20);

        ts2 = new JTextField("65.6");
        ts2.setBounds(300, 90, 50, 20);

        ts3 = new JTextField("65.5");
        ts3.setBounds(300, 120, 50, 20);

        ts4 = new JTextField("66");
        ts4.setBounds(300, 150, 50, 20);

        ts5 = new JTextField("67");
        ts5.setBounds(300, 180, 50, 20);

        l = new JLabel("Sensors System");
        l.setBounds(20, 30, 100, 20);

        body_sensors_status = new JTextField();
        body_sensors_status.setBounds(50, 270, 150, 20);

        suit_sensors_status = new JTextField();
        suit_sensors_status.setBounds(250, 270, 150, 20);

        r1 = new JCheckBox("Receiver 1");
        r1.setBounds(50, 220, 100, 20);

        r2 = new JCheckBox("Receiver 2");
        r2.setBounds(200, 220, 100, 20);

        r3 = new JCheckBox("Receiver 3");
        r3.setBounds(350, 220, 100, 20);

        receivers_status = new JLabel("Receivers status");
        receivers_status.setBounds(600, 220, 200, 20);


        b = new JButton("Click");
        b.setBounds(200, 330, 80, 30);

        frame.add(tb1);
        frame.add(tb2);
        frame.add(tb3);
        frame.add(tb4);
        frame.add(tb5);
        frame.add(ts1);
        frame.add(ts2);
        frame.add(ts3);
        frame.add(ts4);
        frame.add(ts5);
        frame.add(suit_sensors_status);
        frame.add(body_sensors_status);
        //   frame.add(l);

        frame.add(r1);
        frame.add(r2);
        frame.add(r3);

        frame.add(b);
        //   frame.add(receivers_status);


//        b.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//
//                CentralUnit cu = new CentralUnit();
//
//                BodySuitSensorsChecker bssc = new BodySuitSensorsChecker();
//                boolean suit_body_senors_status = bssc.checkBodySuitSenors();
//                if(!suit_body_senors_status)
//                {
//                    Toolkit.getDefaultToolkit().beep();
//                    JOptionPane.showMessageDialog(frame, "Can't Trust EnvirowearSensor System", "Warning", JOptionPane.WARNING_MESSAGE);
//
//                }
//
//
//                ReceiversChecker rs = new ReceiversChecker();
//                boolean receivers_status = rs.getReceiversStatus();
//                if(!receivers_status)
//                {
//                    Toolkit.getDefaultToolkit().beep();
//                    JOptionPane.showMessageDialog(frame, "Can't Trust Receivers System", "Warning", JOptionPane.WARNING_MESSAGE);
//
//                }
//
//
//
//                SensorsReceiversChecker src = new SensorsReceiversChecker();
//                boolean frame_cental_unit= src.getSensorsReceiversStatus();
//
//                if(frame_cental_unit)
//                    cu.createJframe();
//                else {
//                    Toolkit.getDefaultToolkit().beep();
//                    JOptionPane.showMessageDialog(frame, "Can't Trust EnvirowearSensor and Receivers System", "Warning", JOptionPane.WARNING_MESSAGE);
//
//                }
//
//            }
//        });


    }


    private static void positionTextBoxes(final JFrame frame,
                                          final List<UISensor> bodySensor,
                                          final List<UISensor> suitSensor) {
        {
            int bodySensorLabelXpos = 40;
            int bodySensorLabelYpos = 30;

            int yPosOffset = 30;
            for (UISensor sensor : bodySensor) {
                bodySensorLabelYpos = bodySensorLabelYpos + yPosOffset;
                sensor.getLabel().setBounds(bodySensorLabelXpos, bodySensorLabelYpos, 100, 20);
                sensor.getTextField().setBounds(bodySensorLabelXpos + 100 + 10, bodySensorLabelYpos, 50,
                        20);
                frame.add(sensor.getTextField());
                frame.add(sensor.getLabel());
            }
        }

        {
            int suitSensorLabelXpos = 240;
            int suitSensorLabelYpos = 30;

            int yPosOffset = 30;
            for (UISensor sensor : suitSensor) {
                suitSensorLabelYpos = suitSensorLabelYpos + yPosOffset;
                sensor.getLabel().setBounds(suitSensorLabelXpos, suitSensorLabelYpos, 100, 20);
                sensor.getTextField().setBounds(suitSensorLabelXpos + 100 + 10, suitSensorLabelYpos, 50,
                        20);
                frame.add(sensor.getTextField());
                frame.add(sensor.getLabel());
            }
        }
    }


    public static void positionCheckBoxes(final JFrame frame, final List<UIReceiver> receivers) {
        int xPos = 20;
        int yPos = 220;

        int xOffset = 150;
        for (UIReceiver receiver : receivers) {
            receiver.getCheckBox().setBounds(xPos, yPos, 100, 20);
            frame.add(receiver.getCheckBox());
            xPos += xOffset;
        }
    }

    public static JFrame buildFrame(final List<UISensor> bodySensors,
                                    final List<UISensor> suitSensor,
                                    final List<UIReceiver> receivers,
                                    final DesiredSuitTemperature desiredSuitTemperature,
                                    final Thermostat thermostat) {
        final JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setTitle("Envirowear Body Suit");
        JCheckBox onOffSwitch = addPowerButton(frame);
        positionTextBoxes(frame, bodySensors, suitSensor);
        positionCheckBoxes(frame, receivers);
        positionDesiredSuitTemperature(frame, desiredSuitTemperature);
        JButton startButton = addStartButton(frame);
        disableSensorWhenTurnedOff(onOffSwitch, bodySensors, suitSensor, receivers, startButton);
        RegulatorFrame regulatorFrame = new RegulatorFrame();
        onPressingStartButton(startButton, thermostat, regulatorFrame);
        frame.setSize(450, 400);  // size of frame
        frame.setVisible(true);  // to able to see frame
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }


    private static void positionDesiredSuitTemperature(final JFrame frame,
                                                       final DesiredSuitTemperature suitTemperature) {

        int xpos = 200;
        suitTemperature.getLabel().setBounds(xpos, 20, 180, 20);
        suitTemperature.getTextField().setBounds(xpos + 180, 20, 50, 20);

        frame.add(suitTemperature.getLabel());
        frame.add(suitTemperature.getTextField());
    }

    private static JButton addStartButton(final JFrame frame) {
        JButton startButton = new JButton("Generate Round");
        startButton.setBounds(150, 300, 200, 30);

        frame.add(startButton);
        return startButton;
    }

    private static JCheckBox addPowerButton(final JFrame frame) {
        Font f = new Font("Lucida Grande", Font.BOLD, 13);

        power_label = new JLabel("Power On");
        power_label.setFont(f);
        power_label.setBounds(40, 20, 90, 20);

        power_on_off = new JCheckBox();
        power_on_off.setSelected(true);
        power_on_off.setBounds(140, 20, 50, 20);

        frame.add(power_label);
        frame.add(power_on_off);
        return power_on_off;
    }

    private static void disableSensorWhenTurnedOff(final JCheckBox onOffSwitch,
                                                   final List<UISensor> bodySensors,
                                                   final List<UISensor> suitSensors,
                                                   final List<UIReceiver> receivers,
                                                   final JButton startButton) {
        onOffSwitch.addActionListener(e -> {
            if (onOffSwitch.isSelected()) {
                Stream.of(bodySensors, suitSensors).flatMap(Collection::stream)
                        .forEach(s -> s.getTextField().setEnabled(true));
                receivers.forEach(r -> r.getCheckBox().setEnabled(true));
                startButton.setEnabled(true);
            } else {
                Stream.of(bodySensors, suitSensors).flatMap(Collection::stream)
                        .forEach(s -> s.getTextField().setEnabled(false));
                receivers.forEach(r -> r.getCheckBox().setEnabled(false));
                startButton.setEnabled(false);
            }
        });
    }

    private static void onPressingStartButton(final JButton startButton,
                                              final Thermostat thermostat,
                                              final RegulatorFrame regulatorFrame) {
        startButton.addActionListener(e -> {
            if (startButton.isEnabled()) {
                //JFrame frame = RegulatorFrame.buildSecondFrame();
                final ThermostatMetaData thermostatMetaData = thermostat.onClockSignal(new Signal());
                final List<Event> events = thermostatMetaData.getEvents();

                final List<Alert> validationAlerts = getAlertForType(events, AlertType.VALIDATION);
                if (!validationAlerts.isEmpty()) {
                    JOptionPane.showMessageDialog(null, UiUtils.formatAlerts(validationAlerts));
                }
                regulatorFrame.viewRegulatorFrame(thermostatMetaData);
            }
        });
    }


}
