package cs102project;
import java.util.*;
/**
 *
 * @author
 */
public class Admin {
     public void viewAnalytics(List<Student> allRecords) {
    int total = allRecords.size();
    int present = 0;

    for (Student s : allRecords) {
        for (AttendanceRecord record : s.getAttendanceRecords()) {
            if (record.getStatus() == AttendanceRecord.AttendanceStatus.PRESENT) {
                present++;
                break;
            }
        }
    }

    double percentage;
    if (total > 0) {
        percentage = (present * 100.0) / total;
    } else {
        percentage = 0;
    }

    System.out.println("Total students: " + total);
    System.out.println("Present today: " + present);
    System.out.println("Attendance percentage: " + percentage + "%");
}


    public void handleCorrections(Map<String, String> requests) {
        Set<String> ids = requests.keySet();
        for (String id : ids) {
            System.out.println("Request ID: " + id);
            System.out.println("Reason given: " + requests.get(id));
            System.out.println("---------------------");
        }
    }

    public void addStudent(Map<String, Student> students, Scanner scanner) {
    System.out.print("Student ID: ");
    String id = scanner.nextLine();

    System.out.print("Full name: ");
    String name = scanner.nextLine();

    System.out.print("Email: ");
    String email = scanner.nextLine();

    System.out.print("Phone number: ");
    String phone = scanner.nextLine();

    System.out.print("Class section: ");
    String classSection = scanner.nextLine();

    Student newStudent = new Student(id, name, email, phone, classSection, new ArrayList<>(), new ArrayList<>());
    students.put(id, newStudent);

    System.out.println("Student added.");
    }

}
