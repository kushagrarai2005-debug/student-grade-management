# Student Grade Management System

A console-based Java application for managing students, courses, and grades. Built as part of the **Build Your Own Project** evaluation.

**Author:** Kushagra Rai
**Registration No:** 23BCE11575
**Course:** Object Oriented Programming (Java)

---

## Overview

The Student Grade Management System (SGMS) is a Java application that allows educational administrators to manage students, courses, and academic grades. It demonstrates core Object-Oriented Programming concepts such as encapsulation, modular design, and separation of concerns.

The system uses CSV files for persistence, so no external database is required. All data is automatically loaded on startup and saved on every modification.

---

## Features

### Functional Modules

1. **Student Management**
   - Add, view, update, delete, and find students
   - Stores student ID, name, email, program, and semester

2. **Course Management**
   - Add, view, delete, and find courses
   - Stores course code, title, credits, and instructor

3. **Grade Management**
   - Record grades for any student in any course
   - Automatic conversion of marks to letter grades and grade points
   - Weighted CGPA calculation

4. **Reporting & Analytics**
   - Generate student transcripts with CGPA
   - Generate course statistics (avg, max, min marks)
   - System-wide summary reports

### Non-Functional Features

- **Input validation** — all numeric fields are validated
- **Error handling** — duplicate detection, missing records, invalid input
- **Modular architecture** — clear package separation (`model`, `manager`, `util`)
- **File-based persistence** — data survives application restarts
- **Clean console UI** — menu-driven interaction with formatted output

---

## Technologies / Tools Used

| Layer | Tool |
|-------|------|
| Language | Java (JDK 8+) |
| Persistence | CSV files via `java.nio` |
| Build | None required — direct `javac` / `java` |
| Testing | Custom validation test suite |
| Version Control | Git |

---

## Project Structure

```
student-grade-management/
├── src/
│   ├── model/
│   │   ├── Student.java        # Student entity
│   │   ├── Course.java         # Course entity
│   │   └── Grade.java          # Grade entity + auto letter grade
│   ├── manager/
│   │   ├── StudentManager.java # CRUD for students
│   │   ├── CourseManager.java  # CRUD for courses
│   │   └── GradeManager.java   # Grade ops + CGPA
│   ├── util/
│   │   ├── FileHandler.java    # CSV read/write helpers
│   │   └── ReportGenerator.java # Reports & analytics
│   └── Main.java               # Entry point + menu UI
├── data/
│   ├── students.csv            # Seed student records
│   ├── courses.csv             # Seed course records
│   └── grades.csv              # Seed grade records
├── test/
│   └── ValidationTest.java     # Self-contained validation tests
├── docs/
│   ├── diagrams/               # UML + architecture diagrams
│   └── report/                 # Final project report PDF
├── README.md
├── statement.md
└── .gitignore
```

---

## Steps to Install & Run

### Prerequisites
- Java JDK 8 or higher
- A terminal / command prompt

### Compilation
```bash
cd student-grade-management
mkdir -p out
javac -d out src/model/*.java src/manager/*.java src/util/*.java src/Main.java src/test/*.java
```

### Run the Main Application
```bash
java -cp out Main
```

### Run the Tests
```bash
java -cp out test.ValidationTest
```

---

## How to Use

When you start the application you will see a **Main Menu** with four options:

```
1. Student Management
2. Course Management
3. Grade Management
4. Reports & Analytics
0. Exit
```

Each submenu lets you add, view, update, or delete records. Data is automatically saved to the `data/` folder on every change. Pre-seeded sample data is included so you can explore the system right away.

### Sample Walkthrough

1. From the main menu, press **4** for Reports & Analytics.
2. Choose **1** for Student Transcript.
3. Enter student ID `23BCE11575` to see Kushagra Rai's grades and CGPA.

Expected CGPA for the pre-seeded data: **9.07 / 10.0**

---

## Instructions for Testing

A self-contained test class is included in `test/ValidationTest.java`. It validates:

- Student / Course entity creation
- Grade letter and grade-point mapping across all bands
- Bounds checking on marks (rejects values < 0 and > 100)
- Weighted CGPA formula correctness

Run it with:
```bash
java -cp out test.ValidationTest
```

A summary line is printed at the end:
```
Passed: X
Failed: Y
Total:  Z
```

---

## Screenshots (Console Output Example)

```
=========================================
  STUDENT GRADE MANAGEMENT SYSTEM
  Author: Kushagra Rai (23BCE11575)
=========================================

----- MAIN MENU -----
1. Student Management
2. Course Management
3. Grade Management
4. Reports & Analytics
0. Exit
Enter choice:
```

---

## Git

Initialise a local repo and push to GitHub:
```bash
cd student-grade-management
git init
git add .
git commit -m "Initial commit - Student Grade Management System"
git branch -M main
git remote add origin https://github.com/<your-username>/<repo-name>.git
git push -u origin main
```
