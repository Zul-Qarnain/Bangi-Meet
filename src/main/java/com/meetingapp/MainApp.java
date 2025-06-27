package com.meetingapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import com.meetingapp.controllers.DashboardController;
import com.meetingapp.controllers.LoginController;
import com.meetingapp.models.User; // Assuming User model exists

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private static final int SPLASH_DURATION_MS = 5000; // 5 seconds minimum splash duration

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Connect Meeting App"); // Initial title

        // Initially show the Splash screen
        showSplashScreen();
    }

    /**
     * Loads and displays the Splash screen.
     */
    public void showSplashScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/splash.fxml"));
            Parent splashRoot = loader.load();

            // SplashController controller = loader.getController();
            // If SplashController had methods to update UI, get controller here

            // Set scene size (optional, can let it fit content)
            Scene splashScene = new Scene(splashRoot, 800, 600);
            splashScene.getStylesheets().add(MainApp.class.getResource("/css/style.css").toExternalForm());

            primaryStage.setScene(splashScene);
            // Splash screen typically doesn't have a window title visible
            // primaryStage.setTitle("Loading..."); // Optional title during splash
            primaryStage.show();

            // Create a timer to switch to the login screen after SPLASH_DURATION_MS
            Timeline splashTimer = new Timeline(new KeyFrame(
                    Duration.millis(SPLASH_DURATION_MS),
                    event -> showLoginScreen() // Call showLoginScreen after duration
            ));
            splashTimer.play();

        } catch (IOException e) {
            System.err.println("Error loading splash screen: ");
            e.printStackTrace();
            // If splash fails, try to load login directly or show critical error
            showLoginScreen(); // Fallback to login if splash fails
        }
    }

    /**
     * Loads and displays the Login screen.
     */
    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/login.fxml"));
            Parent loginRoot = loader.load();

            LoginController controller = loader.getController();
            controller.setMainApp(this); // Pass reference to MainApp

            // Set preferred window size for the login screen
            Scene loginScene = new Scene(loginRoot, 800, 600);
            // Link the CSS file
            loginScene.getStylesheets().add(MainApp.class.getResource("/css/style.css").toExternalForm());

            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Connect - Sign In"); // Update window title
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Error loading login screen: ");
            e.printStackTrace();
            // TODO: Show error dialog and possibly exit
        }
    }


    /**
     * Loads and displays the Dashboard screen.
     * @param loggedInUser The user who successfully logged in.
     */
    public void showDashboardScreen(User loggedInUser) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/dashboard.fxml"));
            Parent dashboardRoot = loader.load();

            DashboardController controller = loader.getController();
            controller.setCurrentUser(loggedInUser); // Pass the logged-in user

            // Set preferred window size for the dashboard screen
            Scene dashboardScene = new Scene(dashboardRoot, 1000, 800);
            // Link the CSS file
            dashboardScene.getStylesheets().add(MainApp.class.getResource("/css/style.css").toExternalForm());

            primaryStage.setScene(dashboardScene);
            primaryStage.setTitle("Connect - Dashboard"); // Update window title
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Error loading dashboard screen: ");
            e.printStackTrace();
            // TODO: Show error dialog
        }
    }

    // TODO: Add methods to show other screens (e.g., Meeting, Settings)


    public static void main(String[] args) {
        launch(args);
    }

    // Dummy User class definition if it doesn't exist in models/User.java
    // Remove this if you have a separate User.java file
    /*
    public static class User {
        private String email;
        private String id;
        private String name;
        // Add other user properties

        public User(String id, String email, String name) {
            this.id = id;
            this.email = email;
            this.name = name;
        }

        public String getId() { return id; }
        public String getEmail() { return email; }
        public String getName() { return name; }
        // Add getters for other properties
    }
    */
}