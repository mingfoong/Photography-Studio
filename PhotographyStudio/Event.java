package PhotographyStudio;

public class Event extends PhotographySession {
    private int numOfGuest;
    private String eventType;

    // Parameterized constructor (including parent class fields)
    public Event(String photoSessionID, String photoSessionName, String photoSessionType, double price, String isAPackage, String eventType, int numOfGuest, String appointmentID) {
        super(photoSessionID, photoSessionName, photoSessionType, price, isAPackage, appointmentID);
        this.numOfGuest = numOfGuest;
        this.eventType = eventType;
    }

    // Getters and Setters
    public int getNumOfGuest() {
        return numOfGuest;
    }

    public void setNumOfGuest(int numOfGuest) {
        this.numOfGuest = numOfGuest;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    @Override
    public String toString() {
        StringBuilder strbuild = new StringBuilder();
        strbuild.append(String.format("%-20s | %s%n", "Photography Session ID", photoSessionID));
        strbuild.append(String.format("%-20s | %s%n", "Photography Session Name", photoSessionName));
        strbuild.append(String.format("%-20s | %s%n", "Photography Session Type", photoSessionType));
        strbuild.append(String.format("%-20s | %s%n", "Price", price));
        strbuild.append(String.format("%-20s | %s%n", "Number Of Guest", numOfGuest));
        strbuild.append(String.format("%-20s | %s%n\n", "Event Type", eventType));

        return strbuild.toString();
    }
}