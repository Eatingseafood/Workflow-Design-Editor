package controller.listener;

import controller.EditorController;
import model.DiagramObject;
import model.basic.BasicObject;
import view.CustomLabelStyleDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuBarListener implements ActionListener {
    private EditorController controller;

    public MenuBarListener(EditorController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Group":
                controller.groupSelectedObjects();
                break;
            case "Ungroup":
                controller.ungroupSelectedObject();
                break;
            case "Customize Label":
                openCustomLabelDialog();
                break;
        }
    }

    private void openCustomLabelDialog() {
        List<DiagramObject> selectedObjects = controller.getSelectedObjects();
        if (!selectedObjects.isEmpty() && selectedObjects.get(0) instanceof BasicObject) {
            BasicObject selectedObject = (BasicObject) selectedObjects.get(0);
            CustomLabelStyleDialog dialog = new CustomLabelStyleDialog(
                    controller.getMainFrame(), selectedObject);
            dialog.setVisible(true);
        }
    }
}
