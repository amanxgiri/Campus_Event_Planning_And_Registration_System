package app.ui;

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

    public DashboardView() {
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
                createStatCard("Total Events", "0"),
                createStatCard("Total Participants", "0"),
                createStatCard("Confirmed Registrations", "0"),
                createStatCard("Waitlisted Participants", "0"),
                createStatCard("Upcoming Events", "0"),
                createStatCard("Venue Conflicts", "0"));

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
