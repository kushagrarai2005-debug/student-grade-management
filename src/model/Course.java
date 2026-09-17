package model;

import java.io.Serializable;

/**
 * Course entity class representing a course in the system.
 * Each course has a unique code, title, and credit value.
 */
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String courseCode;
    private String courseTitle;
    private int credits;
    private String instructor;

    public Course(String courseCode, String courseTitle, int credits, String instructor) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.credits = credits;
        this.instructor = instructor;
    }

    // Getters
    public String getCourseCode() { return courseCode; }
    public String getCourseTitle() { return courseTitle; }
    public int getCredits() { return credits; }
    public String getInstructor() { return instructor; }

    // Setters
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }
    public void setCredits(int credits) { this.credits = credits; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    @Override
    public String toString() {
        return String.format("[%s] %s (%d credits) - %s",
                courseCode, courseTitle, credits, instructor);
    }
}
