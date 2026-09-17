package manager;

import model.Course;
import util.FileHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * CourseManager - handles CRUD operations for courses.
 * Functional Module 2: Course Management
 */
public class CourseManager {
    private List<Course> courses;
    private static final String FILE = "data/courses.csv";

    public CourseManager() {
        courses = new ArrayList<>();
        loadFromFile();
    }

    /**
     * Adds a new course.
     */
    public boolean addCourse(Course course) {
        if (findByCode(course.getCourseCode()) != null) {
            System.out.println("Error: Course code already exists.");
            return false;
        }
        if (course.getCredits() <= 0) {
            System.out.println("Error: Credits must be positive.");
            return false;
        }
        courses.add(course);
        saveToFile();
        System.out.println("Course added: " + course.getCourseTitle());
        return true;
    }

    /**
     * Removes a course by code.
     */
    public boolean removeCourse(String courseCode) {
        Course c = findByCode(courseCode);
        if (c == null) {
            System.out.println("Error: Course not found.");
            return false;
        }
        courses.remove(c);
        saveToFile();
        System.out.println("Course removed: " + c.getCourseTitle());
        return true;
    }

    /**
     * Finds a course by code.
     */
    public Course findByCode(String courseCode) {
        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Returns all courses.
     */
    public List<Course> getAllCourses() {
        return courses;
    }

    /**
     * Returns count of courses.
     */
    public int getCount() {
        return courses.size();
    }

    /**
     * Persists courses to CSV file.
     */
    private void saveToFile() {
        List<String> lines = new ArrayList<>();
        lines.add("courseCode,courseTitle,credits,instructor");
        for (Course c : courses) {
            lines.add(String.format("%s,%s,%d,%s",
                    c.getCourseCode(), c.getCourseTitle(), c.getCredits(), c.getInstructor()));
        }
        FileHandler.writeLines(FILE, lines);
    }

    /**
     * Loads courses from CSV file.
     */
    private void loadFromFile() {
        List<String> lines = FileHandler.readLines(FILE);
        boolean header = true;
        for (String line : lines) {
            if (header) { header = false; continue; }
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",", -1);
            if (parts.length >= 4) {
                try {
                    courses.add(new Course(
                            parts[0].trim(),
                            parts[1].trim(),
                            Integer.parseInt(parts[2].trim()),
                            parts[3].trim()
                    ));
                } catch (NumberFormatException e) {
                    System.err.println("Skipping malformed course record: " + line);
                }
            }
        }
    }
}
