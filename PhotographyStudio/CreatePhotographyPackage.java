package PhotographyStudio;

import java.util.Scanner;

//An interface to allow specific users to create offered or promotion of photography package
public interface CreatePhotographyPackage {
    public abstract void createPackage(FileManager fileManager, Scanner createPackage);
    public abstract void removePackage(FileManager fileManager, Scanner removePackage);
}