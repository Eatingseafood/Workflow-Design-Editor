package model.link;


import java.awt.Graphics;

import model.ConnectionPort;
import model.basic.BasicObject;

public class CompositionLink extends LinkObject {

    public CompositionLink(ConnectionPort source, ConnectionPort target) {
        super(source, target);
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) target.getCenterX();
        int y = (int) target.getCenterY();
        int diamondSize = 10; // Size of the diamond
        int[] xPoints = {x, x + diamondSize / 2, x, x - diamondSize / 2};
        int[] yPoints = {y - diamondSize / 2, y, y + diamondSize / 2, y};

        g.drawLine((int) source.getCenterX(), (int) source.getCenterY(), x, y);
        g.drawPolygon(xPoints, yPoints, 4);
    }

}
