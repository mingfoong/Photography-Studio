package PhotographyStudio;

import java.util.Random;

public class IDGenerator {
    //generate an ID for new register client
     public static String generateRandomClientID(int length) {
        String startingID = "CLT";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";  
        Random random = new Random();
        StringBuilder modifyString = new StringBuilder(startingID);

        //Exclude startingID length from total length
        for (int i = 0; i < length - startingID.length(); i++) {
            int index = random.nextInt(characters.length());
            modifyString.append(characters.charAt(index));
        }

        return modifyString.toString();
     }

     //generate random ID for new registered photographer
     public static String generateRandomPhotographerID(int length) {
        String startingID = "PHG";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";  
        Random random = new Random();
        StringBuilder modifyString = new StringBuilder(startingID);

        //Exclude startingID length from total length
        for (int i = 0; i < length - startingID.length(); i++) {
            int index = random.nextInt(characters.length());
            modifyString.append(characters.charAt(index));
        }

        return modifyString.toString();
     }

     //generate random ID for new admin
     public static String generateRandomAdminID(int length) {
        String startingID = "ADM";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";  
        Random random = new Random();
        StringBuilder modifyString = new StringBuilder(startingID);

        //Exclude startingID length from total length
        for (int i = 0; i < length - startingID.length(); i++) {
            int index = random.nextInt(characters.length());
            modifyString.append(characters.charAt(index));
        }

        return modifyString.toString();
     }

     public static String generateRandomAppointmentID(int length) {
        String startingID = "APT";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";  
        Random random = new Random();
        StringBuilder modifyString = new StringBuilder(startingID);

        //Exclude startingID length from total length
        for (int i = 0; i < length - startingID.length(); i++) {
            int index = random.nextInt(characters.length());
            modifyString.append(characters.charAt(index));
        }

        return modifyString.toString();
    }

    public static String generateRandomPortraitSessionID(int length) {
        String startingID = "PRT";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";  
        Random random = new Random();
        StringBuilder modifyString = new StringBuilder(startingID);

        //Exclude startingID length from total length
        for (int i = 0; i < length - startingID.length(); i++) {
            int index = random.nextInt(characters.length());
            modifyString.append(characters.charAt(index));
        }

        return modifyString.toString();
    }

    public static String generateRandomWeddingSessionID(int length) {
        String startingID = "WED";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";  
        Random random = new Random();
        StringBuilder modifyString = new StringBuilder(startingID);

        //Exclude startingID length from total length
        for (int i = 0; i < length - startingID.length(); i++) {
            int index = random.nextInt(characters.length());
            modifyString.append(characters.charAt(index));
        }

        return modifyString.toString();
    }

    public static String generateRandomEventSessionID(int length) {
        String startingID = "EVT";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";  
        Random random = new Random();
        StringBuilder modifyString = new StringBuilder(startingID);

        //Exclude startingID length from total length
        for (int i = 0; i < length - startingID.length(); i++) {
            int index = random.nextInt(characters.length());
            modifyString.append(characters.charAt(index));
        }

        return modifyString.toString();
    }

    public static String generateRandomProductSessionID(int length) {
        String startingID = "PDC";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";  
        Random random = new Random();
        StringBuilder modifyString = new StringBuilder(startingID);

        //Exclude startingID length from total length
        for (int i = 0; i < length - startingID.length(); i++) {
            int index = random.nextInt(characters.length());
            modifyString.append(characters.charAt(index));
        }

        return modifyString.toString();
    }
}
