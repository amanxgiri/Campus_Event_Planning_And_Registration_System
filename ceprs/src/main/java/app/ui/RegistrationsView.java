package app.ui;

import app.model.Registration;
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

    public RegistrationsView() {
        this.root = new VBox(20);
        this.root.setPadding(new Insets(20));

        Label titleLabel = new Label("Registrations");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        // 1. Form Section
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(10);

        TextField regIdField = new TextField();
        regIdField.setPromptText("Registration ID");
        regIdField.setEditable(false);

        TextField eventIdField = new TextField();
        eventIdField.setPromptText("Event ID");

        TextField participantIdField = new TextField();
        participantIdField.setPromptText("Participant ID");

        ComboBox<String> statusCombo = new ComboBox<>(FXCollections.observableArrayList(
                "CONFIRMED", "WAITLISTED", "CANCELLED"));
        statusCombo.setPromptText("Status");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Registration Date");

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
        TableView<Registration> table = new TableView<>();

        TableColumn<Registration, Integer> idCol = new TableColumn<>("Registration ID");
        TableColumn<Registration, Integer> eventIdCol = new TableColumn<>("Event ID");
        TableColumn<Registration, Integer> participantIdCol = new TableColumn<>("Participant ID");
        TableColumn<Registration, String> statusCol = new TableColumn<>("Status");
        TableColumn<Registration, LocalDate> dateCol = new TableColumn<>("Registration Date");

        table.getColumns().addAll(idCol, eventIdCol, participantIdCol, statusCol, dateCol);

        this.root.getChildren().addAll(titleLabel, formGrid, buttonBox, table);
    }

    public Parent getView() {
        return root;
    }
}