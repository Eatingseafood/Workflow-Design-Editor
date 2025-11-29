package view;

import controller.listener.MenuBarListener;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private JMenuItem groupMenuItem;
    private JMenuItem ungroupMenuItem;
    private JMenuItem customizeLabelMenuItem;

    public MenuBar() {
        JMenu editMenu = new JMenu("Edit");
        groupMenuItem = new JMenuItem("Group");
        ungroupMenuItem = new JMenuItem("Ungroup");
        customizeLabelMenuItem = new JMenuItem("Customize Label");

        editMenu.add(groupMenuItem);
        editMenu.add(ungroupMenuItem);
        editMenu.add(customizeLabelMenuItem);
        add(editMenu);
    }
    
    public void addMenuListeners(MenuBarListener listener) {
        groupMenuItem.addActionListener(listener);
        ungroupMenuItem.addActionListener(listener);
        customizeLabelMenuItem.addActionListener(listener);
    }
}
