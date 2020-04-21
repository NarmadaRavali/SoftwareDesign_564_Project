package src.envirowear.gui;

import javax.swing.*;

public class UIReceiver {

    public String getId() {
        return id;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    private final String id;
    private final JCheckBox checkBox;

    public UIReceiver(String id) {
        this.id = id;
        this.checkBox = new JCheckBox("Receiver " + id);
    }
}
