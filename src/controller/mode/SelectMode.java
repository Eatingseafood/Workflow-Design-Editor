package controller.mode;

import controller.EditorController;
import model.DiagramObject;
import model.basic.BasicObject;
import view.ToolBar;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SelectMode extends EditorMode {
    private Point dragStartPoint;
    private Point currentDragPoint;
    private boolean isDragging = false;
    private boolean isAreaSelecting = false;
    private DiagramObject draggedObject;

    public SelectMode(EditorController controller) {
        super(controller);
    }

    @Override
    public void handleMouseClicked(Point p) {
        DiagramObject clickedObject = controller.findObjectAt(p);
        if (clickedObject != null) {
            controller.selectObject(clickedObject);
        } else {
            controller.clearSelection();
        }
    }

    @Override
    public void handleMousePressed(Point p) {
        dragStartPoint = p;
        DiagramObject clickedObject = controller.findObjectAt(p);
        
        if (clickedObject != null) {
            // Object clicked - prepare for move
            if (!clickedObject.isSelected()) {
                controller.selectObject(clickedObject);
            }
            draggedObject = clickedObject;
            isDragging = true;
        } else {
            // Empty area clicked - prepare for area selection
            controller.clearSelection();
            isAreaSelecting = true;
        }
    }

    @Override
    public void handleMouseDragged(Point p) {
        currentDragPoint = p;
        
        if (isDragging && draggedObject != null) {
            // Move selected objects
            int dx = p.x - dragStartPoint.x;
            int dy = p.y - dragStartPoint.y;
            
            List<DiagramObject> selectedObjects = controller.getSelectedObjects();
            for (DiagramObject obj : selectedObjects) {
                obj.move(dx, dy);
            }
            
            dragStartPoint = p;
            controller.getMainFrame().getCanvasPanel().repaint();
            
        } else if (isAreaSelecting) {
            // Update selection rectangle
            Rectangle selectionRect = createSelectionRectangle(dragStartPoint, currentDragPoint);
            controller.getMainFrame().getCanvasPanel().setSelectionRect(selectionRect);
        }
    }

    @Override
    public void handleMouseReleased(Point p) {
        if (isAreaSelecting) {
            // Complete area selection
            Rectangle selectionRect = createSelectionRectangle(dragStartPoint, p);
            List<DiagramObject> objectsInArea = controller.findObjectsInArea(selectionRect);
            
            controller.clearSelection();
            for (DiagramObject obj : objectsInArea) {
                controller.addToSelection(obj);
            }
            
            controller.getMainFrame().getCanvasPanel().setSelectionRect(null);
            isAreaSelecting = false;
        }
        
        isDragging = false;
        draggedObject = null;
    }
    
    private Rectangle createSelectionRectangle(Point start, Point end) {
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int width = Math.abs(end.x - start.x);
        int height = Math.abs(end.y - start.y);
        return new Rectangle(x, y, width, height);
    }
}
