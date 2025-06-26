package com.meetingapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.meetingapp.controllers.DashboardController; // Import the controller

public class MainApp extends Application {

    private Stage primaryStage; // Keep a reference to the primary stage

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Connect Meeting App"); // Initial title

        // --- TEMPORARY: Load Dashboard directly for testing ---
        showDashboardScreen(null); // Pass null user for now

        // --- In a real app, you'd load splash or login first ---
        // showSplashScreen();
        // showLoginScreen();
    }

    public void showDashboardScreen(com.meetingapp.models.User loggedInUser) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
            Parent dashboardRoot = loader.load();

            DashboardController controller = loader.getController();
            // Pass the logged-in user data (replace with actual user later)
            controller.setCurrentUser(loggedInUser);
            // If using MeetingItemController, you might need to pass the DashboardController instance to it here
            // controller.setDashboardController(this); // Example if DashboardController needs MainApp ref

            Scene dashboardScene = new Scene(dashboardRoot, 800, 600); // Set your desired window size
            // Link the CSS file
            dashboardScene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

            // Set the scene on the primary stage
            primaryStage.setScene(dashboardScene);
            primaryStage.setTitle("Connect - Dashboard"); // Update title
            primaryStage.show();

        } catch (Exception e) { // Use Exception for broader catch during development
            e.printStackTrace();
            // TODO: Show a user-friendly error message or log the error
        }
    }

    // You would add methods for showing other screens (splash, login, meeting) here

    public static void main(String[] args) {
        launch(args);
    }
}