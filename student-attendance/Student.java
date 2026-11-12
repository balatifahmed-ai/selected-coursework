package cs102project;
import java.time.LocalDate;
import java.util.*;
/**
 *
 * @author
 */
public class Student {
    private String studentName;
    private String studentId;
    private String email;
    private String phoneNumber;
    private String classSection;
    private List<AttendanceRecord> attendanceRecords;
    private List attendanceCorrection;

    public Student(String studentName, String studentId, String email, String phoneNumber, String classSection, List attendanceRecords, List attendanceCorrection) 
    {
        this.studentName = studentName;
        this.studentId = studentId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.classSection = classSection;
        this.attendanceRecords = attendanceRecords;
        this.attendanceCorrection = attendanceCorrection;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getClassSection() {
        return classSection;
    }

    public void setClassSection(String classSection) {
        this.classSection = classSection;
    }
    
    public List<AttendanceRecord> getAttendanceRecords() {
        return attendanceRecords;
    }
    
    public void setAttendanceRecords(List attendanceRecords) {
        this.attendanceRecords = attendanceRecords;
    }
    
    public List getAttendanceCorrection(List attendanceCorrection) {
        return attendanceCorrection;
    }

    public void setAttendanceCorrection(List attendanceCorrection) {
        this.attendanceCorrection = attendanceCorrection;
    }

    @Override
    public String toString() {
        return "Student{" + "studentName=" + studentName + ", studentId=" + studentId + ", email=" + email + ", phoneNumber=" + phoneNumber + ", classSection=" + classSection + ", attendanceRecords=" + attendanceRecords + ", attendanceCorrection=" + attendanceCorrection + '}';
    }

    
    public void addAttendanceRecord(AttendanceRecord record)
    {
        attendanceRecords.add(record);
    }
    
    public void viewAttendance(Student student, String classSection, String date, String status, String remarks)
    {
        System.out.println("The attendance record for "+this.studentName+" "+this.studentId+":");
        
        if(attendanceRecords.isEmpty())
        {
            System.out.println("No records available");
            return;
        }
        
        for(AttendanceRecord record : student.getAttendanceRecords())
        {
            System.out.println(record);
        }
}
        public void requestCorrection(LocalDate date, AttendanceRecord status, String reason)
        {
            boolean recordFound = false;
            
            for(AttendanceRecord record : attendanceRecords)
            {
                if(record.getDate().equals(date))
                {
                    recordFound = true;
                }
            }
            
            if(recordFound = false)
            {
                System.out.println("No record found for the date: "+date);
            }
            
            attendanceCorrection.add("Correction requested on " + date + " for reason: " + reason);
            
        }
}
