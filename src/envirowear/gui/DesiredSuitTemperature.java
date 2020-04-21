package src.envirowear.gui;

import javax.swing.*;

public class DesiredSuitTemperature {

    private JLabel label;
    private JTextField textField;

    public DesiredSuitTemperature(final double defaultTemp) {
        this.label = new JLabel("Desired Suit Temperature");
        this.textField = new JTextField(String.valueOf(defaultTemp));
    }

    public JLabel getLabel() {
        return label;
    }

    public JTextField getTextField() {
        return textField;
    }
}
