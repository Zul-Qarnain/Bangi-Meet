module com.meetingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens com.meetingapp.controllers to javafx.fxml;
    exports com.meetingapp;
    exports com.meetingapp.controllers;
    exports com.meetingapp.models;
}