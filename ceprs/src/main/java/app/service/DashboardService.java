package app.service;

import app.model.Event;
import app.model.Registration;

import java.time.LocalDate;
import java.util.List;

public class DashboardService {

    private final EventService eventService;
    private final ParticipantService participantService;
    private final RegistrationService registrationService;

    public DashboardService(EventService eventService, ParticipantService participantService,
            RegistrationService registrationService) {
        this.eventService = eventService;
        this.participantService = participantService;
        this.registrationService = registrationService;
    }

    public int getTotalEvents() {
        if (eventService == null)
            return 0;
        return eventService.getAllEvents().size();
    }

    public int getTotalParticipants() {
        if (participantService == null)
            return 0;
        return participantService.getAllParticipants().size();
    }

    public int getConfirmedRegistrationsCount() {
        if (registrationService == null)
            return 0;
        int count = 0;
        for (Registration registration : registrationService.getAllRegistrations()) {
            if ("CONFIRMED".equalsIgnoreCase(registration.getRegistrationStatus())) {
                count++;
            }
        }
        return count;
    }

    public int getWaitlistedParticipantsCount() {
        if (registrationService == null)
            return 0;
        int count = 0;
        for (Registration registration : registrationService.getAllRegistrations()) {
            if ("WAITLISTED".equalsIgnoreCase(registration.getRegistrationStatus())) {
                count++;
            }
        }
        return count;
    }

    public int getUpcomingEventsCount() {
        if (eventService == null)
            return 0;
        int count = 0;
        LocalDate today = LocalDate.now();
        for (Event event : eventService.getAllEvents()) {
            if (event.getEventDate() != null && event.getEventDate().isAfter(today)) {
                count++;
            }
        }
        return count;
    }

    public int getVenueConflictsCount() {
        if (eventService == null)
            return 0;
        List<Event> events = eventService.getAllEvents();
        int conflicts = 0;

        for (int i = 0; i < events.size(); i++) {
            Event e1 = events.get(i);
            boolean hasConflict = false;

            for (int j = 0; j < events.size(); j++) {
                if (i == j)
                    continue;
                Event e2 = events.get(j);

                if (e1.getEventDate() != null && e1.getEventDate().equals(e2.getEventDate()) &&
                        e1.getEventTime() != null && e1.getEventTime().equals(e2.getEventTime()) &&
                        e1.getVenue() != null && e1.getVenue().equalsIgnoreCase(e2.getVenue()) &&
                        !e1.getVenue().trim().isEmpty()) {
                    hasConflict = true;
                    break;
                }
            }
            if (hasConflict) {
                conflicts++;
            }
        }
        return conflicts;
    }
}
