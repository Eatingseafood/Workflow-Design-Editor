package controller;

import controller.listener.CanvasPanelListener;
import controller.listener.MenuBarListener;
import controller.listener.ToolBarListener;
import controller.mode.EditorMode;
import controller.mode.SelectMode;
import controller.mode.basic.OvalMode;
import controller.mode.basic.RectMode;
import controller.mode.link.AssociationMode;
import controller.mode.link.CompositionMode;
import controller.mode.link.GeneralizationMode;
import model.DiagramObject;
import model.CompositeObject;
import model.basic.BasicObject;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditorController {
    private MainFrame mainFrame;
    private EditorMode currentMode;
    private List<DiagramObject> diagramObjects;
    
    // All available modes
    private SelectMode selectMode;
    private RectMode rectMode;
    private OvalMode ovalMode;
    private AssociationMode associationMode;
    private CompositionMode compositionMode;
    private GeneralizationMode generalizationMode;
    
    // Listeners
    private CanvasPanelListener canvasPanelListener;
    private ToolBarListener toolBarListener;
    private MenuBarListener menuBarListener;

    public EditorController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.diagramObjects = new ArrayList<>();
        
        // Initialize modes
        initializeModes();
        
        // Set default mode
        this.currentMode = selectMode;
        
        // Initialize listeners
        initializeListeners();
        
        // Set up event listeners
        setupEventListeners();
    }
    
    private void initializeModes() {
        selectMode = new SelectMode(this);
        rectMode = new RectMode(this);
        ovalMode = new OvalMode(this);
        associationMode = new AssociationMode(this);
        compositionMode = new CompositionMode(this);
        generalizationMode = new GeneralizationMode(this);
    }
    
    private void initializeListeners() {
        canvasPanelListener = new CanvasPanelListener(this);
        toolBarListener = new ToolBarListener(this);
        menuBarListener = new MenuBarListener(this);
    }
    
    private void setupEventListeners() {
        // Canvas listeners
        mainFrame.getCanvasPanel().addMouseListener(canvasPanelListener);
        mainFrame.getCanvasPanel().addMouseMotionListener(canvasPanelListener);
        
        // Toolbar listeners
        mainFrame.getToolBar().getSelectButton().addActionListener(toolBarListener);
        mainFrame.getToolBar().getAssociationButton().addActionListener(toolBarListener);
        mainFrame.getToolBar().getGeneralizationButton().addActionListener(toolBarListener);
        mainFrame.getToolBar().getCompositionButton().addActionListener(toolBarListener);
        mainFrame.getToolBar().getRectButton().addActionListener(toolBarListener);
        mainFrame.getToolBar().getOvalButton().addActionListener(toolBarListener);
        
        // Menu bar listeners
        mainFrame.getCustomMenuBar().addMenuListeners(menuBarListener);
    }

    public EditorMode getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(EditorMode mode) {
        this.currentMode = mode;
    }

    // Getters for modes
    public SelectMode getSelectMode() { return selectMode; }
    public RectMode getRectMode() { return rectMode; }
    public OvalMode getOvalMode() { return ovalMode; }
    public AssociationMode getAssociationMode() { return associationMode; }
    public CompositionMode getCompositionMode() { return compositionMode; }
    public GeneralizationMode getGeneralizationMode() { return generalizationMode; }
    
    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
    // Methods for managing diagram objects
    public List<DiagramObject> getDiagramObjects() {
        return diagramObjects;
    }
    
    public void addDiagramObject(DiagramObject object) {
        diagramObjects.add(object);
        mainFrame.getCanvasPanel().setDiagramObjects(diagramObjects);
    }
    
    public void removeDiagramObject(DiagramObject object) {
        diagramObjects.remove(object);
        mainFrame.getCanvasPanel().setDiagramObjects(diagramObjects);
    }
    
    // Methods for selection
    public List<DiagramObject> getSelectedObjects() {
        List<DiagramObject> selected = new ArrayList<>();
        for (DiagramObject obj : diagramObjects) {
            if (obj.isSelected()) {
                selected.add(obj);
            }
        }
        return selected;
    }
    
    public void clearSelection() {
        for (DiagramObject obj : diagramObjects) {
            obj.setSelected(false);
        }
        mainFrame.getCanvasPanel().repaint();
    }
    
    public void selectObject(DiagramObject object) {
        clearSelection();
        object.setSelected(true);
        mainFrame.getCanvasPanel().repaint();
    }
    
    public void addToSelection(DiagramObject object) {
        object.setSelected(true);
        mainFrame.getCanvasPanel().repaint();
    }
    
    // Methods for grouping
    public void groupSelectedObjects() {
        List<DiagramObject> selectedObjects = getSelectedObjects();
        if (selectedObjects.size() > 1) {
            // Remove selected objects from main list
            for (DiagramObject obj : selectedObjects) {
                diagramObjects.remove(obj);
                obj.setSelected(false);
            }
            
            // Create composite object
            CompositeObject composite = new CompositeObject(selectedObjects);
            composite.setSelected(true);
            diagramObjects.add(composite);
            
            mainFrame.getCanvasPanel().setDiagramObjects(diagramObjects);
        }
    }
    
    public void ungroupSelectedObject() {
        List<DiagramObject> selectedObjects = getSelectedObjects();
        if (selectedObjects.size() == 1 && selectedObjects.get(0) instanceof CompositeObject) {
            CompositeObject composite = (CompositeObject) selectedObjects.get(0);
            
            // Remove composite from main list
            diagramObjects.remove(composite);
            
            // Add children back to main list
            for (DiagramObject child : composite.getChildren()) {
                child.setSelected(true);
                diagramObjects.add(child);
            }
            
            mainFrame.getCanvasPanel().setDiagramObjects(diagramObjects);
        }
    }
    
    // Method to find object at point (for selection and interaction)
    public DiagramObject findObjectAt(Point point) {
        // Search in reverse order to get topmost object first (highest depth)
        for (int i = diagramObjects.size() - 1; i >= 0; i--) {
            DiagramObject obj = diagramObjects.get(i);
            if (obj.contains(point)) {
                return obj;
            }
        }
        return null;
    }
    
    // Method to find all objects within a rectangle (for area selection)
    public List<DiagramObject> findObjectsInArea(Rectangle area) {
        List<DiagramObject> objectsInArea = new ArrayList<>();
        for (DiagramObject obj : diagramObjects) {
            if (obj instanceof BasicObject) {
                BasicObject basicObj = (BasicObject) obj;
                Rectangle objBounds = basicObj.getBounds();
                if (area.contains(objBounds)) {
                    objectsInArea.add(obj);
                }
            } else if (obj instanceof CompositeObject) {
                CompositeObject composite = (CompositeObject) obj;
                Rectangle objBounds = composite.getBounds();
                if (area.contains(objBounds)) {
                    objectsInArea.add(obj);
                }
            }
        }
        return objectsInArea;
    }
    
    // Method to highlight current mode button
    public void updateButtonHighlight() {
        // Reset all button backgrounds
        resetButtonColors();

        // Highlight current mode button
        if (currentMode == selectMode) {
            mainFrame.getToolBar().getSelectButton().setBackground(Color.BLACK);
            mainFrame.getToolBar().getSelectButton().setForeground(Color.WHITE);
        } else if (currentMode == rectMode) {
            mainFrame.getToolBar().getRectButton().setBackground(Color.BLACK);
            mainFrame.getToolBar().getRectButton().setForeground(Color.WHITE);
        } else if (currentMode == ovalMode) {
            mainFrame.getToolBar().getOvalButton().setBackground(Color.BLACK);
            mainFrame.getToolBar().getOvalButton().setForeground(Color.WHITE);
        } else if (currentMode == associationMode) {
            mainFrame.getToolBar().getAssociationButton().setBackground(Color.BLACK);
            mainFrame.getToolBar().getAssociationButton().setForeground(Color.WHITE);
        } else if (currentMode == generalizationMode) {
            mainFrame.getToolBar().getGeneralizationButton().setBackground(Color.BLACK);
            mainFrame.getToolBar().getGeneralizationButton().setForeground(Color.WHITE);
        } else if (currentMode == compositionMode) {
            mainFrame.getToolBar().getCompositionButton().setBackground(Color.BLACK);
            mainFrame.getToolBar().getCompositionButton().setForeground(Color.WHITE);
        }
    }

    private void resetButtonColors() {
        mainFrame.getToolBar().getSelectButton().setBackground(UIManager.getColor("Button.background"));
        mainFrame.getToolBar().getSelectButton().setForeground(UIManager.getColor("Button.foreground"));
        mainFrame.getToolBar().getRectButton().setBackground(UIManager.getColor("Button.background"));
        mainFrame.getToolBar().getRectButton().setForeground(UIManager.getColor("Button.foreground"));
        mainFrame.getToolBar().getOvalButton().setBackground(UIManager.getColor("Button.background"));
        mainFrame.getToolBar().getOvalButton().setForeground(UIManager.getColor("Button.foreground"));
        mainFrame.getToolBar().getAssociationButton().setBackground(UIManager.getColor("Button.background"));
        mainFrame.getToolBar().getAssociationButton().setForeground(UIManager.getColor("Button.foreground"));
        mainFrame.getToolBar().getGeneralizationButton().setBackground(UIManager.getColor("Button.background"));
        mainFrame.getToolBar().getGeneralizationButton().setForeground(UIManager.getColor("Button.foreground"));
        mainFrame.getToolBar().getCompositionButton().setBackground(UIManager.getColor("Button.background"));
        mainFrame.getToolBar().getCompositionButton().setForeground(UIManager.getColor("Button.foreground"));
    }
}