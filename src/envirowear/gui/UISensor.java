package src.envirowear.gui;

import javax.swing.*;

public class UISensor {

    private String id;

    public JLabel getLabel() {
        return label;
    }

    private JLabel label;
    private JTextField textField;

    public UISensor(final String id, final String label, final double defaultTemp) {
        this.id = id;
        this.label = new JLabel(label + " " + id);
        this.textField = new JTextField(String.valueOf(defaultTemp));
    }

    public JTextField getTextField() {
        return textField;
    }
}
