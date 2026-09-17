package util;

import manager.CourseManager;
import manager.GradeManager;
import manager.StudentManager;
import model.Course;
import model.Grade;
import model.Student;

import java.util.List;

/**
 * ReportGenerator - generates reports and analytics.
 * Functional Module 4: Reporting & Analytics
 */
public class ReportGenerator {

    /**
     * Generates a transcript for a student showing all grades and CGPA.
     */
    public void generateTranscript(String studentId, StudentManager sm,
                                   CourseManager cm, GradeManager gm) {
        Student student = sm.findById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("\n========== ACADEMIC TRANSCRIPT ==========");
        System.out.println("Student: " + student.getName());
        System.out.println("ID:      " + student.getStudentId());
        System.out.println("Program: " + student.getProgram() + " (Sem " + student.getSemester() + ")");
        System.out.println("-----------------------------------------");

        List<Grade> grades = gm.getGradesForStudent(studentId);
        if (grades.isEmpty()) {
            System.out.println("No grades recorded yet.");
        } else {
            System.out.printf("%-10s %-30s %-8s %-8s%n",
                    "Code", "Course", "Marks", "Grade");
            System.out.println("-----------------------------------------");
            int totalCredits = 0;
            for (Grade g : grades) {
                Course c = cm.findByCode(g.getCourseCode());
                String title = c != null ? c.getCourseTitle() : "Unknown";
                System.out.printf("%-10s %-30s %-8.2f %-8s%n",
                        g.getCourseCode(), truncate(title, 28),
                        g.getMarks(), g.getLetterGrade());
                if (c != null) totalCredits += c.getCredits();
            }
            System.out.println("-----------------------------------------");
            System.out.println("Total Credits: " + totalCredits);
            System.out.printf("CGPA: %.2f / 10.0%n", gm.calculateCGPA(studentId, cm));
        }
        System.out.println("=========================================\n");
    }

    /**
     * Generates a course statistics report (avg, highest, lowest, count).
     */
    public void generateCourseStats(String courseCode, CourseManager cm, GradeManager gm) {
        Course course = cm.findByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        List<Grade> grades = gm.getGradesForCourse(courseCode);
        if (grades.isEmpty()) {
            System.out.println("No grades recorded for this course.");
            return;
        }

        double sum = 0, max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        for (Grade g : grades) {
            sum += g.getMarks();
            if (g.getMarks() > max) max = g.getMarks();
            if (g.getMarks() < min) min = g.getMarks();
        }

        System.out.println("\n========== COURSE STATISTICS ==========");
        System.out.println("Course: " + course.getCourseTitle() + " (" + course.getCourseCode() + ")");
        System.out.println("Instructor: " + course.getInstructor());
        System.out.println("Credits: " + course.getCredits());
        System.out.println("---------------------------------------");
        System.out.println("Students Enrolled: " + grades.size());
        System.out.printf("Average Marks:     %.2f%n", sum / grades.size());
        System.out.printf("Highest Marks:     %.2f%n", max);
        System.out.printf("Lowest Marks:      %.2f%n", min);
        System.out.println("=======================================\n");
    }

    /**
     * Generates an overall summary of the system.
     */
    public void generateSystemSummary(StudentManager sm, CourseManager cm, GradeManager gm) {
        System.out.println("\n========== SYSTEM SUMMARY ==========");
        System.out.println("Total Students: " + sm.getCount());
        System.out.println("Total Courses:  " + cm.getCount());
        System.out.println("Total Grades:   " + gm.getCount());
        System.out.println("===================================\n");
    }

    /**
     * Truncates string to a max length.
     */
    private String truncate(String s, int max) {
        return s.length() > max ? s.substring(0, max - 1) + "…" : s;
    }
}
