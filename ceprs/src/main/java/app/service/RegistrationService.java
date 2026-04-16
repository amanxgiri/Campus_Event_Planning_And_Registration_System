package app.service;

import app.model.Registration;
import java.util.ArrayList;
import java.util.List;

public class RegistrationService {
    private List<Registration> registrations;

    public RegistrationService() {
        this.registrations = new ArrayList<>();
    }

    public void registerParticipant(int eventId, int participantId) {
        // TODO: implement registering
    }

    public void cancelRegistration(int registrationId) {
        // TODO: implement canceling
    }

    public List<Registration> getAllRegistrations() {
        // TODO: return all registrations
        return this.registrations;
    }

    public List<Registration> getRegistrationsByEvent(int eventId) {
        // TODO: return filtered by event
        return new ArrayList<>();
    }

    public List<Registration> getRegistrationsByParticipant(int participantId) {
        // TODO: return filtered by participant
        return new ArrayList<>();
    }
}
