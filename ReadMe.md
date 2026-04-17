# Campus Event Planning and Registration System

Campus Event Planning and Registration System is a desktop application built with JavaFX for managing campus events, participants, registrations, attendance, and basic reporting in one place.

The project is designed around a simple in-memory service layer with lightweight file persistence, which makes it suitable for academic projects, demos, and incremental feature development without introducing database complexity too early.

## What the App Does

The application helps manage the full event registration flow for a campus environment:

- Create, update, view, and delete events
- Create, update, view, and delete participants
- Register participants for events using shared services
- Automatically manage confirmed and waitlisted registrations based on event capacity
- Prevent duplicate active registrations for the same participant-event pair
- Cancel registrations safely
- Mark attendance only for confirmed registrations
- View dashboard metrics such as total events, total participants, confirmed registrations, waitlisted participants, upcoming events, and venue conflicts
- Search events by name and view registration summaries
- Persist application data locally between runs

## Core Features

### Event Management

- Add, update, delete, and view campus events
- Track event metadata such as type, date, time, venue, capacity, and status
- Automatically display registered and waitlisted counts per event
- Prevent deleting events that already have linked registrations or attendance records

### Participant Management

- Add, update, delete, and view participant records
- Store participant name, email, and phone details
- Prevent deleting participants that already have linked registrations or attendance records

### Registration Management

- Create registrations using shared `EventService`, `ParticipantService`, and `RegistrationService`
- Validate event and participant IDs before registration
- Block duplicate active registrations for the same event and participant
- Support registration updates and cancellation
- Apply capacity-based promotion and waitlist logic automatically
- Recalculate event registration counts when registrations or capacities change
- Block registration cancellation after attendance has been marked

### Attendance Management

- Mark attendance for registered participants
- Restrict attendance marking to confirmed registrations only
- Store and display attendance records in a dedicated screen

### Dashboard and Reports

- Show live summary cards for key metrics
- Search events by keyword
- Show registration summary totals for selected events

### Persistence

- Save data to local text files on application close
- Load saved data automatically on startup
- Persist events, participants, registrations, and attendance records in `ceprs/data/`

## Tech Stack

- Java
- JavaFX
- Maven
- Modular Java (`module-info.java`)
- Pure JavaFX UI construction in Java code
- In-memory services with local file-based persistence

## Project Structure

The codebase follows a simple layered structure:

- `app.model`  
  Plain Java objects such as `Event`, `Participant`, `Registration`, and `AttendanceRecord`

- `app.service`  
  Business logic and in-memory service classes such as `EventService`, `ParticipantService`, `RegistrationService`, `AttendanceService`, `DashboardService`, and `FileService`

- `app.ui`  
  JavaFX views such as `DashboardView`, `EventsView`, `ParticipantsView`, `RegistrationsView`, `AttendanceView`, and `SearchReportsView`

- `MainLayout`  
  The shared application shell that owns service instances and handles navigation between screens

## Architecture Notes

- Services are shared through `MainLayout`
- Views receive services through constructors
- `TableView` columns use lambda-based bindings
- The UI is built without FXML screen files
- Persistence is intentionally lightweight and file-based for now
- The application favors controlled, incremental logic over heavy abstraction

## Current Functional Scope

This version is already suitable for a demo or academic submission and includes:

- working CRUD for events and participants
- registration add, update, cancel, validation, waitlist, and duplicate prevention
- attendance flow linked to registrations
- dashboard metrics
- search and reporting
- local persistence
- data integrity safeguards across modules

## How to Run

### Using Maven Wrapper

From the `ceprs` directory:

```bash
./mvnw javafx:run
```

On Windows:

```powershell
.\mvnw.cmd javafx:run
```

### Build Only

```bash
./mvnw compile
```

On Windows:

```powershell
.\mvnw.cmd compile
```

## Data Storage

Runtime data is stored in:

```text
ceprs/data/
```

Typical files created there:

- `events.txt`
- `participants.txt`
- `registrations.txt`
- `attendance.txt`

These files are generated runtime data and are ignored in git.

## Future Improvements

Possible next enhancements include:

- UI polish and better visual consistency
- richer validation messages
- more advanced reports and filters
- export/import improvements
- automated tests
- database integration if the project moves beyond lightweight local persistence

## Summary

This project demonstrates a modular JavaFX desktop application with shared-service architecture, event registration workflows, attendance tracking, dashboard reporting, and local persistence, while keeping the design simple enough to understand and extend.
