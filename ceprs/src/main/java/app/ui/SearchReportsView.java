package app.ui;

import app.model.Event;
import app.model.Registration;
import app.service.EventService;
import app.service.RegistrationService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.util.List;

public class SearchReportsView {

    private final VBox root;
    private final EventService eventService;
    private final RegistrationService registrationService;

    private final TextField keywordField;
    private final Label feedbackLabel;
    private final Label reportLabel;
    private final TableView<Event> table;

    public SearchReportsView(EventService eventService, RegistrationService registrationService) {
        this.eventService = eventService;
        this.registrationService = registrationService;
        this.root = new VBox(20);
        this.root.setPadding(new Insets(20));

        Label titleLabel = new Label("Search & Reports");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        this.keywordField = new TextField();
        this.keywordField.setPromptText("Search events by name");

        Button searchBtn = new Button("Search");
        Button clearBtn = new Button("Clear");

        HBox searchBox = new HBox(10, keywordField, searchBtn, clearBtn);

        this.feedbackLabel = new Label();
        this.reportLabel = new Label("Select an event to view registration summary.");

        this.table = new TableView<>();

        TableColumn<Event, Integer> idCol = new TableColumn<>("Event ID");
        idCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getEventId()));

        TableColumn<Event, String> nameCol = new TableColumn<>("Event Name");
        nameCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEventName()));

        TableColumn<Event, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEventType()));

        TableColumn<Event, LocalDate> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getEventDate()));

        TableColumn<Event, String> venueCol = new TableColumn<>("Venue");
        venueCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getVenue()));

        TableColumn<Event, Integer> registeredCol = new TableColumn<>("Registered");
        registeredCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getRegisteredCount()));

        TableColumn<Event, Integer> waitlistCol = new TableColumn<>("Waitlist");
        waitlistCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getWaitlistCount()));

        table.getColumns().addAll(idCol, nameCol, typeCol, dateCol, venueCol, registeredCol, waitlistCol);

        searchBtn.setOnAction(e -> searchEvents());
        clearBtn.setOnAction(e -> clearSearch());

        table.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> updateReport(selected));

        this.root.getChildren().addAll(titleLabel, searchBox, feedbackLabel, table, reportLabel);

        loadAllEvents();
    }

    private void searchEvents() {
        String keyword = keywordField.getText();
        if (keyword == null || keyword.trim().isEmpty()) {
            loadAllEvents();
            feedbackLabel.setText("");
            return;
        }

        List<Event> results = eventService.searchEventsByName(keyword.trim());
        table.setItems(FXCollections.observableArrayList(results));

        if (results.isEmpty()) {
            feedbackLabel.setText("No events found for the given keyword.");
            reportLabel.setText("Select an event to view registration summary.");
        } else {
            feedbackLabel.setText("Found " + results.size() + " matching event(s).");
        }
    }

    private void clearSearch() {
        keywordField.setText("");
        feedbackLabel.setText("");
        reportLabel.setText("Select an event to view registration summary.");
        table.getSelectionModel().clearSelection();
        loadAllEvents();
    }

    private void loadAllEvents() {
        table.setItems(FXCollections.observableArrayList(eventService.getAllEvents()));
    }

    private void updateReport(Event event) {
        if (event == null) {
            reportLabel.setText("Select an event to view registration summary.");
            return;
        }

        List<Registration> registrations = registrationService.getRegistrationsByEvent(event.getEventId());
        int confirmedCount = 0;
        int waitlistedCount = 0;
        int cancelledCount = 0;

        for (Registration registration : registrations) {
            if ("CONFIRMED".equalsIgnoreCase(registration.getRegistrationStatus())) {
                confirmedCount++;
            } else if ("WAITLISTED".equalsIgnoreCase(registration.getRegistrationStatus())) {
                waitlistedCount++;
            } else if ("CANCELLED".equalsIgnoreCase(registration.getRegistrationStatus())) {
                cancelledCount++;
            }
        }

        reportLabel.setText(
                "Event " + event.getEventId()
                        + " summary: Total Registrations = " + registrations.size()
                        + ", Confirmed = " + confirmedCount
                        + ", Waitlisted = " + waitlistedCount
                        + ", Cancelled = " + cancelledCount);
    }

    public Parent getView() {
        return root;
    }
}
