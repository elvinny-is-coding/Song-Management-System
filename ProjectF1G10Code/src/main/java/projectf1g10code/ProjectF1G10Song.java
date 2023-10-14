/* 
Class       : Song
Methods     : Add song attribute / Modify song attribute
Attributes  : song name, song duration
 */
// Package declaration
package projectf1g10code;

// Prerequisite Class
import java.time.Duration;      // Is used for time-based calculations
import java.util.Scanner;       // Is used for inputs
import java.util.regex.Matcher; // Is used to match regex
import java.util.regex.Pattern; // Is used for validation

// Song class
public class ProjectF1G10Song {

    // ATTRIBUTES
    // Song's name
    private String songName;
    // Song's duration
    private Duration songDuration;
    // Song's duration in string
    private String songDurationStr;

    // Constructor
    // Objective    : To obtain and validate inputs
    public ProjectF1G10Song() throws IllegalArgumentException {

        // Variable declaration
        Scanner userInput = new Scanner(System.in);        // Scanner object for input

        String inputSongName;                                   // To store inputted song name
        System.out.println("Enter the song name: ");
        inputSongName = userInput.nextLine();

        while (inputSongName == null || inputSongName.trim().isEmpty()) {
            // Ask and validate song name
            System.out.println("Invalid input: Song name cannot be blank.");
            inputSongName = userInput.nextLine();
        }

        String inputSongDurationStr; // To store inputed song duration
        System.out.println("Enter the song duration (mm:ss): ");
        inputSongDurationStr = userInput.nextLine();

        while (!validateSongDuration(inputSongDurationStr)) {
            System.out.println("Invalid input: Song duration must be in (mm:ss) format,");
            System.out.println("where both mm and ss value is between 0 and 59");
            inputSongDurationStr = userInput.nextLine().trim();
        }

        // Attribute assignment
        this.songName = inputSongName;
        this.songDuration = createSongDuration(inputSongDurationStr);
        this.songDurationStr = inputSongDurationStr;
    }

    public ProjectF1G10Song(String songName, String songDurationStr) {
        // Validate inputs, ensure they are not empty and that the duration is in the correct format.
        if (songName == null || songName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input: Song name cannot be empty.");
        }
        if (!validateSongDuration(songDurationStr)) {
            throw new IllegalArgumentException("Invalid input: Song duration must be in (mm:ss) format.");
        }

        this.songName = songName;
        this.songDurationStr = songDurationStr;
        this.songDuration = createSongDuration(songDurationStr);
    }

    // METHODS
    // Return song name
    // Parameter    : null
    // Return value : String object, song name 
    public String getSongName() {
        return this.songName;
    }

    // Return song duration
    // Parameter    : null
    // Return value : Duration object, song duration 
    public Duration getSongDuration() {
        return this.songDuration;
    }

    // Return song duration in string
    // Parameter    : null
    // Return value : String object, song duration in string
    public String getSongDurationStr() {
        return this.songDurationStr;
    }

    // Change song name
    public void setSongName(String newSongName) {
        this.songName = newSongName;
    }

    // Validate song duration
    // Parameter    : null
    // Return value : null 
    private boolean validateSongDuration(String uncheckedSongDuration) {
        // Create a regex, where m = minute, s = second
        // m, must be less than 59
        // s, must be less then 59
        Pattern regexSongDuration = Pattern.compile("^(?!00:00)(?:[0-5]?[0-9]|[0-9]):[0-5][0-9]$");
        Matcher matchSongDuration = regexSongDuration.matcher(uncheckedSongDuration);

        if (!matchSongDuration.matches()) {
            // If not valid, ask for reinput
            return matchSongDuration.matches();
        }
        return matchSongDuration.matches();
    }

    // Validate song duration
    // Parameter    : String object, checked song duration
    // Return value : Duration object
    private Duration createSongDuration(String songDurationStr) {
        // Split the minute and second parts
        String[] songParts = songDurationStr.split(":");

        // Store the split parts into separate string variables
        int minutes = Integer.parseInt(songParts[0]);
        int seconds = Integer.parseInt(songParts[1]);

        // Return duration object
        return Duration.ofMinutes(minutes).plusSeconds(seconds);
    }
}
