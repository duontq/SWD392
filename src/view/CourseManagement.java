package view;

import javax.swing.*;
import java.awt.*;

public class CourseManagement extends JFrame {
    private JButton btnAddCourse;
    private JButton btnDisplayCourses;
    private JButton btnSearchCourse;
    private JButton btnExit;

    public CourseManagement() {
        setTitle("Courses Management");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        btnAddCourse = new JButton("Add a new Course");
        btnDisplayCourses = new JButton("Display all Courses");
        btnSearchCourse = new JButton("Search Course by Course Code");
        btnExit = new JButton("Exit Application");

        panel.add(btnAddCourse);
        panel.add(btnDisplayCourses);
        panel.add(btnSearchCourse);
        panel.add(btnExit);

        add(panel, BorderLayout.CENTER);
        
        JLabel titleLabel = new JLabel("Courses Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        add(titleLabel, BorderLayout.NORTH);
    }

    public JButton getBtnAddCourse() { return btnAddCourse; }
    public JButton getBtnDisplayCourses() { return btnDisplayCourses; }
    public JButton getBtnSearchCourse() { return btnSearchCourse; }
    public JButton getBtnExit() { return btnExit; }
}

