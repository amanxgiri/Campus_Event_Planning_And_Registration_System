# Project Improvements: Campus Event Planning and Registration System

This document outlines suggested validation gaps and architectural improvements for the project.

## 1. Validation Gaps

### Format & Constraint Validation
*   **Email & Phone Validation:** Implement Regex-based validation in `ParticipantService` to ensure emails follow standard formats and phone numbers are numeric.
*   **Strict Time Formatting:** Transition `Event.eventTime` from a `String` to `LocalTime` or apply strict `HH:mm` regex validation to prevent inconsistent time entries.
*   **Temporal Logic:** Add checks to ensure `eventDate` is not in the past and prevent registrations for events that have already occurred.

### Uniqueness & Integrity
*   **Duplicate Prevention:** Ensure participant uniqueness by checking for existing emails before adding a new record.
*   **Proactive Venue Conflict Resolution:** Block the creation or update of an event if another event is already scheduled at the same venue, date, and time.
*   **Capacity Edge Cases:** Implement logic to handle capacity reductions, such as automatically moving the "overflow" confirmed participants to the waitlist or preventing the reduction if it violates current registration counts.

### Data Safety
*   **Enhanced Sanitization:** Improve the `safe()` method in `FileService` to handle multi-line input or special characters that might interfere with the tab-separated file structure.

## 2. Architectural Improvements

### Layering & Decoupling
*   **Repository Pattern:** Introduce a Repository layer to decouple `FileService` from the business logic. This makes it easier to migrate from text files to a database (SQL) in the future.
*   **Service Locator:** Instead of `MainLayout` manually instantiating every service, use a simple Service Locator or Registry to manage service singletons.

### Logic & State Management
*   **Centralized Validation:** Create a dedicated `Validator` utility that returns structured error messages, keeping the UI and Services focused on their primary responsibilities.
*   **Reactive UI (Observer Pattern):** Utilize JavaFX `ObservableList` and property listeners more extensively so that UI components update automatically when the underlying service data changes, reducing manual `refreshTable()` calls.
*   **Robust ID Generation:** Move away from `size() + 1` for ID generation (which can cause collisions after deletions) to a persistent "Last ID" counter or UUIDs.

### Error Handling & UX
*   **Custom Exceptions:** Define specific exceptions (e.g., `CapacityExceededException`) to allow the UI to provide more granular and helpful feedback to the user.
*   **View Caching:** Cache UI view instances in `MainLayout` to preserve user state (like scroll position and selection) during navigation.
*   **Command Pattern:** Implement actions as Commands to support future "Undo/Redo" functionality.

## 3. Phase 2: Advanced Functional Features

### Security & Access Control
*   **Role-Based Access Control (RBAC):** Implement a login system with distinct roles (e.g., `Admin` for management, `Student` for registration) to restrict sensitive actions like deleting events.
*   **Secure Authentication:** Use `BCrypt` password hashing to ensure user credentials are never stored in plain text.

### Automation & Notifications
*   **Email Waitlist Notifications:** Create a notification service to automatically alert participants via email (or a mocked console log) when they are promoted from `WAITLISTED` to `CONFIRMED`.
*   **PDF Certificate Generation:** Add a feature to generate PDF "Certificates of Participation" for participants marked as `PRESENT` once an event is marked as `COMPLETED`.

### Engineering Quality
*   **Unit Testing (JUnit):** Implement comprehensive unit tests for the `service` layer, focusing on complex logic like registration validation and capacity management.
*   **UI Testing (TestFX):** Use TestFX to automate functional testing of the JavaFX interface, ensuring buttons and forms behave correctly across updates.

### Enhanced UI/UX
*   **Custom CSS Styling:** Apply a custom `.css` stylesheet to transition from the default JavaFX "Modena" theme to a modern, branded aesthetic (e.g., Material Design).
*   **Visual Icons:** Integrate an icon library (like `FontAwesomeFX` or `Ikonli`) to provide visual cues for sidebar navigation and action buttons (Add, Update, Delete).
*   **Search-As-You-Type:** Enhance the Search & Reports view to filter table results in real-time as the user types, removing the need for a manual "Search" button.
