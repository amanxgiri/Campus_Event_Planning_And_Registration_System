package app.model;

import java.time.LocalDate;

public class Registration {
    private int registrationId;
    private int eventId;
    private int participantId;
    private String registrationStatus;
    private LocalDate registrationDate;

    public Registration() {
    }

    public Registration(int registrationId, int eventId, int participantId, String registrationStatus,
            LocalDate registrationDate) {
        this.registrationId = registrationId;
        this.eventId = eventId;
        this.participantId = participantId;
        this.registrationStatus = registrationStatus;
        this.registrationDate = registrationDate;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
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

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "registrationId=" + registrationId +
                ", eventId=" + eventId +
                ", participantId=" + participantId +
                ", registrationStatus='" + registrationStatus + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
