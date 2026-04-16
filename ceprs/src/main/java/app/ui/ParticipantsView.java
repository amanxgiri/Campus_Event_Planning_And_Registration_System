package app.ui;

import app.model.Participant;
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

    public ParticipantsView() {
        this.root = new VBox(20);
        this.root.setPadding(new Insets(20));

        Label titleLabel = new Label("Participants");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        // 1. Form Section
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(10);

        TextField idField = new TextField();
        idField.setPromptText("Participant ID");
        idField.setEditable(false);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");

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
        TableView<Participant> table = new TableView<>();

        TableColumn<Participant, Integer> idCol = new TableColumn<>("Participant ID");
        TableColumn<Participant, String> nameCol = new TableColumn<>("Name");
        TableColumn<Participant, String> emailCol = new TableColumn<>("Email");
        TableColumn<Participant, String> phoneCol = new TableColumn<>("Phone");

        table.getColumns().addAll(idCol, nameCol, emailCol, phoneCol);

        this.root.getChildren().addAll(titleLabel, formGrid, buttonBox, table);
    }

    public Parent getView() {
        return root;
    }
}
