package model;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ConnectionPort extends Rectangle {
    private static final int DEFAULT_PORT_SIZE = 5;

    public ConnectionPort(Point position) {
        super(position.x - DEFAULT_PORT_SIZE / 2, position.y - DEFAULT_PORT_SIZE / 2, DEFAULT_PORT_SIZE, DEFAULT_PORT_SIZE);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(this);
    }
}
