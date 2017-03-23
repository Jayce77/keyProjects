/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.dao.DVDsDaoException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jayce
 */
public class DVDLibraryController {
    private UserIO io = new UserIOConsoleImpl();
    DVDLibraryView view;
    DVDLibraryDao dao;
    
    String[] mainMenu = {"**************************************" + 
            "\n\t   Main Menu\n" + "**************************************" + 
            "\n","\t1. List DVDs","\t2. Add New DVD",
            "\t3. Remove a DVD","\t4. Edit a DVD",
            "\t5. Find a DVD","\t6. Display total number DVDs",
            "\t7. Exit"};
    
    String[] editMenu = {"Select an Option From This List\n================================",
        "\t1. Edit DVD Title","\t2. Edit DVD Release Date",
        "\t3. Edit MPAA Rating","\t4. Edit Director's name",
        "\t5. Edit the Movie Studio","\t6. Edit Your Personal Note or Review",
        "\t7. I Don't Want to Make Any Changes"};
    
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view){
        this.dao = dao;
        this.view = view;
    }
    
    public void run(){
       boolean keepGoing = true;
       int menuSelection = 0;
       try{
            while(keepGoing){

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                     case 1:
                         listAllDVDs();
                         break;
                     case 2:
                         createDVD();
                         break;
                     case 3:
                         removeDVD();
                         break;
                     case 4:
                         editDVD();
                         break;
                     case 5:
                         findDVD();
                         break;
                     case 6:
                         getLibrarySize();
                         break;
                     case 7:
                         keepGoing = false;
                         break;
                     default:
                         io.print("UNKNOWN COMMAND");
                }
            }
            io.print("Good Bye");
        } catch (DVDsDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
       
   

    private int getMenuSelection() {
        return view.printMenuAndGetSelection(mainMenu, 1, 7);
    }
    
    private void getLibrarySize() throws DVDsDaoException{
        List<DVD> dvdList = dao.getAllDVDs();
        int DVDLibrarySize = dvdList.size();
        view.displayDVDLibrarySize(DVDLibrarySize);
    }
    
    private void createDVD() throws DVDsDaoException{
        String newId = createId();
        view.diplayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo(newId);
        dao.addDVD(newDVD.getId(), newDVD);
        view.displayCreateSuccessBanner();
    }
    
    private String createId() throws DVDsDaoException{
        Random randId = new Random();
        boolean isUsed = false;
        List<DVD> dvdArray = dao.getAllDVDs();
         String newId = "";
        do {            
            int intId = randId.nextInt(9999999);
            newId = String.format("%07d", intId);
            for(DVD dvd : dvdArray){
                if(newId.equals(dvd.getId())) {
                    isUsed = true;
                }
            }
        } while(isUsed);
        
        return newId;
    }
    
    private void listAllDVDs() throws DVDsDaoException{
        view.displayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
        view.displayContinueScreen();
    }
    
    private String getIdByTitle() throws DVDsDaoException{
        String title = view.getTitle();
        List<DVD> dvdList = dao.getAllDVDs();
        String id = view.displayTitles(dvdList, title);
        return id;
    }
    
    private void findDVD() throws DVDsDaoException{
        view.displayTitlesBanner();
        String id = getIdByTitle();
        if (id != null) {
            view.yourSelectionBanner();
            viewDVD(id);
        }
    }
    
    private void viewDVD(String id) throws DVDsDaoException{
        String dvdId = id;
        DVD dvd = dao.getDVD(dvdId);
        view.displayDVD(dvd);
        view.displayContinueScreen();
    }
    
    private void removeDVD() throws DVDsDaoException{
        view.removeDVDBanner();
        String id = getIdByTitle();
        DVD dvd = dao.getDVD(id);
        if (id != null) {
            int willRemove = view.getRemovalConfirmation(dvd);
            if (willRemove == 1) {
                dao.removeDVD(id);
                view.removalSuccessBanner();
                view.displayContinueScreen();
            }
            
        }
    }
    
    private void editDVD() throws DVDsDaoException{
        view.displayEditBanner();
        String id = getIdByTitle();
        DVD dvd = dao.getDVD(id);
        if (id != null) {
            view.displayEditBanner();
            viewDVD(id);
            int editChoice = view.printMenuAndGetSelection(editMenu, 1, 7);
            String editParam = "";
            String newParam = "";
            
            switch (editChoice) {
                case 1:
                    editParam = "DV Title";
                    newParam = view.getEdit(editParam);
                    dvd.setTitle(newParam);
                    break;
                case 2:
                    editParam = "DVD Release Date";
                    newParam = view.getEdit(editParam);
                    dvd.setReleaseDate(newParam);
                    break;
                case 3:
                    editParam = "MPAA Rating";
                    newParam = view.getEdit(editParam);
                    dvd.setMpaaRating(newParam);
                    break;
                case 4:
                    editParam = "Movie Director";
                    newParam = view.getEdit(editParam);
                    dvd.setDirector(newParam);
                    break;
                case 5:
                    editParam = "Production Studio";
                    newParam = view.getEdit(editParam);
                    dvd.setStudio(newParam);
                    break;
                case 6:
                    editParam = "Your Pesonal Note";
                    newParam = view.getEdit(editParam);
                    dvd.setNote(newParam);
                    break;
                case 7:
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }
            dao.addDVD(id, dvd);
            view.yourSelectionBanner();
            viewDVD(id);
        }
    }
    
}