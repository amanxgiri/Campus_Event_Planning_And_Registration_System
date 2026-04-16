package app.service;

import app.model.AttendanceRecord;
import java.util.ArrayList;
import java.util.List;

public class AttendanceService {
    private List<AttendanceRecord> attendanceRecords;

    public AttendanceService() {
        this.attendanceRecords = new ArrayList<>();
    }

    public void markAttendance(int eventId, int participantId, String attendanceStatus) {
        // TODO: implement marking attendance
    }

    public List<AttendanceRecord> getAttendanceByEvent(int eventId) {
        // TODO: filter logically by event
        return new ArrayList<>();
    }

    public List<AttendanceRecord> getAllAttendanceRecords() {
        // TODO: return all
        return this.attendanceRecords;
    }
}
