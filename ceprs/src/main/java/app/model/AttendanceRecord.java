package app.model;

public class AttendanceRecord {
    private int attendanceId;
    private int eventId;
    private int participantId;
    private String attendanceStatus;

    public AttendanceRecord() {
    }

    public AttendanceRecord(int attendanceId, int eventId, int participantId, String attendanceStatus) {
        this.attendanceId = attendanceId;
        this.eventId = eventId;
        this.participantId = participantId;
        this.attendanceStatus = attendanceStatus;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    @Override
    public String toString() {
        return "AttendanceRecord{" +
                "attendanceId=" + attendanceId +
                ", eventId=" + eventId +
                ", participantId=" + participantId +
                ", attendanceStatus='" + attendanceStatus + '\'' +
                '}';
    }
}
