package com.meetingapp.controllers;

import com.meetingapp.models.User; // Assuming you have a User model
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem; // For context menu items
import javafx.scene.control.ContextMenu; // For the context menu
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox; // Container for upcoming meetings
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List; // To hold meeting data
// Import for dynamically loading meeting items (if using separate FXML)
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;


public class DashboardController implements Initializable {

    @FXML
    private ImageView userAvatar; // Link to the user avatar image view

    @FXML
    private VBox upcomingMeetingsContainer; // Link to the container for upcoming meetings

    // You might add FXML fields for other elements if you need to manipulate them in code
    // @FXML
    // private Button createMeetingButton;
    // @FXML
    // private Button joinMeetingButton;

    private User currentUser; // Hold the logged-in user data

    // Method to set the current user (called by MainApp after login)
    public void setCurrentUser(User user) {
        this.currentUser = user;
        // Update UI elements based on user data, e.g., load user's avatar
        // if (user != null && user.getAvatarUrl() != null) {
        //     Image avatarImage = new Image(user.getAvatarUrl()); // Or load from local path
        //     userAvatar.setImage(avatarImage);
        // }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic here
        // This method is called after all FXML elements are injected
        System.out.println("DashboardController initialized");

        // Example: Load dummy upcoming meetings
        loadUpcomingMeetings();
    }

    @FXML
    private void handleCreateNewMeeting() {
        System.out.println("Create New Meeting button clicked");
        // TODO: Implement logic to start or schedule a new meeting
        // This would likely involve showing a dialog or navigating to a meeting setup screen
    }

    @FXML
    private void handleJoinMeeting() {
        System.out.println("Join a Meeting button clicked");
        // TODO: Implement logic to show a dialog for entering a meeting code/link
        // Then navigate to the Meeting screen (using WebView)
    }

    // Example handler for a specific upcoming meeting item's Join button (if not using a separate controller)
    // If using a separate controller per item, this logic belongs there.
    public void handleJoinUpcomingMeeting(String meetingId) {
        System.out.println("Joining upcoming meeting: " + meetingId);
        // TODO: Navigate to the Meeting screen (using WebView) with the meetingId
        // You'd likely need a reference back to MainApp or a navigation service
    }

    @FXML
    private void handleSettings() {
        System.out.println("Settings button clicked");
        // TODO: Navigate to settings screen or open settings dialog
    }

    @FXML
    private void handleProfile() {
        System.out.println("Profile menu item clicked");
        // TODO: Navigate to profile screen
    }

    @FXML
    private void handleLogout() {
        System.out.println("Logout menu item clicked");
        // TODO: Handle logout logic (clear session, navigate back to Login screen)
        // This would likely involve calling a method in MainApp
    }


    // Method to load and display upcoming meetings dynamically
    private void loadUpcomingMeetings() {
        // In a real app, fetch meeting data from a service/API
        List<Meeting> meetings = getMeetingsFromService(); // Dummy method

        upcomingMeetingsContainer.getChildren().clear(); // Clear existing items

        if (meetings != null) {
            for (Meeting meeting : meetings) {
                try {
                    // Load the meeting_item.fxml for each meeting
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/meeting_item.fxml"));
                    Parent meetingItemNode = loader.load();

                    // Get the controller for the meeting item
                    MeetingItemController itemController = loader.getController();

                    // Pass data to the meeting item controller
                    itemController.setMeetingData(meeting);

                    // Add the loaded meeting item UI to the container
                    upcomingMeetingsContainer.getChildren().add(meetingItemNode);

                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle error loading meeting item UI
                }
            }
        }
    }

    // Dummy method to simulate fetching meeting data
    private List<Meeting> getMeetingsFromService() {
        // Replace with actual data fetching logic
        return List.of(
                new Meeting("1", "Team Sync", "10:00 AM - 11:00 AM"),
                new Meeting("2", "Project Review", "11:30 AM - 12:30 PM"),
                new Meeting("3", "Client Meeting", "2:00 PM - 3:00 PM")
        );
    }

    // Dummy Meeting class (replace with your actual model)
    public static class Meeting {
        String id;
        String title;
        String time;

        public Meeting(String id, String title, String time) {
            this.id = id;
            this.title = title;
            this.time = time;
        }

        public String getId() { return id; }
        public String getTitle() { return title; }
        public String getTime() { return time; }
    }
}