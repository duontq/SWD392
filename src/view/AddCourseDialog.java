package view;

import javax.swing.*;
import java.awt.*;

public class AddCourseDialog extends JDialog {
    private JTextField txtCode;
    private JTextField txtName;
    private JTextField txtCredit;
    private JButton btnAdd;
    private JButton btnClear;

    public AddCourseDialog(JFrame parent) {
        super(parent, "Add Course", true);
        setSize(350, 200);
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
        panel.add(new JLabel("Code"), gbc);
        txtCode = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtCode, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Name"), gbc);
        txtName = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Credit"), gbc);
        txtCredit = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtCredit, gbc);

        JPanel buttonPanel = new JPanel();
        btnAdd = new JButton("Add");
        btnClear = new JButton("Clear");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnClear);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        add(panel);
    }

    public JTextField getTxtCode() { return txtCode; }
    public JTextField getTxtName() { return txtName; }
    public JTextField getTxtCredit() { return txtCredit; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnClear() { return btnClear; }

    public void clearFields() {
        txtCode.setText("");
        txtName.setText("");
        txtCredit.setText("");
    }
}
