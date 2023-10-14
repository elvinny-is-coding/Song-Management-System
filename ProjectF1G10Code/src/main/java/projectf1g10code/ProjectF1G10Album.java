/* 
Class       : Album
Methods     : Add new song / Calc and return song's total duration / display album information
Attributes  : null
 */
// Package declaration
package projectf1g10code;

// Prerequisite Class
import java.util.Scanner;       // Used for input
import java.time.Duration;      // Used for albumTotalDuration calculation
import java.util.ArrayList;     // Used to keep track of albumSongs & albumAll
import java.util.concurrent.TimeUnit;

// Album class
public class ProjectF1G10Album {

    // ATTRIBUTES
    // Album's name
    private String albumName;
    //Album's artist
    private String albumArtist;
    // The sum of all song duration within an album
    private String albumTotalDuration;
    // To track number of song added
    private int albumTotalSong;
    // To track all songs added
    private ArrayList<ProjectF1G10Song> albumSongs;

    // Constructor
    // Objective    : Create new album
    // Monitor      : Album artist, Album name, Calc and record total album duration, generated new songs arraylist, add album to allAlbum arraylist 
    public ProjectF1G10Album() {
        // Variable declaration
        Scanner userInput = new Scanner(System.in); // Scanner object
        String inputAlbumName;                           // Input of album name
        String inputAlbumArtist;                         // Input of album artist

        // Ask user for album's name
        System.out.println("Enter the album name: ");
        inputAlbumName = userInput.nextLine();

        // Check if album name is empty or null
        while (inputAlbumName == null || inputAlbumName.trim().isEmpty()) {
            System.out.println("Album name cannot be empty. Try again.");
            inputAlbumName = userInput.nextLine();
        }

        // Ask user for album's artist
        System.out.println("Enter the album artist: ");
        inputAlbumArtist = userInput.nextLine();

        // Check if album artist is empty or null
        while (inputAlbumArtist == null || inputAlbumArtist.trim().isEmpty()) {
            System.out.println("Album artist cannot be empty. Try again.");
            inputAlbumArtist = userInput.nextLine();
        }

        // Self assignment
        this.albumArtist = inputAlbumArtist;
        this.albumName = inputAlbumName;
        this.albumTotalSong = 0;
        this.albumSongs = new ArrayList<>();
        this.albumTotalSong = 0;

        // Calculate and assign album song duration
        String calculatedSongDurationStr = albumStrSongDuration(albumCalcSongDuration());
        this.albumTotalDuration = calculatedSongDurationStr;

        System.out.println(inputAlbumName + " successfully added.");
    }

    // Constructor
    // Objective    : Create default album
    // Monitor      : Album artist, Album name, Calc and record total album duration, generated new songs arraylist, add album to allAlbum arraylist 
    public ProjectF1G10Album(String albumArtist, String albumName) {
        // Self assignment
        this.albumArtist = albumArtist;
        this.albumName = albumName;
        this.albumSongs = new ArrayList<>();

        this.albumTotalDuration = albumStrSongDuration(albumCalcSongDuration());
    }

    // METHODS
    // Return album name
    // Parameter    : null
    // Return value : String object, album name
    public String getAlbumName() {
        return this.albumName;
    }

    // Return album artist
    // Parameter    : null
    // Return value : String object, album artist
    public String getAlbumArtist() {
        return this.albumArtist;
    }

    // Return album total duration 
    // Parameter    : null
    // Return value : duration object, album total duration 
    public String getAlbumTotalDuration() {
        return this.albumTotalDuration;
    }

    // Return album total song 
    // Parameter    : null
    // Return value : integer object, album total song
    public int getAlbumTotalSong() {
        return this.albumTotalSong;
    }

    public void setAlbumName(String inputAlbumName) {
        this.albumName = inputAlbumName;
    }

    public void setAlbumArtist(String inputAlbumArtist) {
        this.albumArtist = inputAlbumArtist;
    }

    // This shouldn't be changed
    public void setAlbumTotalDuration(String inputAlbumTotalDuration) {
        this.albumTotalDuration = inputAlbumTotalDuration;
    }

    // This shouldn't be changed
    public void setAlbumTotalSong(int inputAlbumTotalSong) {
        this.albumTotalSong = inputAlbumTotalSong;
    }

    // Add new song to an existing album
    // Parameter    : song object
    // Return value : null 
    // Monitor      : each song added, recalculate albumTotalDuration, increment albumTotalSong
    public final void albumAddSong(ProjectF1G10Song song) {
        albumSongs.add(song);
        this.albumTotalDuration = albumStrSongDuration(albumCalcSongDuration());
        albumTotalSong++;
        System.out.println(song.getSongName() + " is added to the album, " + this.albumName);
    }

    // Calculate and return the total duration of the album
    // Parameter    : null 
    // Return value : Duration object, the sum of all song duration within the album 
    private Duration albumCalcSongDuration() {

        Duration sumDuration = Duration.ZERO;

        if (albumSongs.isEmpty()) {
            return sumDuration;
        }

        for (int index = 0; index < albumSongs.size(); index++) {
            Duration currentDuration = albumSongs.get(index).getSongDuration();
            sumDuration = sumDuration.plus(currentDuration);
        }
        return sumDuration;
    }

    // Takes Duration object and convert into string
    // Parameter    : Duration Object 
    // Return value : String object, return album total duration in (mm:ss)
    private String albumStrSongDuration(Duration songDuration) {

        long years = TimeUnit.SECONDS.toDays(songDuration.getSeconds()) / 365;
        long days = TimeUnit.SECONDS.toDays(songDuration.getSeconds()) % 365;
        long hours = songDuration.toHours() % 24;
        long minutes = songDuration.toMinutes() % 60;
        long seconds = songDuration.getSeconds() % 60;

        // Format minutes and seconds into "mm:ss" format
        return String.format("%d years, %02d days, %02d hours, %02d minutes, %02d seconds", years, days, hours, minutes, seconds);
    }

    // Display the album information
    // Parameter    : null 
    // Return value : album name, artist name, total number of songs
    public void albumDisplayInfo() {
        System.out.println("Album Name\t\t: " + this.getAlbumName());
        System.out.println("Album Artist\t\t: " + this.getAlbumArtist());
        System.out.println("Album Total Duration\t: " + this.getAlbumTotalDuration());

        System.out.println("Songs list");

        if (this.albumSongs.isEmpty()) {
            System.out.println("There is no song within the album.");
        } else {
            for (int index = 0; index < albumSongs.size(); index++) {
                System.out.println((index + 1) + ". " + albumSongs.get(index).getSongName() + " " + albumSongs.get(index).getSongDurationStr());
            }
        }
    }

}
