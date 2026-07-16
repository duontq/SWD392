package controller;

import model.Course;
import service.CourseService;
import view.AddCourseDialog;
import view.ListCourseDialog;
import view.MainFrame;
import view.SearchCourseDialog;

import javax.swing.*;

public class CourseController {
    private CourseService service;
    private MainFrame mainFrame;
    private AddCourseDialog addDialog;
    private SearchCourseDialog searchDialog;
    private ListCourseDialog listDialog;

    public CourseController(CourseService service, MainFrame mainFrame) {
        this.service = service;
        this.mainFrame = mainFrame;
        
        this.addDialog = new AddCourseDialog(mainFrame);
        this.searchDialog = new SearchCourseDialog(mainFrame);
        
        // Pass service to view so it can observe data changes (Observer Pattern)
        this.listDialog = new ListCourseDialog(mainFrame, service); 

        initController();
    }

    private void initController() {
        // Main Frame Buttons
        mainFrame.getBtnAddCourse().addActionListener(e -> {
            addDialog.clearFields();
            addDialog.setVisible(true);
        });

        mainFrame.getBtnDisplayCourses().addActionListener(e -> {
            // No need to manually fetch and set data. Observer pattern handles it automatically!
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
            Course course = new Course(code, name, credit);
            
            // Delegate logic and validation to Service
            service.addCourse(course); 
            
            JOptionPane.showMessageDialog(addDialog, "Course added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            addDialog.setVisible(false);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(addDialog, "Credit must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(addDialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchCourse() {
        String code = searchDialog.getTxtEnterCode().getText().trim();
        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(searchDialog, "Please enter a course code to search.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Course course = service.searchCourseByCode(code);
        if (course != null) {
            searchDialog.getTxtCourseName().setText(course.getName());
            searchDialog.getTxtCredit().setText(String.valueOf(course.getCredit()));
        } else {
            searchDialog.clearResults();
            JOptionPane.showMessageDialog(searchDialog, "Course not found.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
