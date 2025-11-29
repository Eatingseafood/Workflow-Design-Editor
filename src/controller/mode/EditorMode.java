package controller.mode;

import controller.EditorController;
import view.ToolBar;

import java.awt.*;

public abstract class EditorMode {
    protected EditorController controller;
    
    public EditorMode(EditorController controller) {
        this.controller = controller;
    }
    
    public void handleMouseClicked(Point p) {

    }

    public void handleMousePressed(Point p) {

    }

    public void handleMouseDragged(Point p) {

    }

    public void handleMouseReleased(Point p) {

    }

}
