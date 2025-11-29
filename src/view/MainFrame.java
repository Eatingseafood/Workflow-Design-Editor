package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CanvasPanel canvasPanel;
    private ToolBar toolBar;
    private MenuBar menuBar;

    public MainFrame() {
        setTitle("Workflow Design Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvasPanel = new CanvasPanel();
        toolBar = new ToolBar();
        menuBar = new MenuBar();
        add(toolBar, BorderLayout.WEST);
        add(canvasPanel, BorderLayout.CENTER);
        setJMenuBar(menuBar);
    }

    public CanvasPanel getCanvasPanel() {
        return canvasPanel;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }
    
    public MenuBar getCustomMenuBar() {
        return menuBar;
    }
}
