package manager;

import model.Course;
import model.Grade;
import model.Student;
import util.FileHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * GradeManager - handles grade recording and GPA calculation.
 * Functional Module 3: Grade Management
 */
public class GradeManager {
    private List<Grade> grades;
    private static final String FILE = "data/grades.csv";

    public GradeManager() {
        grades = new ArrayList<>();
        loadFromFile();
    }

    /**
     * Records a new grade for a student in a course.
     */
    public boolean recordGrade(String studentId, String courseCode, double marks,
                               StudentManager sm, CourseManager cm) {
        // Validate student and course exist
        Student student = sm.findById(studentId);
        Course course = cm.findByCode(courseCode);

        if (student == null) {
            System.out.println("Error: Student not found.");
            return false;
        }
        if (course == null) {
            System.out.println("Error: Course not found.");
            return false;
        }

        // Remove existing grade for same student-course combo
        grades.removeIf(g -> g.getStudentId().equalsIgnoreCase(studentId)
                && g.getCourseCode().equalsIgnoreCase(courseCode));

        try {
            Grade grade = new Grade(studentId, courseCode, marks);
            grades.add(grade);
            saveToFile();
            System.out.printf("Recorded: %s in %s -> %s (%.1f GP)%n",
                    student.getName(), course.getCourseTitle(),
                    grade.getLetterGrade(), grade.getGradePoint());
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Returns all grades for a student.
     */
    public List<Grade> getGradesForStudent(String studentId) {
        List<Grade> result = new ArrayList<>();
        for (Grade g : grades) {
            if (g.getStudentId().equalsIgnoreCase(studentId)) {
                result.add(g);
            }
        }
        return result;
    }

    /**
     * Returns all grades for a course.
     */
    public List<Grade> getGradesForCourse(String courseCode) {
        List<Grade> result = new ArrayList<>();
        for (Grade g : grades) {
            if (g.getCourseCode().equalsIgnoreCase(courseCode)) {
                result.add(g);
            }
        }
        return result;
    }

    /**
     * Calculates CGPA (weighted by credits) for a student.
     */
    public double calculateCGPA(String studentId, CourseManager cm) {
        List<Grade> studentGrades = getGradesForStudent(studentId);
        if (studentGrades.isEmpty()) return 0.0;

        double totalWeightedPoints = 0;
        int totalCredits = 0;

        for (Grade g : studentGrades) {
            Course c = cm.findByCode(g.getCourseCode());
            if (c != null) {
                totalWeightedPoints += g.getGradePoint() * c.getCredits();
                totalCredits += c.getCredits();
            }
        }

        return totalCredits == 0 ? 0.0 : totalWeightedPoints / totalCredits;
    }

    /**
     * Returns count of all grade records.
     */
    public int getCount() {
        return grades.size();
    }

    /**
     * Persists grades to CSV file.
     */
    private void saveToFile() {
        List<String> lines = new ArrayList<>();
        lines.add("studentId,courseCode,marks,letterGrade,gradePoint");
        for (Grade g : grades) {
            lines.add(String.format("%s,%s,%.2f,%s,%.1f",
                    g.getStudentId(), g.getCourseCode(), g.getMarks(),
                    g.getLetterGrade(), g.getGradePoint()));
        }
        FileHandler.writeLines(FILE, lines);
    }

    /**
     * Loads grades from CSV file.
     */
    private void loadFromFile() {
        List<String> lines = FileHandler.readLines(FILE);
        boolean header = true;
        for (String line : lines) {
            if (header) { header = false; continue; }
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",", -1);
            if (parts.length >= 3) {
                try {
                    Grade g = new Grade(parts[0].trim(), parts[1].trim(),
                            Double.parseDouble(parts[2].trim()));
                    grades.add(g);
                } catch (Exception e) {
                    System.err.println("Skipping malformed grade record: " + line);
                }
            }
        }
    }
}
