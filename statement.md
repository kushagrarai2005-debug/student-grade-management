# Project Statement

## Problem Statement

In many small-scale educational settings, student academic records are still maintained in spreadsheets or on paper. This leads to:

- **Data inconsistency** when multiple staff members edit the same record
- **Slow lookup** of student transcripts and grades
- **Manual and error-prone** CGPA calculation
- **No centralised reporting** for course performance statistics

There is a need for a simple, self-contained, console-based system that can manage students, courses, and grades reliably without requiring a database or web framework.

The **Student Grade Management System (SGMS)** addresses these problems by providing a modular Java application that automates record keeping, calculates weighted CGPA, and produces standardised reports.

---

## Scope of the Project

The scope of SGMS covers:

1. **Student records** — managing basic demographic and academic information.
2. **Course catalog** — maintaining a list of courses with credit weights.
3. **Grade recording** — capturing marks per student per course and converting to letter grades.
4. **Report generation** — producing student transcripts and course statistics.

The system **does not** cover:

- Web or mobile access (console-only by design, to keep dependencies minimal)
- Multi-user concurrency (single-user CLI)
- Authentication / role management
- Database integration (file-based CSV persistence is sufficient for the assignment)

---

## Target Users

| User | Use Case |
|------|----------|
| **Course Instructors** | Record marks for students in their courses |
| **Academic Administrators** | Add/update student records, manage the course catalog |
| **Students** | View their own transcript and CGPA |

---

## High-Level Features

- **Three major functional modules** (Student, Course, Grade management) plus a fourth Reporting module
- **CRUD operations** for both students and courses
- **Automatic letter-grade conversion** based on marks
- **Credit-weighted CGPA** calculation
- **CSV-based persistence** — no external database required
- **Console-based menu UI** — intuitive and easy to test
- **Validation tests** for the grading logic
- **Robust error handling** for invalid input and missing records
