package manager;

import model.Student;
import util.FileHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * StudentManager - handles CRUD operations for students.
 * Functional Module 1: Student Management
 */
public class StudentManager {
    private List<Student> students;
    private static final String FILE = "data/students.csv";

    public StudentManager() {
        students = new ArrayList<>();
        loadFromFile();
    }

    /**
     * Adds a new student to the system.
     */
    public boolean addStudent(Student student) {
        if (findById(student.getStudentId()) != null) {
            System.out.println("Error: Student ID already exists.");
            return false;
        }
        students.add(student);
        saveToFile();
        System.out.println("Student added successfully: " + student.getName());
        return true;
    }

    /**
     * Removes a student by ID.
     */
    public boolean removeStudent(String studentId) {
        Student s = findById(studentId);
        if (s == null) {
            System.out.println("Error: Student not found.");
            return false;
        }
        students.remove(s);
        saveToFile();
        System.out.println("Student removed: " + s.getName());
        return true;
    }

    /**
     * Updates an existing student.
     */
    public boolean updateStudent(String studentId, String name, String email, String program, int semester) {
        Student s = findById(studentId);
        if (s == null) {
            System.out.println("Error: Student not found.");
            return false;
        }
        s.setName(name);
        s.setEmail(email);
        s.setProgram(program);
        s.setSemester(semester);
        saveToFile();
        System.out.println("Student updated: " + s.getName());
        return true;
    }

    /**
     * Finds a student by ID.
     */
    public Student findById(String studentId) {
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(studentId)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Returns all students.
     */
    public List<Student> getAllStudents() {
        return students;
    }

    /**
     * Returns count of students.
     */
    public int getCount() {
        return students.size();
    }

    /**
     * Persists students to CSV file.
     */
    private void saveToFile() {
        List<String> lines = new ArrayList<>();
        lines.add("studentId,name,email,program,semester");
        for (Student s : students) {
            lines.add(String.format("%s,%s,%s,%s,%d",
                    s.getStudentId(), s.getName(), s.getEmail(), s.getProgram(), s.getSemester()));
        }
        FileHandler.writeLines(FILE, lines);
    }

    /**
     * Loads students from CSV file.
     */
    private void loadFromFile() {
        List<String> lines = FileHandler.readLines(FILE);
        boolean header = true;
        for (String line : lines) {
            if (header) { header = false; continue; }
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",", -1);
            if (parts.length >= 5) {
                try {
                    students.add(new Student(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            Integer.parseInt(parts[4].trim())
                    ));
                } catch (NumberFormatException e) {
                    System.err.println("Skipping malformed student record: " + line);
                }
            }
        }
    }
}
