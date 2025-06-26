package com.meetingapp.controllers;

import com.meetingapp.controllers.DashboardController.Meeting; // Import the Meeting model
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class MeetingItemController implements Initializable {

    @FXML
    private Label meetingTitleLabel;

    @FXML
    private Label meetingTimeLabel;

    @FXML
    private Button joinButton;

    private Meeting meetingData; // To hold the data for this item

    // Reference back to the DashboardController or a navigation service
    // You could pass this during setup or use a shared context
    private DashboardController dashboardController;

    public void setDashboardController(DashboardController controller) {
        this.dashboardController = controller;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic if needed after FXML load
    }

    // Method to set the data for this meeting item
    public void setMeetingData(Meeting meeting) {
        this.meetingData = meeting;
        meetingTitleLabel.setText(meeting.getTitle());
        meetingTimeLabel.setText(meeting.getTime());

        // You might add a tooltip or other info here
    }

    @FXML
    private void handleJoinMeeting() {
        if (meetingData != null && dashboardController != null) {
            System.out.println("Join button clicked for meeting: " + meetingData.getTitle());
            // Delegate the actual joining logic back to the DashboardController
            dashboardController.handleJoinUpcomingMeeting(meetingData.getId());
        }
    }
}