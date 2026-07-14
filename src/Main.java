

import controller.CourseController;
import model.CourseManager;
import view.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Run GUI construction on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Set System L&F
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Ignore and use default Look and Feel
            }

            CourseManager manager = new CourseManager();
            MainFrame mainFrame = new MainFrame();
            
            // Wire MVC
            new CourseController(manager, mainFrame);
            
            mainFrame.setVisible(true);
        });
    }
}
