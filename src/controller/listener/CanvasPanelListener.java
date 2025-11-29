package controller.listener;

import controller.EditorController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CanvasPanelListener extends MouseAdapter {
    private EditorController editorController;

    public CanvasPanelListener(EditorController editorController) {
        this.editorController = editorController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        editorController.getCurrentMode().handleMouseClicked(e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        editorController.getCurrentMode().handleMousePressed(e.getPoint());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        editorController.getCurrentMode().handleMouseDragged(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        editorController.getCurrentMode().handleMouseReleased(e.getPoint());
    }
}
