package app.ui;

import app.model.Registration;
import app.service.EventService;
import app.service.ParticipantService;
import app.service.RegistrationService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;

public class RegistrationsView {

    private final VBox root;
    private final EventService eventService;
    private final ParticipantService participantService;
    private final RegistrationService registrationService;

    private final TextField regIdField;
    private final TextField eventIdField;
    private final TextField participantIdField;
    private final ComboBox<String> statusCombo;
    private final DatePicker datePicker;
    private final TableView<Registration> table;
    private final Label feedbackLabel;

    public RegistrationsView(EventService eventService, ParticipantService participantService,
            RegistrationService registrationService) {
        this.eventService = eventService;
        this.participantService = participantService;
        this.registrationService = registrationService;
        this.root = new VBox(20);
        this.root.setPadding(new Insets(20));

        Label titleLabel = new Label("Registrations");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        // 1. Form Section
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(10);

        this.regIdField = new TextField();
        this.regIdField.setPromptText("Registration ID");
        this.regIdField.setEditable(false);

        this.eventIdField = new TextField();
        this.eventIdField.setPromptText("Event ID");

        this.participantIdField = new TextField();
        this.participantIdField.setPromptText("Participant ID");

        this.statusCombo = new ComboBox<>(FXCollections.observableArrayList(
                "CONFIRMED", "WAITLISTED", "CANCELLED"));
        this.statusCombo.setPromptText("Status");

        this.datePicker = new DatePicker();
        this.datePicker.setPromptText("Registration Date");

        this.feedbackLabel = new Label();

        formGrid.add(new Label("Registration ID:"), 0, 0);
        formGrid.add(regIdField, 1, 0);
        formGrid.add(new Label("Event ID:"), 2, 0);
        formGrid.add(eventIdField, 3, 0);

        formGrid.add(new Label("Participant ID:"), 0, 1);
        formGrid.add(participantIdField, 1, 1);
        formGrid.add(new Label("Status:"), 2, 1);
        formGrid.add(statusCombo, 3, 1);

        formGrid.add(new Label("Registration Date:"), 0, 2);
        formGrid.add(datePicker, 1, 2);

        // 2. Buttons Section
        HBox buttonBox = new HBox(10);
        Button addBtn = new Button("Add Registration");
        Button updateBtn = new Button("Update Registration");
        Button cancelBtn = new Button("Cancel Registration");
        Button clearBtn = new Button("Clear");

        buttonBox.getChildren().addAll(addBtn, updateBtn, cancelBtn, clearBtn);

        // 3. Table Section
        this.table = new TableView<>();

        TableColumn<Registration, Integer> idCol = new TableColumn<>("Registration ID");
        idCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getRegistrationId()));

        TableColumn<Registration, Integer> eventIdCol = new TableColumn<>("Event ID");
        eventIdCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getEventId()));

        TableColumn<Registration, Integer> participantIdCol = new TableColumn<>("Participant ID");
        participantIdCol
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getParticipantId()));

        TableColumn<Registration, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getRegistrationStatus()));

        TableColumn<Registration, LocalDate> dateCol = new TableColumn<>("Registration Date");
        dateCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getRegistrationDate()));

        table.getColumns().addAll(idCol, eventIdCol, participantIdCol, statusCol, dateCol);

        table.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                regIdField.setText(String.valueOf(selected.getRegistrationId()));
                eventIdField.setText(String.valueOf(selected.getEventId()));
                participantIdField.setText(String.valueOf(selected.getParticipantId()));
                statusCombo.setValue(selected.getRegistrationStatus());
                datePicker.setValue(selected.getRegistrationDate());
            }
        });

        addBtn.setOnAction(e -> {
            int eventId = parseInteger(eventIdField.getText());
            int participantId = parseInteger(participantIdField.getText());

            if (!hasValidLinkedIds(eventId, participantId)) {
                feedbackLabel.setText("Enter existing Event ID and Participant ID before adding.");
                return;
            }

            Registration registration = new Registration();
            registration.setEventId(eventId);
            registration.setParticipantId(participantId);
            registration.setRegistrationStatus(statusCombo.getValue());
            registration.setRegistrationDate(datePicker.getValue());

            registrationService.addRegistration(registration);
            refreshTable();
            clearForm();
        });

        updateBtn.setOnAction(e -> {
            Registration selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                int eventId = parseInteger(eventIdField.getText());
                int participantId = parseInteger(participantIdField.getText());

                if (!hasValidLinkedIds(eventId, participantId)) {
                    feedbackLabel.setText("Enter existing Event ID and Participant ID before updating.");
                    return;
                }

                Registration updatedRegistration = new Registration();
                updatedRegistration.setRegistrationId(selected.getRegistrationId());
                updatedRegistration.setEventId(eventId);
                updatedRegistration.setParticipantId(participantId);
                updatedRegistration.setRegistrationStatus(statusCombo.getValue());
                updatedRegistration.setRegistrationDate(datePicker.getValue());

                registrationService.updateRegistration(updatedRegistration);
                refreshTable();
                clearForm();
            }
        });

        cancelBtn.setOnAction(e -> {
            Registration selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                registrationService.cancelRegistration(selected.getRegistrationId());
                refreshTable();
                clearForm();
            }
        });

        clearBtn.setOnAction(e -> clearForm());

        this.root.getChildren().addAll(titleLabel, formGrid, buttonBox, feedbackLabel, table);

        refreshTable();
        clearForm();
    }

    private boolean hasValidLinkedIds(int eventId, int participantId) {
        return eventId > 0
                && participantId > 0
                && eventService.findEventById(eventId) != null
                && participantService.findParticipantById(participantId) != null;
    }

    private void refreshTable() {
        table.setItems(FXCollections.observableArrayList(registrationService.getAllRegistrations()));
    }

    private void clearForm() {
        table.getSelectionModel().clearSelection();
        regIdField.setText(String.valueOf(registrationService.getAllRegistrations().size() + 1));
        eventIdField.setText("");
        participantIdField.setText("");
        statusCombo.getSelectionModel().clearSelection();
        datePicker.setValue(null);
        feedbackLabel.setText("");
    }

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public Parent getView() {
        return root;
    }
}
