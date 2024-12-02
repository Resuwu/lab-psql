package com.example.labpsql.utils;

public class ConsoleMessages {
    public static final String WELCOME = "Welcome to the LabPsql application!";
    public static final String MENU = """
            
            Please choose an option:
            1. Insert a new country
            2. Insert a new subject
            3. Insert a new player
            4. Insert a new team
            5. Insert a new team composition
            6. Insert a new result
            7. Display all countries
            8. Display all players
            9. Display all results
            10. Display results for a specific year
            11. Display all subjects
            12. Display all team compositions
            13. Display all teams
            14. Exit
            """;
    public static final String INVALID_OPTION = "Invalid option. Please try again.";
    public static final String WAITING_FOR_ENTER = "\nPress ENTER to continue...";
    public static final String SEARCH_ABORT = "Search aborted";
    public static final String INDEX_OUT_OF_BOUNDS = "Index out of range! Please enter a valid index.";
    public static final String EXITING_APPLICATION = "Exiting the application...";
}
