package PhotographyStudio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Photographer extends User implements CreatePhotographyPackage{

    //Photographer's attributes
    private String specialization;
    private int experienceYears;

    //constructor
    public Photographer(String aUserID, String aUsername, String aPassword, String aName, String theGender, String anEmail,
            String aPhoneNumber, String aRole, String theSpecialization, int theExperienceYears) {
        super(aUserID, aUsername, aPassword, aName, theGender, anEmail, aPhoneNumber, aRole);
        specialization = theSpecialization;
        experienceYears = theExperienceYears;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setSpecialization(String theSpecialization) {
        specialization = theSpecialization;
    }

    public void setExperienceYears(int theExperienceYears) {
        experienceYears = theExperienceYears;
    }

    @Override
    public void viewProfile() {
        System.out.println("##############################################");
        System.out.println("                User Profile                  ");
        System.out.println("##############################################");
        System.out.println("Name                    : " + getName());
        System.out.println("Gender                  : " + getGender());
        System.out.println("User ID                 : " + getUserID());
        System.out.println("Username                : " + getUsername());
        System.out.println("Email                   : " + getEmail());
        System.out.println("Contact Number          : " + getPhoneNumber());
        System.out.println("Role                    : " + getRole());
        System.out.println("Specialization          : " + getSpecialization());
        System.out.println("Years of Experience     : " + getExperienceYears());
        System.out.println();
        System.out.println();
    }

    @Override
    public void editProfile(FileManager updateProfile, Scanner newValue) {             //allow user to make changes to their current profile

        String oldPassword;
        String newPassword;
        String confirmPassword;
        char changeGender;
        String email;

        int profileChange = 0;

        System.out.println("Select profile category to change: ");
        System.out.println("(1) Username");
        System.out.println("(2) Password");
        System.out.println("(3) Name");
        System.out.println("(4) Gender");
        System.out.println("(5) Email");
        System.out.println("(6) Contact Number");
        System.out.println("(7) Specialization");
        System.out.println("(8) Back to Main Menu");

        //To ensure no input fail
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("-------------------------");
                profileChange = newValue.nextInt();
                newValue.nextLine(); // Consume newline
                System.out.println("-------------------------");
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Please enter valid input (e.g. 1, 2, 3, 4, 5, 6, 7)");
                newValue.nextLine(); // Clear invalid input from scanner
            }
        }

        switch (profileChange) {
            case 1 -> {
                System.out.print("Enter new username: ");
                setUsername(newValue.nextLine());
                System.out.println();
            }
            case 2 -> {

                while (true) {

                    System.out.print("Enter your current password: ");
                    oldPassword = newValue.nextLine().trim();
                    
                    if (!oldPassword.equals(getPassword())) {
                        System.out.println("Incorrect current password. Please try again.");
                    } else {
                        break; // correct old password entered
                    }
                }

                //enter and confirm new password
                while (true) {
                    System.out.print("Enter new password: ");
                    newPassword = newValue.nextLine().trim();

                    // check length
                    if (newPassword.length() < 8 || newPassword.length() > 12) {
                        System.out.println("Password must be between 8 and 12 characters.");
                        continue;
                    }

                    // check weak sequences
                    String lower = newPassword.toLowerCase();
                    if (lower.contains("12345678") || lower.contains("87654321")
                            || lower.contains("abcdefgh") || lower.contains("hgfedcba")) {
                        System.out.println("Weak password. Please avoid simple sequences.");
                        continue;
                    }

                    // require at least one letter and one digit
                    if (!newPassword.matches(".*[A-Za-z].*") || !newPassword.matches(".*\\d.*")) {
                        System.out.println("Password must contain both letters and numbers.");
                        continue;
                    }

                    // confirm new password
                    System.out.print("Confirm new password: ");
                    confirmPassword = newValue.nextLine().trim();

                    if (!newPassword.equals(confirmPassword)) {
                        System.out.println("Passwords do not match. Try again.");
                        continue;
                    }

                    // all checks passed
                    setPassword(newPassword);
                    System.out.println("Password updated successfully!");
                    break;
                }
            }
            case 3 -> {
                System.out.print("Enter new name: ");
                setName(newValue.nextLine());
                System.out.println();
            }
            case 4 -> {
                OUTER:
                while (true) {
                    System.out.print("Enter new Gender ((M)MALE/(F)FEMALE): ");
                    try {
                        changeGender = newValue.next().charAt(0);
                        newValue.nextLine();

                        switch (changeGender) {
                            case 'M', 'm' -> {
                                setGender("MALE");
                                break OUTER;
                            }
                            case 'F', 'f' -> {
                                setGender("FEMALE");
                                break OUTER;
                            }
                            default ->
                                System.out.println("Invalid Input. Please enter a valid input (M/F)");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid Input. Please enter a valid input (M/F).");
                    }
                }
            }
            case 5 -> {
                System.out.print("Enter new email: ");
                email = newValue.nextLine();
                System.out.println();

                //ensure email complies to the format @gmail.com
                if (!email.contains("@gmail.com")) {
                    email = email + "@gmail.com";
                }
                setEmail(email);
            }
            case 6 -> {
                while (true) {
                    System.out.print("Enter new contact number (e.g. 60123456789): ");
                    String newPhoneNumber = newValue.nextLine().trim();
                    System.out.println();

                    //To ensure phone number entered is number and in length between 8 and 10 
                    if (newPhoneNumber.matches("\\d{8,11}")) {
                        setPhoneNumber(newPhoneNumber);
                        break;
                    } else {
                        System.out.println("Invalid Input. Please enter a valid contact number (e.g. 60123456789)");
                        System.out.println("Number must contain 8-11 digits only");
                        System.out.println();
                    }
                }
            }
            case 7 -> {
                boolean specializationUpdated = false;
                while (!specializationUpdated) {
                    System.out.print("Enter new specialization (PORTRAIT/WEDDING/EVENT/PRODUCT): ");
                    String specialization = newValue.nextLine().trim().toUpperCase();
                    
                    // Validate specialization input
                    switch (specialization) {
                        case "PORTRAIT", "WEDDING", "EVENT", "PRODUCT" -> {
                            setSpecialization(specialization); // Use the validated input
                            System.out.println("Specialization updated successfully!");
                            specializationUpdated = true; // Exit the while loop
                        }
                        default -> {
                            System.out.println("Invalid Input. Please enter a valid specialization input");
                            System.out.println("e.g. PORTRAIT/WEDDING/EVENT/PRODUCT");
                            System.out.println();
                        }
                    }
                }
            }
            case 8 -> {
                return;
            }
            default -> {
                System.out.println("Invalid Input. Please enter a valid input (e.g. 1, 2, 3).");
                System.out.println();
            }
            }

            //update edited profile to file
            updateProfile.updatePhotographerData(FileManager.PHOTOGRAPHER_FILE, this);
            //update the user's details saved on the appointment data
            updateProfile.readPhotographerData(FileManager.PHOTOGRAPHER_FILE);
            //update the profile details related to the appointment
            updateProfile.updateAllAppointmentData(FileManager.APPOINTMENT_FILE);
            //Read the new updated appointments data
            updateProfile.readAppointmentData(FileManager.APPOINTMENT_FILE);
    }

    public void viewAllAppointmentDetails(FileManager fileManager) {
        ArrayList<Appointment> appointments = fileManager.getAppointmentList();
        
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        
        System.out.println("========================== ALL APPOINTMENTS ==============================");
        for (Appointment appointment : appointments) {
            System.out.println(appointment.toString());
            System.out.println("----------------------------");
        }
    }

    //Allow the photographer to be responsible for an appointment with no any photographer assigned
    public void engageInPhotoSession(FileManager engagePhotographer, Scanner engageSession) {
        
            String respondAppointmentID;       //Variable to hold user input appointment ID
            char confirmEngage;             //Variable to hold user input for confirmation
            
            //get the appointments from FileManager class
            ArrayList<Appointment> appointments = new ArrayList<>();
            appointments = engagePhotographer.getAppointmentList();
            Appointment appointment = null;
            
            for(int i = 0; i < appointments.size(); i++) {
                System.out.println("(" + (i + 1) + ")" +"Appointment ID: " + appointments.get(i).getAppointmentID());
            }

            System.out.println("Enter the Appointment ID you want to be in charge of ");
            System.out.println("--------------------------------------------------------");
            System.out.print("Appointment ID: ");
            respondAppointmentID = engageSession.next().trim();
            System.out.println("--------------------------------------------------------");
            System.out.println();

            OUTER:
            while (true) {
                System.out.println("Are you sure you want to be in charge of the photography session? (Y/N)");
                System.out.println("------------------------------------------");
                System.out.print("Confirmation: ");
                confirmEngage = engageSession.next().trim().toUpperCase().charAt(0);
                System.out.println("------------------------------------------");
                System.out.println();
                
                switch (confirmEngage) {
                    case 'Y' -> {
                        boolean found = false;
                        //To get the index of the appointment in the array list
                        for (int i = 0; i < engagePhotographer.getAppointmentList().size(); i++) {
                            //Check whether is the current index of the appointmentID is the one we are looking for
                            if (engagePhotographer.getAppointmentList().get(i).getAppointmentID().equals(respondAppointmentID)) {
                                if(engagePhotographer.getAppointmentList().get(i).getPhotographerName().equals("N/A")) {
                                    //set the photographerID and Name to the appointment as a record
                                    engagePhotographer.getAppointmentList().get(i).setPhotographerID(getUserID());
                                    engagePhotographer.getAppointmentList().get(i).setPhotographerName(getName());
                                    appointment = engagePhotographer.getAppointmentList().get(i);
                                    found = true;   //The appointment is found
                                    break;
                                } else {
                                    found = true;   //The appointment is found but a photography is assigned to it
                                    System.out.println("A photographer has already assigned to the appointment");
                                    System.out.println();
                                    return;
                                } 
                            }
                        }
                        
                        if (appointment != null) { 
                            engagePhotographer.updateAppointmentData(FileManager.APPOINTMENT_FILE, appointment);
                            engagePhotographer.readAppointmentData(FileManager.APPOINTMENT_FILE);
                        }
                        
                        if (found) {
                            System.out.println("Engage successful!");
                        } else {
                            System.out.println("No appointment found with ID: " + respondAppointmentID);
                        }   System.out.println();

                        break OUTER;
                    }
                    case 'N' -> {
                        System.out.println("Engage cancelled.");
                        System.out.println();
                        break OUTER;
                    }
                    default -> {
                        System.out.println("Invalid Input! Please enter a valid input (Y/N).");
                        System.out.println();
                    }
                }
            }
        
    }

    //Allow the photographer to cancel their assignment to a specific appointment
    public void cancelPhotographyAssignment(String photographerID, FileManager fileManager, String filename, Scanner cancelAssign) {
            ArrayList<Appointment> appointmentList = fileManager.getAppointmentList();

            //Show all the appointments assigned to this photographer
            List<Appointment> assignedAppointments = new ArrayList<>();
            for (Appointment appoint : appointmentList) {
                if (appoint.getPhotographerID() != null && appoint.getPhotographerID().equals(photographerID)) {
                    assignedAppointments.add(appoint);
                }
            }

            if (assignedAppointments.isEmpty()) {
                System.out.println("You have no assigned appointments to cancel.");
                return;
            }

            System.out.println("Appointments assigned:");
            for (int i = 0; i < assignedAppointments.size(); i++) {
                Appointment a = assignedAppointments.get(i);
                System.out.println((i+1) + ". " + a.getAppointmentID() + " (" + a.getAppointmentDate() + " " + 
                                a.getStartTime() + "-" + a.getEndTime() + ", Client: " + a.getClientName() + ")");
            }

            //Select appointment to cancel
            System.out.print("Enter the number of the appointment you want to cancel assignment for: ");
            int choice = cancelAssign.nextInt();
            cancelAssign.nextLine();

            if (choice < 1 || choice > assignedAppointments.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            Appointment selected = assignedAppointments.get(choice - 1);

            //Confirm cancel
            System.out.println("Are you sure you want to cancel your assignment/engagement for appointment ID " + selected.getAppointmentID() + "? (Y/N)");
            char confirm = cancelAssign.next().charAt(0);

            boolean validConfirmation = false;

            while (!validConfirmation) {
                System.out.println("Are you sure you want to cancel your assignment/engagement for appointment ID " + selected.getAppointmentID() + "? (Y/N)");
                char confirm1 = cancelAssign.next().toUpperCase().charAt(0);
                cancelAssign.nextLine(); // Consume the newline

                switch (confirm1) {
                    case 'Y' -> {
                        selected.setPhotographerID("N/A");      // unassign photographer
                        selected.setPhotographerName("N/A");   
                        System.out.println("You have been unassigned from appointment " + selected.getAppointmentID());

                        // Save changes to file
                        fileManager.updateAppointmentData(FileManager.APPOINTMENT_FILE, selected);
                        validConfirmation = true;
                    }
                    case 'N' -> {
                        System.out.println("Cancellation aborted.");
                        validConfirmation = true;
                    }
                    default -> {
                        System.out.println("Invalid input! Please enter 'Y' for Yes or 'N' for No.");
                        System.out.println();
                    }
                }
            }
    }

    //To allow admin to launch a photography package which is like a promotion
    @Override
    public void createPackage(FileManager cPackage, Scanner createPac) {

        //attributes for superclass PhotographySession
        String id;
        String photographySessionName;
        String photographySessionType;
        double price;
        String isAPackage = "YES";
        String appointmentID = "N/A";   //No appointment ID since there is no client book the photography package yet

        //specific attributes for subclass Portrait 
        int numOfPeople;
        String printPackage;
        String portraitType;

        //specific attributes for subclass Wedding
        int numOfWeddingGuest;
        String albumInc;
        String videoInc;
        String wedCulStyle;

        //specific attributes for subclass Event
        String eventType;
        int numOfEventGuest;

        //specific attributes for subclass Product
        String productType;
        int numOfProduct;
        String editLevel;

        System.out.println("Enter Photography Package Name ");
        System.out.println("-------------------------------------------------------------------");
        System.out.print("Photography Package Name: ");
        photographySessionName = createPac.nextLine();
        System.out.println("-------------------------------------------------------------------");

        OUTER_3:
        while (true) {
            System.out.println("Enter Photography Package Type");
            System.out.println("(1) PORTRAIT ");
            System.out.println("(2) WEDDING ");
            System.out.println("(3) EVENT ");
            System.out.println("(4) PRODUCT ");
            System.out.println("-------------------------------------------------------------------");
            System.out.print("Photography Type: ");
            photographySessionType = createPac.next().toUpperCase();
            createPac.nextLine();
            System.out.println("-------------------------------------------------------------------");
            System.out.println();

            switch (photographySessionType) {
                case "1" -> {
                    break OUTER_3;
                }
                case "2" -> {
                    break OUTER_3;
                }
                case "3" -> {
                    break OUTER_3;
                }
                case "4" -> {
                    break OUTER_3;
                }
                default -> {
                    System.out.println("Invalid Input. Please enter (1/2/3/4)");
                    System.out.println();
                }
            }
        }

        while(true) {
            try {
                System.out.println("Enter price of the photography package ");
                System.out.println("------------------------------------------");
                System.out.print("Price: ");
                price = createPac.nextDouble();
                createPac.nextLine(); // consume newline
                System.out.println("------------------------------------------");
                System.out.println();

                if(price < 0) {
                    System.out.println("Invalid Input. Please enter a positive number for price.");
                    System.out.println();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Please enter a number for price.");
                createPac.nextLine(); // Clear the invalid input from scanner
                System.out.println();
            }
        }

        switch (photographySessionType) {
            //if the photography package is portrait
            case "1" -> {
                photographySessionType = "PORTRAIT";
                id = IDGenerator.generateRandomPortraitSessionID(10);   //generate the id for portrait photography session
                System.out.println("Enter number of people of the photography session included: ");
                System.out.println("--------------------------");
                System.out.print("Number of person: ");
                numOfPeople = createPac.nextInt();
                createPac.nextLine();
                System.out.println("--------------------------");
                System.out.println();

                System.out.println("Enter the backdrop style included: ");
                System.out.println("--------------------------");
                System.out.print("Backdrop Style: ");
                String backdropStyle = createPac.nextLine();
                System.out.println("--------------------------");
                System.out.println();

                OUTER:
                while (true) {
                    System.out.println("Does it include print package? (Y/N)");
                    System.out.println("--------------------------");
                    System.out.print("Include: ");
                    printPackage = createPac.next();
                    createPac.nextLine();
                    System.out.println("--------------------------");
                    System.out.println();

                    if (null == printPackage) {
                        System.out.println("Invalid input. Please enter valid input (Y/N)");
                        System.out.println();
                    } else {
                        switch (printPackage) {
                            case "Y", "y" -> {
                                printPackage = "YES";
                                break OUTER;
                            }
                            case "N", "n" -> {
                                printPackage = "NO";
                                break OUTER;
                            }
                            default -> {
                                System.out.println("Invalid input. Please enter valid input (Y/N)");
                                System.out.println();
                            }
                        }
                    }
                }

                System.out.println("Enter the portrait type included: ");
                System.out.println("--------------------------");
                System.out.println("Backdrop Style: ");
                portraitType = createPac.nextLine();
                System.out.println("--------------------------");
                System.out.println();

                Portrait port = new Portrait(id, photographySessionName, photographySessionType, price, isAPackage, numOfPeople, backdropStyle, printPackage, portraitType, appointmentID);
                cPackage.addPortraitSession(FileManager.PORTRAIT_FILE, port);
                cPackage.getPortraitList().add(port);
                cPackage.readPortraitService(FileManager.PORTRAIT_FILE);
            }

            //If the photography package is wedding
            case "2" -> {
                photographySessionType = "WEDDING";
                id = IDGenerator.generateRandomWeddingSessionID(10); //generate the ID for the photography session
                System.out.println("Enter number of guest of the photography session included: ");
                System.out.println("--------------------------");
                System.out.print("Number of Guest: ");
                numOfWeddingGuest = createPac.nextInt();
                createPac.nextLine();
                System.out.println("--------------------------");
                System.out.println();
                OUTER_1:
                while (true) {
                    System.out.println("Does it include album? (Y/N)");
                    System.out.println("--------------------------------");
                    System.out.print("Include: ");
                    albumInc = createPac.next();
                    createPac.nextLine();
                    System.out.println("--------------------------------");
                    System.out.println();

                    switch (albumInc) {
                        case "Y", "y" -> {
                            albumInc = "YES";
                            break OUTER_1;
                        }
                        case "N", "n" -> {
                            albumInc = "NO";
                            break OUTER_1;
                        }
                        default -> {
                            System.out.println("Invalid input. Please enter valid input (Y/N)");
                            System.out.println();
                        }
                    }
                }
                OUTER_2:
                while (true) {
                    System.out.println("Does it include videography package? (Y/N)");
                    System.out.println("--------------------------");
                    System.out.print("Include: ");
                    videoInc = createPac.next();
                    createPac.nextLine();
                    System.out.println("--------------------------");
                    System.out.println();

                    if (null == videoInc) {
                        System.out.println("Invalid input. Please enter valid input (Y/N)");
                        System.out.println();
                    } else {
                        switch (videoInc) {
                            case "Y", "y" -> {
                                videoInc = "YES";
                                break OUTER_2;
                            }
                            case "N", "n" -> {
                                videoInc = "NO";
                                break OUTER_2;
                            }
                            default -> {
                                System.out.println("Invalid input. Please enter valid input (Y/N)");
                                System.out.println();
                            }
                        }
                    }
                }

                System.out.println("Enter the wedding culture style of the photography session: ");
                System.out.println("--------------------------");
                System.out.print("Culture Style: ");
                wedCulStyle = createPac.nextLine();
                System.out.println("--------------------------");

                //Create the wedding object and save it into the file and arrayList
                Wedding wedding = new Wedding(id, photographySessionName, photographySessionType, price, isAPackage, numOfWeddingGuest, albumInc, videoInc, wedCulStyle, appointmentID);
                cPackage.addWeddingSession(FileManager.WEDDING_FILE, wedding);
                cPackage.getWeddingList().add(wedding);
                cPackage.readWeddingService(FileManager.WEDDING_FILE);

            }
            case "3" -> {
                id = IDGenerator.generateRandomEventSessionID(10);  //Generate the ID for event photography session
                System.out.println("Enter the event type of the photography session: ");
                System.out.println("--------------------------");
                System.out.print("Event type: ");
                eventType = createPac.nextLine();
                System.out.println("--------------------------");

                while (true) {
                    try {
                        System.out.println("Enter number of guest of the photography session included ");
                        System.out.println("-------------------------------------------------------------");
                        System.out.print("Number of Guest: ");
                        numOfEventGuest = createPac.nextInt();
                        createPac.nextLine(); // consume newline
                        System.out.println("-------------------------------------------------------------");
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input. Please enter a valid number (e.g. 10, 20, 30)");
                        createPac.nextLine(); // Clear the invalid input from scanner
                        System.out.println(); // Add spacing for better readability
                    }
                }

                //Create an Event object and save it to the file and arrayList
                Event event = new Event(id, photographySessionName, photographySessionType, price, isAPackage, eventType, numOfEventGuest, appointmentID);
                cPackage.addEventSession(FileManager.EVENT_FILE, event);
                cPackage.getEventList().add(event);
                cPackage.readEventService(FileManager.EVENT_FILE);

            }
            case "4" -> {
                id = IDGenerator.generateRandomProductSessionID(10);
                System.out.println("Enter the product type of the photography session ");
                System.out.println("--------------------------------------------------");
                System.out.print("Product type: ");
                productType = createPac.nextLine();
                System.out.println("--------------------------------------------------");
                System.out.println();

                while (true) {
                    try {
                        System.out.println("Enter number of product included of the photography session included: ");
                        System.out.println("----------------------------------------------------------------------");
                        System.out.print("Number of Product: "); // Changed from println to print
                        numOfProduct = createPac.nextInt();
                        createPac.nextLine(); // consume newline
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println();
                        break;

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input. Please enter a valid number (e.g. 3, 6, 8)");
                        createPac.nextLine(); // Clear the invalid input from scanner
                        System.out.println(); // Add spacing for better readability
                    }
                }

                System.out.println("Enter editing level of the photography ");
                System.out.println("----------------------------------------");
                System.out.print("Edit Level: ");
                editLevel = createPac.next();
                createPac.nextLine();
                System.out.println("----------------------------------------");

                //Create Product object and store it into file and arrayList
                Product product = new Product(id, photographySessionName, photographySessionType, price, isAPackage, productType, numOfProduct, editLevel, appointmentID);
                cPackage.addProductSession(FileManager.PRODUCT_FILE, product);
                cPackage.getProductList().add(product);
                cPackage.readProductService(FileManager.PRODUCT_FILE);
            }
            default -> {
                System.out.println("Invalid Input. Please enter a valid input");
            }
        }
    }

    @Override
    public void removePackage(FileManager rPackage, Scanner removePac) {
        
        //A variable to hold user input
        String packageID;

        System.out.println("Enter the photography session ID to be deleted:");
        System.out.println("--------------------------------");
        System.out.print("ID: ");
        packageID = removePac.next();
        System.out.println("--------------------------------");

        //Searches for the entered photography session ID
        //If the entered photography package is portrait
        if (packageID.startsWith("PRT")) {
            Portrait portrait = null;

            for (int i = 0; i < rPackage.getPortraitList().size(); i++) {
                if (packageID.equals(rPackage.getPortraitList().get(i).getPhotoSessionID())) {
                    portrait = rPackage.getPortraitList().get(i);
                    break;
                }
            }

            if (portrait != null) {
                //remove the photography package
                rPackage.removePortraitSession(FileManager.PORTRAIT_FILE, portrait);
                rPackage.readPortraitService(FileManager.PORTRAIT_FILE);
                System.out.println("Portrait session with ID " + packageID + " removed successfully.");
            } else {
                System.out.println("No portrait session found with ID " + packageID);
            }
            //If the entered photography package is wedding
        } else if (packageID.startsWith("WED")) {
            Wedding wedding = null;

            for (int i = 0; i < rPackage.getWeddingList().size(); i++) {
                if (packageID.equals(rPackage.getWeddingList().get(i).getPhotoSessionID())) {
                    wedding = rPackage.getWeddingList().get(i);
                    break;
                }
            }

            if (wedding != null) {
                //remove the photography package
                rPackage.removeWeddingSession(FileManager.WEDDING_FILE, wedding);
                rPackage.readWeddingService(FileManager.WEDDING_FILE);
                System.out.println("Wedding session with ID " + packageID + " removed successfully.");
            } else {
                System.out.println("No wedding session found with ID " + packageID);
            }

            //If the photography package entered is Event
        } else if (packageID.startsWith("EVT")) {
            Event event = null;

            for (int i = 0; i < rPackage.getEventList().size(); i++) {
                if (packageID.equals(rPackage.getEventList().get(i).getPhotoSessionID())) {
                    event = rPackage.getEventList().get(i);
                    break;
                }
            }

            if (event != null) {
                //remove the photography package
                rPackage.removeEventSession(FileManager.EVENT_FILE, event);
                rPackage.readEventService(FileManager.EVENT_FILE);
                System.out.println("Event session with ID " + packageID + " removed successfully.");
            } else {
                System.out.println("No event session found with ID " + packageID);
            }

            //If the photography package entered is product
        } else if (packageID.startsWith("PDC")) {
            Product product = null;

            for (int i = 0; i < rPackage.getProductList().size(); i++) {
                if (packageID.equals(rPackage.getProductList().get(i).getPhotoSessionID())) {
                    product = rPackage.getProductList().get(i);
                    break;
                }
            }

            if (product != null) {
                //remove the photography package
                rPackage.removeProductSession(FileManager.PRODUCT_FILE, product);
                rPackage.readProductService(FileManager.PRODUCT_FILE);
                System.out.println("Event session with ID " + packageID + " removed successfully.");
            } else {
                System.out.println("No event session found with ID " + packageID);
            }

        } else {
            System.out.println("Photography package ID does not exist.");
            System.out.println();
        }
    }
}
