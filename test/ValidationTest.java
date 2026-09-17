package test;

import model.Course;
import model.Grade;
import model.Student;

/**
 * ValidationTest - basic unit tests to verify core entity behaviour.
 * Run by adding this file to the src/test directory and executing
 * with a JUnit-style main method.
 */
public class ValidationTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testStudentCreation();
        testCourseCreation();
        testGradeCalculation();
        testInvalidMarks();
        testCGPAWeightedCalculation();
        printResults();
    }

    static void testStudentCreation() {
        try {
            Student s = new Student("23BCE11575", "Kushagra Rai",
                    "kushagra.rai@vitstudent.ac.in", "B.Tech CSE", 3);
            assertEq("23BCE11575", s.getStudentId(), "Student ID matches");
            assertEq("Kushagra Rai", s.getName(), "Student name matches");
            assertEq(3, s.getSemester(), "Student semester matches");
        } catch (Exception e) {
            fail("testStudentCreation: " + e.getMessage());
        }
    }

    static void testCourseCreation() {
        try {
            Course c = new Course("CSE2001", "Data Structures", 4, "Dr. Priya");
            assertEq("CSE2001", c.getCourseCode(), "Course code matches");
            assertEq(4, c.getCredits(), "Course credits matches");
        } catch (Exception e) {
            fail("testCourseCreation: " + e.getMessage());
        }
    }

    static void testGradeCalculation() {
        Grade g = new Grade("23BCE11575", "CSE2001", 92.5);
        assertEq("A+", g.getLetterGrade(), "Grade A+ for 92.5 marks");
        assertEq(10.0, g.getGradePoint(), "GradePoint 10.0 for 92.5 marks");

        Grade g2 = new Grade("23BCE11575", "CSE2003", 65.0);
        assertEq("B", g2.getLetterGrade(), "Grade B for 65 marks");
        assertEq(7.0, g2.getGradePoint(), "GradePoint 7.0 for 65 marks");

        Grade g3 = new Grade("23BCE11575", "CSE2005", 35.0);
        assertEq("F", g3.getLetterGrade(), "Grade F for 35 marks");
        assertEq(0.0, g3.getGradePoint(), "GradePoint 0.0 for 35 marks");
    }

    static void testInvalidMarks() {
        try {
            new Grade("23BCE11575", "CSE2001", 150.0);
            fail("Should throw IllegalArgumentException for marks > 100");
        } catch (IllegalArgumentException e) {
            pass("Correctly rejects marks > 100");
        }
        try {
            new Grade("23BCE11575", "CSE2001", -5.0);
            fail("Should throw IllegalArgumentException for marks < 0");
        } catch (IllegalArgumentException e) {
            pass("Correctly rejects marks < 0");
        }
    }

    static void testCGPAWeightedCalculation() {
        // Manually compute weighted CGPA: (10*4 + 9*3 + 8*3 + 9*4) / (4+3+3+4)
        // = (40 + 27 + 24 + 36) / 14 = 127 / 14 = 9.0714...
        double totalWeightedPoints = 10.0 * 4 + 9.0 * 3 + 8.0 * 3 + 9.0 * 4;
        int totalCredits = 4 + 3 + 3 + 4;
        double expected = totalWeightedPoints / totalCredits;
        // Allow rounding
        assertApprox(expected, 9.07, 0.01, "Weighted CGPA calculation");
    }

    /* ============= helpers ============= */
    static void assertEq(Object expected, Object actual, String msg) {
        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) {
            pass(msg);
        } else {
            fail(msg + " (expected=" + expected + ", actual=" + actual + ")");
        }
    }

    static void assertApprox(double expected, double actual, double tolerance, String msg) {
        if (Math.abs(expected - actual) <= tolerance) {
            pass(msg);
        } else {
            fail(msg + " (expected≈" + expected + ", actual=" + actual + ")");
        }
    }

    static void pass(String msg) { passed++; System.out.println("  [PASS] " + msg); }
    static void fail(String msg) { failed++; System.out.println("  [FAIL] " + msg); }

    static void printResults() {
        System.out.println("\n========== TEST RESULTS ==========");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total:  " + (passed + failed));
        System.out.println("==================================");
        if (failed > 0) System.exit(1);
    }
}
