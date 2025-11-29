package view;

import javax.swing.*;

public class ToolBar extends JToolBar {
    private JButton selectButton;
    private JButton associationButton;
    private JButton generalizationButton;
    private JButton compositionButton;
    private JButton rectButton;
    private JButton ovalButton;

    public ToolBar() {
        super(VERTICAL);
        setFloatable(false);
        addButtons();
    }

    private void addButtons() {
        selectButton = new JButton("Select");
        associationButton = new JButton("Association");
        generalizationButton = new JButton("Generalization");
        compositionButton = new JButton("Composition");
        rectButton = new JButton("Rect");
        ovalButton = new JButton("Oval");

        add(selectButton);
        add(associationButton);
        add(generalizationButton);
        add(compositionButton);
        add(rectButton);
        add(ovalButton);
    }

    public JButton getSelectButton() { return selectButton; }
    public JButton getAssociationButton() { return associationButton; }
    public JButton getGeneralizationButton() { return generalizationButton; }
    public JButton getCompositionButton() { return compositionButton; }
    public JButton getRectButton() { return rectButton; }
    public JButton getOvalButton() { return ovalButton; }
}