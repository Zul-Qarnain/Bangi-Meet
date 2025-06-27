package com.meetingapp.controllers;

import com.meetingapp.MainApp; // You'll need a way to reference your main application
import com.meetingapp.models.User; // Your User model
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox; // Used for tabs and error area
import javafx.scene.layout.Region; // Used for tab indicator
import javafx.scene.layout.VBox; // Used for form containers
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField emailField; // fx:id="emailField"
    @FXML
    private PasswordField passwordField; // fx:id="passwordField"
    @FXML
    private Button signInButton; // fx:id="signInButton"
    @FXML
    private Hyperlink forgotPasswordLink; // fx:id="forgotPasswordLink"
    @FXML
    private Button googleSignInButton; // fx:id="googleSignInButton"
    @FXML
    private Button microsoftSignInButton; // fx:id="microsoftSignInButton"

    @FXML
    private HBox errorMessageArea; // fx:id="errorMessageArea" - container for error message
    @FXML
    private Label errorMessageLabel; // fx:id="errorMessageLabel" - the text itself

    // Tab related elements
    @FXML
    private Label signInTabLabel; // fx:id="signInTabLabel"
    @FXML
    private Label signUpTabLabel; // fx:id="signUpTabLabel"
    @FXML
    private Region signInIndicator; // fx:id="signInIndicator"
    @FXML
    private Region signUpIndicator; // fx:id="signUpIndicator"
    @FXML
    private VBox signInFormPane; // fx:id="signInFormPane"
    @FXML
    private VBox signUpFormPane; // fx:id="signUpFormPane"


    // Reference to the main application or a navigation service
    private MainApp mainApp;

    // Method to set the main application reference (call this after loading FXML)
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Hide the error message area initially
        hideErrorMessage();

        // Set initial tab state (Sign In active)
        setTabActive(signInTabLabel, signInIndicator, true);
        setTabActive(signUpTabLabel, signUpIndicator, false);
        signInFormPane.setVisible(true);
        signInFormPane.setManaged(true);
        signUpFormPane.setVisible(false);
        signUpFormPane.setManaged(false);

        // Add event listeners for tab clicks (already defined in FXML using onMouseClicked)
        // But you might need to add them programmatically if FXML onMouseClicked isn't sufficient
    }

    @FXML
    private void handleSignIn() {
        String email = emailField.getText();
        String password = passwordField.getText();

        System.out.println("Attempting Sign In with Email: " + email);

        // Basic validation
        if (email.isEmpty() || password.isEmpty()) {
            showErrorMessage("Please enter both email and password.");
            return;
        }

        // TODO: Implement actual authentication logic
        // Call your API or authentication service here
        boolean isAuthenticated = performAuthentication(email, password); // Dummy method

        if (isAuthenticated) {
            hideErrorMessage();
            System.out.println("Sign In successful!");
            // TODO: Fetch user data
            User loggedInUser = new User(email); // Dummy user
            // Navigate to the Dashboard screen
            if (mainApp != null) {
                mainApp.showDashboardScreen(loggedInUser); // Assuming MainApp has this method
            }
        } else {
            showErrorMessage("Invalid email or password. Please try again.");
            System.out.println("Sign In failed.");
        }
    }

    @FXML
    private void handleForgotPassword() {
        System.out.println("Forgot Password link clicked");
        // TODO: Implement forgot password flow (show dialog, navigate to reset page)
    }

    @FXML
    private void handleGoogleSignIn() {
        System.out.println("Sign in with Google button clicked");
        // TODO: Implement Google Sign-In flow (requires external libraries/API calls)
    }

    @FXML
    private void handleMicrosoftSignIn() {
        System.out.println("Sign in with Microsoft button clicked");
        // TODO: Implement Microsoft Sign-In flow (requires external libraries/API calls)
    }

    @FXML
    private void handleHeaderSignUpLink() {
        System.out.println("Header Sign Up link clicked");
        // If the header link should switch tabs, call the tab handler
        handleSignUpTabClick();
    }

    @FXML
    private void handleSignInTabClick() {
        System.out.println("Sign In tab clicked");
        setTabActive(signInTabLabel, signInIndicator, true);
        setTabActive(signUpTabLabel, signUpIndicator, false);
        signInFormPane.setVisible(true);
        signInFormPane.setManaged(true); // Managed ensures it takes up space
        signUpFormPane.setVisible(false);
        signUpFormPane.setManaged(false); // Managed ensures it does NOT take up space
    }

    @FXML
    private void handleSignUpTabClick() {
        System.out.println("Sign Up tab clicked");
        setTabActive(signInTabLabel, signInIndicator, false);
        setTabActive(signUpTabLabel, signUpIndicator, true);
        signInFormPane.setVisible(false);
        signInFormPane.setManaged(false);
        signUpFormPane.setVisible(true);
        signUpFormPane.setManaged(true);
    }


    // Helper methods for showing/hiding error message
    private void showErrorMessage(String message) {
        errorMessageLabel.setText(message);
        errorMessageArea.setVisible(true);
        errorMessageArea.setManaged(true); // Make it visible and take up layout space
        // Optional: Add animation or styling changes
    }

    private void hideErrorMessage() {
        errorMessageLabel.setText("");
        errorMessageArea.setVisible(false);
        errorMessageArea.setManaged(false); // Hide it and remove from layout calculations
    }

    // Helper method to set tab active state using CSS classes
    private void setTabActive(Label tabLabel, Region indicator, boolean isActive) {
        if (isActive) {
            tabLabel.getStyleClass().add("active-tab");
            indicator.getStyleClass().add("active-indicator");
            indicator.getStyleClass().remove("inactive-indicator");
        } else {
            tabLabel.getStyleClass().remove("active-tab");
            indicator.getStyleClass().remove("active-indicator");
            indicator.getStyleClass().add("inactive-indicator");
        }
    }


    // Dummy authentication method (replace with real logic)
    private boolean performAuthentication(String email, String password) {
        // This is a placeholder. Connect this to your Node.js backend later.
        // For now, just check for a hardcoded user or always return true/false.
        // Example:
        return "test@example.com".equals(email) && "password123".equals(password);
        // Or for initial testing: return true; // Always successful
        // Or for initial testing: return false; // Always failed
    }
}