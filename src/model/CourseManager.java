package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CourseManager {
    private List<Course> courseList;

    public CourseManager() {
        courseList = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public Course searchCourseByCode(String code) {
        for (Course course : courseList) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null; // Not found
    }

    public List<Course> getCoursesOrderedByCredit() {
        List<Course> sortedList = new ArrayList<>(courseList);
        sortedList.sort(Comparator.comparingInt(Course::getCredit));
        return sortedList;
    }
    
    public List<Course> getAllCourses() {
        return courseList;
    }
}
