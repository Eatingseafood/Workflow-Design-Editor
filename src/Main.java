import controller.EditorController;
import view.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            EditorController controller = new EditorController(mainFrame);
            mainFrame.setVisible(true);
        });
    }
}
