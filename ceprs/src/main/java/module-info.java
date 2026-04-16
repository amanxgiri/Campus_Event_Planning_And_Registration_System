module com.giri.events {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.giri.events to javafx.fxml;
    exports com.giri.events;
}
