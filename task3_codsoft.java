import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;
    private String dataFilePath;

    public StudentManagementSystem(String dataFilePath) {
        this.students = new ArrayList<>();
        this.dataFilePath = dataFilePath;
        loadStudentsFromFile();
    }

    public void addStudent(String name, int rollNumber, String grade) {
        Student student = new Student(name, rollNumber, grade);
        students.add(student);
        saveStudentsToFile();
    }

    public void removeStudent(int rollNumber) {
        for (Iterator<Student> iterator = students.iterator(); iterator.hasNext(); ) {
            Student student = iterator.next();
            if (student.getRollNumber() == rollNumber) {
                iterator.remove();
                saveStudentsToFile();
                return;
            }
        }
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    private void loadStudentsFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(dataFilePath))) {
            students = (List<Student>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            // Ignore if the file doesn't exist initially
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveStudentsToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            outputStream.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class task3_codsoft {
    private static StudentManagementSystem studentManagementSystem;
    private static Scanner scanner;

    public static void main(String[] args) {
        studentManagementSystem = new StudentManagementSystem("students.dat");
        scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("----- Student Management System -----");
            System.out.println("1. Add a student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();

        studentManagementSystem.addStudent(name, rollNumber, grade);
        System.out.println("Student added successfully!");
        System.out.println();
    }

    private static void removeStudent() {
        System.out.print("Enter roll number of the student to remove: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        studentManagementSystem.removeStudent(rollNumber);
        System.out.println("Student removed successfully!");
        System.out.println();
    }

    private static void searchStudent() {
        System.out.print("Enter roll number of the student to search: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student student = studentManagementSystem.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student found:");
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
        System.out.println();
    }

    private static void displayAllStudents() {
        System.out.println("All students:");
        List<Student> students = studentManagementSystem.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
        System.out.println();
    }
}
