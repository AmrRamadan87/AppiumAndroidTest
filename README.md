# Appium Android Test for Simple Clinic App

This project contains automated tests for the **Simple Clinic** Android app using **Appium** and **Cucumber**.

## Feature: Simple Clinic App Test

### Scenario: User onboarding and basic app navigation
- **Given** the Simple Clinic app is launched
- **When** the user proceeds through the intro screens
- **And** the user agrees to the terms of use
- **And** the user selects "Demo Country" and "Sikkim" as the state
- **And** the user enters a unique phone number
- **And** the user enters their full name
- **And** the user sets and confirms a PIN
- **And** the user skips facility selection and permissions
- **Then** the user should see the "SCAN ID" button

## Test Setup

### Dependencies
- Java (JDK 8 or higher)
- Appium
- Cucumber
- Android SDK
- Android Emulator or Physical Device

### Device/Emulator Requirements
 - An Android emulator or physical device with API level 21 or higher.
 - Ensure the device/emulator is connected and accessible via adb.

### How to Run the Tests
1. Ensure Appium server is running.
2. Update the `DesiredCapabilities` in the `SimpleClinicSteps.java` file with your device/emulator details.

