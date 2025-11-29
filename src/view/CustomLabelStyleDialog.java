package view;

import model.basic.BasicObject;
import model.basic.label.Label;
import model.basic.label.LabelShape;
import model.basic.label.Oval;
import model.basic.label.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomLabelStyleDialog extends JDialog {
    private BasicObject targetObject;
    private JTextField nameField;
    private JComboBox<String> shapeComboBox;
    private JComboBox<String> colorComboBox;
    private JComboBox<Integer> fontSizeComboBox;
    private JButton okButton;
    private JButton cancelButton;

    public CustomLabelStyleDialog(Frame parent, BasicObject targetObject) {
        super(parent, "Customize Label Style", true);
        this.targetObject = targetObject;
        initializeComponents();
        setupLayout();
        setupListeners();
        loadCurrentValues();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

    private void initializeComponents() {
        // Name field
        nameField = new JTextField(20);
        
        // Shape combo box
        shapeComboBox = new JComboBox<>(new String[]{"Rectangle", "Ellipse"});
        
        // Color combo box
        colorComboBox = new JComboBox<>(new String[]{"White", "Red", "Green", "Blue", "Yellow", "Gray"});
        
        // Font size combo box
        Integer[] fontSizes = {8, 10, 12, 14, 16, 18, 20, 24, 28, 32};
        fontSizeComboBox = new JComboBox<>(fontSizes);
        
        // Buttons
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Label name
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Label Name:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(nameField, gbc);
        
        // Label shape
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Label Shape:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(shapeComboBox, gbc);
        
        // Label color
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Label Color:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(colorComboBox, gbc);
        
        // Font size
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("Font Size:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(fontSizeComboBox, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyChanges();
                dispose();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void loadCurrentValues() {
        Label label = targetObject.getLabel();
        if (label != null) {
            nameField.setText(label.getLabelName());
            
            // Set shape
            if (label.getLabelShape() instanceof Rect) {
                shapeComboBox.setSelectedItem("Rectangle");
            } else {
                shapeComboBox.setSelectedItem("Ellipse");
            }
            
            // Set color
            Color color = label.getLabelColor();
            if (color.equals(Color.WHITE)) colorComboBox.setSelectedItem("White");
            else if (color.equals(Color.RED)) colorComboBox.setSelectedItem("Red");
            else if (color.equals(Color.GREEN)) colorComboBox.setSelectedItem("Green");
            else if (color.equals(Color.BLUE)) colorComboBox.setSelectedItem("Blue");
            else if (color.equals(Color.YELLOW)) colorComboBox.setSelectedItem("Yellow");
            else if (color.equals(Color.GRAY)) colorComboBox.setSelectedItem("Gray");
            
            // Set font size
            fontSizeComboBox.setSelectedItem(label.getLabelFontSize());
        }
    }

    private void applyChanges() {
        Label label = targetObject.getLabel();
        
        // Update name
        label.setLabelName(nameField.getText());
        
        // Update shape
        String selectedShape = (String) shapeComboBox.getSelectedItem();
        LabelShape shape = selectedShape.equals("Rectangle") ? new Rect() : new Oval();
        label.setLabelShape(shape);
        
        // Update color
        String selectedColor = (String) colorComboBox.getSelectedItem();
        Color color = Color.WHITE;
        switch (selectedColor) {
            case "Red": color = Color.RED; break;
            case "Green": color = Color.GREEN; break;
            case "Blue": color = Color.BLUE; break;
            case "Yellow": color = Color.YELLOW; break;
            case "Gray": color = Color.GRAY; break;
        }
        label.setLabelColor(color);
        
        // Update font size
        Integer fontSize = (Integer) fontSizeComboBox.getSelectedItem();
        label.setLabelFontSize(fontSize);
        
        // Repaint to show changes
        ((Frame) getParent()).repaint();
    }
}
