package repository;

import model.Course;
import java.util.ArrayList;
import java.util.List;

public class InMemoryCourseRepository implements ICourseRepository {
    private List<Course> courseList;

    public InMemoryCourseRepository() {
        courseList = new ArrayList<>();
    }

    @Override
    public void add(Course course) {
        courseList.add(course);
    }

    @Override
    public Course findByCode(String code) {
        for (Course course : courseList) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courseList); // Return a copy to prevent external modification
    }
}
