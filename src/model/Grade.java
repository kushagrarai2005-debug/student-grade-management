package model;

import java.io.Serializable;

/**
 * Grade entity class representing a grade assigned to a student for a course.
 * Includes marks obtained, letter grade, and grade point.
 */
public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentId;
    private String courseCode;
    private double marks;          // marks out of 100
    private String letterGrade;    // A+, A, B, C, D, F
    private double gradePoint;     // on 10-point scale

    public Grade(String studentId, String courseCode, double marks) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        setMarks(marks);
    }

    /**
     * Sets marks and auto-calculates letter grade and grade point.
     */
    public void setMarks(double marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100");
        }
        this.marks = marks;
        calculateGrade();
    }

    /**
     * Calculates letter grade and grade point based on marks.
     */
    private void calculateGrade() {
        if (marks >= 90) { letterGrade = "A+"; gradePoint = 10.0; }
        else if (marks >= 80) { letterGrade = "A";  gradePoint = 9.0; }
        else if (marks >= 70) { letterGrade = "B+"; gradePoint = 8.0; }
        else if (marks >= 60) { letterGrade = "B";  gradePoint = 7.0; }
        else if (marks >= 50) { letterGrade = "C";  gradePoint = 6.0; }
        else if (marks >= 40) { letterGrade = "D";  gradePoint = 5.0; }
        else                  { letterGrade = "F";  gradePoint = 0.0; }
    }

    // Getters
    public String getStudentId() { return studentId; }
    public String getCourseCode() { return courseCode; }
    public double getMarks() { return marks; }
    public String getLetterGrade() { return letterGrade; }
    public double getGradePoint() { return gradePoint; }

    @Override
    public String toString() {
        return String.format("Student: %s | Course: %s | Marks: %.2f | Grade: %s (%.1f GP)",
                studentId, courseCode, marks, letterGrade, gradePoint);
    }
}
