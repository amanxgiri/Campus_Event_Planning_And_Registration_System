package app.service;

import app.model.Event;
import app.model.Registration;
import java.util.ArrayList;
import java.util.List;

public class EventService {
    private List<Event> events;

    public EventService() {
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        if (event != null) {
            this.events.add(event);
        }
    }

    public void updateEvent(Event event) {
        if (event == null) {
            return;
        }
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEventId() == event.getEventId()) {
                events.set(i, event);
                return;
            }
        }
    }

    public void deleteEvent(int eventId) {
        events.removeIf(e -> e.getEventId() == eventId);
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(this.events);
    }

    public void replaceAllEvents(List<Event> events) {
        if (events == null) {
            this.events = new ArrayList<>();
            return;
        }
        this.events = new ArrayList<>(events);
    }

    public void updateRegistrationCounts(List<Registration> registrations) {
        for (Event event : events) {
            event.setRegisteredCount(0);
            event.setWaitlistCount(0);
        }

        if (registrations == null) {
            return;
        }

        for (Registration registration : registrations) {
            Event event = findEventById(registration.getEventId());
            if (event == null || registration.getRegistrationStatus() == null) {
                continue;
            }

            if ("CONFIRMED".equalsIgnoreCase(registration.getRegistrationStatus())) {
                event.setRegisteredCount(event.getRegisteredCount() + 1);
            } else if ("WAITLISTED".equalsIgnoreCase(registration.getRegistrationStatus())) {
                event.setWaitlistCount(event.getWaitlistCount() + 1);
            }
        }
    }

    public Event findEventById(int eventId) {
        for (Event event : events) {
            if (event.getEventId() == eventId) {
                return event;
            }
        }
        return null;
    }

    public List<Event> searchEventsByName(String keyword) {
        List<Event> result = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return result;
        }
        String searchLower = keyword.toLowerCase();
        for (Event event : events) {
            if (event.getEventName() != null && event.getEventName().toLowerCase().contains(searchLower)) {
                result.add(event);
            }
        }
        return result;
    }
}
