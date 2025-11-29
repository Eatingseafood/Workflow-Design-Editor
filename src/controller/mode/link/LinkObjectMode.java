package controller.mode.link;

import controller.EditorController;
import controller.mode.EditorMode;
import model.basic.BasicObject;
import model.link.LinkObject;
import model.ConnectionPort;
import model.DiagramObject;

import java.awt.Point;

public abstract class LinkObjectMode extends EditorMode {
    protected BasicObject sourceObject;
    protected Point dragStartPoint;
    protected boolean isDragging = false;

    public LinkObjectMode(EditorController controller) {
        super(controller);
    }

    @Override
    public void handleMousePressed(Point p) {
        DiagramObject clickedObject = controller.findObjectAt(p);
        if (clickedObject instanceof BasicObject) {
            sourceObject = (BasicObject) clickedObject;
            dragStartPoint = p;
            isDragging = true;
        }
    }

    @Override
    public void handleMouseDragged(Point p) {
        if (isDragging && sourceObject != null) {
            controller.getMainFrame().getCanvasPanel().repaint();
        }
    }

    @Override
    public void handleMouseReleased(Point p) {
        if (isDragging && sourceObject != null) {
            DiagramObject targetObject = controller.findObjectAt(p);
            if (targetObject instanceof BasicObject && targetObject != sourceObject) {
                BasicObject target = (BasicObject) targetObject;
                
                // Find closest connection ports
                ConnectionPort sourcePort = findClosestConnectionPort(sourceObject, dragStartPoint);
                ConnectionPort targetPort = findClosestConnectionPort(target, p);
                
                if (sourcePort != null && targetPort != null) {
                    LinkObject linkObject = createLinkObject(sourcePort, targetPort);
                    controller.addDiagramObject(linkObject);
                }
            }
        }
        
        // Reset state
        isDragging = false;
        sourceObject = null;
        dragStartPoint = null;
    }
    
    private ConnectionPort findClosestConnectionPort(BasicObject object, Point point) {
        ConnectionPort[] ports = object.getConnectionPorts();
        ConnectionPort closest = null;
        double minDistance = Double.MAX_VALUE;
        
        for (ConnectionPort port : ports) {
            double distance = point.distance(port.getCenterX(), port.getCenterY());
            if (distance < minDistance) {
                minDistance = distance;
                closest = port;
            }
        }
        
        return closest;
    }

    protected abstract LinkObject createLinkObject(ConnectionPort source, ConnectionPort target);
}
