package model.basic.label;

import java.awt.Color;

public class Label {
    private String labelName;
    private LabelShape labelShape;
    private Color labelColor;
    private int labelFontSize;
    
    public Label() {
        this.labelName = "Object";
        this.labelShape = new Rect();
        this.labelColor = Color.WHITE;
        this.labelFontSize = 12;
    }
    
    // Getters
    public String getLabelName() { return labelName; }
    public LabelShape getLabelShape() { return labelShape; }
    public Color getLabelColor() { return labelColor; }
    public int getLabelFontSize() { return labelFontSize; }
    
    // Setters
    public void setLabelName(String labelName) { this.labelName = labelName; }
    public void setLabelShape(LabelShape labelShape) { this.labelShape = labelShape; }
    public void setLabelColor(Color labelColor) { this.labelColor = labelColor; }
    public void setLabelFontSize(int labelFontSize) { this.labelFontSize = labelFontSize; }
}
