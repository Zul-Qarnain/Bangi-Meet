package com.meetingapp.controllers;

import com.meetingapp.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML private ImageView userAvatar;
    @FXML private VBox upcomingMeetingsContainer;

    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("DashboardController initialized");
        loadUpcomingMeetings();
    }

    @FXML private void handleCreateNewMeeting() {
        System.out.println("Create New Meeting button clicked");
    }

    @FXML private void handleJoinMeeting() {
        System.out.println("Join a Meeting button clicked");
    }

    public void handleJoinUpcomingMeeting(String meetingId) {
        System.out.println("Joining upcoming meeting with ID: " + meetingId);
        // TODO: Implement actual meeting joining logic here
        // This could involve:
        // - Opening a meeting window
        // - Connecting to a video call service
        // - Launching a browser with meeting URL
    }

    @FXML private void handleProfile() {
        System.out.println("Profile menu clicked");
        // TODO: Open profile window
    }

    @FXML private void handleSettings() {
        System.out.println("Settings menu clicked");
        // TODO: Open settings window
    }

    @FXML private void handleLogout() {
        System.out.println("Logout clicked");
        // TODO: Implement logout logic
    }

    // Other handler methods...

    private void loadUpcomingMeetings() {
        List<Meeting> meetings = getMeetingsFromService();
        upcomingMeetingsContainer.getChildren().clear();

        for (Meeting meeting : meetings) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/meeting_item.fxml"));
                Parent meetingItemNode = loader.load();
                MeetingItemController controller = loader.getController();
                controller.setMeetingData(meeting);
                upcomingMeetingsContainer.getChildren().add(meetingItemNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Meeting> getMeetingsFromService() {
        return List.of(
                new Meeting("1", "Team Sync", "10:00 AM"),
                new Meeting("2", "Project Review", "11:30 AM"),
                new Meeting("3", "Client Meeting", "2:00 PM")
        );
    }

    public static class Meeting {
        private final String id;
        private final String title;
        private final String time;

        public Meeting(String id, String title, String time) {
            this.id = id;
            this.title = title;
            this.time = time;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getTime() {
            return time;
        }
    }
}