package com.meetingapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.meetingapp.controllers.DashboardController;
import com.meetingapp.controllers.LoginController; // Import LoginController
import com.meetingapp.models.User;

import java.io.IOException; // Import for catching IOException

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) { // Use throws IOException or catch it
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Connect Meeting App");

        // Start with the Login screen
        showLoginScreen();
    }

    /**
     * Loads and displays the Login screen.
     */
    public void showLoginScreen() {
        try {
            // Fixed resource loading using class loader
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/login.fxml"));
            Parent loginRoot = loader.load();

            LoginController controller = loader.getController();
            controller.setMainApp(this); // Pass reference to MainApp

            Scene loginScene = new Scene(loginRoot, 800, 600); // Set initial window size
            // Fixed CSS loading path
            loginScene.getStylesheets().add(MainApp.class.getResource("/css/style.css").toExternalForm());

            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Connect - Sign In"); // Update window title
            primaryStage.show();

        } catch (IOException e) { // Catch IOException specifically
            System.err.println("Error loading login screen: ");
            e.printStackTrace();
            // Handle the error, e.g., show an error dialog and exit
        }
    }


    /**
     * Loads and displays the Dashboard screen.
     * @param loggedInUser The user who successfully logged in.
     */
    public void showDashboardScreen(User loggedInUser) {
        try {
            // Fixed resource loading using class loader
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/dashboard.fxml"));
            Parent dashboardRoot = loader.load();

            DashboardController controller = loader.getController();
            controller.setCurrentUser(loggedInUser); // Pass the logged-in user

            Scene dashboardScene = new Scene(dashboardRoot, 1000, 800); // Adjust size for dashboard
            // Fixed CSS loading path
            dashboardScene.getStylesheets().add(MainApp.class.getResource("/css/style.css").toExternalForm());

            primaryStage.setScene(dashboardScene);
            primaryStage.setTitle("Connect - Dashboard"); // Update window title
            primaryStage.show();

        } catch (IOException e) { // Catch IOException specifically
            System.err.println("Error loading dashboard screen: ");
            e.printStackTrace();
            // Handle the error
        }
    }

    // TODO: Add methods to show other screens (e.g., Meeting, Settings)


    public static void main(String[] args) {
        launch(args);
    }
}