package app.service;

import app.model.Event;
import java.util.ArrayList;
import java.util.List;

public class EventService {
    private List<Event> events;

    public EventService() {
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        // TODO: implement adding event
        this.events.add(event);
    }

    public void updateEvent(Event event) {
        // TODO: implement updating event
    }

    public void deleteEvent(int eventId) {
        // TODO: implement deleting event
    }

    public List<Event> getAllEvents() {
        // TODO: implement returning all events
        return this.events;
    }

    public Event findEventById(int eventId) {
        // TODO: implement finding event
        return null;
    }

    public List<Event> searchEventsByName(String keyword) {
        // TODO: implement searching events
        return new ArrayList<>();
    }
}
