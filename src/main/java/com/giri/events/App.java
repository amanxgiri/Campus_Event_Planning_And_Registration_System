package com.giri.events;

import app.ui.MainLayout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MainLayout mainLayout = new MainLayout();
        Scene scene = new Scene(mainLayout.getRoot(), 1000, 700);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> mainLayout.getFileService().saveData());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
