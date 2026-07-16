import controller.CourseController;
import repository.InMemoryCourseRepository;
import service.CourseService;
import view.CourseManagement;

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

            // Cấu trúc Dependency Injection để tuân thủ Dependency Inversion Principle (DIP)
            InMemoryCourseRepository repository = new InMemoryCourseRepository();
            CourseService service = new CourseService(repository);
            CourseManagement CourseManagement = new CourseManagement();
            
            // Wire MVC
            new CourseController(service, CourseManagement);
            
            CourseManagement.setVisible(true);
        });
    }
}

