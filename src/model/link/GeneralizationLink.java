package model.link;

import model.ConnectionPort;
import java.awt.Graphics;

public class GeneralizationLink extends LinkObject {
    
    public GeneralizationLink(ConnectionPort source, ConnectionPort target) {
        super(source, target);
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) target.getCenterX();
        int y = (int) target.getCenterY();
        int triangleSize = 10; // Size of the triangle
        int[] xPoints = {x, x + triangleSize / 2, x - triangleSize / 2};
        int[] yPoints = {y - triangleSize / 2, y + triangleSize / 2, y + triangleSize / 2};

        g.drawLine((int) source.getCenterX(), (int) source.getCenterY(), x, y);
        g.drawPolygon(xPoints, yPoints, 3);
    }
}
