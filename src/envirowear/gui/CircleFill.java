package src.envirowear.gui;

import javax.swing.*;
import java.awt.*;

class CircleFill extends JComponent {

    static Color currentActionColor = Color.WHITE;
    static Color alertColor = Color.WHITE;



    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(currentActionColor);
        g2.fillOval(150, 195, 30, 30);

        int[]x={60,80,100};

        //All triangle corner y coordinate
        int[]y={50,10,50};

        g2.setColor(alertColor);
        g2.fillPolygon(x,y,3);

    }
}
