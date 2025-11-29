package view;

import model.DiagramObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CanvasPanel extends JPanel {
    private List<DiagramObject> diagramObjects = new ArrayList<>();
    private Rectangle selectionRect = null; // For drawing selection rectangle

    public CanvasPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
    }

    public void setDiagramObjects(List<DiagramObject> objects) {
        this.diagramObjects = objects;
        repaint(); // Repaint when diagram objects change
    }

    public void setSelectionRect(Rectangle rect) {
        this.selectionRect = rect;
        repaint(); // Repaint when selection rectangle changes
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (DiagramObject obj : diagramObjects) {
            obj.draw(g);
        }

        Graphics2D g2d = (Graphics2D) g;
        if (selectionRect != null) {
            g2d.setColor(new Color(0, 0, 255, 50)); // Semi-transparent blue
            g2d.fill(selectionRect);
            g2d.setColor(Color.BLUE);
            g2d.draw(selectionRect);
        }
    }

}
