package app.service;

import app.model.Registration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrationService {
    private List<Registration> registrations;
    private int nextRegistrationId;

    public RegistrationService() {
        this.registrations = new ArrayList<>();
        this.nextRegistrationId = 1;
    }

    public void registerParticipant(int eventId, int participantId) {
        Registration registration = new Registration(
                nextRegistrationId++,
                eventId,
                participantId,
                "CONFIRMED",
                LocalDate.now());
        this.registrations.add(registration);
    }

    public void cancelRegistration(int registrationId) {
        for (Registration registration : registrations) {
            if (registration.getRegistrationId() == registrationId) {
                registration.setRegistrationStatus("CANCELLED");
                return;
            }
        }
    }

    public List<Registration> getAllRegistrations() {
        return new ArrayList<>(this.registrations);
    }

    public List<Registration> getRegistrationsByEvent(int eventId) {
        List<Registration> filtered = new ArrayList<>();
        for (Registration registration : registrations) {
            if (registration.getEventId() == eventId) {
                filtered.add(registration);
            }
        }
        return filtered;
    }

    public List<Registration> getRegistrationsByParticipant(int participantId) {
        List<Registration> filtered = new ArrayList<>();
        for (Registration registration : registrations) {
            if (registration.getParticipantId() == participantId) {
                filtered.add(registration);
            }
        }
        return filtered;
    }
}
