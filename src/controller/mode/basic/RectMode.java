package controller.mode.basic;

import controller.EditorController;
import view.ToolBar;
import model.basic.RectObject;
import model.DiagramObject;
import java.awt.*;

public class RectMode extends BasicObjectMode {
    
    public RectMode(EditorController controller) {
        super(controller);
    }
    
    @Override
    public void addBasicObject(Point position) {
        DiagramObject rect = new RectObject(position, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        controller.addDiagramObject(rect);
    }


}
