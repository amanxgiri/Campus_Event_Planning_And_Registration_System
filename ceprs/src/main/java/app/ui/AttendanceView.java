package app.ui;

import app.model.AttendanceRecord;
import app.service.AttendanceService;
import app.service.RegistrationService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AttendanceView {

    private final VBox root;
    private final AttendanceService attendanceService;
    private final RegistrationService registrationService;

    private final TextField attendanceIdField;
    private final TextField eventIdField;
    private final TextField participantIdField;
    private final ComboBox<String> statusCombo;
    private final Label feedbackLabel;
    private final TableView<AttendanceRecord> table;

    public AttendanceView(AttendanceService attendanceService, RegistrationService registrationService) {
        this.attendanceService = attendanceService;
        this.registrationService = registrationService;
        this.root = new VBox(20);
        this.root.setPadding(new Insets(20));

        Label titleLabel = new Label("Attendance");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(10);

        this.attendanceIdField = new TextField();
        this.attendanceIdField.setPromptText("Attendance ID");
        this.attendanceIdField.setEditable(false);

        this.eventIdField = new TextField();
        this.eventIdField.setPromptText("Event ID");

        this.participantIdField = new TextField();
        this.participantIdField.setPromptText("Participant ID");

        this.statusCombo = new ComboBox<>(FXCollections.observableArrayList(
                "PRESENT", "ABSENT"));
        this.statusCombo.setPromptText("Attendance Status");

        this.feedbackLabel = new Label();

        formGrid.add(new Label("Attendance ID:"), 0, 0);
        formGrid.add(attendanceIdField, 1, 0);
        formGrid.add(new Label("Event ID:"), 2, 0);
        formGrid.add(eventIdField, 3, 0);

        formGrid.add(new Label("Participant ID:"), 0, 1);
        formGrid.add(participantIdField, 1, 1);
        formGrid.add(new Label("Status:"), 2, 1);
        formGrid.add(statusCombo, 3, 1);

        HBox buttonBox = new HBox(10);
        Button markBtn = new Button("Mark Attendance");
        Button clearBtn = new Button("Clear");
        buttonBox.getChildren().addAll(markBtn, clearBtn);

        this.table = new TableView<>();

        TableColumn<AttendanceRecord, Integer> idCol = new TableColumn<>("Attendance ID");
        idCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAttendanceId()));

        TableColumn<AttendanceRecord, Integer> eventIdCol = new TableColumn<>("Event ID");
        eventIdCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getEventId()));

        TableColumn<AttendanceRecord, Integer> participantIdCol = new TableColumn<>("Participant ID");
        participantIdCol
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getParticipantId()));

        TableColumn<AttendanceRecord, String> statusCol = new TableColumn<>("Attendance Status");
        statusCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAttendanceStatus()));

        table.getColumns().addAll(idCol, eventIdCol, participantIdCol, statusCol);

        table.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                attendanceIdField.setText(String.valueOf(selected.getAttendanceId()));
                eventIdField.setText(String.valueOf(selected.getEventId()));
                participantIdField.setText(String.valueOf(selected.getParticipantId()));
                statusCombo.setValue(selected.getAttendanceStatus());
            }
        });

        markBtn.setOnAction(e -> {
            int eventId = parseInteger(eventIdField.getText());
            int participantId = parseInteger(participantIdField.getText());

            if (!hasConfirmedRegistration(eventId, participantId)) {
                feedbackLabel.setText("Mark attendance only for a confirmed registration.");
                return;
            }

            attendanceService.markAttendance(eventId, participantId, statusCombo.getValue());
            refreshTable();
            clearForm();
        });

        clearBtn.setOnAction(e -> clearForm());

        this.root.getChildren().addAll(titleLabel, formGrid, buttonBox, feedbackLabel, table);

        refreshTable();
        clearForm();
    }

    private void refreshTable() {
        table.setItems(FXCollections.observableArrayList(attendanceService.getAllAttendanceRecords()));
    }

    private void clearForm() {
        table.getSelectionModel().clearSelection();
        attendanceIdField.setText(String.valueOf(attendanceService.getAllAttendanceRecords().size() + 1));
        eventIdField.setText("");
        participantIdField.setText("");
        statusCombo.getSelectionModel().clearSelection();
        feedbackLabel.setText("");
    }

    private boolean hasConfirmedRegistration(int eventId, int participantId) {
        return eventId > 0
                && participantId > 0
                && registrationService.hasConfirmedRegistration(eventId, participantId);
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
