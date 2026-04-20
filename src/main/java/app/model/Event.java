package app.model;

import java.time.LocalDate;

public class Event {
    private int eventId;
    private String eventName;
    private String eventType;
    private LocalDate eventDate;
    private String eventTime;
    private String venue;
    private int capacity;
    private int registeredCount;
    private int waitlistCount;
    private String status;

    public Event() {
    }

    public Event(int eventId, String eventName, String eventType, LocalDate eventDate, String eventTime, String venue,
            int capacity, int registeredCount, int waitlistCount, String status) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venue = venue;
        this.capacity = capacity;
        this.registeredCount = registeredCount;
        this.waitlistCount = waitlistCount;
        this.status = status;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRegisteredCount() {
        return registeredCount;
    }

    public void setRegisteredCount(int registeredCount) {
        this.registeredCount = registeredCount;
    }

    public int getWaitlistCount() {
        return waitlistCount;
    }

    public void setWaitlistCount(int waitlistCount) {
        this.waitlistCount = waitlistCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventDate=" + eventDate +
                ", eventTime='" + eventTime + '\'' +
                ", venue='" + venue + '\'' +
                ", capacity=" + capacity +
                ", registeredCount=" + registeredCount +
                ", waitlistCount=" + waitlistCount +
                ", status='" + status + '\'' +
                '}';
    }
}
