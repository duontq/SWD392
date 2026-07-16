package view;

import model.Course;
import observer.IObserver;
import service.CourseService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListCourseDialog extends JDialog implements IObserver {
    private JTextArea textArea;
    private CourseService courseService;

    public ListCourseDialog(JFrame parent, CourseService courseService) {
        super(parent, "List Course", true);
        this.courseService = courseService;
        this.courseService.registerObserver(this); // Đăng ký làm Observer
        
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setResizable(false);

        initComponents();
        update(); // Load data initially
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("List of all courses (order by Credit)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(titleLabel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    @Override
    public void update() {
        // Hàm này sẽ được CourseService gọi tự động khi có thêm Course mới
        List<Course> courses = courseService.getCoursesOrderedByCredit();
        StringBuilder sb = new StringBuilder();
        for (Course c : courses) {
            sb.append(String.format("%-8s | %-15s | %d\n", c.getCode(), c.getName(), c.getCredit()));
        }
        textArea.setText(sb.toString());
    }
}
