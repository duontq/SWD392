package view;

import javax.swing.*;
import java.awt.*;

public class ListCourseDialog extends JDialog {
    private JTextArea textArea;

    public ListCourseDialog(JFrame parent) {
        super(parent, "List Course", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setResizable(false);

        initComponents();
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

    public void setCoursesText(String text) {
        textArea.setText(text);
    }
}
