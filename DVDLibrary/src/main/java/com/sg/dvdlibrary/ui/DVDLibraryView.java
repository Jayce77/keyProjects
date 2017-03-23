/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jayce
 */
public class DVDLibraryView {
    UserIO io;
    
    public DVDLibraryView(UserIO io){
        this.io = io;
    }
    
    public int printMenuAndGetSelection(String[] options, int min, int max){
        
        for (int i = 0; i < options.length; i++) {
            io.print(options[i]);
        }
        return io.readInt("\nPlease select from the above choices.", min, max);
    }
    
    public void displayDVDLibrarySize(int size){
        if (size > 1) {
            io.print("Your DVD library contains " + size + " DVDs");
        } else if (size == 1) {
            io.print("Your DVD library contains " + size + " DVD");
        } else {
            io.print("Your DVD library doesn't contain any DVDs");
        }
        io.readString("\nPlease hit enter to continue.");
    }
    
    public DVD getNewDVDInfo(String id){
        String title = io.readString("Please Enter DVD Title");
        String releaseDate = io.readString("Please Enter the Movie Release Date");
        String mpaaRating = io.readString("Please Enter the MPAA Rating");
        String director = io.readString("Please Enter the Movie's Director");
        String studio = io.readString("Please Enter the Studio that Produced the Movie");
        String note = io.readString("Please Enter Your Own Personal Note or Review");
        DVD currentDVD = new DVD(id);
        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirector(director);
        currentDVD.setStudio(studio);
        currentDVD.setNote(note);
        return currentDVD;
    }
    
    public void diplayCreateDVDBanner(){
        io.print("++++++++ Enter New DVD ++++++++");
    }
    
    public void displayCreateSuccessBanner(){
        io.print("DVD Successfully entered. Please hit any key to continue");
    }
    
    public void displayContinueScreen(){
        io.readString("\nPress Enter to continue");
    }
    
    public void displayDVDList(List<DVD> dvdList){
        int count =1;
        for(DVD currentDVD : dvdList){
            io.print("============ MOVIE " + count + " ============");
            displayDVD(currentDVD);
            io.print("************************************\n");
            count++;
        }
    }
    
    public void displayDVD(DVD dvd){
        io.print("Title: " + dvd.getTitle());
        io.print("Release Date: " + dvd.getReleaseDate());
        io.print("MPAA Rating: " + dvd.getMpaaRating());
        io.print("Director: " + dvd.getDirector());
        io.print("Studio: " + dvd.getStudio()); 
        io.print("My Note: " + dvd.getNote()); 
    }
    
    public void displayAllBanner(){
        io.print("========= Display All DVDs =========");
    }
    
    public void displayDVDBanner(){
        io.print("========= Your Movie =========");
    }
    
    public void displayTitlesBanner(){
        io.print("=== Enter a Title ===");
    }
    
    public void yourSelectionBanner(){
        io.print("\n======= Your Selecton =======");
    }
    
    public String getTitle(){
        String title = io.readString("Please Enter Move Title or any word in the Title");
        return title;
    }
    
    public String displayTitles(List<DVD> dvds, String title){
        int counter = 0;
        boolean hasTitle = false;
        ArrayList<DVD> matchedTitles = new ArrayList();
        for(DVD dvd : dvds){
            if (dvd.getTitle().matches("(?i:"+ ".*" + title + ".*)")) {
                matchedTitles.add(dvd);
                counter += 1;
                hasTitle = true;
                io.print(counter + ": " + dvd.getTitle());
            }
        }
        if (hasTitle) {
            int titleChoice = io.readInt("Please select from the list", 
                    1, counter);
             String dvdId = matchedTitles.get(titleChoice - 1).getId();
             return dvdId;
        } else {
            io.print("No titles found.");
            io.readString("\nPlease hit enter to continue.");
            return null;
        }
    }
    
    
    public void displayErrorMessage(String errorMsg){
        io.print("========== ERROR ==========");
        io.print(errorMsg);
    }
    
    public void removeDVDBanner(){
        io.print("========== REMOVE DVD ==========");
        io.print("\nWhich DVD would you like to remove?");
    }
    
    public int getRemovalConfirmation(DVD dvd){
        io.print("Are you sure you would like to remove this DVD:");
        displayDVD(dvd);
        int remChoice = io.readInt("1. Yes\n2. No", 1, 2);
        return remChoice;
    }
    
    public void removalSuccessBanner(){
        io.print("The DVD has been succesfully removed from the DVD Library.");
    }
    
    public void displayEditBanner(){
        io.print("========== Edit Menu ==========");
    }
    
    public String getEdit(String editParam){
        io.print("You have chosen to edit the "+ editParam);
        String newParam = io.readString("Enter the new " + editParam);
        return newParam;
    }
    
}
