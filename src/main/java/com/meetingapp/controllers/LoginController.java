package com.meetingapp.controllers;

import com.meetingapp.MainApp; // You'll need a way to reference your main application
import com.meetingapp.models.User; // Your User model
import javafx.animation.TranslateTransition; // Example animation import
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
import javafx.util.Duration; // Example animation import

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List; // Example for meeting list in dashboard


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

    // Sign Up form fields (Add @FXML for each if you include them in FXML)
    @FXML private TextField signUpFullNameField; // Assuming fx:id="signUpFullNameField"
    @FXML private TextField signUpEmailField; // Assuming fx:id="signUpEmailField"
    @FXML private PasswordField signUpPasswordField; // Assuming fx:id="signUpPasswordField"
    @FXML private PasswordField signUpConfirmPasswordField; // Assuming fx:id="signUpConfirmPasswordField"
    @FXML private Button signUpButton; // Assuming fx:id="signUpButton"
    // If social buttons are duplicated in signup form:
    // @FXML private Button googleSignUpButton; // Assuming fx:id="googleSignUpButton"
    // @FXML private Button microsoftSignUpButton; // Assuming fx:id="microsoftSignUpButton"


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
        // These methods handle setting visibility and styles
        setTabActive(signInTabLabel, signInIndicator, true);
        setTabActive(signUpTabLabel, signUpIndicator, false);

        // Explicitly set initial pane visibility
        signInFormPane.setVisible(true);
        signInFormPane.setManaged(true); // Managed ensures it takes up space
        signUpFormPane.setVisible(false);
        signUpFormPane.setManaged(false); // Managed ensures it does NOT take up space

        // Add event listeners for tab clicks (already defined in FXML using onMouseClicked)
        // Programmatic listeners are an alternative if not using FXML onMouseClicked
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

        // TODO: Implement actual authentication logic (connect to Node.js backend)
        boolean isAuthenticated = performAuthentication(email, password); // Dummy method

        if (isAuthenticated) {
            hideErrorMessage();
            System.out.println("Sign In successful!");
            // TODO: Fetch user data from authentication service response
            // Create a dummy user for now
            User loggedInUser = new User("dummy-id-123", email, "Authenticated User"); // Example User constructor
            // Navigate to the Dashboard screen
            if (mainApp != null) {
                mainApp.showDashboardScreen(loggedInUser); // Assuming MainApp has this method
            }
        } else {
            showErrorMessage("Invalid email or password.");
            System.out.println("Sign In failed.");
        }
    }

    @FXML
    private void handleSignUp() {
        System.out.println("Sign Up button clicked");

        // TODO: Get data from signup fields
        String fullName = signUpFullNameField.getText(); // Assuming fx:id="signUpFullNameField"
        String email = signUpEmailField.getText();     // Assuming fx:id="signUpEmailField"
        String password = signUpPasswordField.getText(); // Assuming fx:id="signUpPasswordField"
        String confirmPassword = signUpConfirmPasswordField.getText(); // Assuming fx:id="signUpConfirmPasswordField"

        System.out.println("Attempting Sign Up with Email: " + email);

        // TODO: Implement basic validation for signup fields (e.g., not empty, passwords match)
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showErrorMessage("Please fill in all sign up fields.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            showErrorMessage("Passwords do not match.");
            return;
        }

        // TODO: Implement actual signup logic (connect to Node.js backend)
        boolean isSignedUp = performSignup(fullName, email, password); // Dummy method

        if (isSignedUp) {
            hideErrorMessage();
            System.out.println("Sign Up successful!");
            // On successful signup, maybe show login tab again with a success message
            handleSignInTabClick();
            // Optional: Show a temporary success message
            // showErrorMessage("Sign Up successful! Please sign in."); // Reusing error area for success is not ideal but possible
        } else {
            // TODO: Handle specific signup errors (e.g., email already exists)
            showErrorMessage("Sign Up failed. Please try again.");
            System.out.println("Sign Up failed.");
        }
    }


    @FXML
    private void handleForgotPassword() {
        System.out.println("Forgot Password link clicked");
        // TODO: Implement forgot password flow (show dialog, navigate to reset page)
    }

    @FXML
    private void handleGoogleSignIn() {
        // TODO: Implement Google Sign-In logic (likely involves WebView/external browser)
        System.out.println("Sign in with Google button clicked");
    }

    @FXML
    private void handleMicrosoftSignIn() {
        System.out.println("Sign in with Microsoft button clicked");
        // TODO: Implement Microsoft Sign-In flow (likely involves WebView/external browser)
    }

    // Handlers for Google/Microsoft Sign Up buttons if implemented
    @FXML
    private void handleGoogleSignUp() {
        System.out.println("Sign up with Google button clicked");
        // TODO: Implement Google Sign-Up flow
    }

    @FXML
    private void handleMicrosoftSignUp() {
        System.out.println("Sign up with Microsoft button clicked");
        // TODO: Implement Microsoft Sign-Up flow
    }


    // Handlers for Header Navigation links (from Login reference)
    @FXML
    private void handleNavDashboard() {
        System.out.println("Header Dashboard link clicked (from Login)");
        // For testing purposes - allow navigation to dashboard without authentication
        if (mainApp != null) {
            // Create a dummy test user for testing purposes
            User testUser = new User("test-id-123", "test@example.com", "Test User");
            mainApp.showDashboardScreen(testUser);
            System.out.println("Navigating to Dashboard for testing purposes");
        } else {
            System.out.println("MainApp reference is null. Cannot navigate to Dashboard.");
        }
    }

    @FXML
    private void handleNavPricing() {
        System.out.println("Header Pricing link clicked (from Login)");
        // TODO: Implement navigation or external link opening (likely using MainApp or HostServices)
    }

    @FXML
    private void handleNavSupport() {
        System.out.println("Header Support link clicked (from Login)");
        // TODO: Implement navigation or external link opening (likely using MainApp or HostServices)
    }

    @FXML
    private void handleHeaderSignInButton() {
        System.out.println("Header Sign In button clicked (from Login)");
        // If this button is meant to switch TO Sign In, call handleSignInTabClick()
        handleSignInTabClick();
    }


    @FXML
    private void handleHeaderSignUpLink() {
        System.out.println("Header Sign Up link clicked (from Login)");
        // Call the tab handler to switch to the Sign Up form
        handleSignUpTabClick();
    }


    @FXML
    private void handleSignInTabClick() {
        System.out.println("Sign In tab clicked");
        // Update tab styles
        setTabActive(signInTabLabel, signInIndicator, true);
        setTabActive(signUpTabLabel, signUpIndicator, false);

        // Switch visible form pane
        signInFormPane.setVisible(true);
        signInFormPane.setManaged(true);
        signUpFormPane.setVisible(false);
        signUpFormPane.setManaged(false);

        // Hide error message when switching tabs
        hideErrorMessage();
    }

    @FXML
    private void handleSignUpTabClick() {
        System.out.println("Sign Up tab clicked");
        // Update tab styles
        setTabActive(signInTabLabel, signInIndicator, false);
        setTabActive(signUpTabLabel, signUpIndicator, true);

        // Switch visible form pane
        signInFormPane.setVisible(false);
        signInFormPane.setManaged(false);
        signUpFormPane.setVisible(true);
        signUpFormPane.setManaged(true);

        // Hide error message when switching tabs
        hideErrorMessage();
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
            indicator.getStyleClass().remove("inactive-indicator"); // Ensure inactive style is removed
        } else {
            tabLabel.getStyleClass().remove("active-tab"); // Ensure active style is removed
            indicator.getStyleClass().remove("active-indicator");
            indicator.getStyleClass().add("inactive-indicator"); // Add inactive style
        }
    }


    // Dummy authentication method (replace with real logic connecting to Node.js)
    private boolean performAuthentication(String email, String password) {
        System.out.println("Calling dummy authentication for: " + email);
        // This is a placeholder. Connect this to your Node.js backend later.
        // Example:
        return "test@example.com".equals(email) && "password123".equals(password);
        // Or for initial testing: return true; // Always successful
        // Or for initial testing: return false; // Always failed
    }

    // Dummy signup method (replace with real logic connecting to Node.js)
    private boolean performSignup(String fullName, String email, String password) {
        System.out.println("Calling dummy signup for: " + email);
        // This is a placeholder. Connect this to your Node.js backend later.
        // For now, always return true for dummy signup
        return true;
    }

    // Dummy User class (assuming it's in com.meetingapp.models)
    // You should have a proper User.java file if you haven't already
     /*
     public static class User {
         private String id;
         private String email;
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