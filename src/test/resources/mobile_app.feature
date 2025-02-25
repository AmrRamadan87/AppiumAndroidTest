Feature: Simple Clinic App Test

  Scenario: User onboarding and basic app navigation
    Given the Simple Clinic app is launched
    When the user proceeds through the intro screens
    And the user agrees to the terms of use
    And the user selects "Demo Country" and "Sikkim" as the state
    And the user enters a unique phone number
    And the user enters their full name
    And the user sets and confirms a PIN
    And the user skips facility selection and permissions
    Then the user should see the "SCAN ID" button