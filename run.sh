#!/bin/bash

# Set JavaFX path
JAVAFX_PATH="lib/javafx-sdk-24.0.1/lib"

# Compile the project
echo "Compiling project..."
javac --module-path $JAVAFX_PATH \
      --add-modules javafx.controls,javafx.fxml,javafx.base,javafx.graphics \
      -d target/classes \
      src/main/java/module-info.java \
      src/main/java/com/meetingapp/*.java \
      src/main/java/com/meetingapp/*/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful. Running application..."
    # Run the application
    java --module-path $JAVAFX_PATH:target/classes \
         --add-modules javafx.controls,javafx.fxml,javafx.base,javafx.graphics \
         -m com.meetingapp/com.meetingapp.MainApp
else
    echo "Compilation failed!"
    exit 1
fi
