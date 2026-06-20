package PhotographyStudio;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends User implements CreatePhotographyPackage {
    public Admin(String aUserID, String aUsername, String aPassword, String aName, 
                    String theGender, String anEmail, String aPhoneNumber, String aRole) {
        super(aUserID, aUsername, aPassword, aName, theGender, anEmail, aPhoneNumber, aRole);
    }

    @Override
    public void viewProfile() {
        System.out.println("##############################################");
        System.out.println("                User Profile                  ");
        System.out.println("##############################################");
        System.out.println("Name            : " + getName());
        System.out.println("Gender          : " + getGender());
        System.out.println("User ID         : " + getUserID());
        System.out.println("Username        : " + getUsername());
        System.out.println("Email           : " + getEmail());
        System.out.println("Contact Number  : " + getPhoneNumber());
        System.out.println("Role            : " + getRole());
        System.out.println();
        System.out.println();
    }

    @Override
    public void editProfile(FileManager updateProfile, Scanner newValue) {             //allow user to make changes to their current profile
        int profileChange = 0;

        System.out.println("Select profile category to change: ");
        System.out.println("(1) Username");
        System.out.println("(2) Password");
        System.out.println("(3) Name");
        System.out.println("(4) Gender");
        System.out.println("(5) Email");
        System.out.println("(6) Contact Number");
        System.out.println("(7) Back to Main Menu");
        
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
                System.out.println("Enter new username: ");
                setUsername(newValue.nextLine());
            }
            case 2 -> {
                while (true) {
                    System.out.print("Enter your current password: ");
                    String oldPassword = newValue.nextLine().trim();

                    if (!oldPassword.equals(getPassword())) {
                        System.out.println("Incorrect current password. Please try again.");
                        System.out.println();
                    } else {
                        break; // correct old password entered
                    }
                }

                // enter and confirm new password
                while (true) {
                    System.out.print("Enter new password: ");
                    String newPassword = newValue.nextLine().trim();

                    // check length
                    if (newPassword.length() < 8 || newPassword.length() > 12) {
                        System.out.println("Password must be between 8 and 12 characters.");
                        continue;
                    }

                    //Ensure no weak password
                    String lower = newPassword.toLowerCase();
                    if (lower.contains("12345678") || lower.contains("87654321")
                            || lower.contains("abcdefgh") || lower.contains("hgfedcba")) {
                        System.out.println("Weak password. Please enter a strong password.");
                        continue;
                    }

                    // require at least one letter and one digit
                    if (!newPassword.matches(".*[A-Za-z].*") || !newPassword.matches(".*\\d.*")) {
                        System.out.println("Password must contain both letters and numbers.");
                        continue;
                    }

                    // confirm new password
                    System.out.print("Confirm new password: ");
                    String confirmPassword = newValue.nextLine().trim();

                    if (!newPassword.equals(confirmPassword)) {
                        System.out.println("Passwords do not match. Try again.");
                        continue;
                    }

                    //set the new password
                    setPassword(newPassword);
                    System.out.println("Password updated successfully!");
                    break;
                }
            }
            case 3 -> {
                System.out.println("Enter new name: ");
                setName(newValue.nextLine());
            }
            case 4 -> {
                OUTER:
                while (true) {
                    System.out.println("Enter new Gender ((M)MALE/(F)FEMALE): ");
                    try {
                        char changeGender = newValue.next().charAt(0);
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
                System.out.println("Enter new email (e.g. abcdef@gmail.com): ");
                String email = newValue.nextLine();

                //ensure each email complies to the format @gmail.com
                if (!email.contains("@gmail.com")) {
                    email = email + "@gmail.com";
                }
                setEmail(email);
            }
            case 6 -> {
                while(true) {
                    System.out.println("Enter new contact number (e.g. 60123456789): ");
                    String newPhoneNumber = newValue.nextLine().trim();

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
                return;
            }
            default -> {
                System.out.println("Invalid Input. Please enter a valid input (e.g. 3).");
                System.out.println();
            }
        }

        //update the edited profile to the file
        updateProfile.updateAdminData(FileManager.ADMIN_FILE, this);
        //read the new updated file data again
        updateProfile.readAdminData(FileManager.ADMIN_FILE);
        //update the profile details that are related to the appointment
        updateProfile.updateAllAppointmentData(FileManager.APPOINTMENT_FILE);
        //read the new updated appointment data
        updateProfile.readAppointmentData(FileManager.APPOINTMENT_FILE);
    }

    public void viewAllAppointmentDetails(FileManager fileManager) {
        ArrayList<Appointment> appointments = fileManager.getAppointmentList();
        
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        
        System.out.println("=== ALL APPOINTMENTS ===");
        for (Appointment appointment : appointments) {
            System.out.println(appointment.toString());
            System.out.println("----------------------------");
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
                System.out.println("Portrait type: ");
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
                //Read the new data
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
                //Read the new data
                cPackage.readProductService(FileManager.PORTRAIT_FILE);

            }
            default -> {
                System.out.println("Invalid Input. Please enter a valid input");
            }
        }
    }

    @Override
    public void removePackage(FileManager rPackage, Scanner removePac) {
        //variable to hold admin's input for the package ID(Photography Session ID) to be deleted.
        String packageID;
        
        System.out.println("Enter the Package ID (Photography session ID) to be deleted:");
        System.out.println("--------------------------------");
        System.out.print("ID: ");
        packageID = removePac.next();
        System.out.println("--------------------------------");
        System.out.println();

        //Searches for the entered photography session ID
        //If the entered photography package is portrait
        if(packageID.startsWith("PRT")) {
            Portrait portrait = null;

            for(int i = 0; i < rPackage.getPortraitList().size(); i++) {
                if(packageID.equals(rPackage.getPortraitList().get(i).getPhotoSessionID())) {
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
            
            for(int i = 0; i < rPackage.getWeddingList().size(); i++) {
                if(packageID.equals(rPackage.getWeddingList().get(i).getPhotoSessionID())) {
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

            for(int i = 0; i < rPackage.getEventList().size(); i++) {
                if(packageID.equals(rPackage.getEventList().get(i).getPhotoSessionID())) {
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
        } else if (packageID.startsWith("PDC")){
            Product product = null;

            for(int i = 0; i < rPackage.getProductList().size(); i++) {
                if(packageID.equals(rPackage.getProductList().get(i).getPhotoSessionID())) {
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
            System.out.println("Photography ID does not exist.");
            System.out.println();
        }
    }

    //A helper method to removeClient
    public boolean removeClient(String userID, FileManager fileManager) {
        ArrayList<Client> clients = fileManager.getClientList();

        for (Client c : clients) {
            if (c.getUserID().equals(userID)) {
                fileManager.removeClient(FileManager.CLIENT_FILE, c);
                return true; // removed successfully
            }
        }
        System.out.println("UserID does not exist.");
        return false; // userID not found
    }

    //A helper method to remove photographer
    public boolean removePhotographer(String userID, FileManager fileManager) {
        ArrayList<Photographer> photographers = fileManager.getPhotographerList();

        for (Photographer p : photographers) {
            if (p.getUserID().equals(userID)) {
                fileManager.removePhotographer(FileManager.PHOTOGRAPHER_FILE, p);
                return true; // removed successfully
            }
        }
        System.out.println("UserID does not exist.");
        return false; // userID not found
    }

    //A helper method to remove other admin
    public boolean removeAdmin(String userID, FileManager fileManager) {
        ArrayList<Admin> admins = fileManager.getAdminList();

        for (Admin a : admins) {
            if (a.getUserID().equals(userID)) {
                fileManager.removeAdmin(FileManager.ADMIN_FILE, a);
                return true; // removed successfully
            }
        }
        System.out.println("UserID does not exist.");
        return false; // userID not found
    }

    //allow admin to remove user account from the system
    public void deleteUser(FileManager delUser, Scanner delete) {
            
            System.out.println("Enter the user ID you wants to delete: ");
            System.out.println("--------------------------------");
            System.out.print("UserID: ");
            String delID = delete.next();
            System.out.println("--------------------------------");
            System.out.println();
            
            if(delID.equals(getUserID())) {
                System.out.println("Unable to remove yourself from the system. The user ID entered is your user ID.");
                return;
            }

            //Confirm to delete
            while (true) { 
                System.out.println("Are you sure to delete the following user " + delID + " ?");
                System.out.println("Deletion process cannot be undo once confirmed.");
                System.out.println("(Y)Yes/(N)No");
                System.out.println("------------------------------------------------------");
                System.out.print("Confirmation: ");
                String confirm = delete.next().toUpperCase();
                delete.nextLine();
                System.out.println("------------------------------------------------------");

                if(confirm.equals("Y") || confirm.equals("YES")) {
                    //checks the user ID for deletion
                    if(delID.startsWith("CLT")) {
                        removeClient(delID, delUser);
                        break;
                    } else if (delID.startsWith("PHG")) {
                        removePhotographer(delID, delUser);
                        break;
                    } else if (delID.startsWith("ADM")) {
                        removeAdmin(delID, delUser);
                        break;
                    } else {
                        System.out.println("User Not Found. Please try again.");
                        System.out.println();
                        break;
                    }
                } else if(confirm.equals("N") || confirm.equals("NO")) {
                    System.out.println("Delete user cancelled");
                    System.out.println();
                    break;
                } else {
                    System.out.println("Invalid Input. Please enter (Y/N).");
                    System.out.println();
                }
            }

            
    }

    //Allow Admin to assign photographer to an appointment
    public void assignPhotographer(FileManager assignPhotographer, Scanner assign) {
        String appID; //A variable to hold input appointment ID
        int choice; //A variable to hold input for photographer which to be assigned
        int count = 0;  //output number of appointments
        String confirm; //A variable to hold input to confirm assignment
        ArrayList<Appointment> appointmentList = assignPhotographer.getAppointmentList();

        System.out.println("Available Appointments");
        for(Appointment appointment: appointmentList) {
            System.out.println("(" + (count + 1) + ") " + appointment.getAppointmentID() + "(" + appointment.getStatus() + ")");
            count++;
        }


        System.out.println("Enter the appointment ID you wish to assign the photographer to");
        System.out.println("------------------------------------------------------------------");
        System.out.print("Appointment ID: ");
        appID = assign.next();
        System.out.println("------------------------------------------------------------------");
        System.out.println();
        
        Appointment selectedAppointment = null;
        //Loop until get the details for the specific appointment
        for (Appointment appt : assignPhotographer.getAppointmentList()) {
            if (appt.getAppointmentID().equals(appID)) {
                selectedAppointment = appt;
                break;
            }
        }

        //Appointment does not exist
        if (selectedAppointment == null) {
            System.out.println("Appointment not found!");
            System.out.println();
            return;
        }

        //list all available photographers
        while(true) {
            System.out.println("Available photographers:");
            for (int i = 0; i < assignPhotographer.getPhotographerList().size(); i++) {
                Photographer p = assignPhotographer.getPhotographerList().get(i);
                System.out.println((i + 1) + ". " + p.getName() + " (ID: " + p.getUserID() + ")");
            }

            System.out.println("Enter the number of the photographer you want to assign");
            System.out.println("-------------------------------------------------------");
            System.out.print("Choice: ");
            
            try {
                if (assign.hasNextInt()) {
                    choice = assign.nextInt();
                    assign.nextLine(); // Consume the newline
                    System.out.println("-------------------------------------------------------");
                    System.out.println();

                    if (choice >= 1 && choice <= assignPhotographer.getPhotographerList().size()) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and " + assignPhotographer.getPhotographerList().size() + ".");
                        System.out.println();
                    }
                } else {
                    // Handle non-integer input
                    String invalidInput = assign.nextLine();
                    System.out.println("Invalid input! '" + invalidInput + "' is not a number. Please enter a valid number.");
                    System.out.println();
                }
            } catch (Exception e) {
                assign.nextLine(); // Clear any invalid input
                System.out.println("An error occurred. Please try again.");
                System.out.println();
            }
        }
        Photographer p = assignPhotographer.getPhotographerList().get(choice - 1);
        String selectedPhotographerID = p.getUserID();
        String selectedPhotographerName = p.getName();

        // check for time clash
        boolean clash = false;
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

        //Use getter method to get the time String
        LocalTime validStartTime = LocalTime.parse(selectedAppointment.getStartTime(), timeFormat);
        LocalTime validEndTime   = LocalTime.parse(selectedAppointment.getEndTime(), timeFormat);

        //Find the other appointment's time duration from the list
        for (Appointment other : assignPhotographer.getAppointmentList()) {
            if (selectedPhotographerID.equals(other.getPhotographerID()) && other.getStatus().equalsIgnoreCase("BOOKED")) {

                LocalTime existingStart = LocalTime.parse(other.getStartTime(), timeFormat);
                LocalTime existingEnd   = LocalTime.parse(other.getEndTime(), timeFormat);
                
                //validate the appointment
                if (!(validEndTime.isBefore(existingStart) || validStartTime.isAfter(existingEnd))) {
                    clash = true;
                    System.out.println("Photographer is not available at that time (time clash)");
                    System.out.println("Appointment ID: " + other.getAppointmentID() + " (" + other.getStartTime() + " - " + other.getEndTime() + ")");
                    System.out.println();
                    break;
                }
            }
        }


        //No Time crashing, successfully assigned to appointment
        if (!clash) {
            while (true) { 
                System.out.println("Are you sure you want to assign the photographer to the appointment(Y/N)?");
                System.out.println("-----------------------------------------------------------------------");
                System.out.print("Confirmation: ");
                confirm = assign.next().toUpperCase();
                assign.nextLine();
                System.out.println("-----------------------------------------------------------------------");

                if(confirm.equalsIgnoreCase("Y")) {
                    //Update the data
                    selectedAppointment.setPhotographerID(selectedPhotographerID);
                    selectedAppointment.setPhotographerName(selectedPhotographerName);
                    assignPhotographer.updateAppointmentData(FileManager.APPOINTMENT_FILE, selectedAppointment);
                    System.out.println("Photographer " + p.getName() + " assigned to appointment " + selectedAppointment.getAppointmentID());
                    System.out.println();
                    break;
                } else if (confirm.equalsIgnoreCase("N")) {
                    System.out.println("Photographer Assignment Cancelled");
                    System.out.println();
                    break;
                } else {
                    System.out.println("Invalid Input. Please enter a valid input(Y/N).");
                    System.out.println();
                }
            }
        }
    }
    
    //To unassign a photographer from an appointment
    public void unassignPhotographer(FileManager unasgPhoto, Scanner unassign) {
    
        System.out.println("Enter the appointment ID you wish to remove the photographer from");
        System.out.println("------------------------------------------------------------------");
        System.out.print("Appointment ID: ");
        String appID = unassign.next();
        System.out.println("------------------------------------------------------------------");
        System.out.println();

        // find existing appointment
        Appointment selectedAppointment = null;
        for (Appointment appt : unasgPhoto.getAppointmentList()) {
            if (appt.getAppointmentID().equals(appID)) {
                selectedAppointment = appt;
                break;
            }
        }

        //Appointment not exist
        if (selectedAppointment == null) {
            System.out.println("Appointment not found!");
            System.out.println();
            return;
        }

        //To tell the admin which the there is no photographer to be remove from the appointment due to no photographer assigned
        if (selectedAppointment.getPhotographerID().equals("N/A") || selectedAppointment.getPhotographerName().equals("N/A")) {
            System.out.println("No photographer is currently assigned to this appointment.");
            System.out.println();
            return;
        }

        while(true) {
            //confirmation to remove photographer
            System.out.println("Photographer (ID: " + selectedAppointment.getPhotographerID() + ") is currently assigned to appointment " + selectedAppointment.getAppointmentID());
            System.out.println("Do you want to remove the photographer assignment? (Y/N)");
            System.out.println("---------------------------------------------------------");
            System.out.print("Confirmation: ");
            String confirm = unassign.next();
            System.out.println("---------------------------------------------------------");
            System.out.println();

            if (confirm.equalsIgnoreCase("Y")) {
                selectedAppointment.setPhotographerID("N/A");
                selectedAppointment.setPhotographerName("N/A");
                System.out.println("Photographer assignment removed successfully!");
                System.out.println();
                break;
            } else if (confirm.equalsIgnoreCase("N")) {
                System.out.println("Operation cancelled.");
                System.out.println();
                break;
            } else {
                System.out.println("Invalid Input. Please enter (Y/N)");
                System.out.println();
            }
        }

        //Update the appointment record in the file
        unasgPhoto.updateAppointmentData(FileManager.APPOINTMENT_FILE, selectedAppointment);
        unasgPhoto.readAppointmentData(FileManager.APPOINTMENT_FILE);
    }
}
