package cs102project;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, Student> students = new HashMap<>();
        Scanner input = new Scanner(System.in);

        System.out.println("Would you like to:");
        System.out.println("1 - Load students data from a file");
        System.out.println("2 - Manually add students");
        System.out.print("Enter choice (1 or 2): ");
        int choice = input.nextInt();
        input.nextLine();

        if (choice == 1) {
            System.out.print("Put file location here: ");
            String fileLocation = input.nextLine();
            Scanner scanner = new Scanner(new File(fileLocation));
            Student currentStudent = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (!line.startsWith("ATTENDANCE")) {
                    currentStudent = new Student(parts[0], parts[1], parts[2], parts[3], parts[4], new ArrayList<>(), new ArrayList<>());
                    students.put(parts[0], currentStudent);
                } else {
                    currentStudent.addAttendanceRecord(new AttendanceRecord(
                        currentStudent.getStudentId(),
                        currentStudent.getStudentName(),
                        currentStudent.getClassSection(),
                        LocalDate.parse(parts[1]),
                        AttendanceRecord.AttendanceStatus.valueOf(parts[2].trim().toUpperCase()),
                        null, null, parts[3]
                    ));
                }
            }
            scanner.close();
        } else {
            System.out.print("How many students will you enter? ");
            int count = input.nextInt();
            input.nextLine();

            for (int i = 0; i < count; i++) {
                System.out.println("Enter details for student " + (i + 1) + ":");
                System.out.print("Student ID: ");
                String id = input.nextLine();
                System.out.print("Full Name: ");
                String name = input.nextLine();
                System.out.print("Email: ");
                String email = input.nextLine();
                System.out.print("Phone Number: ");
                String phone = input.nextLine();
                System.out.print("Class Section: ");
                String section = input.nextLine();

                Student student = new Student(id, name, email, phone, section, new ArrayList<>(), new ArrayList<>());

                System.out.print("Attendance status (PRESENT/ABSENT/LATE/EXCUSED): ");
                String status = input.nextLine().trim().toUpperCase();
                System.out.print("Reason or remarks: ");
                String remarks = input.nextLine();

                student.addAttendanceRecord(new AttendanceRecord(id, name, section, LocalDate.now(), AttendanceRecord.AttendanceStatus.valueOf(status), null, null, remarks));
                students.put(id, student);
            }
        }

        List<Student> studentList = new ArrayList<>(students.values());
        Admin admin = new Admin();
        Teacher teacher = new Teacher("T001", "Demo Teacher", "teacher@example.org", "0000000000", new ArrayList<>());

        int total = studentList.size();
        int presentOrLateCount = 0;

        for (Student s : studentList) {
            for (AttendanceRecord r : s.getAttendanceRecords()) {
                AttendanceRecord.AttendanceStatus status = r.getStatus();
                if (status == AttendanceRecord.AttendanceStatus.PRESENT || status == AttendanceRecord.AttendanceStatus.LATE) {
                    presentOrLateCount++;
                    break;
                }
            }
        }

        double percentage = total > 0 ? (presentOrLateCount * 100.0) / total : 0;

        for (Student s : studentList) {
            s.viewAttendance(s, s.getClassSection(), "", "", "");
        }

        admin.viewAnalytics(studentList);

        String exportPath = "new_students.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(exportPath));
        for (Student s : students.values()) {
            writer.write(s.getStudentId() + "," + s.getStudentName() + "," + s.getEmail() + "," + s.getPhoneNumber() + "," + s.getClassSection());
            writer.newLine();
            for (AttendanceRecord r : s.getAttendanceRecords()) {
                writer.write("ATTENDANCE," + r.getDate() + "," + r.getStatus() + "," + r.getRemarks());
                writer.newLine();
            }
        }
        writer.write("Total Students: " + total);
        writer.newLine();
        writer.write("Present Today (including LATE): " + presentOrLateCount);
        writer.newLine();
        writer.write("Attendance Percentage: " + percentage + "%");
        writer.newLine();
        writer.close();
    }
}