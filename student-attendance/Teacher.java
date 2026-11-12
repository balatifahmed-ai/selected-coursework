package cs102project;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author
 */
public class Teacher {
    private String teacherId;
    private String name;
    private String email;
    private String phoneNumber;
    private List assignedClasses;

    public Teacher(String teacherID, String name, String email, String phoneNumber, List assignedClasses) {
        this.teacherId = teacherID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.assignedClasses = assignedClasses;
    }

    public String getTeacherID() {
        return teacherId;
    }

    public void setTeacherID(String teacherID) {
        this.teacherId = teacherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List getAssignedClasses() {
        return assignedClasses;
    }

    public void setAssignedClasses(List assignedClasses) {
        this.assignedClasses = assignedClasses;
    }

    @Override
    public String toString() {
        return "teacherId=" + teacherId + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", assignedClasses=" + assignedClasses + '}';
    }
    
    
    
    public void markAttendance(Student student, String status, String remarks) {
        AttendanceRecord record = new AttendanceRecord(student.getStudentId(), student.getStudentName(), student.getClassSection(), LocalDate.now(), AttendanceRecord.AttendanceStatus.valueOf(status.toUpperCase()), null, null, remarks);
        student.addAttendanceRecord(record);
    }
    
    public List<AttendanceRecord> viewClassAttendance(List<Student> students, String classId) {
        List<AttendanceRecord> classRecords = new ArrayList<>();
        for (Student student : students) {
            if (student.getClassSection().equals(classId))
                classRecords.addAll(student.getAttendanceRecords());
        }
        return classRecords;
    }
    
     public void modifyAttendanceRecord(Student student, Date date, AttendanceRecord.AttendanceStatus newStatus, String newRemarks) {
            for (AttendanceRecord record : student.getAttendanceRecords()) {
                if (record.getDate().equals(date)) {
                    record.setStatus(newStatus);
                    record.setRemarks(newRemarks);
             }
         }
     }
    
    public String generateAttendanceReports(List<Student> students, String classId, LocalDate startDate, LocalDate endDate) {
        StringBuilder report = new StringBuilder();
        report.append("Attendance Report for Class: ").append(classId).append("\n");
        for (Student student : students) {
            if (student.getClassSection().equals(classId)) {
                report.append("Student: ").append(student.getStudentName()).append("\n");
                for (AttendanceRecord record : student.getAttendanceRecords()) {
                    if (!record.getDate().isBefore(startDate) && !record.getDate().isAfter(endDate)) {
                        report.append(record.toString()).append("\n");
                    }
                }
            }
        }
        return report.toString();
    }
    
    public void sendAttendanceAlert(Student student, String message) {
        System.out.println("Alert, student: "+student.getStudentName()+" with ID: "+student.getStudentId()+". "+message);
    }
}
