package com.coursemanagement.controller;

import com.coursemanagement.model.Course;
import com.coursemanagement.model.CourseManager;
import com.coursemanagement.view.AddCourseDialog;
import com.coursemanagement.view.ListCourseDialog;
import com.coursemanagement.view.MainFrame;
import com.coursemanagement.view.SearchCourseDialog;

import javax.swing.*;
import java.util.List;

public class CourseController {
    private CourseManager manager;
    private MainFrame mainFrame;
    private AddCourseDialog addDialog;
    private SearchCourseDialog searchDialog;
    private ListCourseDialog listDialog;

    public CourseController(CourseManager manager, MainFrame mainFrame) {
        this.manager = manager;
        this.mainFrame = mainFrame;
        
        this.addDialog = new AddCourseDialog(mainFrame);
        this.searchDialog = new SearchCourseDialog(mainFrame);
        this.listDialog = new ListCourseDialog(mainFrame);

        initController();
    }

    private void initController() {
        // Main Frame Buttons
        mainFrame.getBtnAddCourse().addActionListener(e -> {
            addDialog.clearFields();
            addDialog.setVisible(true);
        });

        mainFrame.getBtnDisplayCourses().addActionListener(e -> {
            displayAllCourses();
            listDialog.setVisible(true);
        });

        mainFrame.getBtnSearchCourse().addActionListener(e -> {
            searchDialog.getTxtEnterCode().setText("");
            searchDialog.clearResults();
            searchDialog.setVisible(true);
        });

        mainFrame.getBtnExit().addActionListener(e -> {
            System.exit(0);
        });

        // Add Dialog Buttons
        addDialog.getBtnAdd().addActionListener(e -> addCourse());
        
        addDialog.getBtnClear().addActionListener(e -> addDialog.clearFields());

        // Search Dialog Buttons
        searchDialog.getBtnSearch().addActionListener(e -> searchCourse());
    }

    private void addCourse() {
        String code = addDialog.getTxtCode().getText().trim();
        String name = addDialog.getTxtName().getText().trim();
        String creditStr = addDialog.getTxtCredit().getText().trim();

        if (code.isEmpty() || name.isEmpty() || creditStr.isEmpty()) {
            JOptionPane.showMessageDialog(addDialog, "Code, name and credit cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int credit = Integer.parseInt(creditStr);
            if (credit <= 0 || credit > 33) {
                JOptionPane.showMessageDialog(addDialog, "Credit must be a positive number and less than or equals to 33.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (manager.searchCourseByCode(code) != null) {
                JOptionPane.showMessageDialog(addDialog, "Course with this code already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Course course = new Course(code, name, credit);
            manager.addCourse(course);
            JOptionPane.showMessageDialog(addDialog, "Course added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            addDialog.setVisible(false);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(addDialog, "Credit must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchCourse() {
        String code = searchDialog.getTxtEnterCode().getText().trim();
        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(searchDialog, "Please enter a course code to search.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Course course = manager.searchCourseByCode(code);
        if (course != null) {
            searchDialog.getTxtCourseName().setText(course.getName());
            searchDialog.getTxtCredit().setText(String.valueOf(course.getCredit()));
        } else {
            searchDialog.clearResults();
            JOptionPane.showMessageDialog(searchDialog, "Course not found.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void displayAllCourses() {
        List<Course> courses = manager.getCoursesOrderedByCredit();
        StringBuilder sb = new StringBuilder();
        for (Course c : courses) {
            sb.append(String.format("%-8s | %-15s | %d\n", c.getCode(), c.getName(), c.getCredit()));
        }
        listDialog.setCoursesText(sb.toString());
    }
}
