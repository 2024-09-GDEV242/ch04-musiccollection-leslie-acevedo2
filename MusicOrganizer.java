import java.util.ArrayList;
import javax.swing.Box;
import java.util.Random;
import java.util.Collections;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;
    
    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        tracks = new ArrayList<>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary("../audio");
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
    }
    
    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }
    
    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }
    
    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if(indexValid(index)) {
            Track track = tracks.get(index);
            player.playSample(track.getFilename());
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
        }
    }
    
    /** 
     * 4.43 exercise
     */
    public void playRandomTrack() {
        if (tracks.size() > 0) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(tracks.size());
            playTrack(randomIndex);
        } else {
            System.out.println("No Tracks Available to play.");
        }
    }
    
    /**
     * 4.45 exercise 
     */
    public void playAllTracksRandomOrder(){
        if (tracks.size() > 0) {
            ArrayList<Track> shuffleTracks = new ArrayList<>(tracks);
            Collections.shuffle(shuffleTracks);
            for (Track track : shuffleTracks) {
                player.playSample(track.getFilename());
                System.out.println("Now playing: " + track.getArtist() + "-" + track.getTitle());
            }
        } else {
            System.out.println("No tracks available to play.");
        }
    }
    
    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }
    
    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }
    
    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }
    
    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }
    
    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        if(tracks.size() > 0) {
            player.startPlaying(tracks.get(0).getFilename());
        }
    }
    
    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
    }
    
    public void 
        removedThirdTrack() {
            if(tracks.size() >=3) {
                tracks.remove(2);
            } else {
                System.out.println("Less than 3 items in the list.");
            }
        }
        
    public boolean checkIndex(int index) {
        return index >= 0 &&
        index < tracks.size();
    }
    
    public void listFiles() {
        for (int i = 0; i <
        tracks.size(); i++) {
            if (validIndex(i)) {
                System.out.println(tracks.get(i));
            }
        }
    }
    
    public void multipleOfFive() {
        int i = 10;
        while (i <= 95) {
            System.out.println(i);
            i += 5;
        }
    }
    
    public void sumOfOneToTen() {
        int sum = 0;
        int i = 1;
        while (i <= 10) {
            sum += i;
            i++;
        }
        System.out.println("Sum is: " + sum);
    }
    
    public int sum(int a, int b) {
        int sum = 0;
        while (a <= b) {
            sum += a;
            a++;
        }
        return sum;
    }
    public static void  main(String[] args) {
        MusicOrganizer organizer = new MusicOrganizer();
    }
    
    public boolean validIndex(int index) {
        if (index >= 0 && index < tracks.size()) {
            return true;
        } else {
            System.out.println("Invalid index:" + index);
            return false;
        }
    }
    
    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
    
    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }
}
