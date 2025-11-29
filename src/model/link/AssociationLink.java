package model.link;

import java.awt.Graphics;

import model.ConnectionPort;

public class AssociationLink extends LinkObject {
    public AssociationLink(ConnectionPort source, ConnectionPort target) {
        super(source, target);
    }

    @Override
    public void draw(Graphics g) {
        g.drawLine(
                (int) source.getCenterX(),
                (int) source.getCenterY(),
                (int) target.getCenterX(),
                (int) target.getCenterY()
        );
    }
}
