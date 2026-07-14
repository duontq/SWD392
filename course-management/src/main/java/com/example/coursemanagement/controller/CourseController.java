package com.example.coursemanagement.controller;

import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "add-course";
    }

    @PostMapping("/add")
    public String addCourse(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-course";
        }
        courseService.addCourse(course);
        model.addAttribute("successMessage", "Course added successfully!");
        model.addAttribute("course", new Course()); // reset form
        return "add-course";
    }

    @GetMapping("/search")
    public String showSearchCourseForm() {
        return "search-course";
    }

    @PostMapping("/search")
    public String searchCourse(@RequestParam("code") String code, Model model) {
        Optional<Course> courseOpt = courseService.findCourseByCode(code);
        if (courseOpt.isPresent()) {
            model.addAttribute("course", courseOpt.get());
        } else {
            model.addAttribute("errorMessage", "Course not found.");
        }
        model.addAttribute("searchCode", code);
        return "search-course";
    }

    @GetMapping("/list")
    public String listAllCourses(Model model) {
        List<Course> courses = courseService.getAllCoursesOrderedByCredit();
        model.addAttribute("courses", courses);
        return "list-courses";
    }
}
