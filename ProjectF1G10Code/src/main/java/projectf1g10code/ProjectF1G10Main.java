/*
Title       : Song Management System
Objective   : To store and search song albums.
Course Code : ICT2113
Section     : F1
Group Num   : 10
 */
// Package declaration
package projectf1g10code;

// Prerequisite class
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Main class
public class ProjectF1G10Main {

    // Main method - start of program
    public static void main(String[] args) {

        // User input
        Scanner userInput = new Scanner(System.in);

        // Declare an array to store 10 album, 
        // Monitor  : 3 album minimum, 10 album maximum, increment counter for each new album
        final short MAX_ALBUM_NUM = 10; // Store int value of max album
        ProjectF1G10Album[] albumArray = new ProjectF1G10Album[MAX_ALBUM_NUM];  // Initialize new array to store albums
        short counter = 0;  // Keep track of album index

        // Used to stop program       
        boolean continueProgram = true;

        // Options
        // To increase clarity
        final int ADD_ALBUM_OPTION = 1;
        final int ADD_SONG_TO_ALBUM_OPTION = 2;
        final int SEARCH_FOR_ALBUM_OPTION = 3;
        final int DISPLAY_ALL_ALBUM = 4;
        final int EXIT_OPTION = 5;

        // Default album
        // Monitor: increment counter
        // Has no song
        albumArray[counter] = new ProjectF1G10Album("Freddie Mercury", "Mr. Bad Guy");
        counter++;
        albumArray[counter] = new ProjectF1G10Album("Elvis Presley", "Christmas");
        counter++;
        albumArray[counter] = new ProjectF1G10Album("Michael Jackson", "Thriller");
        counter++;

        // If continueProgram is true, program will loop
        do {
            // Increase readibility
            System.out.println();

            // Program instruction to user
            System.out.println("This program is used to store and search song albums.");
            System.out.println("You can create a maximum of " + MAX_ALBUM_NUM + " albums.");

            // Display menu
            displayMenu();

            // Used to store user choice for switch
            // Uses -1 for handling empty input
            // Each time loop begin, userChoice is reset
            int userChoice = -1;

            // Ask user to select an action
            try {
                userChoice = userInput.nextInt();
                userInput.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input: Please select an action via entering its index.");
                // Consume the invalid input
                userInput.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Invalid Input: Empty input is invalid. Please provide an input.");
            }

            // After proper choice is entered
            switch (userChoice) {
                // Create new album
                case ADD_ALBUM_OPTION -> {
                    // If max album size is not reached
                    // Monitor: increment counter
                    if (counter < MAX_ALBUM_NUM) {
                        albumArray[counter] = new ProjectF1G10Album();
                        counter++;
                    } else {
                        System.out.println("Max album size reached: Album size cannot be greater than " + MAX_ALBUM_NUM);
                    }
                }
                // Add a song to an available album
                case ADD_SONG_TO_ALBUM_OPTION -> {

                    boolean addAnotherSong = true;
                    int selectedAlbum = -1;

                    while (addAnotherSong) {
                        // Display all available albums
                        displayAllAlbum(albumArray, counter);

                        // Ask the user to select an album
                        do {
                            try {
                                System.out.println("Select an album by entering its index: ");
                                selectedAlbum = Integer.parseInt(userInput.nextLine());

                                if (selectedAlbum < counter && selectedAlbum > -1) {
                                    break;
                                } else {
                                    System.out.println("Invalid Input: Please select 0 to " + (counter - 1));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid Input: Only integer value is accepted. \nPlease select 0 to " + (counter - 1));
                            }

                        } while (true);

                        // Create a new song
                        ProjectF1G10Song newSong = new ProjectF1G10Song();

                        // Add the new song to the selected album
                        albumArray[selectedAlbum].albumAddSong(newSong);

                        boolean choiceYNContinue = true;

                        while (choiceYNContinue) {
                            System.out.println("Do you want to add another song (Y/N): ");
                            String addAnotherSongStr = userInput.nextLine().toLowerCase();

                            switch (addAnotherSongStr) {
                                case "yes", "y" -> {
                                    System.out.println("You have chosen to add another song.");
                                    choiceYNContinue = false;
                                }
                                case "no", "n" -> {
                                    System.out.println("You have chosen not to add more songs.");
                                    addAnotherSong = false;
                                    choiceYNContinue = false;
                                }
                                default -> {
                                    System.out.println("Invalid input: Please enter Y for yes, N for no.");
                                }
                            }
                        }
                    }
                }
                // display info of specific album
                case SEARCH_FOR_ALBUM_OPTION -> {
                    // show album list
                    displayAllAlbum(albumArray, counter);

                    // ask user
                    System.out.println("Enter album name to select: ");
                    String selectAlbum = userInput.nextLine();

                    // check if exist, if true, display album info, if false, return -1
                    int selectedAlbum = selectAlbum(albumArray, counter, selectAlbum);

                    if (selectedAlbum > -1) {
                        albumArray[selectedAlbum].albumDisplayInfo();
                    }
                }
                // display all album
                case DISPLAY_ALL_ALBUM -> {
                    System.out.println("");
                    displayAllAlbumsInfo(albumArray, counter);
                    System.out.println("");
                }
                // terminate program
                case EXIT_OPTION -> {
                    System.out.println("You exited the program");
                    continueProgram = false;
                }
                default -> {
                    System.out.println("Invalid Input: Value entered must be within 1 to 5");
                }
            }

        } while (continueProgram);
        System.out.println("Thank you for using the system.");

        // Close scanner object
        userInput.close();
    }

    public static void displayMenu() {
        System.out.println("1. Add Album");
        System.out.println("2. Add song to album");
        System.out.println("3. Search for an album");
        System.out.println("4. Display all album");
        System.out.println("5. Exit");
    }

    public static void displayAllAlbum(ProjectF1G10Album[] albumArray, int albumCounter) {
        System.out.println("Index\tAlbum Name");
        for (int index = 0; index < albumCounter; index++) {
            System.out.println(index + "\t" + albumArray[index].getAlbumName());
        }
    }

    public static void displayAllAlbumsInfo(ProjectF1G10Album[] albumArray, int albumCounter) {
        System.out.println("Index\tAlbum Name\t\tAlbum Artist\t\tTotal Songs");
        for (int index = 0; index < albumCounter; index++) {
            String albumName = albumArray[index].getAlbumName();
            String albumArtist = albumArray[index].getAlbumArtist();
            int albumTotalSongs = albumArray[index].getAlbumTotalSong();

            // To ensure consistent column width
            System.out.printf("%d\t%-15s\t%-15s\t%d\n", index, albumName, albumArtist, albumTotalSongs);
        }
    }

    public static int selectAlbum(ProjectF1G10Album[] albumArray, int albumCounter, String albumSelect) {
        for (int index = 0; index < albumCounter; index++) {
            if (albumSelect.equalsIgnoreCase(albumArray[index].getAlbumName())) {
                System.out.println("You selected " + albumArray[index].getAlbumName());
                return index;
            }
        }
        System.out.println(albumSelect + " " + "does not exist.");
        return -1;
    }
}
