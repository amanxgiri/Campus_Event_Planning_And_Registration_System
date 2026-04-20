# Campus Event Planning and Registration System 🎓📅

A modular, desktop-based JavaFX application designed to streamline the management of campus events, from initial planning and registration to attendance tracking and reporting.

## 🚀 The Motivation

This project was born out of a real-world need. While managing events for my own campus clubs, I witnessed firsthand the chaos of manual event planning—handling scattered spreadsheets, tracking waitlists by hand, and struggling to keep attendance records accurate. 

I developed this system to provide a centralized, automated solution that simplifies the entire event lifecycle, allowing campus organizers to focus more on the event experience and less on administrative overhead.

## ✨ Key Features

- **Dynamic Event Management:** Create and track events with capacity limits and venue scheduling.
- **Smart Registration & Waitlisting:** 
    - Automatically confirms participants until capacity is reached.
    - Moves additional registrants to a **Waitlist**.
    - **Automatic Promotion:** When a confirmed registration is cancelled, the system automatically promotes the next person on the waitlist to "Confirmed" status.
- **Attendance Tracking:** Link attendance records directly to confirmed registrations.
- **Live Dashboard:** Real-time metrics on total participation, upcoming events, and venue conflicts.
- **Search & Reporting:** Filter events by keyword and view detailed registration summaries.
- **Portable Persistence:** All data is saved to local text files in the `data/` folder, making the project easy to share and run.

## 📸 Screenshots

*(Add your screenshots here to show off the UI)*

> **Tip:** You can use the files listed in `PROJECT_SUBMISSION.md` as a guide for what to capture.

## 🛠️ Tech Stack

- **Language:** Java 21+
- **Framework:** JavaFX (Pure Java UI construction - No FXML)
- **Build Tool:** Maven
- **Modular Java:** Full `module-info` support
- **Architecture:** Layered (Model-Service-UI) with Constructor-based Dependency Injection

## 🏃 How to Run

Since this project uses the Maven Wrapper, you don't need Maven installed locally.

### On Windows
```powershell
.\mvnw.cmd javafx:run
```

### On macOS/Linux
```bash
./mvnw javafx:run
```

## 📂 Project Structure

- `src/main/java/app/model`: Core data entities (Event, Participant, etc.)
- `src/main/java/app/service`: Business logic and waitlist algorithms.
- `src/main/java/app/ui`: JavaFX screen implementations.
- `data/`: Local persistence files (Generated at runtime).

## 🗺️ Roadmap (Phase 2)

We are planning several advanced features for the next version:
- **Security:** Role-Based Access Control (Admin vs. Student).
- **Automation:** Automated email notifications for waitlist promotion.
- **UI/UX:** Custom CSS themes and "Search-as-you-type" filtering.
- **Testing:** Comprehensive JUnit and TestFX suites.

*See `improvements.md` for the full technical roadmap.*

## 📄 License

Developed by **Aman Kumar Giri** (Roll: 245891006) as part of the Campus Management Series.
