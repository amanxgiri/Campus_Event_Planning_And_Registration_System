package app.service;

import app.model.Registration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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

    public void addRegistration(Registration registration) {
        if (registration.getRegistrationId() <= 0) {
            registration.setRegistrationId(nextRegistrationId++);
        } else if (registration.getRegistrationId() >= nextRegistrationId) {
            nextRegistrationId = registration.getRegistrationId() + 1;
        }

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

    public void updateRegistration(Registration updatedRegistration) {
        for (int i = 0; i < registrations.size(); i++) {
            Registration existingRegistration = registrations.get(i);
            if (existingRegistration.getRegistrationId() == updatedRegistration.getRegistrationId()) {
                registrations.set(i, updatedRegistration);
                return;
            }
        }
    }

    public List<Registration> getAllRegistrations() {
        return new ArrayList<>(this.registrations);
    }

    public void replaceAllRegistrations(List<Registration> registrations) {
        if (registrations == null) {
            this.registrations = new ArrayList<>();
            this.nextRegistrationId = 1;
            return;
        }

        this.registrations = new ArrayList<>(registrations);
        this.nextRegistrationId = 1;
        for (Registration registration : this.registrations) {
            if (registration.getRegistrationId() >= this.nextRegistrationId) {
                this.nextRegistrationId = registration.getRegistrationId() + 1;
            }
        }
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

    public void applyCapacityRules(int eventId, int capacity) {
        List<Registration> activeRegistrations = new ArrayList<>();
        for (Registration registration : registrations) {
            if (registration.getEventId() == eventId
                    && !"CANCELLED".equalsIgnoreCase(registration.getRegistrationStatus())) {
                activeRegistrations.add(registration);
            }
        }

        activeRegistrations.sort(Comparator.comparingInt(Registration::getRegistrationId));

        int availableSlots = Math.max(capacity, 0);
        for (Registration registration : activeRegistrations) {
            if (availableSlots > 0) {
                registration.setRegistrationStatus("CONFIRMED");
                availableSlots--;
            } else {
                registration.setRegistrationStatus("WAITLISTED");
            }
        }
    }

    public boolean hasActiveRegistration(int eventId, int participantId, Integer excludeRegistrationId) {
        for (Registration registration : registrations) {
            if (registration.getEventId() == eventId
                    && registration.getParticipantId() == participantId
                    && !"CANCELLED".equalsIgnoreCase(registration.getRegistrationStatus())
                    && (excludeRegistrationId == null
                            || registration.getRegistrationId() != excludeRegistrationId)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasConfirmedRegistration(int eventId, int participantId) {
        for (Registration registration : registrations) {
            if (registration.getEventId() == eventId
                    && registration.getParticipantId() == participantId
                    && "CONFIRMED".equalsIgnoreCase(registration.getRegistrationStatus())) {
                return true;
            }
        }
        return false;
    }
}
