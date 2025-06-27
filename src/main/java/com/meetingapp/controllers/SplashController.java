package com.meetingapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable {

    @FXML
    private ProgressBar loadingProgressBar; // fx:id="loadingProgressBar"

    @FXML
    private Label loadingMessageLabel; // fx:id="loadingMessageLabel"

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic for the splash screen goes here if needed.
        // For a fixed delay, most logic is in MainApp.
        System.out.println("SplashController initialized.");

        // Example: If you wanted to update the message based on loading steps
        // loadingMessageLabel.setText("Loading modules...");
    }

    // You could add methods here if you needed to update the UI from MainApp
    // public void setMessage(String message) {
    //     loadingMessageLabel.setText(message);
    // }
    // public void setProgress(double progress) { // If using determinate progress
    //      loadingProgressBar.setProgress(progress);
    // }
}