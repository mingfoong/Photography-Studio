package PhotographyStudio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;   //To handle file errors
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//A class to handle files input and output
public class FileManager {
    //define the file name
    public static final String APPOINTMENT_FILE = "AppointmentData.txt";
    public static final String PORTRAIT_FILE = "PortraitData.txt";
    public static final String WEDDING_FILE = "WeddingData.txt";
    public static final String EVENT_FILE = "EventData.txt";
    public static final String PRODUCT_FILE = "ProductData.txt";
    public static final String CLIENT_FILE = "ClientData.txt";
    public static final String PHOTOGRAPHER_FILE = "PhotographerData.txt";
    public static final String ADMIN_FILE = "AdminData.txt";

    //arrayList to store object for input output purposes
    private final ArrayList<Client> clientList = new ArrayList<>();
    private final ArrayList<Photographer> photographerList = new ArrayList<>();
    private final ArrayList<Admin> adminList = new ArrayList<>();
    private final ArrayList<Appointment> appointmentList = new ArrayList<>();
    private final ArrayList<Portrait> portraitList = new ArrayList<>();
    private final ArrayList<Wedding> weddingList = new ArrayList<>();
    private final ArrayList<Event> eventList = new ArrayList<>();
    private final ArrayList<Product> productList = new ArrayList<>();

    //getters for arrayList
    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public ArrayList<Photographer> getPhotographerList() {
        return photographerList;
    }

    public ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public ArrayList<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public ArrayList<Portrait> getPortraitList() {
        return portraitList;
    }

    public ArrayList<Wedding> getWeddingList() {
        return weddingList;
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
  

    //read client's data from text file
    public void readClientData(String filename) {
        clientList.clear(); // make sure we don’t duplicate data

        try (Scanner readData = new Scanner(new File(filename))) {
            while (readData.hasNextLine()) {
                String line = readData.nextLine().trim();

                // skip empty lines
                if (line.isEmpty()) continue;

                String[] data = line.split(",");

                // validate number of fields (10 expected for Client)
                if (data.length < 10) {
                    System.out.println("Invalid client record, skipping: " + line);
                    continue;
                }

                try {
                    String userID = data[0];
                    String username = data[1];
                    String password = data[2];
                    String name = data[3];
                    String gender = data[4];
                    String email = data[5];
                    String phoneNumber = data[6];
                    String role = data[7];
                    int totalBookings = Integer.parseInt(data[8]);
                    double totalSpent = Double.parseDouble(data[9]);

                    //Initialize the object
                    Client client = new Client(userID, username, password, name, gender, email, phoneNumber, role, totalBookings, totalSpent);
                    clientList.add(client);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number in client record, skipping: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Client file not found: " + filename);
        }
    }

    //Read photographer data from file
    public void readPhotographerData(String filename) {
        photographerList.clear(); // prevent duplicates

        try (Scanner readData = new Scanner(new File(filename))) {
            while (readData.hasNextLine()) {
                String line = readData.nextLine().trim();

                if (line.isEmpty()) continue;

                String[] data = line.split(",");

                if (data.length < 10) {
                    System.out.println("Invalid photographer record, skipping: " + line);
                    continue;
                }

                try {
                    String userID = data[0];
                    String username = data[1];
                    String password = data[2];
                    String name = data[3];
                    String gender = data[4];
                    String email = data[5];
                    String phoneNumber = data[6];
                    String role = data[7];
                    String specialization = data[8];
                    int experienceYears = Integer.parseInt(data[9]);

                    Photographer photographer = new Photographer(
                        userID, username, password, name, gender,
                        email, phoneNumber, role,
                        specialization, experienceYears
                    );
                    photographerList.add(photographer);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number in photographer record, skipping: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Photographer file not found: " + filename);
        }
    }

    //read admin data from file
    public void readAdminData(String filename) {
        adminList.clear(); // prevent duplicates data

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // skip empty lines
                if (line.isEmpty()) continue;

                String[] data = line.split(",");

                // validate number of fields (8 expected for Admin)
                if (data.length < 8) {
                    System.out.println("Invalid admin record, skipping: " + line);
                    continue;
                }

                String userID = data[0];
                String username = data[1];
                String password = data[2];
                String name = data[3];
                String gender = data[4];
                String email = data[5];
                String phoneNumber = data[6];
                String role = data[7];

                Admin admin = new Admin(userID, username, password, name,
                                        gender, email, phoneNumber, role);
                adminList.add(admin);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Admin file not found: " + filename);
        }
    }

    
    public void addClientData(Client newRegisterUser, String filename) {
        try {
            try (FileWriter writeData = new FileWriter(filename, true)) {
                writeData.write(newRegisterUser.getUserID() + ",");
                writeData.write(newRegisterUser.getUsername() + ",");
                writeData.write(newRegisterUser.getPassword() + ",");
                writeData.write(newRegisterUser.getName() + ",");
                writeData.write(newRegisterUser.getGender() + ",");
                writeData.write(newRegisterUser.getEmail() + ",");
                writeData.write(newRegisterUser.getPhoneNumber() + ",");
                writeData.write(newRegisterUser.getRole() + ",");
                writeData.write(newRegisterUser.getTotalBookings() + ",");
                writeData.write(newRegisterUser.getTotalSpent() + "\n");

                clientList.add(newRegisterUser);
            }
        } catch (IOException e) {
            System.out.println("Unable to write file. Please try again.");
        }
    }

    public void addPhotographerData(Photographer newRegisterUser, String filename) {
        try {
            try (FileWriter writeData = new FileWriter(filename, true)) {
                writeData.write(newRegisterUser.getUserID() + ",");
                writeData.write(newRegisterUser.getUsername() + ",");
                writeData.write(newRegisterUser.getPassword() + ",");
                writeData.write(newRegisterUser.getName() + ",");
                writeData.write(newRegisterUser.getGender() + ",");
                writeData.write(newRegisterUser.getEmail() + ",");
                writeData.write(newRegisterUser.getPhoneNumber() + ",");
                writeData.write(newRegisterUser.getRole() + ",");
                writeData.write(newRegisterUser.getSpecialization() + ",");
                writeData.write(newRegisterUser.getExperienceYears() + "\n");

                photographerList.add(newRegisterUser);
            }
        } catch (IOException e) {
            System.out.println("Unable to write file. Please try again.");
        }
    }

    public void addAdminData(Admin newRegisterUser, String filename) {
        try {
            try (FileWriter writeData = new FileWriter(filename, true)) {
                writeData.write(newRegisterUser.getUserID() + ",");
                writeData.write(newRegisterUser.getUsername() + ",");
                writeData.write(newRegisterUser.getPassword() + ",");
                writeData.write(newRegisterUser.getName() + ",");
                writeData.write(newRegisterUser.getGender() + ",");
                writeData.write(newRegisterUser.getEmail() + ",");
                writeData.write(newRegisterUser.getPhoneNumber() + ",");
                writeData.write(newRegisterUser.getRole() + "\n");

                adminList.add(newRegisterUser);
            }
        } catch (IOException e) {
            System.out.println("Unable to write file. Please try again.");
        }
    }

    public void updateClientData(String filename, Client updatedClient) {
        //Search for the specific client
        boolean clientFound = false;
        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getUserID().equals(updatedClient.getUserID())) {
                //update the specific client's data
                clientList.set(i, updatedClient);
                clientFound = true;
                break; 
                // Exit loop once found and updated
            }
        }
        
        if (!clientFound) {
            System.out.println("Error: Client with ID " + updatedClient.getUserID() + " not found.");
            return;
        }
        
        //Rewrite the entire file with the updated client list
        try (FileWriter writer = new FileWriter(filename, false)) {
            for (Client client : clientList) {
                writer.write(
                    client.getUserID() + "," +
                    client.getUsername() + "," +
                    client.getPassword() + "," +
                    client.getName() + "," +
                    client.getGender() + "," +
                    client.getEmail() + "," +
                    client.getPhoneNumber() + "," +
                    client.getRole() + "," +
                    client.getTotalBookings() + "," +
                    client.getTotalSpent() + "\n"
                );
            }
            System.out.println("Client data updated successfully for ID: " + updatedClient.getUserID());
            
        } catch (IOException e) {
            System.out.println("Error: Unable to update client file '" + filename + "'.");
            System.out.println("Please try again.");
        }
    }

    public void updatePhotographerData(String filename, Photographer updatedPhotographer) {
        //Search for the specific photographer
        boolean photographerFound = false;
        for (int i = 0; i < photographerList.size(); i++) {
            if (photographerList.get(i).getUserID().equals(updatedPhotographer.getUserID())) {
                photographerList.set(i, updatedPhotographer);
                photographerFound = true;
                break; 
                // Exit loop once found and updated
            }
        }
        
        //photographer not found
        if (!photographerFound) {
            System.out.println("Error: Photographer with ID " + updatedPhotographer.getUserID() + " not found.");
            return;
        }
        
        //Rewrite the entire file with the updated photographer list
        try (FileWriter writer = new FileWriter(filename, false)) {
            for (Photographer photographer : photographerList) {
                writer.write(
                    photographer.getUserID() + "," +
                    photographer.getUsername() + "," +
                    photographer.getPassword() + "," +
                    photographer.getName() + "," +
                    photographer.getGender() + "," +
                    photographer.getEmail() + "," +
                    photographer.getPhoneNumber() + "," +
                    photographer.getRole() + "," +
                    photographer.getSpecialization() + "," +
                    photographer.getExperienceYears() + "\n"
                );
            }
            System.out.println("Photographer data updated successfully for ID: " + updatedPhotographer.getUserID());
            
        } catch (IOException e) {
            System.out.println("Error: Unable to update photographer file '" + filename + "'.");
            System.out.println("Please try again.");
        }
    }

    public void updateAdminData(String filename, Admin updatedAdmin) {
        //Search for the specific client
        boolean adminFound = false;
        for (int i = 0; i < adminList.size(); i++) {
            if (adminList.get(i).getUserID().equals(updatedAdmin.getUserID())) {
                //update the specific client's data
                adminList.set(i, updatedAdmin);
                adminFound = true;
                break; 
                // Exit loop once found and updated
            }
        }
        
        if (!adminFound) {
            System.out.println("Error: Client with ID " + updatedAdmin.getUserID() + " not found.");
            return;
        }
        
        //Rewrite the entire file with the updated client list
        try (FileWriter writer = new FileWriter(filename, false)) { 
            for (Admin admin : adminList) {
                writer.write(admin.getUserID() + "," + admin.getUsername() + "," + admin.getPassword() + "," + admin.getName() + "," +
                    admin.getGender() + "," + admin.getEmail() + "," + admin.getPhoneNumber() + "," + admin.getRole() + "\n");
            }
            System.out.println("Admin data updated successfully for ID: " + updatedAdmin.getUserID());
            
        } catch (IOException e) {
            System.out.println("Error: Unable to update client file '" + filename + ".");
            System.out.println("Please try again.");
        }
    }


    //Get the appointment data from files
    public void readAppointmentData(String filename) {
        appointmentList.clear(); // prevent duplicates data

        try (Scanner readData = new Scanner(new File(filename))) {
            while (readData.hasNextLine()) {
                String line = readData.nextLine().trim();

                // skip empty lines
                if (line.isEmpty()) 
                    continue;

                String[] data = line.split(",");

                // validate number of fields (10 expected for Appointment)
                if (data.length < 10) {
                    System.out.println("Invalid appointment record, skipping: " + line);
                    continue;
                }

                try {
                    String appointmentID = data[0];
                    String clientID = data[1];
                    String clientName = data[2];
                    String photographerID = data[3];
                    String photographerName = data[4];
                    String appointmentDate = data[5];
                    String startTime = data[6];
                    String endTime = data[7];
                    String status = data[8];
                    String locationName = data[9];

                    Appointment appointment = new Appointment(appointmentID, clientID, clientName, photographerID,
                                                            photographerName, appointmentDate, startTime, endTime, status, locationName);
                    appointmentList.add(appointment);

                } catch (Exception e) {
                    System.out.println("Invalid appointment record, skipping: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Appointment file not found: " + filename);
        }
    }

    //Add appointment to file once client made a booking for an appointment
    public void addAppointmentData(String filename, Appointment appoint) {
        try {
            try (FileWriter writeAppointment = new FileWriter(filename, true)) {
                writeAppointment.write(appoint.getAppointmentID() + ",");
                writeAppointment.write(appoint.getClientID() + ",");
                writeAppointment.write(appoint.getClientName() + ",");
                writeAppointment.write(appoint.getPhotographerID() + ",");
                writeAppointment.write(appoint.getPhotographerName() + ",");
                writeAppointment.write(appoint.getAppointmentDate() + ",");
                writeAppointment.write(appoint.getStartTime() + ",");
                writeAppointment.write(appoint.getEndTime() + ",");
                writeAppointment.write(appoint.getStatus() + ",");
                writeAppointment.write(appoint.getLocationName() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to write file. Pleawse try again.");
        }
    }

    //update data in text file when user do modifications to the current appointment details
    public void updateAppointmentData(String filename, Appointment appointment) {
        if (appointment == null) {
            System.out.println("Error: Cannot update null appointment");
            return;
        }

        for(int i = 0; i < appointmentList.size(); i++) {
            if(appointmentList.get(i).getAppointmentID().equals(appointment.getAppointmentID())) {
                appointmentList.set(i, appointment); 
                break;
            }
        }
        
        try {
            //rewrite the data to update the appointment details
            try(FileWriter writeAppointment = new FileWriter(filename, false)) {
                for(Appointment app : appointmentList) {
                    writeAppointment.write(app.getAppointmentID() + "," + app.getClientID() + "," + app.getClientName() + "," + app.getPhotographerID() + "," + app.getPhotographerName() + "," +
                                            app.getAppointmentDate() + "," + app.getStartTime() + "," + app.getEndTime() + "," + app.getStatus() + "," + app.getLocationName() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to write file. Please try again");
        }
    }

    //For efficiency purpose, used to update the appointments once user made adjustment to their profile
    public void updateAllAppointmentData(String filename) {
        try (FileWriter writeAppointment = new FileWriter(filename, false)) {
            for(Appointment app : appointmentList) {
                writeAppointment.write(app.getAppointmentID() + "," + app.getClientID() + "," +  app.getClientName() + "," + app.getPhotographerID() + "," + 
                                    app.getPhotographerName() + "," + app.getAppointmentDate() + "," + app.getStartTime() + "," + app.getEndTime() + "," + app.getStatus() + "," + app.getLocationName() + "\n");
            }
            System.out.println("All appointment data updated successfully.");
        } catch (IOException e) {
            System.out.println("Unable to write appointment file: " + e.getMessage());
        }
    }
    
    //Get portrait data from file
    public void readPortraitService(String filename) {
        portraitList.clear(); // prevent duplicates

        try (Scanner readData = new Scanner(new File(filename))) {
            while (readData.hasNextLine()) {
                String line = readData.nextLine().trim();

                // skip empty lines
                if (line.isEmpty()) continue;

                //Split the line 
                String[] data = line.split(",");

                // validate number of fields (10 expected for Portrait)
                if (data.length < 10) {
                    System.out.println("Invalid portrait record, skipping: " + line);
                    continue;
                }

                try {
                    String photoSessionID = data[0];
                    String photoSessionName = data[1];
                    String photoSessionType = data[2];
                    double price = Double.parseDouble(data[3]);
                    String isAPackage = data[4];
                    int numOfPeople = Integer.parseInt(data[5]);
                    String backdropStyle = data[6];
                    String printPackage = data[7];
                    String portraitType = data[8];
                    String appointmentID = data[9];

                    Portrait portrait = new Portrait(photoSessionID, photoSessionName, photoSessionType, price, isAPackage, numOfPeople,
                                                backdropStyle, printPackage, portraitType, appointmentID);

                    portraitList.add(portrait);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number in portrait record, skipping: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Portrait file not found: " + filename);
        }
    }

    public void readWeddingService(String filename) {
        weddingList.clear(); // prevent duplicates

        try (Scanner readData = new Scanner(new File(filename))) {
            while (readData.hasNextLine()) {
                String line = readData.nextLine().trim();

                // skip empty lines
                if (line.isEmpty()) 
                    continue;

                String[] data = line.split(",");

                // validate number of fields (10 expected for Wedding)
                if (data.length < 10) {
                    System.out.println("Invalid wedding record, skipping: " + line);
                    continue;
                }

                try {
                    String photoSessionID = data[0];
                    String photoSessionName = data[1];
                    String photoSessionType = data[2];
                    double price = Double.parseDouble(data[3]);
                    String isAPackage = data[4];
                    int weddingNumOfGuest = Integer.parseInt(data[5]);
                    String albumInclusion = data[6];
                    String videographyInclusion = data[7];
                    String weddingCultureStyle = data[8];
                    String appointmentID = data[9];

                    Wedding wedding = new Wedding(photoSessionID, photoSessionName, photoSessionType, price, isAPackage, weddingNumOfGuest,
                        albumInclusion, videographyInclusion, weddingCultureStyle, appointmentID);

                    weddingList.add(wedding);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number in wedding record, skipping: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File '" + filename + "' not found. Unable to read wedding service data.");
        }
    }

    public void readEventService(String filename) {
        eventList.clear(); // prevent duplicates

        try (Scanner readData = new Scanner(new File(filename))) {
            while (readData.hasNextLine()) {
                String line = readData.nextLine().trim();

                // skip empty lines
                if (line.isEmpty()) 
                    continue;

                String[] data = line.split(",");

                // validate number of fields (8 expected for Event)
                if (data.length < 8) {
                    System.out.println("Invalid event record, skipping: " + line);
                    continue;
                }

                try {
                    String photoSessionID = data[0];
                    String photoSessionName = data[1];
                    String photoSessionType = data[2];
                    double price = Double.parseDouble(data[3]);
                    String isAPackage = data[4];
                    int eventNumOfGuest = Integer.parseInt(data[5]);
                    String eventType = data[6];
                    String appointmentID = data[7];

                    Event event = new Event(photoSessionID, photoSessionName, photoSessionType, price,
                                            isAPackage, eventType, eventNumOfGuest, appointmentID);

                    eventList.add(event);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number in event record, skipping: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Unable to read event service data: " + filename);
        }
    }

    public void readProductService(String filename) {
        productList.clear(); // prevent duplicates

        try (Scanner readData = new Scanner(new File(filename))) {
            while (readData.hasNextLine()) {
                String line = readData.nextLine().trim();

                // skip empty lines
                if (line.isEmpty()) 
                    continue;

                String[] data = line.split(",");

                // validate number of fields (9 expected for Product)
                if (data.length < 9) {
                    System.out.println("Invalid product record, skipping: " + line);
                    continue;
                }

                try {
                    String photoSessionID = data[0];
                    String photoSessionName = data[1];
                    String photoSessionType = data[2];
                    double price = Double.parseDouble(data[3]);
                    String isAPackage = data[4];
                    String productType = data[5];
                    int numOfProduct = Integer.parseInt(data[6]);
                    String editingLevel = data[7];
                    String appointmentID = data[8];

                    Product product = new Product(photoSessionID, photoSessionName, photoSessionType, price, isAPackage,
                                            productType, numOfProduct, editingLevel, appointmentID);

                    productList.add(product);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number in product record, skipping: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Unable to read product service data: " + filename);
        }
    }

    public void addPortraitSession(String filename , Portrait portrait) {
        
        try {
            //Append the new portrait data to the file
            try (FileWriter writePortrait = new FileWriter(filename, true)) {
                writePortrait.write(portrait.getPhotoSessionID() + ",");
                writePortrait.write(portrait.getPhotoSessionName() + ",");
                writePortrait.write(portrait.getPhotoSessionType() + ",");
                writePortrait.write(portrait.getPrice() + ",");
                writePortrait.write(portrait.getIsAPackage() + ",");
                writePortrait.write(portrait.getNumOfPeople() + ",");
                writePortrait.write(portrait.getBackdropStyle() + ",");
                writePortrait.write(portrait.getPrintPackage() + ",");
                writePortrait.write(portrait.getPortraitType() + ",");
                writePortrait.write(portrait.getAppointmentID() + "\n");
                
                //add the portrait data to the arrayList
                portraitList.add(portrait);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please try again");
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }

    }

    public void addWeddingSession(String filename, Wedding wedding) {
        
        try {
            //append new wedding data to the file
            try (FileWriter writeWedding = new FileWriter(filename, true)) {
                writeWedding.write(wedding.getPhotoSessionID() + ",");
                writeWedding.write(wedding.getPhotoSessionName() + ",");
                writeWedding.write(wedding.getPhotoSessionType() + ",");
                writeWedding.write(wedding.getPrice() + ",");
                writeWedding.write(wedding.getIsAPackage() + ",");
                writeWedding.write(wedding.getNumOfGuest() + ",");
                writeWedding.write(wedding.getAlbumInclusion() + ",");
                writeWedding.write(wedding.getVideographyInclusion() + ",");
                writeWedding.write(wedding.getWeddingCultureStyle() + ",");
                writeWedding.write(wedding.getAppointmentID() + "\n");
                
                //add the wedding data to the arrayList
                weddingList.add(wedding);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please try again");
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }

    }

    public void addEventSession(String filename , Event event) {
        
        try {
            //append the new event data to the file
            try (FileWriter writeEvent = new FileWriter(filename, true)) {
                writeEvent.write(event.getPhotoSessionID() + ",");
                writeEvent.write(event.getPhotoSessionName() + ",");
                writeEvent.write(event.getPhotoSessionType() + ",");
                writeEvent.write(event.getPrice() + ",");
                writeEvent.write(event.getIsAPackage() + ",");
                writeEvent.write(event.getNumOfGuest() + ",");
                writeEvent.write(event.getEventType() + ",");
                writeEvent.write(event.getAppointmentID() + "\n");
                
                //add the new event data to the arrayList
                eventList.add(event);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please try again");
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }

    }

    public void addProductSession(String filename , Product product) {
        
        try {
            //append the new product data to the file
            try (FileWriter writeProduct = new FileWriter(filename, true)) {
                writeProduct.write(product.getPhotoSessionID() + ",");
                writeProduct.write(product.getPhotoSessionName() + ",");
                writeProduct.write(product.getPhotoSessionType() + ",");
                writeProduct.write(product.getPrice() + ",");
                writeProduct.write(product.getIsAPackage() + ",");
                writeProduct.write(product.getProductType() + ",");
                writeProduct.write(product.getNumOfProduct() + ",");
                writeProduct.write(product.getEditingLevel() + ",");
                writeProduct.write(product.getAppointmentID() + "\n");
                
                //add the new product data to the arrayList
                productList.add(product);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please try again");
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }

    }

    //To update portrait details when user do modifications
    public void updatePortraitSession(String filename , Portrait portrait) {
        for(int i = 0; i < portraitList.size(); i++) {
            if(portraitList.get(i).getPhotoSessionID().equals(portrait.getPhotoSessionID())) {
                portraitList.set(i, portrait);
            }
        }

        try {
            try (FileWriter writePortrait = new FileWriter(filename, false)) {
                for (Portrait p : portraitList) {
                    writePortrait.write(p.getPhotoSessionID() + ","  + p.getPhotoSessionName() + "," + p.getPhotoSessionType() + "," + p.getPrice() + "," + p.getIsAPackage() + "," +
                                p.getNumOfPeople() + "," + p.getBackdropStyle() + "," + p.getPrintPackage() + "," + p.getPortraitType() + "," + p.getAppointmentID() + "\n");
                } 
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }
         
    }

    //method to update wedding details
    public void updateWeddingSession(String filename , Wedding wedding) {
        for(int i = 0; i < weddingList.size(); i++) {
            if(weddingList.get(i).getPhotoSessionID().equals(wedding.getPhotoSessionID())) {
                weddingList.set(i, wedding);
            }
        }

        try {
            try (FileWriter writeWedding = new FileWriter(filename, false)) {
                for (Wedding w : weddingList) {
                    
                    writeWedding.write(w.getPhotoSessionID() + ","  + w.getPhotoSessionID() + "," + w.getPhotoSessionType() + "," + w.getPrice() + "," + w.getIsAPackage() + "," +
                                w.getNumOfGuest() + "," + w.getAlbumInclusion() + "," + w.getVideographyInclusion() + "," + w.getWeddingCultureStyle() + "," + w.getAppointmentID() + "\n");
                } 
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }
        
          
    }

    //method to update event details
    public void updateEventSession(String filename , Event event) {
        for(int i = 0; i < eventList.size(); i++) {
            if(eventList.get(i).getPhotoSessionID().equals(event.getPhotoSessionID())) {
                eventList.set(i, event);
            }
        }

        try {
            try (FileWriter writeEvent = new FileWriter(filename, false)) {
                for (Event e : eventList) {
                    writeEvent.write(e.getPhotoSessionID() + ","  + e.getPhotoSessionID() + "," + e.getPhotoSessionType() + "," + e.getPrice() + "," +
                                e.getIsAPackage() + "," + e.getNumOfGuest() + "," + e.getEventType() + "," + e.getAppointmentID() + "\n");
                } 
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }
    }

    //method to update product details
    public void updateProductSession(String filename , Product product) {
        for(int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getPhotoSessionID().equals(product.getPhotoSessionID())) {
                productList.set(i, product);
            }

            try {
                //rewrite updated object to file
                try (FileWriter writeEvent = new FileWriter(filename, false)) {
                    for (Product p : productList) {                        
                        writeEvent.write(p.getPhotoSessionID() + ","  + p.getPhotoSessionID() + "," + p.getPhotoSessionType() + "," + p.getPrice() + "," + 
                                        p.getProductType() + "," + p.getNumOfProduct() + "," + p.getEditingLevel() + "," + p.getAppointmentID() + "\n");
                    }   
                }
            } catch (IOException e) {
                System.out.println("Unable to write to file. Please try again.");
            }  
        }
    }

    public void removeClient(String filename, Client client) {
        for(int i = 0; i < clientList.size(); i++) {
            //find the index of the client in the array list and remove
            if(clientList.get(i).getUserID().equals(client.getUserID())) {
                clientList.remove(i);
                break;
            }
        }

        try (FileWriter writeClient = new FileWriter(filename, false)) { // overwrite mode to remove old data
            //foreach loop to overwrite back the data one by one
            for (Client c : clientList) {
                writeClient.write(c.getUserID() + "," + c.getUsername() + "," + c.getPassword() + "," + c.getName() + ","
                        + c.getGender() + "," + c.getEmail() + "," + c.getPhoneNumber() + "," + c.getRole() + "," + c.getTotalBookings() + "," + c.getTotalSpent() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }
    }

    public void removePhotographer(String filename, Photographer photographer) {
        for(int i = 0; i < photographerList.size(); i++) {
            //find the index of the photographer in the array list and remove
            if(photographerList.get(i).getUserID().equals(photographer.getUserID())) {
                photographerList.remove(i);
                break;
            }
        }

        try (FileWriter writePhotographer = new FileWriter(filename, false)) { // overwrite mode to remove old data
            //foreach loop to overwrite back the data one by one
            for (Photographer p : photographerList) {
                writePhotographer.write(p.getUserID() + "," + p.getUsername() + "," + p.getPassword() + "," + p.getName() + ","
                        + p.getGender() + "," + p.getEmail() + "," + p.getPhoneNumber() + "," + p.getRole() + "," + p.getSpecialization() + "," + p.getExperienceYears() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }
    }

    public void removeAdmin(String filename, Admin admin) {
        //Search for data and remove from list
        for (int i = 0; i < adminList.size(); i++) {
            if (adminList.get(i).getUserID().equals(admin.getUserID())) {
                adminList.remove(i);
                break; // stop after removal
            }
        }

        try (FileWriter writeAdmin = new FileWriter(filename, false)) { // overwrite mode to remove old data
            //foreach loop to overwrite back the data one by one
            for (Admin a : adminList) {
                writeAdmin.write(a.getUserID() + "," + a.getUsername() + "," + a.getPassword() + "," + a.getName() + ","
                        + a.getGender() + "," + a.getEmail() + "," + a.getPhoneNumber() + "," + a.getRole() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }
    }

    public void removePortraitSession(String filename, Portrait portrait) {
        //Search for data and remove from list
        for (int i = 0; i < portraitList.size(); i++) {
            if (portraitList.get(i).getPhotoSessionID().equals(portrait.getPhotoSessionID())) {
                portraitList.remove(i);
                break; // stop after removal
            }
        }

    
        try (FileWriter writePortrait = new FileWriter(filename, false)) { // overwrite mode to remove old data
            //foreach loop to overwrite back the data one by one
            for (Portrait p : portraitList) {
                writePortrait.write(p.getPhotoSessionID() + "," + p.getPhotoSessionName() + "," + p.getPhotoSessionType() + "," + p.getPrice() + "," + p.getIsAPackage() + ","
                        + p.getNumOfPeople() + "," + p.getBackdropStyle() + "," + p.getPrintPackage() + "," + p.getPortraitType() + "," + p.getAppointmentID() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }
    }

    

    public void removeWeddingSession(String filename, Wedding wedding) {
    //Search for data remove from list
        for (int i = 0; i < weddingList.size(); i++) {
            if (weddingList.get(i).getPhotoSessionID().equals(wedding.getPhotoSessionID())) {
                weddingList.remove(i);
                break; // stop after removal
            }
        }

        try (FileWriter writeWedding = new FileWriter(filename, false)) { // overwrite mode
            //foreach loop to overwrite back the data one by one
            for (Wedding w : weddingList) {
                writeWedding.write(w.getPhotoSessionID() + "," + w.getPhotoSessionName() + "," + w.getPhotoSessionType() + "," + w.getPrice() + "," + w.getIsAPackage() + "," +
                    w.getNumOfGuest() + "," + w.getAlbumInclusion() + "," + w.getVideographyInclusion() + "," + w.getWeddingCultureStyle() + "," + w.getAppointmentID() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }
    }

    public void removeEventSession(String filename, Event event) {
    //Search for data and remove from list
        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).getPhotoSessionID().equals(event.getPhotoSessionID())) {
                eventList.remove(i);
                break; // stop after removal
            }
        }

        try (FileWriter writeEvent = new FileWriter(filename, false)) { // overwrite mode
            //foreach loop to overwrite back the data one by one
            for (Event e : eventList) {
                writeEvent.write(e.getPhotoSessionID() + "," + e.getPhotoSessionName() + "," + e.getPhotoSessionType() + "," + e.getPrice() + "," +
                                e.getIsAPackage() + "," + e.getNumOfGuest() + "," + e.getEventType() + "," + e.getAppointmentID() + "\n");
            }
        } catch (IOException ex) {
            System.out.println("Unable to write to file. Please try again.");
        }
    }

    public void removeProductSession(String filename, Product product) {
        //Search for data and remove from list
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getPhotoSessionID().equals(product.getPhotoSessionID())) {
                productList.remove(i);
                break; // stop after removal
            }
        }

        try (FileWriter writeProduct = new FileWriter(filename, false)) { // overwrite mode
            //foreach loop to overwrite back the data one by one
            for (Product p : productList) {
                writeProduct.write(p.getPhotoSessionID() + "," + p.getPhotoSessionName() + "," + p.getPhotoSessionType() + "," + p.getPrice() + "," + p.getIsAPackage() + "," +
                                    p.getProductType() + "," +  p.getNumOfProduct() + "," + p.getEditingLevel() + "," + p.getAppointmentID() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }
    }

}