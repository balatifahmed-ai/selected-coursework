package cs102project;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
/**
 *
 * @author
 */
public class AttendanceRecord {
    private final String studentId;
    private final String studentName;
    private final String classSection;
    private final LocalDate date;
    private AttendanceStatus status;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private String remarks;

    public AttendanceRecord(String studentId, String studentName, String classSection, 
                          LocalDate date, AttendanceStatus status, LocalTime checkInTime, 
                          LocalTime checkOutTime, String remarks) {
        this.studentId = Objects.requireNonNull(studentId, "Student ID cannot be null");
        this.studentName = Objects.requireNonNull(studentName, "Student name cannot be null");
        this.classSection = Objects.requireNonNull(classSection, "Class section cannot be null");
        this.date = Objects.requireNonNull(date, "Date cannot be null");
        this.status = Objects.requireNonNull(status, "Status cannot be null");
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.remarks = remarks;
    }

    public void printRecord() {
        StringBuilder record = new StringBuilder();
        record.append(String.format("%-15s: %s%n", "Student ID", studentId));
        record.append(String.format("%-15s: %s%n", "Name", studentName));
        record.append(String.format("%-15s: %s%n", "Class Section", classSection));
        record.append(String.format("%-15s: %s%n", "Date", date));
        record.append(String.format("%-15s: %s%n", "Status", status));
        
        if (checkInTime != null) {
            record.append(String.format("%-15s: %s%n", "Check-in Time", checkInTime));
        }
        
        if (checkOutTime != null) {
            record.append(String.format("%-15s: %s%n", "Check-out Time", checkOutTime));
        }
        
        if (remarks != null && !remarks.trim().isEmpty()) {
            record.append(String.format("%-15s: %s%n", "Remarks", remarks));
        }
        
        System.out.println(record.toString());
        System.out.println("-".repeat(40));
    }

    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getClassSection() { return classSection; }
    public LocalDate getDate() { return date; }
    public AttendanceStatus getStatus() { return status; }
    public LocalTime getCheckInTime() { return checkInTime; }
    public LocalTime getCheckOutTime() { return checkOutTime; }
    public String getRemarks() { return remarks; }

    public enum AttendanceStatus {
        PRESENT, ABSENT, LATE, EXCUSED
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public void setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return studentId+", ID: " + studentName + ", Section:" + classSection + ", date=" + date + ", status=" + status + ", remarks: " + remarks;
    }
    
    
}
