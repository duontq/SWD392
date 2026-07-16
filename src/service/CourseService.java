package service;

import model.Course;
import observer.IObserver;
import observer.ISubject;
import repository.ICourseRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CourseService implements ISubject {
    private ICourseRepository repository;
    private List<IObserver> observers;

    public CourseService(ICourseRepository repository) {
        this.repository = repository;
        this.observers = new ArrayList<>();
    }

    public void addCourse(Course course) throws Exception {
        if (repository.findByCode(course.getCode()) != null) {
            throw new Exception("Course with this code already exists.");
        }
        if (course.getCredit() <= 0 || course.getCredit() > 33) {
            throw new Exception("Credit must be a positive number and less than or equals to 33.");
        }
        repository.add(course);
        notifyObservers(); // Thông báo cho tất cả các View khi dữ liệu thay đổi
    }

    public Course searchCourseByCode(String code) {
        return repository.findByCode(code);
    }

    public List<Course> getCoursesOrderedByCredit() {
        List<Course> sortedList = repository.findAll();
        sortedList.sort(Comparator.comparingInt(Course::getCredit));
        return sortedList;
    }

    @Override
    public void registerObserver(IObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }
}

