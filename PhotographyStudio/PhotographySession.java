package PhotographyStudio;

public abstract class PhotographySession {
    protected final String photoSessionID;
    protected String photoSessionName;
    protected String photoSessionType;
    protected double price;
    protected String isAPackage;
    protected String appointmentID;


    public PhotographySession(String photoSessionID, String photoSessionName, String photoSessionType, double price, String isAPackage, String appointmentID) {
        this.photoSessionID = photoSessionID;
        this.photoSessionName = photoSessionName;
        this.photoSessionType = photoSessionType;
        this.price = price;
        this.isAPackage = isAPackage;
        this.appointmentID = appointmentID;
    }

    //getter methods
    public String getPhotoSessionID() {
        return photoSessionID;
    }

    public String getPhotoSessionName() {
        return photoSessionName;
    }

    public String getPhotoSessionType() {
        return photoSessionType;
    }

    public double getPrice() {
        return price;
    }

    public String getIsAPackage() {
        return isAPackage;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    //setter methods
    public void setPhotoSessionName(String photoSessionName) {
        this.photoSessionName = photoSessionName;
    }

    public void setPhotoSessionType(String photoSessionType) {
        this.photoSessionType = photoSessionType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setIsAPackage(String isAPackage) {
        this.isAPackage = isAPackage;
    }
    

    //Can be overrided by subclasses to show subclass own details
    @Override
    public abstract String toString();
}
