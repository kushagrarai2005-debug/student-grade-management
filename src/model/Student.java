package model;

import java.io.Serializable;

/**
 * Student entity class representing a student in the system.
 * Stores basic student information and a list of registered courses.
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentId;
    private String name;
    private String email;
    private String program;
    private int semester;

    public Student(String studentId, String name, String email, String program, int semester) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.program = program;
        this.semester = semester;
    }

    // Getters
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getProgram() { return program; }
    public int getSemester() { return semester; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setProgram(String program) { this.program = program; }
    public void setSemester(int semester) { this.semester = semester; }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | Sem %d | %s",
                studentId, name, program, semester, email);
    }
}
