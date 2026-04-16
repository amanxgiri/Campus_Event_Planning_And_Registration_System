package app.ui;

import app.model.Event;
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

public class EventsView {

    private final VBox root;

    public EventsView() {
        this.root = new VBox(20);
        this.root.setPadding(new Insets(20));

        Label titleLabel = new Label("Events");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        // 1. Form Section
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(10);

        TextField idField = new TextField();
        idField.setPromptText("Event ID");
        idField.setEditable(false); // Typically ID is auto-generated

        TextField nameField = new TextField();
        nameField.setPromptText("Event Name");

        ComboBox<String> typeCombo = new ComboBox<>(FXCollections.observableArrayList(
                "WORKSHOP", "SEMINAR", "CULTURAL", "TECHNICAL", "OTHER"));
        typeCombo.setPromptText("Event Type");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Event Date");

        TextField timeField = new TextField();
        timeField.setPromptText("Event Time");

        TextField venueField = new TextField();
        venueField.setPromptText("Venue");

        TextField capacityField = new TextField();
        capacityField.setPromptText("Capacity");

        ComboBox<String> statusCombo = new ComboBox<>(FXCollections.observableArrayList(
                "UPCOMING", "ONGOING", "COMPLETED"));
        statusCombo.setPromptText("Status");

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
        TableView<Event> table = new TableView<>();

        TableColumn<Event, String> idCol = new TableColumn<>("Event ID");
        TableColumn<Event, String> nameCol = new TableColumn<>("Event Name");
        TableColumn<Event, String> typeCol = new TableColumn<>("Event Type");
        TableColumn<Event, String> dateCol = new TableColumn<>("Event Date");
        TableColumn<Event, String> timeCol = new TableColumn<>("Event Time");
        TableColumn<Event, String> venueCol = new TableColumn<>("Venue");
        TableColumn<Event, String> capCol = new TableColumn<>("Capacity");
        TableColumn<Event, String> regCol = new TableColumn<>("Registered");
        TableColumn<Event, String> waitCol = new TableColumn<>("Waitlist");
        TableColumn<Event, String> statCol = new TableColumn<>("Status");

        table.getColumns().addAll(idCol, nameCol, typeCol, dateCol, timeCol, venueCol, capCol, regCol, waitCol,
                statCol);

        // Assemble everything
        this.root.getChildren().addAll(titleLabel, formGrid, buttonBox, table);
    }

    public Parent getView() {
        return root;
    }
}
