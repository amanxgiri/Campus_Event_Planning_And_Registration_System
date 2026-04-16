package app.ui;

import app.model.Event;
import app.service.EventService;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;

public class EventsView {

    private final VBox root;
    private final EventService eventService;

    private final TextField idField;
    private final TextField nameField;
    private final ComboBox<String> typeCombo;
    private final DatePicker datePicker;
    private final TextField timeField;
    private final TextField venueField;
    private final TextField capacityField;
    private final ComboBox<String> statusCombo;
    private final TableView<Event> table;

    public EventsView(EventService eventService) {
        this.eventService = eventService;
        this.root = new VBox(20);
        this.root.setPadding(new Insets(20));

        Label titleLabel = new Label("Events");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        // 1. Form Section
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(10);

        this.idField = new TextField();
        this.idField.setPromptText("Event ID");
        this.idField.setEditable(false);

        this.nameField = new TextField();
        this.nameField.setPromptText("Event Name");

        this.typeCombo = new ComboBox<>(FXCollections.observableArrayList(
                "WORKSHOP", "SEMINAR", "CULTURAL", "TECHNICAL", "OTHER"));
        this.typeCombo.setPromptText("Event Type");

        this.datePicker = new DatePicker();
        this.datePicker.setPromptText("Event Date");

        this.timeField = new TextField();
        this.timeField.setPromptText("Event Time");

        this.venueField = new TextField();
        this.venueField.setPromptText("Venue");

        this.capacityField = new TextField();
        this.capacityField.setPromptText("Capacity");

        this.statusCombo = new ComboBox<>(FXCollections.observableArrayList(
                "UPCOMING", "ONGOING", "COMPLETED"));
        this.statusCombo.setPromptText("Status");

        formGrid.add(new Label("Event ID:"), 0, 0);
        formGrid.add(idField, 1, 0);
        formGrid.add(new Label("Event Name:"), 2, 0);
        formGrid.add(nameField, 3, 0);

        formGrid.add(new Label("Event Type:"), 0, 1);
        formGrid.add(typeCombo, 1, 1);
        formGrid.add(new Label("Event Date:"), 2, 1);
        formGrid.add(datePicker, 3, 1);

        formGrid.add(new Label("Event Time:"), 0, 2);
        formGrid.add(timeField, 1, 2);
        formGrid.add(new Label("Venue:"), 2, 2);
        formGrid.add(venueField, 3, 2);

        formGrid.add(new Label("Capacity:"), 0, 3);
        formGrid.add(capacityField, 1, 3);
        formGrid.add(new Label("Status:"), 2, 3);
        formGrid.add(statusCombo, 3, 3);

        // 2. Buttons Section
        HBox buttonBox = new HBox(10);
        Button addBtn = new Button("Add Event");
        Button updateBtn = new Button("Update Event");
        Button deleteBtn = new Button("Delete Event");
        Button clearBtn = new Button("Clear");

        buttonBox.getChildren().addAll(addBtn, updateBtn, deleteBtn, clearBtn);

        // 3. Table Section
        this.table = new TableView<>();

        TableColumn<Event, Integer> idCol = new TableColumn<>("Event ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("eventId"));

        TableColumn<Event, String> nameCol = new TableColumn<>("Event Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("eventName"));

        TableColumn<Event, String> typeCol = new TableColumn<>("Event Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("eventType"));

        TableColumn<Event, LocalDate> dateCol = new TableColumn<>("Event Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("eventDate"));

        TableColumn<Event, String> timeCol = new TableColumn<>("Event Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("eventTime"));

        TableColumn<Event, String> venueCol = new TableColumn<>("Venue");
        venueCol.setCellValueFactory(new PropertyValueFactory<>("venue"));

        TableColumn<Event, Integer> capCol = new TableColumn<>("Capacity");
        capCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        TableColumn<Event, Integer> regCol = new TableColumn<>("Registered");
        regCol.setCellValueFactory(new PropertyValueFactory<>("registeredCount"));

        TableColumn<Event, Integer> waitCol = new TableColumn<>("Waitlist");
        waitCol.setCellValueFactory(new PropertyValueFactory<>("waitlistCount"));

        TableColumn<Event, String> statCol = new TableColumn<>("Status");
        statCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(idCol, nameCol, typeCol, dateCol, timeCol, venueCol, capCol, regCol, waitCol,
                statCol);

        // Table row selection
        table.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                idField.setText(String.valueOf(selected.getEventId()));
                nameField.setText(selected.getEventName());
                typeCombo.setValue(selected.getEventType());
                datePicker.setValue(selected.getEventDate());
                timeField.setText(selected.getEventTime());
                venueField.setText(selected.getVenue());
                capacityField.setText(String.valueOf(selected.getCapacity()));
                statusCombo.setValue(selected.getStatus());
            }
        });

        // Button Actions
        addBtn.setOnAction(e -> {
            Event event = new Event();
            event.setEventId(eventService.getAllEvents().size() + 1);
            event.setEventName(nameField.getText());
            event.setEventType(typeCombo.getValue());
            event.setEventDate(datePicker.getValue());
            event.setEventTime(timeField.getText());
            event.setVenue(venueField.getText());

            try {
                event.setCapacity(Integer.parseInt(capacityField.getText()));
            } catch (NumberFormatException ex) {
                event.setCapacity(0);
            }

            event.setStatus(statusCombo.getValue());

            eventService.addEvent(event);
            refreshTable();
            clearForm();
        });

        updateBtn.setOnAction(e -> {
            Event selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setEventName(nameField.getText());
                selected.setEventType(typeCombo.getValue());
                selected.setEventDate(datePicker.getValue());
                selected.setEventTime(timeField.getText());
                selected.setVenue(venueField.getText());

                try {
                    selected.setCapacity(Integer.parseInt(capacityField.getText()));
                } catch (NumberFormatException ex) {
                    selected.setCapacity(0);
                }

                selected.setStatus(statusCombo.getValue());

                eventService.updateEvent(selected);
                refreshTable();
            }
        });

        deleteBtn.setOnAction(e -> {
            Event selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                eventService.deleteEvent(selected.getEventId());
                refreshTable();
                clearForm();
            }
        });

        clearBtn.setOnAction(e -> clearForm());

        // Assemble everything
        this.root.getChildren().addAll(titleLabel, formGrid, buttonBox, table);

        refreshTable();
        clearForm(); // To set initial ID
    }

    private void refreshTable() {
        table.setItems(FXCollections.observableArrayList(eventService.getAllEvents()));
    }

    private void clearForm() {
        table.getSelectionModel().clearSelection();
        idField.setText(String.valueOf(eventService.getAllEvents().size() + 1));
        nameField.setText("");
        typeCombo.getSelectionModel().clearSelection();
        datePicker.setValue(null);
        timeField.setText("");
        venueField.setText("");
        capacityField.setText("");
        statusCombo.getSelectionModel().clearSelection();
    }

    public Parent getView() {
        return root;
    }
}
