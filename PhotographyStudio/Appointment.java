package PhotographyStudio;

public class Appointment {
    private final String appointmentID;
    private final String clientID;
    private String clientName;
    private String photographerID;
    private String photographerName;
    private String appointmentDate;
    private String startTime;
    private String endTime;
    private String status;
    private String locationName;
    private PhotographySession session;

    public Appointment(String appointmentID, String clientID, String clientName, String photographerID, String photographerName,
                       String appointmentDate, String startTime, String endTime, String status, String locationName) {
        this.appointmentID = appointmentID;
        this.clientID = clientID;
        this.clientName = clientName;
        this.photographerID = photographerID;
        this.photographerName = photographerName;
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.locationName = locationName;
    }
    
    public String getAppointmentID() {
        return appointmentID;
    }

    public String getClientID() {
        return clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhotographerID() {
        return photographerID;
    }

    public String getPhotographerName() {
        return photographerName;
    }

    public void setPhotographerID(String photographerID) {
        this.photographerID = photographerID;
    }

    public void setPhotographerName(String photographerName) {
        this.photographerName = photographerName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public PhotographySession getPhotographySession() {
        return session;
    }

    public void setPhotographySession(PhotographySession session) {
        this.session = session;
    }

    //show client's appointment data
    @Override
    public String toString() {
        StringBuilder strbuild = new StringBuilder();
        strbuild.append(String.format("%-20s | %s%n", "Appointment ID", appointmentID));
        strbuild.append(String.format("%-20s | %s%n", "Client ID", clientID));
        strbuild.append(String.format("%-20s | %s%n", "Client Name", clientName));
        strbuild.append(String.format("%-20s | %s%n", "Photographer ID", photographerID));
        strbuild.append(String.format("%-20s | %s%n", "Photographer Name", photographerName));
        strbuild.append(String.format("%-20s | %s%n", "Appointment Date", appointmentDate));
        strbuild.append(String.format("%-20s | %s to %s%n", "Time", startTime, endTime));
        strbuild.append(String.format("%-20s | %s%n", "Status", status));
        strbuild.append(String.format("%-20s | %s%n", "Location", locationName));

        if (session != null) {
            strbuild.append("Session Details:\n").append(session.toString());
        }
        return strbuild.toString();
    }

    public void showAppointmentDetails(User user, FileManager fileManager) {
        boolean haveData = false;
        
        for (Appointment appointment : fileManager.getAppointmentList()) {
            if (user.getUserID().equals(appointment.getClientID()) || 
                user.getUserID().equals(appointment.getPhotographerID())) {
                haveData = true;
                System.out.println(appointment.toString()); 
                System.out.println("----------------------------");
            }
        }

        if (!haveData) {
            System.out.println("You don't have any appointment data.");
            System.out.println();
        }
    }

    public void showAllAppointmentDetails(FileManager filemanager) {
        for(int i = 0; i < filemanager.getAppointmentList().size(); i++) {
            toString();
        }
    }
}