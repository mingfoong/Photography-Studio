package PhotographyStudio;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserAuthentication {
    //User Registration Method
    public void userRegistration(FileManager addData, Scanner register) {
        String username;
        String password;
        String passwordConfirm;
        String name;
        String gender;
        String email;
        String phoneNumber;
        String role;
        String userID;

        //specific attributes for client
        int totalBookings = 0;
        double totalSpent = 0;

        //specific attributes for photographer
        String specialization = "N/A";
        int experienceYears = 0;

        System.out.println("=============================");
        System.out.println("     Register an Account     ");
        System.out.println("=============================");

        System.out.println("Create a username: ");
        System.out.println("-------------------------------");
        System.out.print("Your username: ");
        username = register.nextLine();
        System.out.println("-------------------------------");
        System.out.println();

        while (true) {
            System.out.println("Create a password (8 to 12 characters): ");
            System.out.println("-------------------------------");
            password = register.nextLine();
            System.out.println("-------------------------------");
            System.out.println();

            if (password.length() < 8 || password.length() > 12) {
                System.out.println("Password must be between 8 and 12 characters.");
                continue;
            }

            //Ensure no weak password
            String lower = password.toLowerCase();
            if (lower.contains("12345678") || lower.contains("87654321")
                    || lower.contains("abcdefgh") || lower.contains("hgfedcba")) {
                System.out.println("Weak password. Please enter a strong password.");
                continue;
            }

            // require both letter and digit number
            if (!password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*")) {
                System.out.println("Password must contain both letters and numbers.");
                continue;
            }

            break;
        }

        while (true) {
            System.out.println("Enter the created password: ");   //to ensure no mistyped password during password creation
            System.out.println("-------------------------------");
            System.out.print("Created Password: ");
            passwordConfirm = register.nextLine();
            System.out.println("-------------------------------");
            System.out.println();

            if (!passwordConfirm.equals(password)) {
                System.out.println("Password does not match. Please try again.");
                System.out.println();
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Enter your name: ");
            System.out.println("-------------------------------");
            System.out.print("Your Name here: ");
            name = register.nextLine();
            System.out.println("-------------------------------");
            System.out.println();
            break;
        }

        OUTER:
        while (true) {
            System.out.println("Select your gender (M)Male/(F)Female ");
            System.out.println("------------------");
            System.out.print("Your Gender: ");
            try {
                char genderInput = register.next().charAt(0);
                register.nextLine();    //consume new line
                System.out.println("-------------------");
                System.out.println();

                switch (genderInput) {
                    case 'M', 'm' -> {
                        gender = "MALE";
                        break OUTER;
                    }
                    case 'F', 'f' -> {
                        gender = "FEMALE";
                        break OUTER;
                    }
                    default -> {
                        System.out.println("Invalid Gender Input!");
                        System.out.println("Please enter a valid input (e.g. M)");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Please enter valid input (e.g. M)");
            }
        }

        while (true) {
            System.out.println("Enter your email: ");
            System.out.println("-------------------------------");
            System.out.print("Your Email: ");
            email = register.nextLine();
            System.out.println("-------------------------------");
            System.out.println();

            //To determine whether the email entered fulfills the format or not
            if (email.contains("@gmail.com")) {
                break;
            } else {
                email = email + "@gmail.com";
                break;
            }
        }

        while (true) {
            System.out.println("Enter your phone number (e.g. 6012345678): ");
            System.out.println("-------------------------------");
            System.out.print("Your Phone Number: ");
            phoneNumber = register.nextLine();
            System.out.println("-------------------------------\n");

            //Only digits allowed in the range of 8 to 11 digits
            if (phoneNumber.matches("\\d{8,11}")) {
                break;
            } else {
                System.out.println("Invalid Input. Please enter a valid contact number (e.g. 60123456789)");
                System.out.println("Number must contain 8-11 digits only");
                System.out.println();
            }
        }

        OUTER_1:
        while (true) {
            System.out.println("Enter your role ((C)Client/(P)Photographer/(A)Admin): ");
            System.out.println("-------------------------------");
            System.out.print("Role: ");
            String input = register.next().trim();
            System.out.println("-------------------------------");
            System.out.println();

            if (input.isEmpty()) {
                System.out.println("Invalid input. Please enter a role (C/P/A).");
                continue;
            }

            //A variable to hold the values of role input
            char roleInput = input.charAt(0);

            switch (roleInput) {
                case 'C', 'c' -> {
                    role = "CLIENT";
                    userID = IDGenerator.generateRandomClientID(10);
                    System.out.println("Your user ID is: " + userID);

                    break OUTER_1;
                }
                case 'P', 'p' -> {
                    role = "PHOTOGRAPHER";
                    userID = IDGenerator.generateRandomPhotographerID(10);
                    System.out.println("Your user ID is: " + userID);
                    break OUTER_1;
                }
                case 'A', 'a' -> {
                    role = "ADMIN";
                    userID = IDGenerator.generateRandomAdminID(10);
                    System.out.println("Your user ID is: " + userID);
                    break OUTER_1;
                }
                default ->
                    System.out.println("Invalid role input. Please enter a valid role (C/P/A).");
            }
        }

        System.out.println("Resgistration Successful!");
        System.out.println("You may log in with your user ID and password");
        System.out.println();
        System.out.println();

        //Determine role of the registered user and creates an object based on the role subclasses
        //Add the object to the arrayList and record it to the file
        switch (role) {
            case "CLIENT" -> {
                Client newUser = new Client(userID, username, password, name, gender, email, phoneNumber, role, totalBookings, totalSpent);
                addData.getClientList().add(newUser);
                addData.addClientData(newUser, FileManager.CLIENT_FILE);
            }
            case "PHOTOGRAPHER" -> {
                Photographer newUser = new Photographer(userID, username, password, name, gender, email, phoneNumber, role, specialization, experienceYears);
                addData.getPhotographerList().add(newUser);
                addData.addPhotographerData(newUser, FileManager.PHOTOGRAPHER_FILE);
            }
            case "ADMIN" -> {
                Admin newUser = new Admin(userID, username, password, name, gender, email, phoneNumber, role);
                addData.getAdminList().add(newUser);
                addData.addAdminData(newUser, FileManager.ADMIN_FILE);
            }
            default -> {
            }
        }

        register.nextLine();    //Clear leftover line
    }

    public User userLogin(Scanner login, FileManager identifyUser) {
        identifyUser.readClientData(FileManager.CLIENT_FILE);
        identifyUser.readPhotographerData(FileManager.PHOTOGRAPHER_FILE);
        identifyUser.readAdminData(FileManager.ADMIN_FILE);

        System.out.println("======================================");
        System.out.println("     Log In To Photography Studio     ");
        System.out.println("======================================");

        String identifier;
        User user = null;
        String role = "N/A";

        // Ask for userID or username until valid
        while (true) {
            System.out.print("Enter your userID or username: ");
            identifier = login.nextLine().trim();

            // Check by userID first
            if (identifier.startsWith("CLT")) {
                for (Client client : identifyUser.getClientList()) {
                    if (client.getUserID().equals(identifier)) {
                        user = client;
                        role = "CLIENT";
                        break;
                    }
                }
            } else if (identifier.startsWith("PHG")) {
                for (Photographer photographer : identifyUser.getPhotographerList()) {
                    if (photographer.getUserID().equals(identifier)) {
                        user = photographer;
                        role = "PHOTOGRAPHER";
                        break;
                    }
                }
            } else if (identifier.startsWith("ADM")) {
                for (Admin admin : identifyUser.getAdminList()) {
                    if (admin.getUserID().equals(identifier)) {
                        user = admin;
                        role = "ADMIN";
                        break;
                    }
                }
            }

            // If not found by userID, check by username
            if (user == null) {
                // Check clients
                for (Client client : identifyUser.getClientList()) {
                    if (client.getUsername().equalsIgnoreCase(identifier)) {
                        user = client;
                        role = "CLIENT";
                        break;
                    }
                }

                // Check photographers if not found in clients
                if (user == null) {
                    for (Photographer photographer : identifyUser.getPhotographerList()) {
                        if (photographer.getUsername().equalsIgnoreCase(identifier)) {
                            user = photographer;
                            role = "PHOTOGRAPHER";
                            break;
                        }
                    }
                }

                // Check admins if not found in photographers
                if (user == null) {
                    for (Admin admin : identifyUser.getAdminList()) {
                        if (admin.getUsername().equalsIgnoreCase(identifier)) {
                            user = admin;
                            role = "ADMIN";
                            break;
                        }
                    }
                }
            }

            if (user != null) {
                break;  // valid user found
            }
            System.out.println("User ID or username does not exist :(");
            System.out.println("Please try again.");
        }

        // Ask for password
        while (true) {
            System.out.print("Enter your password: ");
            String password = login.nextLine();
            boolean match = false;

            switch (role) {
                case "CLIENT" -> {
                    Client currentClient = (Client) user;
                    match = currentClient.getPassword().equals(password);
                }
                case "PHOTOGRAPHER" -> {
                    Photographer currentPhotographer = (Photographer) user;
                    match = currentPhotographer.getPassword().equals(password);
                }
                case "ADMIN" -> {
                    Admin currentAdmin = (Admin) user;
                    match = currentAdmin.getPassword().equals(password);
                }
            }

            if (match) {
                System.out.println("Login Successful!");
                return user;   // return the User object
            } else {
                System.out.println("Incorrect password :(");
                System.out.println("Please try again.");
            }
        }
    }

}
