package PhotographyStudio;

public class Wedding extends PhotographySession {
    private int numOfGuest;
    private String albumInclusion;
    private String videographyInclusion;
    private String weddingCultureStyle;

    // Parameterized constructor (including parent class fields)
    public Wedding(String photoSessionID, String photoSessionName, String photoSessionType, double price, String isAPackage,
                   int numOfGuest, String albumInclusion, String videoInclusion, String weddingCultureStyle, String appointmentID) {
        super(photoSessionID, photoSessionName, photoSessionType, price, isAPackage, appointmentID);
        this.numOfGuest = numOfGuest;
        this.albumInclusion = albumInclusion;
        this.videographyInclusion = videoInclusion;
        this.weddingCultureStyle = weddingCultureStyle;
    }

    // Getters and Setters
    public int getNumOfGuest() {
        return numOfGuest;
    }

    public void setNumOfGuest(int numOfGuest) {
        this.numOfGuest = numOfGuest;
    }

    public String getAlbumInclusion() {
        return albumInclusion;
    }

    public void setAlbumInclusion(String albumInclusion) {
        this.albumInclusion = albumInclusion;
    }

    public String getVideographyInclusion() {
        return videographyInclusion;
    }

    public void setVideographyInclusion(String videographyInclusion) {
        this.videographyInclusion = videographyInclusion;
    }

    public String getWeddingCultureStyle() {
        return weddingCultureStyle;
    }

    public void setWeddingCultureStyle(String weddingCultureStyle) {
        this.weddingCultureStyle = weddingCultureStyle;
    }

    @Override
    public String toString() {
        StringBuilder strbuild = new StringBuilder();
        strbuild.append(String.format("%-20s | %s%n", "Photography Session ID", photoSessionID));
        strbuild.append(String.format("%-20s | %s%n", "Photography Session Name", photoSessionName));
        strbuild.append(String.format("%-20s | %s%n", "Photography Session Type", photoSessionType));
        strbuild.append(String.format("%-20s | %s%n", "Price", price));
        strbuild.append(String.format("%-20s | %s%n", "Number Of Guest", numOfGuest));
        strbuild.append(String.format("%-20s | %s%n", "Album Inclusion", albumInclusion));
        strbuild.append(String.format("%-20s | %s%n", "Videography Inclusion", videographyInclusion));
        strbuild.append(String.format("%-20s | %s%n\n", "Wedding Culture Style", weddingCultureStyle));
        
        return strbuild.toString();
    }
}