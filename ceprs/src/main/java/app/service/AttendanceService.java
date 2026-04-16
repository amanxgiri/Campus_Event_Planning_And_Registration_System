package app.service;

import app.model.AttendanceRecord;
import java.util.ArrayList;
import java.util.List;

public class AttendanceService {
    private List<AttendanceRecord> attendanceRecords;
    private int nextAttendanceId;

    public AttendanceService() {
        this.attendanceRecords = new ArrayList<>();
        this.nextAttendanceId = 1;
    }

    public void markAttendance(int eventId, int participantId, String attendanceStatus) {
        if (attendanceStatus == null || attendanceStatus.trim().isEmpty()) {
            return;
        }

        for (AttendanceRecord record : attendanceRecords) {
            if (record.getEventId() == eventId && record.getParticipantId() == participantId) {
                record.setAttendanceStatus(attendanceStatus);
                return;
            }
        }

        AttendanceRecord newRecord = new AttendanceRecord(
                nextAttendanceId++,
                eventId,
                participantId,
                attendanceStatus);
        this.attendanceRecords.add(newRecord);
    }

    public List<AttendanceRecord> getAttendanceByEvent(int eventId) {
        List<AttendanceRecord> filtered = new ArrayList<>();
        for (AttendanceRecord record : attendanceRecords) {
            if (record.getEventId() == eventId) {
                filtered.add(record);
            }
        }
        return filtered;
    }

    public List<AttendanceRecord> getAllAttendanceRecords() {
        return new ArrayList<>(this.attendanceRecords);
    }
}
