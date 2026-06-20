package PhotographyStudio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class User {
    protected String userID;
    protected String username;
    protected String password;
    protected String name;
    protected String gender;
    protected String email;
    protected String phoneNumber;
    protected String role;


    //constructor for User class
    public User(String aUserID, String aUsername, String aPassword, String aName, String theGender, String anEmail, String aPhoneNumber, String aRole) {
        userID = aUserID;
        username = aUsername;
        password = aPassword;
        name = aName;
        gender = theGender;
        email = anEmail;
        phoneNumber = aPhoneNumber;
        role = aRole;
    }

    //Accessor methods
    public String getUserID() {
        return userID; 
    }

    public String getUsername() {
        return username; 
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role; 
    }

    //Mutator methods
    public void setUserID(String newUserID) {
        userID = newUserID;
    }
    public void setUsername(String newUsername) { 
        username = newUsername; 
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public void setName(String newName) {
        name = newName;
    }
        
    public void setGender(String newGender) {
        gender = newGender;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        phoneNumber = newPhoneNumber;
    }

    public void setRole(String newRole) {
        role = newRole; 
    }

    public abstract void viewProfile();

    //method to allow user to change their characteristics
    public abstract void editProfile(FileManager updateProfile, Scanner newValue);


    //A non abstract method allow user to view the photography package offered by the Studio
    public void viewPhotoPackage(FileManager fileManager, Scanner selection) {
        //A variable to hold user input
    	int photoCategory = 0;
    	boolean validInput = false;

    	while (!validInput) {
    	    System.out.println("\nAvailable photography categories: ");
    	    System.out.println("1. Portrait & People Photography (e.g. Formal, Family, Glamour) ");
    	    System.out.println("2. Wedding Photography");
    	    System.out.println("3. Event Photography (e.g. Birthday, Corporate Event)");
    	    System.out.println("4. Product & Commercial Photography");
    	    System.out.println("5. Back to Main Menu");
    	    System.out.println("----------------------------------");
    	    
    	    try {
    	        System.out.print("Enter your selection (1-5): ");
    	        photoCategory = selection.nextInt();
    	        selection.nextLine(); // Consume newline
    	        
    	        if (photoCategory >= 1 && photoCategory <= 5) {
    	            System.out.println("----------------------------------");
    	            validInput = true;
    	        } else {
    	            System.out.println("Invalid choice! Please enter a number between 1 and 5.");
    	        }
    	    } catch (InputMismatchException e) {
    	        System.out.println("Invalid input. Please enter a valid number (e.g. 1, 2, 3, 4, 5)");
    	        selection.nextLine(); // Clear invalid input from scanner
    	    }
    	}

            switch (photoCategory) {
                case 1 -> {
                    ArrayList<Portrait> portraitPackageList = fileManager.getPortraitList();
                        for(int i = 0; i < portraitPackageList.size(); i++) {
                            if(portraitPackageList.get(i).getPhotoSessionName().contains("Customer Custom")) {
                                continue;
                            } else {
                                System.out.println("Package " + (i + 1));
                                System.out.println("---------------------------");
                                System.out.println("Photography Package ID              |" + portraitPackageList.get(i).getPhotoSessionID());
                                System.out.println("Photography Package Name            |" + portraitPackageList.get(i).getPhotoSessionName());
                                System.out.println("Photography Package Type            |" + portraitPackageList.get(i).getPhotoSessionType());
                                System.out.println("Package price                       |" + portraitPackageList.get(i).getPrice());
                                System.out.println("Number of People Included           |" + portraitPackageList.get(i).getNumOfPeople());
                                System.out.println("Backdrop Style                      |" + portraitPackageList.get(i).getBackdropStyle());
                                System.out.println("Print Package                       |" + portraitPackageList.get(i).getPrintPackage());
                                System.out.println("Portrait Type                       |" + portraitPackageList.get(i).getPortraitType() + "\n");
                                System.out.println();
                            }
                        }
                    }
                    case 2 -> {
                        ArrayList<Wedding> weddingPackageList = fileManager.getWeddingList();
                        for(int i = 0; i < weddingPackageList.size(); i++) {
                            if(weddingPackageList.get(i).getPhotoSessionName().contains("Customer Custom")) {
                                continue;
                            } else {
                                System.out.println("Package " + (i + 1));
                                System.out.println("---------------------------");
                                System.out.println("Photography Package ID              |" + weddingPackageList.get(i).getPhotoSessionID());
                                System.out.println("Photography Package Name            |" + weddingPackageList.get(i).getPhotoSessionName());
                                System.out.println("Photography Package Type            |" + weddingPackageList.get(i).getPhotoSessionType());
                                System.out.println("Package price                       |" + weddingPackageList.get(i).getPrice());
                                System.out.println("Number of Guest Included            |" + weddingPackageList.get(i).getNumOfGuest());
                                System.out.println("Backdrop Style                      |" + weddingPackageList.get(i).getAlbumInclusion());
                                System.out.println("Print Package                       |" + weddingPackageList.get(i).getVideographyInclusion());
                                System.out.println("Portrait Type                       |" + weddingPackageList.get(i).getWeddingCultureStyle() + "\n");
                                System.out.println();
                            }
                        }
                    }
                    case 3 -> {
                        ArrayList<Event> eventPackageList = fileManager.getEventList();
                        for(int i = 0; i < eventPackageList.size(); i++) {
                            if(eventPackageList.get(i).getPhotoSessionName().contains("Customer Custom")) {
                                continue;
                            } else {
                                System.out.println("Package " + (i + 1));
                                System.out.println("---------------------------");
                                System.out.println("Photography Package ID              |" + eventPackageList.get(i).getPhotoSessionID());
                                System.out.println("Photography Package Name            |" + eventPackageList.get(i).getPhotoSessionName());
                                System.out.println("Photography Package Type            |" + eventPackageList.get(i).getPhotoSessionType());
                                System.out.println("Package price                       |" + eventPackageList.get(i).getPrice());
                                System.out.println("Number of People Included           |" + eventPackageList.get(i).getNumOfGuest());
                                System.out.println("Backdrop Style                      |" + eventPackageList.get(i).getEventType() + "\n");
                                System.out.println();
                            }
                        }
                    }
                case 4 -> {
                    ArrayList<Product> productPackageList = fileManager.getProductList();
                    for(int i = 0; i < productPackageList.size(); i++) {
                        if(productPackageList.get(i).getPhotoSessionName().contains("Customer Custom")) {
                            continue;
                        } else {
                            System.out.println("Package " + (i + 1));
                            System.out.println("---------------------------");
                            System.out.println("Photography Package ID              |" + productPackageList.get(i).getPhotoSessionID());
                            System.out.println("Photography Package Name            |" + productPackageList.get(i).getPhotoSessionName());
                            System.out.println("Photography Package Type            |" + productPackageList.get(i).getPhotoSessionType());
                            System.out.println("Package price                       |" + productPackageList.get(i).getPrice());
                            System.out.println("Number of Products Included         |" + productPackageList.get(i).getNumOfProduct());
                            System.out.println("Product Type Offered                |" + productPackageList.get(i).getProductType());
                            System.out.println("Product Photo Editing Level         |" + productPackageList.get(i).getEditingLevel() + "\n");
                            System.out.println();
                        }
                    }
                }
                case 5 -> {
                    System.out.println();
                    return;
                }
                default -> {
                    System.out.println("Invalid Input! Please enter a valid input. (e.g. 1, 2...)");
                    System.out.println("----------------------------------");
                }

            }
    }

}
        