package PhotographyStudio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client extends User {

    private int totalBookings;     
    private double totalSpent;                

    public Client(String aUserID, String aUsername, String aPassword, String aName, String theGender, 
                    String anEmail, String aPhoneNumber, String aRole, int theTotalBookings, double theTotalSpent) {
        super(aUserID, aUsername, aPassword, aName, theGender, anEmail, aPhoneNumber, aRole);
        totalBookings = theTotalBookings;
        totalSpent = theTotalSpent;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalBookings(int newTotalBookings) {
        totalBookings = newTotalBookings;
    }

    public void setTotalSpent(double newTotalSpent) {
        totalSpent = newTotalSpent;
    }

    //Allow client to view their profile
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
        System.out.println("Total Booking   : " + getTotalBookings());
        System.out.println("Total Spending  : " + getTotalSpent());
        System.out.println();
        System.out.println();
    }

    //allow client to make changes to their current profile
    @Override
    public void editProfile(FileManager updateProfile, Scanner newValue) {             
        int profileChange = 0;  //to assign input to this variable

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
                System.out.print("Enter new username: ");
                setUsername(newValue.nextLine());
            }
            case 2 -> {
                while (true) {

                    System.out.print("Enter your current password: ");
                    String oldPassword = newValue.nextLine().trim();

                    if (!oldPassword.equals(getPassword())) {
                        System.out.println("Incorrect current password. Please try again.");
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
                System.out.print("Enter new name: ");
                setName(newValue.nextLine());
            }
            case 4 -> {
                OUTER:
                while (true) {
                    System.out.print("Enter new Gender ((M)MALE/(F)FEMALE): ");
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
                System.out.print("Enter new email: ");
                String email = newValue.nextLine();

                //ensure gmail complies to format @gmail.com
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
                System.out.println("Invalid Input. Please enter a valid input (e.g. 1, 2, 3).");
                System.out.println();
            }
        }

        //update the modifications to file
        updateProfile.updateClientData(FileManager.CLIENT_FILE, this);
        //Update user's details saved on the appointment data
        updateProfile.readClientData(FileManager.CLIENT_FILE);
        //update the profile details related to the appointment
        updateProfile.updateAllAppointmentData(FileManager.APPOINTMENT_FILE);
        //Read the new updated appountments data
        updateProfile.readAppointmentData(FileManager.APPOINTMENT_FILE);

        for (int i = 0; i < updateProfile.getClientList().size(); i++) {
            if (updateProfile.getClientList().get(i).getUserID().equals(userID)) {
                updateProfile.getClientList().set(i, this);
            }
        }
    }

    public void bookPhotographyPackage(Scanner bookPackage, FileManager book) {

        boolean existPackage = false;

        System.out.println("Enter the photography package ID you wish to book");
        System.out.println("--------------------------------------------------");
        System.out.print("Package ID: ");
        String photoPackageID = bookPackage.next();
        bookPackage.nextLine();
        System.out.println("--------------------------------------------------");
        System.out.println();

        for(int i = 0; i < book.getPortraitList().size(); i++) {
            if(book.getPortraitList().get(i).getPhotoSessionID().equals(photoPackageID)) {
                existPackage = true;
                break;
            } 
        }

        for(int i = 0; i < book.getWeddingList().size(); i++) {
            if(book.getWeddingList().get(i).getPhotoSessionID().equals(photoPackageID)) {
                existPackage = true;
                break;
            } 
        }

        for(int i = 0; i < book.getEventList().size(); i++) {
            if(book.getEventList().get(i).getPhotoSessionID().equals(photoPackageID)) {
                existPackage = true;
                break;
            } 
        }

        for(int i = 0; i < book.getProductList().size(); i++) {
            if(book.getProductList().get(i).getPhotoSessionID().equals(photoPackageID)) {
                existPackage = true;
                break;
            } 
        }

        if(!existPackage) {
            System.out.println("Photography Package ID not found.");
            System.out.println();
            return;
        }


        String appointmentID = IDGenerator.generateRandomAppointmentID(10);
        String clientID = getUserID();
        String clientName = getName();
        String photographyID = "N/A";
        String photographyName = "N/A";
        String appointmentDate;
        String inputStartTime, inputEndTime;    //client's time input 
        LocalTime validStartTime, validEndTime;     //a variable to hold validation of client's input to ensure correctness
        String status = "BOOKED";
        String location = "ABC Studio";     //The location name of the studio


         while (true) {
            DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
            System.out.println("Enter the appointment date (DD/MM/YYYY) ");
            System.out.println("------------------------------------------------");
            System.out.print("Date: ");
            String input = bookPackage.nextLine().trim();
            System.out.println("------------------------------------------------");
            System.out.println();

            if (input.isEmpty()) {
                System.out.println("Date cannot be empty! Please enter a valid date.");
                continue;
            }

            try {
                LocalDate date = LocalDate.parse(input, dateFmt);
                LocalDate today = LocalDate.now();
                
                // Optional: Validate that date is not in the past
                if (date.isBefore(today)) {
                    System.out.println("Cannot book appointments for past dates. Please enter a future date.");
                    continue;
                }
                
                appointmentDate = input; // Store the validated input
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date input! Please use format DD/MM/YYYY (e.g., 28/07/2025).");
            }
        }



        //Specify Time Format
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");

        while (true) {
            try {
                System.out.println("Enter start time (HH:MM) ");
                System.out.println("------------------------------------------------");
                System.out.print("Start Time: ");
                inputStartTime = bookPackage.nextLine().trim();
                System.out.println("------------------------------------------------");
                System.out.println();

                //Parse and compare the input time with the specified time format to ensure valid
                validStartTime = LocalTime.parse(inputStartTime, timeFmt);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time input! Please enter in the format HH.MM (e.g., 09:30).");
                System.out.println();
            }
        }

        //to ensure the end time is only after the start time 
        while (true) {
            try {
                System.out.println("Enter end time (HH:MM): ");
                System.out.println("------------------------------------------------");
                System.out.print("End time: ");
                inputEndTime = bookPackage.nextLine().trim();
                System.out.println("------------------------------------------------");
                System.out.println();

                //Parse and compare the input end time with the specified time format
                validEndTime = LocalTime.parse(inputEndTime, timeFmt);

                //To ensure logic, end time is after start time
                if (!validEndTime.isAfter(validStartTime)) {
                    System.out.println("End time must be after start time.");
                    System.out.println();
                    continue;
                }
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time input! Please enter in the format HH:MM (e.g., 11:00).");
                System.out.println();
            }
        }

        Appointment appointment = new Appointment(appointmentID, clientID, clientName, photographyID, photographyName, appointmentDate, inputStartTime, inputEndTime, status, location);
        book.addAppointmentData(FileManager.APPOINTMENT_FILE, appointment);     //Add the appointment record to file
        book.readAppointmentData(FileManager.APPOINTMENT_FILE);         //Read the new appointment data
    }


    //allow client to appoint a customized photography session
    public void customBookingPhotographySession(Scanner bookService, FileManager newData) {

        String currentUserID = getUserID();
        String currentUserName = getName();

        String photographerID = "N/A";      //No photographerID since the appointment just created and has to wait for a photographer to be assigned
        String photographerName = "N/A";    //No photographer Name since the appointment just created and has to wait for a photographer to be assigned
        String status = "BOOKED";
        String locationName;

        //choose photography session type
        String photoSessionType = null;
        while (true) {
            System.out.println("Enter the Photography Type you want to book: ");
            System.out.println("(1) Portrait & People Photography");
            System.out.println("(2) Wedding Photography");
            System.out.println("(3) Event Photography");
            System.out.println("(4) Product & Commercial Photography");
            System.out.println("---------------------");
            System.out.print("Choice: ");
            String choice = bookService.next().trim();
            bookService.nextLine();
            System.out.println("---------------------");
            System.out.println();

            switch (choice) {
                case "1" -> { 
                    photoSessionType = "PORTRAIT";
                }
                case "2" -> {
                    photoSessionType = "WEDDING";
                }
                case "3" -> {
                    photoSessionType = "EVENT";
                }
                case "4" -> {
                    photoSessionType = "PRODUCT";
                }
                default -> {
                    System.out.println("Invalid option. Please enter 1-4.");
                    continue;
                }
            }
            break;
        }

        //Generate an appointmentID
        String appointmentID = IDGenerator.generateRandomAppointmentID(10);
        double price = 0.0;
        String isAPackage = "NO";
        String appointmentDate;

        if (null != photoSessionType) // Enter photography session type details
        //Portrait Photography Session customization
        switch (photoSessionType) {
            case "PORTRAIT" -> {
                String photoSessionName = "Customer Custom Portrait Session";

                //Attributes for portrait photography session ID
                int numOfPeople;
                String backdropStyle;
                String printPackage;
                String portraitType;

                //generate an unique id for portrait photography session
                String portraitID = IDGenerator.generateRandomPortraitSessionID(10);

                while (true) {
                    System.out.println("Enter number of people ");
                    System.out.println("-----------------------------------------");
                    System.out.print("Number of People: ");
                    String raw = bookService.nextLine().trim();
                    System.out.println("-----------------------------------------");
                    System.out.println();
                    
                    //To make sure valid number input
                    try {
                        numOfPeople = Integer.parseInt(raw);
                        if (numOfPeople <= 0) {
                            System.out.println("Please enter a valid number (More than 0).");
                            System.out.println();
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Input. Please enter a whole number (e.g. 1, 2, 3).");
                        System.out.println();
                    }
                }       
                
                price = 200 + (numOfPeople * 50);
                System.out.println("Enter backdrop style ");
                System.out.println("-----------------------------------------");
                System.out.print("Style: ");
                backdropStyle = bookService.nextLine().trim();
                System.out.println("-----------------------------------------");

                while (true) {
                    System.out.println("Do you want to include print package? (Y/N) ");
                    System.out.println("-----------------------------------------");
                    System.out.print("Choice: ");
                    printPackage = bookService.nextLine().trim();
                    System.out.println("-----------------------------------------");
                    System.out.println();

                    if (printPackage.equalsIgnoreCase("Y")) {
                        printPackage = "YES";
                        price += 100;
                        break;
                    } else if (printPackage.equalsIgnoreCase("N")) {
                        printPackage = "NO";
                        break;
                    } else {
                        System.out.println("Invalid Input. Please enter Y or N.");
                        System.out.println();
                    }
                }
                       
                    System.out.println("Enter portrait type (e.g., family, graduation, solo) ");
                    System.out.println("------------------------------------------------");
                    System.out.print("Portrait Type: ");
                    portraitType = bookService.nextLine().trim();
                    System.out.println("------------------------------------------------");
                    System.out.println();

                    //create portrait object and save it to the arrayList and the text file
                    Portrait portraitData = new Portrait(portraitID, photoSessionName, photoSessionType, price, isAPackage, numOfPeople, backdropStyle, printPackage, portraitType, appointmentID);
                    newData.getPortraitList().add(portraitData);
                    newData.addPortraitSession(FileManager.PORTRAIT_FILE, portraitData);
            }

            case "WEDDING" -> {
                String photoSessionName = "Customer Custom Wedding Session";
                //specific attributes for wedding photography session
                int numOfGuests;
                String albumInclusion;
                String videoInclusion;
                String cultureStyle;

                //generate a unique ID for wedding photography session
                String weddingID = IDGenerator.generateRandomWeddingSessionID(10);
                while (true) {
                    System.out.println("Enter number of guests ");
                    System.out.println("-----------------------------------------");
                    System.out.print("Number of Guests: ");
                    String raw = bookService.nextLine().trim();
                    System.out.println("-----------------------------------------");
                    System.out.println();

                    try {
                        numOfGuests = Integer.parseInt(raw);
                        if (numOfGuests <= 0) {
                            System.out.println("Please enter valid number (More than 0).");
                            System.out.println();
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a whole number (e.g. 30, 40, 50).");
                        System.out.println();
                    }
                }       
                
                // Base price for wedding (adjust as needed)
                price = 3000;
                if (numOfGuests >= 100)
                    price += 1000;

                while (true) {
                    System.out.println("Include album? (Y/N): ");
                    System.out.println("-----------------------------------------");
                    System.out.print("Choice: ");
                    albumInclusion = bookService.nextLine().trim();
                    System.out.println("-----------------------------------------");
                    
                    if (albumInclusion.equalsIgnoreCase("Y")) {
                        albumInclusion = "YES";
                        price += 350;
                        break;
                    } else if (albumInclusion.equalsIgnoreCase("N")) {
                        albumInclusion = "NO";
                        break;
                    } else {
                        System.out.println("Invalid Input. Please enter Y or N.");
                        System.out.println();
                    }
                }       
                
                while (true) {
                    System.out.println("Include videography? (Y/N): ");
                    System.out.println("-----------------------------------------");
                    System.out.println("Choice: ");
                    videoInclusion = bookService.nextLine().trim();
                    System.out.println("-----------------------------------------");

                    if (videoInclusion.equalsIgnoreCase("Y")) {
                        videoInclusion = "YES";
                        price += 800;
                        break;
                    } else if (videoInclusion.equalsIgnoreCase("N")) {
                        videoInclusion = "NO";
                        break;
                    } else {
                        System.out.println("Invalid Input. Please enter Y or N.");
                        System.out.println();
                    }
                }       
                
                System.out.println("Enter wedding culture style (e.g., traditional, modern): ");
                System.out.println("--------------------------------------------------------------");
                System.out.print("Style: ");
                cultureStyle = bookService.nextLine().trim();
                System.out.println("--------------------------------------------------------------");
                System.out.println();

                //create wedding object and save it to the arrayList and the text file
                Wedding weddingData = new Wedding(weddingID, photoSessionName, photoSessionType, price, isAPackage, numOfGuests, albumInclusion, videoInclusion, cultureStyle, appointmentID);
                newData.getWeddingList().add(weddingData);
                newData.addWeddingSession(FileManager.WEDDING_FILE, weddingData);
            }

            case "EVENT" -> {
                String photoSessionName = "Customer Custom Event Photography Session";
                //Specific attributes for event photography session
                String eventType;
                int numOfGuests;
                //Generate unique ID for the event photography session
                String eventID = IDGenerator.generateRandomEventSessionID(10);

                System.out.println("Enter event type (e.g., conference, birthday, concert) ");
                System.out.println("----------------------------------------------------------");
                System.out.print("Type: ");
                eventType = bookService.nextLine().trim();
                System.out.println("----------------------------------------------------------");
                System.out.println();

                while (true) {
                    System.out.println("Enter number of guests ");
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Number of Guests: ");
                    String raw = bookService.nextLine().trim();
                    System.out.println("----------------------------------------------------------");
                    System.out.println();

                    //To ensure valid number input
                    try {
                        numOfGuests = Integer.parseInt(raw);
                        if (numOfGuests <= 0) {
                            System.out.println("Please enter a valid number (more than 0).");
                            System.out.println();
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Input. Please enter a whole number (e.g. 30, 31, 32).");
                        System.out.println();
                    }
                }       
                
                // Base price for events (adjust as needed)
                price = 800;

                if (numOfGuests > 50)
                    price += (numOfGuests - 50) * 60;
                //create event object and save it to the arrayList and the text file
                Event eventData = new Event(eventID, photoSessionName, photoSessionType, price, isAPackage, eventType, numOfGuests, appointmentID);
                newData.getEventList().add(eventData);
                newData.addEventSession(FileManager.EVENT_FILE, eventData);
                }

            case "PRODUCT" -> {
                String photoSessionName = "Customer Custom Product Photography Session";

                //Specific attributes for product photography session
                int numOfProducts;
                String productType;
                String editingLevel;

                //Generate unique ID for Product photography session
                String productID = IDGenerator.generateRandomProductSessionID(10);
                System.out.println("Enter product type (e.g., clothing, food, electronics) ");
                System.out.println("----------------------------------------------------------");
                System.out.print("Type: ");
                productType = bookService.nextLine().trim();
                System.out.println("----------------------------------------------------------");
                System.out.println();

                while (true) {
                    System.out.println("Enter number of products ");
                    System.out.println("----------------------------------------------------------");
                    System.out.print("Number of Products: ");
                    String raw = bookService.nextLine().trim();
                    System.out.println("----------------------------------------------------------");
                    System.out.println();

                    try {
                        numOfProducts = Integer.parseInt(raw);
                        if (numOfProducts <= 0) {
                            System.out.println("Please enter a valid number (More than 0).");
                            System.out.println();
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Input. Please enter a whole number (e.g. 5, 6, 7).");
                        System.out.println();
                    }
                }       
                
                // Base price for product session
                price = 150 + numOfProducts * 35;
                //allow client to enter the editing level for the product photos

                while (true) {
                    System.out.println("Enter editing level (basic, advanced, premium) ");
                    System.out.println("----------------------------------------------------------");
                    System.out.print("Level: ");
                    editingLevel = bookService.nextLine().trim();
                    System.out.println("----------------------------------------------------------");
                    System.out.println();
                    
                    if (editingLevel.equalsIgnoreCase("basic")) {
                        price += 50;
                        editingLevel = "BASIC";
                        break;
                    } else if (editingLevel.equalsIgnoreCase("advanced")) {
                        price += 150;
                        editingLevel = "ADVANCED";
                        break;
                    } else if (editingLevel.equalsIgnoreCase("premium")) {
                        price += 300;
                        editingLevel = "PREMIUM";
                        break;
                    } else {
                        System.out.println("Invalid Input. Please enter basic, advanced, or premium.");
                    }
                }       //create product object and save it to the arrayList and the text file
                Product productData = new Product(productID, photoSessionName, photoSessionType, price, isAPackage, productType, numOfProducts, editingLevel, appointmentID);
                newData.getProductList().add(productData);
                newData.addProductSession(FileManager.PRODUCT_FILE, productData);
                }
            default -> {
            }
        }

        
        //Allow client to choose their the date of appointment
        while (true) {
            DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
            System.out.println("Enter the appointment date (DD/MM/YYYY) ");
            System.out.println("------------------------------------------------");
            System.out.print("Date: ");
            String input = bookService.nextLine().trim();
            System.out.println("------------------------------------------------");
            System.out.println();

            if (input.isEmpty()) {
                System.out.println("Date cannot be empty! Please enter a valid date.");
                continue;
            }

            try {
                LocalDate date = LocalDate.parse(input, dateFmt);
                LocalDate today = LocalDate.now();
                
                // Optional: Validate that date is not in the past
                if (date.isBefore(today)) {
                    System.out.println("Cannot book appointments for past dates. Please enter a future date.");
                    continue;
                }
                
                appointmentDate = input; // Store the validated input
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date input! Please use format DD/MM/YYYY (e.g., 28/07/2025).");
            }
        }

        //let client to choose the time for the appointment
        String inputStartTime, inputEndTime;    //client's input
        LocalTime validStartTime, validEndTime;     //a variable to hold validation of client's input to ensure correctness

        //Specify Time Format
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");

        while (true) {
            try {
                System.out.println("Enter start time (HH:MM) ");
                System.out.println("------------------------------------------------");
                System.out.print("Start Time: ");
                inputStartTime = bookService.nextLine().trim();
                System.out.println("------------------------------------------------");
                System.out.println();

                //Parse and compare the input time with the specified time format to ensure valid
                validStartTime = LocalTime.parse(inputStartTime, timeFmt);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time input! Please enter in the format HH.MM (e.g., 09:30).");
                System.out.println();
            }
        }

        //to ensure the end time is only after the start time 
        while (true) {
            try {
                System.out.println("Enter end time (HH:MM): ");
                System.out.println("------------------------------------------------");
                System.out.print("End time: ");
                inputEndTime = bookService.nextLine().trim();
                System.out.println("------------------------------------------------");
                System.out.println();

                //Parse and compare the input end time with the specified time format
                validEndTime = LocalTime.parse(inputEndTime, timeFmt);

                //To ensure logic, end time is after start time
                if (!validEndTime.isAfter(validStartTime)) {
                    System.out.println("End time must be after start time.");
                    System.out.println();
                    continue;
                }
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time input! Please enter in the format HH:MM (e.g., 11:00).");
                System.out.println();
            }
        }

        //Create a FileManager object to get the appointments data stored in the appointment arrayList 
        ArrayList<Appointment> appointmentList = newData.getAppointmentList();

        //Ensure appointment does not have time conflict with other existing appointments
        boolean timeClash = false;
        for (Appointment other : appointmentList) {
            if (!"BOOKED".equalsIgnoreCase(other.getStatus())) 
                continue;
            if (!currentUserID.equals(other.getClientID())) 
                continue;
            if (!appointmentDate.equals(other.getAppointmentDate())) 
                continue;

            //parse and validate the appointment time of other existing appointments
            LocalTime existingStart = LocalTime.parse(other.getStartTime(), timeFmt);
            LocalTime existingEnd   = LocalTime.parse(other.getEndTime(),   timeFmt);

            //checks for time clashing
            boolean overlap = !(validEndTime.isBefore(existingStart) || validStartTime.isAfter(existingEnd));
            if (overlap) {
                timeClash = true;
                System.out.println("Unable to book due to time clash with an existing appointment");
                System.out.println("Appointment ID: " + other.getAppointmentID() + " (" + other.getStartTime() + " - " + other.getEndTime() + ")");
                break;
            }
        }

        if (timeClash) {
            System.out.println("Unable to book photography session due to time conflict.");
            return;
        }

        System.out.println("Please enter the appointment's location ");
        System.out.println("------------------------------------------------");
        System.out.print("Location Name: ");
        locationName = bookService.nextLine();
        System.out.println("------------------------------------------------");

        
        //A variable to hold asking client to confirm booking
        String confirmBook;

        //To ensure valid input
        while (true) {
            System.out.print("Are you sure you want to confirm the customized booking? (Y/N): ");
            confirmBook = bookService.nextLine().trim();

            if (confirmBook.equalsIgnoreCase("Y") || confirmBook.equalsIgnoreCase("N")) {
                break;
            }

            System.out.println("Please enter (Y) or (N).");
        }

        if (confirmBook.equalsIgnoreCase("Y")) {
            //create appointment object and store to arrayList;
            Appointment customAppoint = new Appointment(appointmentID, currentUserID, currentUserName, photographerID, photographerName,
                                                        appointmentDate, inputStartTime, inputEndTime, status, locationName);
            appointmentList.add(customAppoint);

            //Write the new appointment data to the file
            newData.addAppointmentData(FileManager.APPOINTMENT_FILE, customAppoint);

            
            System.out.println("Booking successful!");
            System.out.println("We will assign you a photographer in 3 working days.");
        } else {
            System.out.println("Booking has been cancelled.");
        }
    }

    //to get client's appointment details
    public void viewbookedAppointment(FileManager viewBooking) {
        boolean hasAppointments = false;

        System.out.println("============================================");
        System.out.println("          YOUR BOOKED APPOINTMENTS          ");
        System.out.println("============================================");
        
        for (Appointment p : viewBooking.getAppointmentList()) {
            if (getUserID().equals(p.getClientID())) {
                hasAppointments = true;
                // Print the appointment details
                System.out.println(p.toString());
                System.out.println("---------------------------------");
            }
        }

        //No Appointment found
        if (!hasAppointments) {
            System.out.println("You don't have any booked appointments.");
            System.out.println();
        }
}
    //Not finish
    public void modifyAppointmentDetails(String currentUserID, FileManager modifyAppointments, String filename, Scanner modify) {
        //A variable to hold user input    
        int choice = 0;
        int modifyChoice;

        // Get appointments data from FileManager
        ArrayList<Appointment> appointmentList = modifyAppointments.getAppointmentList();

        // Filter only this client's booked appointments
        ArrayList<Appointment> userAppointments = new ArrayList<>();
        for (Appointment appoint : appointmentList) {
            if (appoint.getClientID().equals(currentUserID) && appoint.getStatus().equalsIgnoreCase("BOOKED")) {
                userAppointments.add(appoint);
            }
        }

        if (userAppointments.isEmpty()) {
            System.out.println("You have no booked appointments to modify.");
            return;
        }

        //Show client's booked appointments
        System.out.println("Your booked appointments:");
        for (int i = 0; i < userAppointments.size(); i++) {
            Appointment appointment = userAppointments.get(i);
            System.out.println((i + 1) + ". " + appointment.getAppointmentID() + " (" + appointment.getAppointmentDate() + " "
                    + appointment.getStartTime() + "-" + appointment.getEndTime() + ")");
        }

        while (true) {
            System.out.println("Enter the number of the appointment you want to modify: ");
            System.out.println("----------------------------------------------------------");
            System.out.print("Choice: ");
        
            try {
                choice = modify.nextInt();
                modify.nextLine();
                System.out.println("----------------------------------------------------------");
                System.out.println();

                //To make sure legal input
                if (choice > 0 && choice <= userAppointments.size()) {
                    break;
                } else {
                    System.out.println("Invalid Choice.");
                    System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Please enter a number");
                System.out.println();
                modify.nextLine();
            }
        }

        //Get the selected appointment from array list
        Appointment selected = userAppointments.get(choice - 1);

        while (true) {
            try {
                //Choose appointment details to modify
                System.out.println("Which detail would you like to modify?");
                System.out.println("1. Appointment Date");
                System.out.println("2. Start Time");
                System.out.println("3. End Time");
                System.out.println("4. Location Details");
                System.out.println("5. Photography Session Details");

                System.out.println("------------------------");
                System.out.print("Choice: ");
                modifyChoice = modify.nextInt();
                modify.nextLine();
                System.out.println("------------------------");

                //validate input
                if (modifyChoice < 1 || modifyChoice > 5) {
                    System.out.println("Invalid Input. Please enter a valid number input (1 - 5)");
                    System.out.println();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Please enter a valid number input (1 - 5)");
                System.out.println();
                modify.nextLine();
            }
        }

        //Specify the input time (for case 3 and case 4)
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

        switch (modifyChoice) {
            case 1 -> {
                boolean dateUpdated = false;
                while (!dateUpdated) {
                    System.out.print("Enter new appointment date (DD/MM/YYYY): ");
                    try {
                        String input = modify.nextLine().trim();

                        // Validate input is not empty
                        if (input.isEmpty()) {
                            System.out.println("Date cannot be empty! Please enter a valid date.");
                            System.out.println();
                            continue;
                        }

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate date = LocalDate.parse(input, formatter);

                        //Ensure date input is not in the past
                        LocalDate today = LocalDate.now();
                        if (date.isBefore(today)) {
                            System.out.println("Cannot set appointment date in the past! Please enter a future date.");
                            System.out.println();
                            continue;
                        }

                        selected.setAppointmentDate(input);
                        modifyAppointments.updateAppointmentData(FileManager.APPOINTMENT_FILE, selected);
                        modifyAppointments.readAppointmentData(FileManager.APPOINTMENT_FILE);  //Read the new updated data
                        System.out.println("Appointment date updated successfully!");
                        System.out.println();
                        dateUpdated = true;

                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format! Please enter in format DD/MM/YYYY.");
                        System.out.println();
                    }
                }
            }
            case 2 -> {
                boolean timeUpdated = false;
                while (!timeUpdated) {
                    System.out.print("Enter new start time (HH:MM): ");
                    String input = modify.nextLine().trim();

                    // Validate input is not empty
                    if (input.isEmpty()) {
                        System.out.println("Time cannot be empty! Please enter a valid time.");
                        System.out.println();
                        continue;
                    }

                    try {
                        // Parse and validate the time format
                        LocalTime time = LocalTime.parse(input, timeFormat);

                        // Optional: Add business logic validation
                        // For example, ensure time is within business hours
                        if (time.isBefore(LocalTime.of(8, 0)) || time.isAfter(LocalTime.of(18, 0))) {
                            System.out.println("Time must be between 08:00 and 18:00!");
                            System.out.println();
                            continue;
                        }

                        selected.setStartTime(time.toString());
                        modifyAppointments.updateAppointmentData(FileManager.APPOINTMENT_FILE, selected);
                        modifyAppointments.readAppointmentData(FileManager.APPOINTMENT_FILE);  //Read the new updated data
                        System.out.println("Start time updated successfully!");
                        System.out.println();
                        timeUpdated = true;

                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid time format! Please use HH:MM format (e.g., 09:30, 14:45).");
                        System.out.println();
                    }
                }
            }
            case 3 -> {
                boolean timeUpdated = false;
                while (!timeUpdated) {
                    System.out.print("Enter new end time (HH:MM): ");
                    String input = modify.nextLine().trim();

                    // Validate input is not empty
                    if (input.isEmpty()) {
                        System.out.println("End time cannot be empty! Please enter a valid time.");
                        System.out.println();
                        continue;
                    }

                    try {
                        LocalTime newEndTime = LocalTime.parse(input, timeFormat);

                        // Parse the current start time for validation
                        LocalTime currentStartTime = LocalTime.parse(selected.getStartTime(), timeFormat);

                        // Validate end time is after start time
                        if (!newEndTime.isAfter(currentStartTime)) {
                            System.out.println("End time must be after the start time (" + selected.getStartTime() + ")!");
                            System.out.println();
                            continue;
                        }

                        selected.setEndTime(newEndTime.toString());
                        modifyAppointments.updateAppointmentData(FileManager.APPOINTMENT_FILE, selected);
                        modifyAppointments.readAppointmentData(FileManager.APPOINTMENT_FILE);   //Read the new updated data
                        System.out.println("End time updated successfully!");
                        System.out.println();
                        timeUpdated = true;

                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid time format! Please use HH:MM format (e.g., 09:30, 14:45).");
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println("Error processing time: " + e.getMessage());
                        System.out.println();
                    }
                }
            }

            case 4 -> {
                System.out.println("Enter new location name: ");
                String newLocation = modify.nextLine();
                selected.setLocationName(newLocation);
                modifyAppointments.updateAppointmentData(FileManager.APPOINTMENT_FILE, selected);
                modifyAppointments.readAppointmentData(FileManager.APPOINTMENT_FILE);       //Read the new updated data
            }

            case 5 -> {
                PhotographySession session = null;

                for(int i = 0; i < userAppointments.size(); i++) {
                    if(selected.getAppointmentID().equals(modifyAppointments.getPortraitList().get(i).getAppointmentID())) {
                        session = modifyAppointments.getPortraitList().get(i);
                        break;
                    } else if (selected.getAppointmentID().equals(modifyAppointments.getWeddingList().get(i).getAppointmentID())) {
                        session = modifyAppointments.getWeddingList().get(i);
                        break;
                    } else if (selected.getAppointmentID().equals(modifyAppointments.getEventList().get(i).getAppointmentID())) {
                        session = modifyAppointments.getEventList().get(i);
                        break;
                    } else {
                        session = modifyAppointments.getEventList().get(i);
                        break;
                    }
                }

                if (session == null) {
                    System.out.println("No photography session details found for this appointment.");
                    return;
                }

                //A variable to hold user input for selection to do modification 
                int productChoice;
                int portraitChoice;
                int weddingChoice;
                int eventChoice;

                System.out.println("\n--- Modify Photography Session Details ---");
                System.out.println("Current session type: " + session.getPhotoSessionType());

                //Switch to Handle different session types
                switch (session.getPhotoSessionType().toUpperCase()) {
                    case "PRODUCT" -> {
                        //Further Downcast to access subclass's (Product) attributes
                        Product productSession = (Product) session;

                        while (true) {
                            try {
                                System.out.println("Which product session detail would you like to modify?");
                                System.out.println("1. Product Type");
                                System.out.println("2. Number of Products");
                                System.out.println("3. Editing Level");
                                System.out.println("------------------------");
                                System.out.print("Choice: ");
                                productChoice = modify.nextInt();
                                modify.nextLine(); // Consume newline
                                System.out.println("------------------------");

                                if(productChoice < 1 || productChoice > 3) {
                                    System.out.println("Invalid Input. Please enter a valid number input (1 - 3)");
                                    System.out.println();
                                } else {
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid Input. Please enter a valid number input (1 - 3)");
                                System.out.println();
                                modify.nextLine();
                            }
                        }

                        //Another wrapped switch session to allow client to change the specific attributes based on the subclasses
                        switch (productChoice) {
                            case 1 -> {
                                System.out.print("Enter new product type: ");
                                productSession.setProductType(modify.nextLine());

                                //Update the file and read the new updated file
                                modifyAppointments.updateProductSession(FileManager.PRODUCT_FILE, productSession);
                                modifyAppointments.readProductService(FileManager.PRODUCT_FILE);
                                System.out.println("Product type updated!");
                            }
                            case 2 -> {
                                System.out.print("Enter new number of products: ");
                                try {
                                    int numProducts = modify.nextInt();
                                    modify.nextLine(); // Consume newline
                                    productSession.setNumOfProduct(numProducts);

                                    modifyAppointments.updateProductSession(FileManager.PRODUCT_FILE, productSession);
                                    modifyAppointments.readProductService(FileManager.PRODUCT_FILE);
                                    System.out.println("Number of products updated!");
                                } catch (Exception e) {
                                    System.out.println("Invalid number format!");
                                    modify.nextLine(); // Clear invalid input
                                }
                            }
                            case 3 -> {
                                System.out.print("Enter new editing level: ");
                                productSession.setEditingLevel(modify.nextLine());

                                modifyAppointments.updateProductSession(FileManager.PRODUCT_FILE, productSession);
                                modifyAppointments.readProductService(FileManager.PRODUCT_FILE);
                                System.out.println("Editing level updated!");
                                
                            }
                            default -> {
                                System.out.println("Invalid choice.");
                                return;
                            }
                        }
                    }

                    case "PORTRAIT" -> {

                        //Further Downcast to access subclass's (Portrait) attributes
                        Portrait portraitSession = (Portrait) session;

                        while(true) {
                            try {
                                System.out.println("Which portrait session detail would you like to modify?");
                                System.out.println("1. Number of People");
                                System.out.println("2. Backdrop Style");
                                System.out.println("3. Print Package");
                                System.out.println("4. Portrait Type");
                                System.out.println("------------------------");
                                System.out.print("Choice: ");

                                portraitChoice = modify.nextInt();
                                modify.nextLine(); // Consume newline
                                System.out.println("------------------------");

                                if(portraitChoice < 1 || portraitChoice > 4) {
                                    System.out.println("Invalid Input. Please enter a valid number input (1 - 4)");
                                    System.out.println();
                                } else {
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid Input. Please enter a valid number input (1 - 4)");
                                System.out.println();
                                modify.nextLine();
                            }
                        } 

                        //Another wrapped switch session to allow client to change the specific attributes based on the subclasses
                        switch (portraitChoice) {
                            case 1 -> {
                                System.out.print("Enter new number of people: ");
                                try {
                                    int numPeople = modify.nextInt();
                                    modify.nextLine(); // Consume newline
                                    portraitSession.setNumOfPeople(numPeople);

                                    modifyAppointments.updatePortraitSession(FileManager.PORTRAIT_FILE, portraitSession);
                                    modifyAppointments.readPortraitService(FileManager.PORTRAIT_FILE);
                                    System.out.println("Number of people updated!");
                                } catch (Exception e) {
                                    System.out.println("Invalid number format!");
                                    modify.nextLine(); // Clear invalid input
                                }
                            }
                            case 2 -> {
                                System.out.print("Enter new backdrop style: ");
                                portraitSession.setBackdropStyle(modify.nextLine());

                                modifyAppointments.updatePortraitSession(FileManager.PORTRAIT_FILE, portraitSession);
                                modifyAppointments.readPortraitService(FileManager.PORTRAIT_FILE);
                                System.out.println("Backdrop style updated!");
                            }
                            case 3 -> {
                                System.out.print("Enter new print package: ");
                                portraitSession.setPrintPackage(modify.nextLine());

                                modifyAppointments.updatePortraitSession(FileManager.PORTRAIT_FILE, portraitSession);
                                modifyAppointments.readPortraitService(FileManager.PORTRAIT_FILE);
                                System.out.println("Print package updated!");
                            }
                            case 4 -> {
                                System.out.print("Enter new portrait type: ");
                                portraitSession.setPortraitType(modify.nextLine());

                                modifyAppointments.updatePortraitSession(FileManager.PORTRAIT_FILE, portraitSession);
                                modifyAppointments.readPortraitService(FileManager.PORTRAIT_FILE);
                                System.out.println("Portrait type updated!");
                            }
                            default -> {
                                System.out.println("Invalid choice.");
                                return;
                            }
                        }
                    }

                    case "WEDDING" -> {
                        //Further Downcast to access subclass's (Wedding) attributes
                        Wedding weddingSession = (Wedding) session;

                        while(true) {
                            try {
                                System.out.println("Which wedding session detail would you like to modify?");
                                System.out.println("1. Number of Guests");
                                System.out.println("2. Album Inclusion");
                                System.out.println("3. Videography Inclusion");
                                System.out.println("4. Wedding Culture Style");
                                System.out.println("------------------------");
                                System.out.print("Choice: ");

                                weddingChoice = modify.nextInt();
                                modify.nextLine(); // Consume newline
                                System.out.println("------------------------");

                                if(weddingChoice < 1 || weddingChoice > 4) {
                                    System.out.println("Invalid Input. Please enter a valid number input (1 - 4)");
                                    System.out.println();
                                } else {
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid Input. Please enter a valid number input (1 - 4)");
                                System.out.println();
                                modify.nextLine();
                            }
                        }

                        //Another wrapped switch session to allow client to change the specific attributes based on the subclasses
                        switch (weddingChoice) {
                            case 1 -> {
                                System.out.print("Enter new number of guests: ");
                                try {
                                    int numGuests = modify.nextInt();
                                    modify.nextLine(); // Consume newline
                                    weddingSession.setNumOfGuest(numGuests);

                                    //update to file and read the new updated file
                                    modifyAppointments.updateWeddingSession(FileManager.WEDDING_FILE, weddingSession);
                                    modifyAppointments.readWeddingService(FileManager.WEDDING_FILE);
                                    System.out.println("Number of guests updated!");
                                } catch (Exception e) {
                                    System.out.println("Invalid number format!");
                                    modify.nextLine(); // Clear invalid input
                                }
                            }
                            case 2 -> {
                                System.out.print("Enter new album inclusion details: ");
                                weddingSession.setAlbumInclusion(modify.nextLine());

                                modifyAppointments.updateWeddingSession(FileManager.WEDDING_FILE, weddingSession);
                                modifyAppointments.readWeddingService(FileManager.WEDDING_FILE);
                                System.out.println("Album inclusion updated!");
                            }
                            case 3 -> {
                                System.out.print("Enter new videography inclusion details: ");
                                weddingSession.setVideographyInclusion(modify.nextLine());

                                modifyAppointments.updateWeddingSession(FileManager.WEDDING_FILE, weddingSession);
                                modifyAppointments.readWeddingService(FileManager.WEDDING_FILE);
                                System.out.println("Videography inclusion updated!");
                            }
                            case 4 -> {
                                System.out.print("Enter new wedding culture style: ");
                                weddingSession.setWeddingCultureStyle(modify.nextLine());

                                modifyAppointments.updateWeddingSession(FileManager.WEDDING_FILE, weddingSession);
                                modifyAppointments.readWeddingService(FileManager.WEDDING_FILE);
                                System.out.println("Wedding culture style updated!");
                            }
                            default -> {
                                System.out.println("Invalid choice.");
                                return;
                            }
                        }
                    }

                    case "EVENT" -> {
                        //Further Downcast to access subclass's (Event) attributes
                        Event eventSession = (Event) session;
                        while(true) {
                            try {
                                System.out.println("Which event session detail would you like to modify?");
                                System.out.println("1. Number of Guests");
                                System.out.println("2. Event Type");
                                System.out.println("------------------------");
                                System.out.print("Choice: ");

                                eventChoice = modify.nextInt();
                                modify.nextLine(); // Consume newline
                                System.out.println("------------------------");
                                System.out.println();

                                if(eventChoice < 1 || eventChoice > 2) {
                                    System.out.println("Invalid Input. Please enter a valid number input (1/2)");
                                    System.out.println();
                                } else {
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid Input. Please enter a valid number input (1/2)");
                                System.out.println();
                            }
                        }

                        //Another wrapped switch session to allow client to change the specific attributes based on the subclasses
                        switch (eventChoice) {
                            case 1 -> {
                                System.out.print("Enter new number of guests: ");
                                try {
                                    int numGuests = modify.nextInt();
                                    modify.nextLine(); // Consume newline
                                    eventSession.setNumOfGuest(numGuests);

                                    modifyAppointments.updateEventSession(FileManager.EVENT_FILE, eventSession);
                                    modifyAppointments.readEventService(FileManager.EVENT_FILE);
                                    System.out.println("Number of guests updated!");
                                } catch (Exception e) {
                                    System.out.println("Invalid number format!");
                                    modify.nextLine(); // Clear invalid input
                                }
                            }
                            case 2 -> {
                                System.out.print("Enter new event type: ");
                                eventSession.setEventType(modify.nextLine());

                                modifyAppointments.updateEventSession(FileManager.EVENT_FILE, eventSession);
                                modifyAppointments.readEventService(FileManager.EVENT_FILE);
                                System.out.println("Event type updated!");
                            }
                            default -> {
                                System.out.println("Invalid choice.");
                                return;
                            }
                        }
                    }

                    default -> {
                        System.out.println("Unknown session type: " + session.getPhotoSessionType());
                        return;
                    }
                }
                System.out.println("Photography session details updated successfully!");
            }

            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        //Save updated appointments back to file
        modifyAppointments.updateAppointmentData(FileManager.APPOINTMENT_FILE, selected);

        System.out.println("Appointment updated successfully!");
    }

    //To cancel an appointment
    public void cancelAppointment(FileManager fileManager, Scanner cancelAppoint) {
        ArrayList<Appointment> appointments = fileManager.getAppointmentList();

        if (appointments.isEmpty()) {
            System.out.println("No appointments found to cancel.");
            return;
        }

        // Display appointments
        System.out.println("\nYour Appointments:");
        System.out.println("============================================================");
        for (Appointment app : appointments) {
            if (app.getClientID().equals(this.getUserID())) { // Show only current user's appointments
                System.out.println("ID: " + app.getAppointmentID() + " | Date: " + app.getAppointmentDate() + " | Time: " + app.getStartTime() + " - " + app.getEndTime() + " | Status: " + app.getStatus());
            }
        }
        System.out.println("============================================================");

        // Get appointment ID
        System.out.print("Enter the Appointment ID to cancel (or 'exit' to cancel): ");
        String appointmentId = cancelAppoint.nextLine().trim();

        //allow user to exit the cancellation section
        if ("exit".equalsIgnoreCase(appointmentId)) {
            System.out.println("Cancellation process cancelled.");
            return;
        }

        // Find the appointment
        Appointment appointmentToCancel = null;
        for (Appointment app : appointments) {
            if (app.getAppointmentID().equals(appointmentId)
                    && app.getClientID().equals(this.getUserID())) { // Ensure user owns this appointment
                appointmentToCancel = app;
                break;
            }
        }

        if (appointmentToCancel == null) {
            System.out.println("Appointment not found.");
            return;
        }

        //Check whether the appointment is already cancelled or not
        if ("CANCELLED".equalsIgnoreCase(appointmentToCancel.getStatus())) {
            System.out.println("This appointment is already cancelled.");
            return;
        }

        // Ask for confirmation
        System.out.println("\nYou are going to cancel:");
        System.out.println("Appointment ID: " + appointmentToCancel.getAppointmentID());
        System.out.println("Date: " + appointmentToCancel.getAppointmentDate());
        System.out.println("Time: " + appointmentToCancel.getStartTime() + " - " + appointmentToCancel.getEndTime());
        System.out.print("Are you sure? (Y/N): ");
     
        while (true) {
            System.out.print("Confirm cancellation? (Y/N): ");
            String confirmation = cancelAppoint.nextLine().trim().toUpperCase(); // Read input here
            
            if ("Y".equals(confirmation)) {
                //mark the status as "CANCELLED"
                appointmentToCancel.setStatus("CANCELLED");

                //update the object's record
                try {
                    fileManager.updateAppointmentData(FileManager.APPOINTMENT_FILE, appointmentToCancel);
                    fileManager.readAppointmentData(FileManager.APPOINTMENT_FILE);
                    System.out.println("Appointment cancelled successfully!");
                    break; // Exit the loop after successful cancellation
                } catch (Exception e) {
                    System.out.println("Error updating appointment: " + e.getMessage());
                    appointmentToCancel.setStatus("BOOKED"); // Revert on error
                    break; // Exit the loop even on error
                }
            } else if ("N".equals(confirmation)) {
                System.out.println("Cancellation cancelled.");
                break; // Exit the loop
            } else {
                System.out.println("Invalid input! Please enter 'Y' for Yes or 'N' for No.");
                // Loop continues to ask for valid input
            }
        }
    }

}