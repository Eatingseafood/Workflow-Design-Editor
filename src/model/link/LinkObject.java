package model.link;

import model.ConnectionPort;
import model.DiagramObject;
import model.basic.BasicObject;

import java.awt.*;

public abstract class LinkObject extends DiagramObject {
    protected ConnectionPort source;
    protected ConnectionPort target;

    public LinkObject(ConnectionPort source, ConnectionPort target) {
        super();
        this.source = source;
        this.target = target;
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }

    @Override
    public void move(int dx, int dy) {
        source.setLocation(new Point(source.getLocation().x + dx, source.getLocation().y + dy));
        target.setLocation(new Point(target.getLocation().x + dx, target.getLocation().y + dy));
    }

    @Override
    public abstract void draw(Graphics g);
}