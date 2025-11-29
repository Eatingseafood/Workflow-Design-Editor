package controller.mode.basic;

import controller.EditorController;
import controller.mode.EditorMode;

import java.awt.*;

public abstract class BasicObjectMode extends EditorMode {
    protected static final int DEFAULT_WIDTH = 70;
    protected static final int DEFAULT_HEIGHT = 50;

    public BasicObjectMode(EditorController controller) {
        super(controller);
    }

    @Override
    public void handleMouseClicked(Point p) {
        addBasicObject(p);
    }

    public abstract void addBasicObject(Point position);
}
