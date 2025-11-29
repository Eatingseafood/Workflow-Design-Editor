package model.basic;

import model.ConnectionPort;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class OvalObject extends BasicObject {
    private Ellipse2D ellipse;

    public OvalObject(Point position, int width, int height) {
        super(position, width, height);
        this.ellipse = new Ellipse2D.Double(position.x, position.y, width, height);
        initConnectionPorts();
    }

    @Override
    public boolean contains(Point p) {
        return ellipse.contains(p);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // Draw the ellipse
        g2d.setColor(Color.BLACK);
        g2d.draw(ellipse);
        
        // Draw the label
        drawLabel(g2d);
        
        // Draw connection ports if selected
        if (isSelected()) {
            drawConnectionPorts(g);
        }
    }
    
    private void drawLabel(Graphics2D g2d) {
        if (getLabel() != null && getLabel().getLabelName() != null) {
            // Set font
            Font font = new Font("Arial", Font.PLAIN, getLabel().getLabelFontSize());
            g2d.setFont(font);
            
            // Calculate text position (center of ellipse)
            FontMetrics fm = g2d.getFontMetrics();
            String text = getLabel().getLabelName();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();
            
            int textX = position.x + (width - textWidth) / 2;
            int textY = position.y + (height + textHeight) / 2 - fm.getDescent();
            
            // Draw label background if shape is rectangle
            if (getLabel().getLabelShape() instanceof model.basic.label.Rect) {
                g2d.setColor(getLabel().getLabelColor());
                g2d.fillRect(textX - 2, textY - textHeight + fm.getDescent(), textWidth + 4, textHeight);
            } else if (getLabel().getLabelShape() instanceof model.basic.label.Oval) {
                g2d.setColor(getLabel().getLabelColor());
                g2d.fillOval(textX - 2, textY - textHeight + fm.getDescent(), textWidth + 4, textHeight);
            }
            
            // Draw text
            g2d.setColor(Color.BLACK);
            g2d.drawString(text, textX, textY);
        }
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        ellipse.setFrame(position.x, position.y, width, height);
    }

    @Override
    public void initConnectionPorts() {
        connectionPorts = new ConnectionPort[4];
        connectionPorts[0] = new ConnectionPort(new Point(position.x + width / 2, position.y));
        connectionPorts[1] = new ConnectionPort(new Point(position.x + width, position.y + height / 2));
        connectionPorts[2] = new ConnectionPort(new Point(position.x + width / 2, position.y + height));
        connectionPorts[3] = new ConnectionPort(new Point(position.x, position.y + height / 2));
    }
}
