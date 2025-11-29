package controller.mode.basic;

import controller.EditorController;
import model.DiagramObject;
import model.basic.OvalObject;
import view.ToolBar;

import java.awt.*;

public class OvalMode extends BasicObjectMode {
    
    public OvalMode(EditorController controller) {
        super(controller);
    }
    
    @Override
    public void addBasicObject(Point position) {
        DiagramObject oval = new OvalObject(position, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        controller.addDiagramObject(oval);
    }


}
