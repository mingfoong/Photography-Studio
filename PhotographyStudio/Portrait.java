package PhotographyStudio;

public class Portrait extends PhotographySession {
    private int numOfPeople = 0;
    private String backdropStyle;
    private String printPackage;
    private String portraitType;

    //constructor
    public Portrait(String photoSessionID, String photoSessionName, String photoSessionType, double price, String isAPackage,
                           int numOfPeople, String backdropStyle, String printPackage, String portraitType, String appointmentID) {
        super(photoSessionID, photoSessionName, photoSessionType, price, isAPackage, appointmentID);
        this.numOfPeople = numOfPeople;
        this.backdropStyle = backdropStyle;
        this.printPackage = printPackage;
        this.portraitType = portraitType;
    }


    // Getters and Setters
    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public String getBackdropStyle() {
        return backdropStyle;
    }

    public void setBackdropStyle(String backdropStyle) {
        this.backdropStyle = backdropStyle;
    }

    public String getPrintPackage() {
        return printPackage;
    }

    public void setPrintPackage(String printPackage) {
        this.printPackage = printPackage;
    }

    public String getPortraitType() {
        return portraitType;
    }

    public void setPortraitType(String portraitType) {
        this.portraitType = portraitType;
    }

    @Override
    public String toString() {
        StringBuilder strbuild = new StringBuilder();
        strbuild.append(String.format("%-20s | %s%n", "Photography Session ID", photoSessionID));
        strbuild.append(String.format("%-20s | %s%n", "Photography Session Name", photoSessionName));
        strbuild.append(String.format("%-20s | %s%n", "Photography Session Type", photoSessionType));
        strbuild.append(String.format("%-20s | %s%n", "Price", price));
        strbuild.append(String.format("%-20s | %s%n", "Number Of People", numOfPeople));
        strbuild.append(String.format("%-20s | %s%n", "Backdrop Style", backdropStyle));
        strbuild.append(String.format("%-20s | %s%n", "Print Package", printPackage));
        strbuild.append(String.format("%-20s | %s%n\n", "Photographer ID", portraitType));

        return strbuild.toString();
    }
}
