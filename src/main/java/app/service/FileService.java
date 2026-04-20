package app.service;

import app.model.AttendanceRecord;
import app.model.Event;
import app.model.Participant;
import app.model.Registration;

import java.io.IOException;
import java.time.LocalDate;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private static final String SEPARATOR = "\t";

    private final Path dataDirectory;
    private final Path eventsFile;
    private final Path participantsFile;
    private final Path registrationsFile;
    private final Path attendanceFile;
    private final EventService eventService;
    private final ParticipantService participantService;
    private final RegistrationService registrationService;
    private final AttendanceService attendanceService;

    public FileService(EventService eventService, ParticipantService participantService,
            RegistrationService registrationService, AttendanceService attendanceService) {
        this.eventService = eventService;
        this.participantService = participantService;
        this.registrationService = registrationService;
        this.attendanceService = attendanceService;
        this.dataDirectory = Paths.get("data");
        this.eventsFile = this.dataDirectory.resolve("events.txt");
        this.participantsFile = this.dataDirectory.resolve("participants.txt");
        this.registrationsFile = this.dataDirectory.resolve("registrations.txt");
        this.attendanceFile = this.dataDirectory.resolve("attendance.txt");

        try {
            if (!Files.exists(this.dataDirectory)) {
                Files.createDirectories(this.dataDirectory);
            }
        } catch (IOException e) {
            // Handle safely without crashing the app; no custom exceptions
            System.err.println("Failed to create base data directory: " + e.getMessage());
        }
    }

    public void saveData() {
        try {
            if (!Files.exists(this.dataDirectory)) {
                Files.createDirectories(this.dataDirectory);
            }
        } catch (IOException e) {
            System.err.println("Failed to ensure data directory exists during save: " + e.getMessage());
        }

        writeLines(eventsFile, serializeEvents());
        writeLines(participantsFile, serializeParticipants());
        writeLines(registrationsFile, serializeRegistrations());
        writeLines(attendanceFile, serializeAttendance());
    }

    public void loadData() {
        try {
            if (!Files.exists(this.dataDirectory)) {
                Files.createDirectories(this.dataDirectory);
            }
        } catch (IOException e) {
            System.err.println("Failed to ensure data directory exists during load: " + e.getMessage());
        }

        eventService.replaceAllEvents(parseEvents(readLines(eventsFile)));
        participantService.replaceAllParticipants(parseParticipants(readLines(participantsFile)));
        registrationService.replaceAllRegistrations(parseRegistrations(readLines(registrationsFile)));
        attendanceService.replaceAllAttendanceRecords(parseAttendance(readLines(attendanceFile)));
    }

    public void writeLines(Path filePath, List<String> lines) {
        try {
            Path parentDir = filePath.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Failed to write lines to " + filePath + ": " + e.getMessage());
        }
    }

    public List<String> readLines(Path filePath) {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }
        try {
            return Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Failed to read lines from " + filePath + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Path getEventsFilePath() {
        return eventsFile;
    }

    public Path getParticipantsFilePath() {
        return participantsFile;
    }

    public Path getRegistrationsFilePath() {
        return registrationsFile;
    }

    public Path getAttendanceFilePath() {
        return attendanceFile;
    }

    private List<String> serializeEvents() {
        List<String> lines = new ArrayList<>();
        for (Event event : eventService.getAllEvents()) {
            lines.add(String.join(SEPARATOR,
                    String.valueOf(event.getEventId()),
                    safe(event.getEventName()),
                    safe(event.getEventType()),
                    safeDate(event.getEventDate()),
                    safe(event.getEventTime()),
                    safe(event.getVenue()),
                    String.valueOf(event.getCapacity()),
                    String.valueOf(event.getRegisteredCount()),
                    String.valueOf(event.getWaitlistCount()),
                    safe(event.getStatus())));
        }
        return lines;
    }

    private List<String> serializeParticipants() {
        List<String> lines = new ArrayList<>();
        for (Participant participant : participantService.getAllParticipants()) {
            lines.add(String.join(SEPARATOR,
                    String.valueOf(participant.getParticipantId()),
                    safe(participant.getName()),
                    safe(participant.getEmail()),
                    safe(participant.getPhone())));
        }
        return lines;
    }

    private List<String> serializeRegistrations() {
        List<String> lines = new ArrayList<>();
        for (Registration registration : registrationService.getAllRegistrations()) {
            lines.add(String.join(SEPARATOR,
                    String.valueOf(registration.getRegistrationId()),
                    String.valueOf(registration.getEventId()),
                    String.valueOf(registration.getParticipantId()),
                    safe(registration.getRegistrationStatus()),
                    safeDate(registration.getRegistrationDate())));
        }
        return lines;
    }

    private List<String> serializeAttendance() {
        List<String> lines = new ArrayList<>();
        for (AttendanceRecord record : attendanceService.getAllAttendanceRecords()) {
            lines.add(String.join(SEPARATOR,
                    String.valueOf(record.getAttendanceId()),
                    String.valueOf(record.getEventId()),
                    String.valueOf(record.getParticipantId()),
                    safe(record.getAttendanceStatus())));
        }
        return lines;
    }

    private List<Event> parseEvents(List<String> lines) {
        List<Event> events = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR, -1);
            if (parts.length != 10) {
                continue;
            }

            Event event = new Event();
            event.setEventId(parseInteger(parts[0]));
            event.setEventName(emptyToNull(parts[1]));
            event.setEventType(emptyToNull(parts[2]));
            event.setEventDate(parseDate(parts[3]));
            event.setEventTime(emptyToNull(parts[4]));
            event.setVenue(emptyToNull(parts[5]));
            event.setCapacity(parseInteger(parts[6]));
            event.setRegisteredCount(parseInteger(parts[7]));
            event.setWaitlistCount(parseInteger(parts[8]));
            event.setStatus(emptyToNull(parts[9]));
            events.add(event);
        }
        return events;
    }

    private List<Participant> parseParticipants(List<String> lines) {
        List<Participant> participants = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR, -1);
            if (parts.length != 4) {
                continue;
            }

            Participant participant = new Participant();
            participant.setParticipantId(parseInteger(parts[0]));
            participant.setName(emptyToNull(parts[1]));
            participant.setEmail(emptyToNull(parts[2]));
            participant.setPhone(emptyToNull(parts[3]));
            participants.add(participant);
        }
        return participants;
    }

    private List<Registration> parseRegistrations(List<String> lines) {
        List<Registration> registrations = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR, -1);
            if (parts.length != 5) {
                continue;
            }

            Registration registration = new Registration();
            registration.setRegistrationId(parseInteger(parts[0]));
            registration.setEventId(parseInteger(parts[1]));
            registration.setParticipantId(parseInteger(parts[2]));
            registration.setRegistrationStatus(emptyToNull(parts[3]));
            registration.setRegistrationDate(parseDate(parts[4]));
            registrations.add(registration);
        }
        return registrations;
    }

    private List<AttendanceRecord> parseAttendance(List<String> lines) {
        List<AttendanceRecord> records = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR, -1);
            if (parts.length != 4) {
                continue;
            }

            AttendanceRecord record = new AttendanceRecord();
            record.setAttendanceId(parseInteger(parts[0]));
            record.setEventId(parseInteger(parts[1]));
            record.setParticipantId(parseInteger(parts[2]));
            record.setAttendanceStatus(emptyToNull(parts[3]));
            records.add(record);
        }
        return records;
    }

    private String safe(String value) {
        return value == null ? "" : value.replace(SEPARATOR, " ").trim();
    }

    private String safeDate(LocalDate date) {
        return date == null ? "" : date.toString();
    }

    private String emptyToNull(String value) {
        return value == null || value.isEmpty() ? null : value;
    }

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private LocalDate parseDate(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(value);
        } catch (Exception ex) {
            return null;
        }
    }
}
