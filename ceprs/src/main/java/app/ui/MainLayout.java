package app.ui;

import app.service.DashboardService;
import app.service.EventService;
import app.service.ParticipantService;
import app.service.RegistrationService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
    private final EventService eventService = new EventService();
    private final ParticipantService participantService = new ParticipantService();
    private final RegistrationService registrationService = new RegistrationService();
    private final DashboardService dashboardService = new DashboardService(
            eventService,
            participantService,
            registrationService);

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

        DashboardView dashboardView = new DashboardView(dashboardService);
        setContent(dashboardView.getView());

        // Button Actions
        dashboardBtn.setOnAction(e -> setContent(new DashboardView(dashboardService).getView()));
        eventsBtn.setOnAction(e -> setContent(new EventsView(eventService).getView()));
        participantsBtn.setOnAction(e -> setContent(new ParticipantsView(participantService).getView()));
        registrationsBtn
                .setOnAction(e -> setContent(
                        new RegistrationsView(eventService, participantService, registrationService).getView()));
        attendanceBtn.setOnAction(e -> setContent(createPlaceholderView("Attendance")));
        searchReportsBtn.setOnAction(e -> setContent(createPlaceholderView("Search & Reports")));

        // Assemble the BorderPane
        this.root.setTop(this.header);
        this.root.setLeft(this.sidebar);
        this.root.setCenter(this.contentArea);
    }

    private void setContent(Parent view) {
        this.contentArea.getChildren().setAll(view);
    }

    private Parent createPlaceholderView(String title) {
        VBox placeholder = new VBox(20);
        placeholder.setAlignment(Pos.CENTER);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.web("#2c3e50"));

        Label subtitleLabel = new Label("This section is under construction");
        subtitleLabel.setFont(Font.font("System", 16));
        subtitleLabel.setTextFill(Color.web("#7f8c8d"));

        placeholder.getChildren().addAll(titleLabel, subtitleLabel);
        return placeholder;
    }

    public BorderPane getRoot() {
        return root;
    }
}
