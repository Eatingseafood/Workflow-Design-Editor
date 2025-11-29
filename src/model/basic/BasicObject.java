package model.basic;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;

import model.ConnectionPort;
import model.DiagramObject;
import model.basic.label.Label;

public abstract class BasicObject extends DiagramObject {
    protected Point position;
    protected int width;
    protected int height;
    protected ConnectionPort[] connectionPorts;
    private Label label;

    public BasicObject(Point position, int width, int height) {
        super();
        this.position = position;
        this.width = width;
        this.height = height;
        this.label = new Label();
    }

    @Override
    public void move(int dx, int dy) {
        position.translate(dx, dy);
        for (ConnectionPort port : connectionPorts) {
            port.setLocation(port.getLocation().x + dx, port.getLocation().y + dy);
        }
    }

    public void drawConnectionPorts(Graphics g) {
        g.setColor(java.awt.Color.BLACK);
        for (ConnectionPort port : connectionPorts) {
            port.draw(g);
        }
    }

    // Getters
    public Point getPosition() { return position; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public ConnectionPort[] getConnectionPorts() { return connectionPorts; }
    public Label getLabel() { return label; }
    
    // Get bounds as Rectangle
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, width, height);
    }

    public abstract boolean contains(Point p);
    public abstract void draw(Graphics g);
    public abstract void initConnectionPorts();
}