package app.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private final Path dataDirectory;
    private final Path eventsFile;
    private final Path participantsFile;
    private final Path registrationsFile;
    private final Path attendanceFile;

    public FileService() {
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
        // TODO: integrate with other services to actually save model objects
        try {
            if (!Files.exists(this.dataDirectory)) {
                Files.createDirectories(this.dataDirectory);
            }
        } catch (IOException e) {
            System.err.println("Failed to ensure data directory exists during save: " + e.getMessage());
        }
    }

    public void loadData() {
        // TODO: integrate with other services to actually load model objects
        try {
            if (!Files.exists(this.dataDirectory)) {
                Files.createDirectories(this.dataDirectory);
            }
        } catch (IOException e) {
            System.err.println("Failed to ensure data directory exists during load: " + e.getMessage());
        }
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
}
