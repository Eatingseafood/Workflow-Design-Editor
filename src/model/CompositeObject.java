package model;

import java.util.List;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;

public class CompositeObject extends DiagramObject {
    private List<DiagramObject> children;

    public CompositeObject(List<DiagramObject> selectedObjects) {
        super();
        this.children = selectedObjects;
    }

    @Override
    public void draw(Graphics g) {
        for (DiagramObject child : children) {
            child.draw(g);
        }
    }

    @Override
    public void move(int dx, int dy) {
        for (DiagramObject child : children) {
            child.move(dx, dy);
        }
    }

    @Override
    public boolean contains(Point p) {
        for (DiagramObject child : children) {
            if (child.contains(p)) {
                return true;
            }
        }
        return false;
    }
    
    public List<DiagramObject> getChildren() {
        return children;
    }
    
    public Rectangle getBounds() {
        if (children.isEmpty()) {
            return new Rectangle(0, 0, 0, 0);
        }
        
        // Calculate bounding rectangle that contains all children
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        
        for (DiagramObject child : children) {
            Rectangle childBounds = null;
            if (child instanceof model.basic.BasicObject) {
                childBounds = ((model.basic.BasicObject) child).getBounds();
            } else if (child instanceof CompositeObject) {
                childBounds = ((CompositeObject) child).getBounds();
            }
            
            if (childBounds != null) {
                minX = Math.min(minX, childBounds.x);
                minY = Math.min(minY, childBounds.y);
                maxX = Math.max(maxX, childBounds.x + childBounds.width);
                maxY = Math.max(maxY, childBounds.y + childBounds.height);
            }
        }
        
        return new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }
}
