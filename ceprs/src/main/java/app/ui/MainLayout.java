package app.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainLayout {

    private final BorderPane root;
    private final VBox sidebar;
    private final HBox header;
    private final StackPane contentArea;

    public MainLayout() {
        this.root = new BorderPane();

        // 1. Header (Top)
        this.header = new HBox();
        this.header.setPadding(new Insets(15));
        this.header.setStyle("-fx-background-color: #2c3e50;");
        Label titleLabel = new Label("Campus Event Planning and Registration System");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        this.header.getChildren().add(titleLabel);

        // 2. Sidebar (Left)
        this.sidebar = new VBox();
        this.sidebar.setPadding(new Insets(15));
        this.sidebar.setSpacing(10);
        this.sidebar.setStyle("-fx-background-color: #ecf0f1;");

        Label navLabel = new Label("Navigation");
        navLabel.setFont(Font.font("System", FontWeight.BOLD, 14));

        Button dashboardBtn = new Button("Dashboard");
        Button eventsBtn = new Button("Events");
        Button participantsBtn = new Button("Participants");
        Button registrationsBtn = new Button("Registrations");
        Button attendanceBtn = new Button("Attendance");
        Button searchReportsBtn = new Button("Search & Reports");

        // Stretch buttons to fill width
        dashboardBtn.setMaxWidth(Double.MAX_VALUE);
        eventsBtn.setMaxWidth(Double.MAX_VALUE);
        participantsBtn.setMaxWidth(Double.MAX_VALUE);
        registrationsBtn.setMaxWidth(Double.MAX_VALUE);
        attendanceBtn.setMaxWidth(Double.MAX_VALUE);
        searchReportsBtn.setMaxWidth(Double.MAX_VALUE);

        this.sidebar.getChildren().addAll(
                navLabel,
                dashboardBtn,
                eventsBtn,
                participantsBtn,
                registrationsBtn,
                attendanceBtn,
                searchReportsBtn);

        // 3. Content Area (Center)
        this.contentArea = new StackPane();
        this.contentArea.setPadding(new Insets(20));
        Label contentLabel = new Label("Main content area");
        contentLabel.setFont(Font.font("System", 16));
        this.contentArea.getChildren().add(contentLabel);

        // Assemble the BorderPane
        this.root.setTop(this.header);
        this.root.setLeft(this.sidebar);
        this.root.setCenter(this.contentArea);
    }

    public BorderPane getRoot() {
        return root;
    }
}
