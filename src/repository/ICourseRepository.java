package repository;

import model.Course;
import java.util.List;

public interface ICourseRepository {
    void add(Course course);
    Course findByCode(String code);
    List<Course> findAll();
}

