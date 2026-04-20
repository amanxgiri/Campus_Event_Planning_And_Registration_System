# CAMPUS EVENT PLANNING AND REGISTRATION SYSTEM

## NAME OF THE STUDENT: Aman Kumar Giri
## ROLL NUM: 245891006
## SEMESTER & SECTION: IV and CyberSecurity 'A'
## COURSE CODE: I'll fill this
## COURSE NAME: I'll fill this

---

## DETAILED PROJECT DESCRIPTION:

Campus Event Planning and Registration System is a desktop-based JavaFX application developed to manage the complete event lifecycle inside a campus environment. The application allows the user to create and manage events, register participants, track attendance, generate dashboard summaries, and view simple reports from one centralized interface.

The main purpose of the project is to simplify campus event handling by combining event planning and participant registration into a single system. Instead of maintaining separate records manually, the application keeps event, participant, registration, and attendance data connected through a shared service-based architecture.

This project is developed using Java, JavaFX, Maven, and Modular Java. The user interface is created entirely using pure JavaFX code without using FXML for the active application flow. The system follows a layered structure:

- `app.model` for data classes
- `app.service` for business logic
- `app.ui` for JavaFX screens
- `MainLayout` for shared service injection and navigation

The project includes the following major modules:

### 1. Event Management

The event module allows the user to:

- add new events
- update existing events
- delete events when no linked records exist
- view event details in a table
- manage event capacity, status, venue, date, and time
- automatically display confirmed and waitlisted counts

### 2. Participant Management

The participant module allows the user to:

- add participants
- update participant records
- delete participants only when no linked records exist
- store participant name, email, and phone details

### 3. Registration Management

The registration module is one of the core parts of the project. It allows the user to:

- create registrations for participants against events
- update registrations
- cancel registrations
- prevent duplicate active registrations for the same event-participant pair
- validate that event ID and participant ID exist
- automatically move participants between confirmed and waitlisted states based on event capacity
- automatically promote waitlisted participants when a confirmed registration is cancelled
- prevent cancellation if attendance has already been marked

### 4. Attendance Management

The attendance module allows the user to:

- mark attendance for confirmed registrations only
- store attendance status such as `PRESENT` or `ABSENT`
- view attendance records in tabular format

### 5. Dashboard and Reporting

The dashboard module provides summary information such as:

- total events
- total participants
- confirmed registrations
- waitlisted participants
- upcoming events
- venue conflicts

The reporting module provides:

- event search by keyword
- registration summary for the selected event

### 6. File Persistence

The project uses lightweight local file persistence instead of a database. Data is:

- loaded automatically on application startup
- saved automatically when the application is closed
- stored in text files inside `data/`

This makes the project simple to run and demonstrate without requiring external database setup.

### 7. Technical Highlights

- Built with JavaFX desktop UI
- Pure JavaFX screen construction
- Shared service architecture through `MainLayout`
- Constructor-based dependency injection for views
- Modular Java support
- Maven-based build and run flow
- File-based persistence
- Current Java source code size: approximately `2019` lines

Overall, this project demonstrates practical software engineering concepts such as modular design, separation of concerns, UI development, data validation, persistence handling, and cross-module integration in a real-world campus management scenario.

---

## SCREENSHOTS OF PROJECT (With different case study and description for each screenshot):

Note: Insert your final screenshots below in the indicated places.

### Screenshot 1: Dashboard Overview

**Case Study:** Overall summary of the campus event system after creating events, participants, and registrations.

**Description:**  
This screenshot should show the dashboard with metrics such as total events, total participants, confirmed registrations, waitlisted participants, upcoming events, and venue conflicts. It demonstrates that the dashboard is connected to shared services and reflects live application data.

**Suggested screenshot file:** `screenshots/dashboard-overview.png`

---

### Screenshot 2: Event Management Screen

**Case Study:** Creating and managing multiple campus events.

**Description:**  
This screenshot should show the Events screen with the event form and event table. It should include event details such as event name, type, date, time, venue, capacity, status, and automatically updated registered/waitlist counts.

**Suggested screenshot file:** `screenshots/events-management.png`

---

### Screenshot 3: Participant Management Screen

**Case Study:** Adding participant records for campus event registration.

**Description:**  
This screenshot should show the Participants screen with participant form data and the participant table. It demonstrates CRUD operations for participants and how participant data is maintained in the system.

**Suggested screenshot file:** `screenshots/participants-management.png`

---

### Screenshot 4: Registration with Capacity and Waitlist Logic

**Case Study:** Event capacity is limited, so one participant is confirmed and another is moved to waitlist automatically.

**Description:**  
This screenshot should show the Registrations screen where registrations are added for the same event. It should clearly demonstrate that once event capacity is reached, later participants are automatically moved to `WAITLISTED`.

**Suggested screenshot file:** `screenshots/registration-waitlist.png`

---

### Screenshot 5: Attendance Marking

**Case Study:** Marking attendance for a confirmed participant after successful registration.

**Description:**  
This screenshot should show the Attendance screen where attendance is marked as `PRESENT` or `ABSENT`. It should also demonstrate that attendance can only be marked for confirmed registrations.

**Suggested screenshot file:** `screenshots/attendance-marking.png`

---

### Screenshot 6: Search and Reports Screen

**Case Study:** Searching for an event and viewing registration statistics for reporting purposes.

**Description:**  
This screenshot should show the Search & Reports screen with a search keyword, filtered event list, and registration summary for the selected event. It demonstrates the reporting and event search capability of the application.

**Suggested screenshot file:** `screenshots/search-reports.png`

---

### Screenshot 7: Persistence Demonstration

**Case Study:** Data remains available after restarting the application.

**Description:**  
This screenshot can show the app after reopening, with previously saved events, participants, registrations, and attendance records still visible. This demonstrates local file persistence using text files.

**Suggested screenshot file:** `screenshots/persistence-demo.png`

---

## CODE OF YOUR PROJECT:

Some of the main components of the project are:

### Main Application and Layout

- `com.giri.events.App`
- `app.ui.MainLayout`

These classes are responsible for launching the application, loading the main interface, managing navigation, and sharing services across views.

### Models

- `Event`
- `Participant`
- `Registration`
- `AttendanceRecord`

These classes represent the core entities of the project.

### Services

- `EventService`
- `ParticipantService`
- `RegistrationService`
- `AttendanceService`
- `DashboardService`
- `FileService`

These services contain the main business logic such as CRUD operations, registration handling, waitlist logic, attendance handling, dashboard metrics, and local file persistence.

### UI Screens

- `DashboardView`
- `EventsView`
- `ParticipantsView`
- `RegistrationsView`
- `AttendanceView`
- `SearchReportsView`

These screens provide the full interactive JavaFX-based user interface for the system.

### Code Size

The project currently contains approximately:

- **2019 lines of Java source code**

All code could be found on GitHub here:  
**Link:** https://github.com/amanxgiri/Campus_Event_Planning_And_Registration_System

You can go there to get a detailed look at the complete project.

---

## PRESENTATION / DEMO TALKING POINTS

You can use the following short flow while presenting:

### 1. Introduction

"This project is a Campus Event Planning and Registration System built using JavaFX, Maven, and Modular Java. It is designed to manage campus events, participants, registrations, attendance, and reports in one desktop application."

### 2. Problem Statement

"In many college environments, event registration and tracking are handled manually or across multiple disconnected files. This project solves that problem by centralizing the event workflow into one system."

### 3. Key Features

"The major features are event management, participant management, registration with waitlist handling, attendance tracking, dashboard metrics, event search, and local file persistence."

### 4. Technical Design

"The application follows a layered structure with models, services, and UI views. Shared services are managed through MainLayout, and the interface is built entirely with pure JavaFX code."

### 5. Best Demonstration Flow

Use this order during your demo:

1. Show Dashboard
2. Open Events and create an event
3. Open Participants and add participants
4. Open Registrations and register participants
5. Show automatic waitlist when capacity is exceeded
6. Return to Events and show registered/waitlist counts
7. Open Attendance and mark attendance
8. Open Search & Reports and search for an event
9. Close and reopen the app to show persistence

### 6. Key Technical Highlights to Mention

- constructor-based service injection
- shared-state architecture
- lambda-based `TableView` bindings for modular Java compatibility
- automatic waitlist promotion logic
- attendance restriction to confirmed registrations
- local persistence without database complexity

### 7. Closing Line

"This project demonstrates practical desktop application development using JavaFX, along with modular architecture, service-based design, validation, persistence, and end-to-end workflow integration."
