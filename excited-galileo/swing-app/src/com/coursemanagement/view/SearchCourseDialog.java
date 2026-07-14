package com.coursemanagement.view;

import javax.swing.*;
import java.awt.*;

public class SearchCourseDialog extends JDialog {
    private JTextField txtEnterCode;
    private JTextField txtCourseName;
    private JTextField txtCredit;
    private JButton btnSearch;

    public SearchCourseDialog(JFrame parent) {
        super(parent, "Search Course", true);
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Enter code"), gbc);
        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        txtEnterCode = new JTextField(10);
        btnSearch = new JButton("Search");
        searchPanel.add(txtEnterCode);
        searchPanel.add(Box.createHorizontalStrut(10));
        searchPanel.add(btnSearch);
        
        gbc.gridx = 1;
        panel.add(searchPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Course name"), gbc);
        txtCourseName = new JTextField(15);
        txtCourseName.setEditable(false);
        gbc.gridx = 1;
        panel.add(txtCourseName, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Credit"), gbc);
        txtCredit = new JTextField(15);
        txtCredit.setEditable(false);
        gbc.gridx = 1;
        panel.add(txtCredit, gbc);

        add(panel);
    }

    public JTextField getTxtEnterCode() { return txtEnterCode; }
    public JTextField getTxtCourseName() { return txtCourseName; }
    public JTextField getTxtCredit() { return txtCredit; }
    public JButton getBtnSearch() { return btnSearch; }

    public void clearResults() {
        txtCourseName.setText("");
        txtCredit.setText("");
    }
}
