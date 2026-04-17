package app.ui;

import app.service.DashboardService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DashboardView {

    private final VBox root;
    private final DashboardService dashboardService;

    public DashboardView(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
        this.root = new VBox(20);
        this.root.setPadding(new Insets(20));
        // Optional light styling for the background of the view
        this.root.setStyle("-fx-background-color: #f4f6f6;");

        // Page title
        Label titleLabel = new Label("Dashboard");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.web("#2c3e50"));

        // Cards container
        FlowPane cardsContainer = new FlowPane(15, 15);

        cardsContainer.getChildren().addAll(
                createStatCard("Total Events", String.valueOf(this.dashboardService.getTotalEvents())),
                createStatCard("Total Participants", String.valueOf(this.dashboardService.getTotalParticipants())),
                createStatCard("Confirmed Registrations",
                        String.valueOf(this.dashboardService.getConfirmedRegistrationsCount())),
                createStatCard("Waitlisted Participants",
                        String.valueOf(this.dashboardService.getWaitlistedParticipantsCount())),
                createStatCard("Upcoming Events", String.valueOf(this.dashboardService.getUpcomingEventsCount())),
                createStatCard("Venue Conflicts", String.valueOf(this.dashboardService.getVenueConflictsCount())));

        this.root.getChildren().addAll(titleLabel, cardsContainer);
    }

    private VBox createStatCard(String title, String value) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.CENTER);

        // Simple card styling with a white background and subtle drop shadow
        card.setStyle("-fx-background-color: white; "
                + "-fx-background-radius: 5; "
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");

        card.setPrefSize(200, 100);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("System", 14));
        titleLabel.setTextFill(Color.web("#7f8c8d"));

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("System", FontWeight.BOLD, 28));
        valueLabel.setTextFill(Color.web("#2c3e50"));

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }

    public Parent getView() {
        return root;
    }
}
