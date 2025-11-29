package controller.listener;

import controller.EditorController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarListener implements ActionListener {
    private EditorController controller;

    public ToolBarListener(EditorController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "Select":
                controller.setCurrentMode(controller.getSelectMode());
                break;
            case "Association":
                controller.setCurrentMode(controller.getAssociationMode());
                break;
            case "Generalization":
                controller.setCurrentMode(controller.getGeneralizationMode());
                break;
            case "Composition":
                controller.setCurrentMode(controller.getCompositionMode());
                break;
            case "Rect":
                controller.setCurrentMode(controller.getRectMode());
                break;
            case "Oval":
                controller.setCurrentMode(controller.getOvalMode());
                break;
        }
    }
}
