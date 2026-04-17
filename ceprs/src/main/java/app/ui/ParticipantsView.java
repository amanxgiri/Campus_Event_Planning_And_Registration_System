package app.ui;

import app.model.Participant;
import app.service.ParticipantService;
import javafx.collections.FXCollections;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ParticipantsView {

    private final VBox root;
    private final ParticipantService participantService;

    private final TextField idField;
    private final TextField nameField;
    private final TextField emailField;
    private final TextField phoneField;
    private final TableView<Participant> table;

    public ParticipantsView(ParticipantService participantService) {
        this.participantService = participantService;
        this.root = new VBox(20);
        this.root.setPadding(new Insets(20));

        Label titleLabel = new Label("Participants");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        // 1. Form Section
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(10);

        this.idField = new TextField();
        this.idField.setPromptText("Participant ID");
        this.idField.setEditable(false);

        this.nameField = new TextField();
        this.nameField.setPromptText("Name");

        this.emailField = new TextField();
        this.emailField.setPromptText("Email");

        this.phoneField = new TextField();
        this.phoneField.setPromptText("Phone");

        formGrid.add(new Label("Participant ID:"), 0, 0);
        formGrid.add(idField, 1, 0);
        formGrid.add(new Label("Name:"), 2, 0);
        formGrid.add(nameField, 3, 0);

        formGrid.add(new Label("Email:"), 0, 1);
        formGrid.add(emailField, 1, 1);
        formGrid.add(new Label("Phone:"), 2, 1);
        formGrid.add(phoneField, 3, 1);

        // 2. Buttons Section
        HBox buttonBox = new HBox(10);
        Button addBtn = new Button("Add Participant");
        Button updateBtn = new Button("Update Participant");
        Button deleteBtn = new Button("Delete Participant");
        Button clearBtn = new Button("Clear");

        buttonBox.getChildren().addAll(addBtn, updateBtn, deleteBtn, clearBtn);

        // 3. Table Section
        this.table = new TableView<>();

        TableColumn<Participant, Integer> idCol = new TableColumn<>("Participant ID");
        idCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getParticipantId()));

        TableColumn<Participant, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));

        TableColumn<Participant, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmail()));

        TableColumn<Participant, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPhone()));

        table.getColumns().addAll(idCol, nameCol, emailCol, phoneCol);

        // Events
        addBtn.setOnAction(e -> {
            Participant participant = new Participant();
            participant.setParticipantId(participantService.getAllParticipants().size() + 1);
            participant.setName(nameField.getText());
            participant.setEmail(emailField.getText());
            participant.setPhone(phoneField.getText());

            participantService.addParticipant(participant);
            refreshTable();
            clearForm();
        });

        clearBtn.setOnAction(e -> clearForm());

        this.root.getChildren().addAll(titleLabel, formGrid, buttonBox, table);

        refreshTable();
        clearForm();
    }

    private void refreshTable() {
        table.setItems(FXCollections.observableArrayList(participantService.getAllParticipants()));
    }

    private void clearForm() {
        idField.setText(String.valueOf(participantService.getAllParticipants().size() + 1));
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        table.getSelectionModel().clearSelection();
    }

    public Parent getView() {
        return root;
    }
}
