import manager.CourseManager;
import manager.GradeManager;
import manager.StudentManager;
import model.Course;
import model.Student;
import util.ReportGenerator;

import java.util.Scanner;

/**
 * Main - Entry point for the Student Grade Management System.
 * Provides a menu-driven console interface for the user.
 *
 * Project: Student Grade Management System
 * Author: Kushagra Rai (23BCE11575)
 */
public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentManager studentManager = new StudentManager();
    private static final CourseManager courseManager = new CourseManager();
    private static final GradeManager gradeManager = new GradeManager();
    private static final ReportGenerator reportGenerator = new ReportGenerator();

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("  STUDENT GRADE MANAGEMENT SYSTEM");
        System.out.println("  Author: Kushagra Rai (23BCE11575)");
        System.out.println("=========================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": studentMenu(); break;
                case "2": courseMenu(); break;
                case "3": gradeMenu(); break;
                case "4": reportsMenu(); break;
                case "0":
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }

    private static void printMainMenu() {
        System.out.println("\n----- MAIN MENU -----");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Grade Management");
        System.out.println("4. Reports & Analytics");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    /* ============== STUDENT MENU ============== */
    private static void studentMenu() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Find Student by ID");
        System.out.print("Choice: ");
        String ch = sc.nextLine().trim();

        switch (ch) {
            case "1": addStudent(); break;
            case "2": viewAllStudents(); break;
            case "3": updateStudent(); break;
            case "4": deleteStudent(); break;
            case "5": findStudent(); break;
            default: System.out.println("Invalid choice.");
        }
    }

    private static void addStudent() {
        try {
            System.out.print("Student ID: ");
            String id = sc.nextLine().trim();
            System.out.print("Name: ");
            String name = sc.nextLine().trim();
            System.out.print("Email: ");
            String email = sc.nextLine().trim();
            System.out.print("Program: ");
            String program = sc.nextLine().trim();
            System.out.print("Semester: ");
            int sem = Integer.parseInt(sc.nextLine().trim());

            if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
                System.out.println("Error: Required fields cannot be empty.");
                return;
            }
            studentManager.addStudent(new Student(id, name, email, program, sem));
        } catch (NumberFormatException e) {
            System.out.println("Error: Semester must be a number.");
        }
    }

    private static void viewAllStudents() {
        var students = studentManager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\n--- All Students ---");
            for (var s : students) System.out.println(s);
        }
    }

    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        String id = sc.nextLine().trim();
        if (studentManager.findById(id) == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("New Name: ");
        String name = sc.nextLine().trim();
        System.out.print("New Email: ");
        String email = sc.nextLine().trim();
        System.out.print("New Program: ");
        String program = sc.nextLine().trim();
        System.out.print("New Semester: ");
        int sem = Integer.parseInt(sc.nextLine().trim());
        studentManager.updateStudent(id, name, email, program, sem);
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = sc.nextLine().trim();
        studentManager.removeStudent(id);
    }

    private static void findStudent() {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine().trim();
        var s = studentManager.findById(id);
        System.out.println(s != null ? s : "Student not found.");
    }

    /* ============== COURSE MENU ============== */
    private static void courseMenu() {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Delete Course");
        System.out.println("4. Find Course by Code");
        System.out.print("Choice: ");
        String ch = sc.nextLine().trim();

        switch (ch) {
            case "1": addCourse(); break;
            case "2": viewAllCourses(); break;
            case "3": deleteCourse(); break;
            case "4": findCourse(); break;
            default: System.out.println("Invalid choice.");
        }
    }

    private static void addCourse() {
        try {
            System.out.print("Course Code: ");
            String code = sc.nextLine().trim();
            System.out.print("Course Title: ");
            String title = sc.nextLine().trim();
            System.out.print("Credits: ");
            int credits = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Instructor: ");
            String instructor = sc.nextLine().trim();
            courseManager.addCourse(new Course(code, title, credits, instructor));
        } catch (NumberFormatException e) {
            System.out.println("Error: Credits must be a number.");
        }
    }

    private static void viewAllCourses() {
        var courses = courseManager.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            System.out.println("\n--- All Courses ---");
            for (var c : courses) System.out.println(c);
        }
    }

    private static void deleteCourse() {
        System.out.print("Enter Course Code to delete: ");
        String code = sc.nextLine().trim();
        courseManager.removeCourse(code);
    }

    private static void findCourse() {
        System.out.print("Enter Course Code: ");
        String code = sc.nextLine().trim();
        var c = courseManager.findByCode(code);
        System.out.println(c != null ? c : "Course not found.");
    }

    /* ============== GRADE MENU ============== */
    private static void gradeMenu() {
        System.out.println("\n--- Grade Management ---");
        System.out.println("1. Record Grade");
        System.out.println("2. View Student Grades");
        System.out.print("Choice: ");
        String ch = sc.nextLine().trim();

        switch (ch) {
            case "1": recordGrade(); break;
            case "2": viewStudentGrades(); break;
            default: System.out.println("Invalid choice.");
        }
    }

    private static void recordGrade() {
        try {
            System.out.print("Student ID: ");
            String sid = sc.nextLine().trim();
            System.out.print("Course Code: ");
            String cid = sc.nextLine().trim();
            System.out.print("Marks (0-100): ");
            double marks = Double.parseDouble(sc.nextLine().trim());
            gradeManager.recordGrade(sid, cid, marks, studentManager, courseManager);
        } catch (NumberFormatException e) {
            System.out.println("Error: Marks must be a number.");
        }
    }

    private static void viewStudentGrades() {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine().trim();
        var grades = gradeManager.getGradesForStudent(id);
        if (grades.isEmpty()) {
            System.out.println("No grades found.");
        } else {
            System.out.println("\n--- Grades for " + id + " ---");
            for (var g : grades) System.out.println(g);
            System.out.printf("CGPA: %.2f%n", gradeManager.calculateCGPA(id, courseManager));
        }
    }

    /* ============== REPORTS MENU ============== */
    private static void reportsMenu() {
        System.out.println("\n--- Reports & Analytics ---");
        System.out.println("1. Student Transcript");
        System.out.println("2. Course Statistics");
        System.out.println("3. System Summary");
        System.out.print("Choice: ");
        String ch = sc.nextLine().trim();

        switch (ch) {
            case "1":
                System.out.print("Student ID: ");
                reportGenerator.generateTranscript(sc.nextLine().trim(),
                        studentManager, courseManager, gradeManager);
                break;
            case "2":
                System.out.print("Course Code: ");
                reportGenerator.generateCourseStats(sc.nextLine().trim(),
                        courseManager, gradeManager);
                break;
            case "3":
                reportGenerator.generateSystemSummary(studentManager, courseManager, gradeManager);
                break;
            default: System.out.println("Invalid choice.");
        }
    }
}
