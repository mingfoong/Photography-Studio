package PhotographyStudio;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();

        Scanner choice = new Scanner(System.in);
            
        //read the data objects from file
            fileManager.readClientData(FileManager.CLIENT_FILE);
            fileManager.readPhotographerData(FileManager.PHOTOGRAPHER_FILE);
            fileManager.readAdminData(FileManager.ADMIN_FILE);
            fileManager.readAppointmentData(FileManager.APPOINTMENT_FILE);
            fileManager.readPortraitService(FileManager.PORTRAIT_FILE);
            fileManager.readWeddingService(FileManager.WEDDING_FILE);
            fileManager.readEventService(FileManager.EVENT_FILE);
            fileManager.readProductService(FileManager.PRODUCT_FILE);

            int loginOrRegister = 0;
            User user = null;
            
            while(true) {
                try {
                    //Let user to log in or register to access the system
                    System.out.println("Log in or register an account to access The Photography Studio System");
                    System.out.println("(1) Log in");
                    System.out.println("(2) Register an account");
                    System.out.print("Choice: ");
                    loginOrRegister = choice.nextInt();
                    choice.nextLine();
                    
                    if(loginOrRegister <= 0 || loginOrRegister > 2) {
                        System.out.println("Invalid input. Please enter 1 or 2.");
                    } else {
                        break;
                    }
                } catch(InputMismatchException e) {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    choice.nextLine(); // consume invalid input
                }
            }
            
            //Create UserAuthentication object to call userLogin() and userRegistration()
            UserAuthentication authenticate = new UserAuthentication();
            
            if(loginOrRegister == 1) {
                user = authenticate.userLogin(choice, fileManager); // store the returned User object
            } else {
                authenticate.userRegistration(fileManager, choice);
                System.out.println();
                user = authenticate.userLogin(choice, fileManager);
            }
            
            // Main menu loop
            boolean exit = false;
            while (!exit) {
                System.out.println("---------------------------------------------------------");
                System.out.println("                  PHOTOGRAPHY STUDIO                     ");
                System.out.println("---------------------------------------------------------");
                System.out.println("(1) View Profile");
                System.out.println("(2) Edit Profile");
                System.out.println("(3) View Offered Photography Packages");
                
                // Show role-specific menu options
                //check whether which subclass user belongs to and get the specific attributes and methods of the user
                if(user instanceof Client currentClient) {
                    System.out.println("(4) Book Photography Package");
                    System.out.println("(5) Create Customized Photography Session");
                    System.out.println("(6) View Booked Appointments");
                    System.out.println("(7) Modify Appointment Details");
                    System.out.println("(8) Cancel Appointment");
                    System.out.println("(9) Logout");
                    
                } else if(user instanceof Photographer currentPhotographer) {
                    System.out.println("(4) View All Appointment Details");
                    System.out.println("(5) Engage/Assign in Photography Session");
                    System.out.println("(6) Cancel Photography Engagement/Assignment");
                    System.out.println("(7) Create new Photography Package");
                    System.out.println("(8) Remove Photography Package");
                    System.out.println("(9) Logout");
                    
                } else if(user instanceof Admin currentAdmin) {
                    System.out.println("(4) View all Appointment Details");
                    System.out.println("(5) Assign Photographer to appointments");
                    System.out.println("(6) Unassign Photographer from appointments");
                    System.out.println("(7) Create New Photography Package");
                    System.out.println("(8) Remove Photography Package");
                    System.out.println("(9) Delete User");
                    System.out.println("(10) Logout");
                }
                
                System.out.println("Enter your choice: ");
                System.out.println("-----------------------");
                System.out.print("Choice: ");
                
                //Allow users to perform specific actions in the system
                try {
                    int menuChoice = choice.nextInt();
                    choice.nextLine(); // consume newline
                    System.out.println("-----------------------");
                    System.out.println();
                    
                    // Handle menu selection based on user type
                    if (user instanceof Client currentClient) {
                        handleClientMenu(menuChoice, currentClient, choice, fileManager);
                        if (menuChoice == 8)
                            exit = true;
                    } else if (user instanceof Photographer currentPhotographer) {
                        handlePhotographerMenu(menuChoice, currentPhotographer, choice, fileManager);
                        if (menuChoice == 9)
                            exit = true;
                    } else if (user instanceof Admin currentAdmin) {
                        handleAdminMenu(menuChoice, currentAdmin, choice, fileManager);
                        if (menuChoice == 10)
                            exit = true;
                    }
                    
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    choice.nextLine(); // consume invalid input
                }
            }
            
            System.out.println("Thank you for using Photography Studio System!");

            choice.close();
    }

    //A method to specify the actions a client can perform in this Photography Studio System
    private static void handleClientMenu(int choice, Client client, Scanner scanner, FileManager fileManager) {
        switch (choice) {
            case 1 -> client.viewProfile();
            case 2 -> client.editProfile(fileManager, scanner);
            case 3 -> client.viewPhotoPackage(fileManager, scanner);
            case 4 -> client.bookPhotographyPackage(scanner, fileManager);
            case 5 -> client.customBookingPhotographySession(scanner, fileManager);
            case 6 -> client.viewbookedAppointment(fileManager);
            case 7 -> client.modifyAppointmentDetails(client.getUserID(), fileManager, FileManager.APPOINTMENT_FILE, scanner);
            case 8 -> client.cancelAppointment(fileManager, scanner);
            case 9 -> System.out.println("Logging out...");
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    //A method to specify the actions a photographer can perform in this Photography Studio System
    private static void handlePhotographerMenu(int choice, Photographer photographer, Scanner scanner, FileManager fileManager) {
        switch (choice) {
            case 1 -> photographer.viewProfile();
            case 2 -> photographer.editProfile(fileManager, scanner);
            case 3 -> photographer.viewPhotoPackage(fileManager, scanner);
            case 4 -> photographer.viewAllAppointmentDetails(fileManager);
            case 5 -> photographer.engageInPhotoSession(fileManager, scanner);
            case 6 -> photographer.cancelPhotographyAssignment(photographer.getUserID(), fileManager, FileManager.APPOINTMENT_FILE, scanner);
            case 7 -> photographer.createPackage(fileManager, scanner);
            case 8 -> photographer.removePackage(fileManager, scanner);
            case 9 -> System.out.println("Logging out...");
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    //A method to specify the actions an admin can perform in this Photography Studio System
    private static void handleAdminMenu(int choice, Admin admin, Scanner scanner, FileManager fileManager) {
        switch (choice) {
            case 1 -> admin.viewProfile();
            case 2 -> admin.editProfile(fileManager, scanner);
            case 3 -> admin.viewPhotoPackage(fileManager, scanner);
            case 4 -> admin.viewAllAppointmentDetails(fileManager);
            case 5 -> admin.assignPhotographer(fileManager, scanner);
            case 6 -> admin.unassignPhotographer(fileManager, scanner);
            case 7 -> admin.createPackage(fileManager, scanner);
            case 8 -> admin.removePackage(fileManager, scanner);
            case 9 -> admin.deleteUser(fileManager, scanner);
            case 10 -> System.out.println("Logging out...");
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }
}