package model;

import java.awt.Graphics;
import java.awt.Point;

public abstract class DiagramObject {
    private int depth;
    private boolean selected;

    public DiagramObject() {
        this.depth = 0;
        this.selected = false;
    }

    public int getDepth() {
        return depth;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public abstract void move(int dx, int dy);
    public abstract void draw(Graphics g);
    public abstract boolean contains(Point p);
}
