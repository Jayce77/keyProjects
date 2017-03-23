/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jayce
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao{
    
    private static final String DVDS_FILE = "dvds.txt";
    private static final String DELIMITER = "::";
    private static final String ENDTAG = "dvd";
    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD addDVD(String id, DVD dvd)
     throws  DVDsDaoException {
        DVD newDVD = dvds.put(id, dvd);
        writeDVDs();
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs()
     throws DVDsDaoException {
        loadDVDs();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDVD(String id)
     throws DVDsDaoException{
        loadDVDs();
        return dvds.get(id);
    }

    @Override
    public DVD removeDVD(String id)
     throws DVDsDaoException {
        DVD removedDVD = dvds.remove(id);
        writeDVDs();
        return removedDVD;
    }
    
    private void loadDVDs() throws DVDsDaoException{
        Scanner scanner;
        
        try{
            scanner = new Scanner(
                new BufferedReader(
                    new FileReader(DVDS_FILE)));
                    
        } catch(FileNotFoundException e){
            throw new DVDsDaoException(
                "-_- Could not load the DVD Library to memory.", e);
        }
        String currentLine;
        
        String[] currentTokens;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            DVD currentDVD = new DVD(currentTokens[0]);
            currentDVD.setTitle(currentTokens[1]);
            currentDVD.setReleaseDate(currentTokens[2]);
            currentDVD.setMpaaRating(currentTokens[3]);
            currentDVD.setDirector(currentTokens[4]);
            currentDVD.setStudio(currentTokens[5]);
            currentDVD.setNote(currentTokens[6]);
            
            dvds.put(currentDVD.getId(), currentDVD);
        }
        scanner.close();
    }
    
    private void writeDVDs() throws DVDsDaoException{
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(DVDS_FILE));
        } catch(IOException e){
            throw new DVDsDaoException(
                "Could not save student data.", e);
        }
        
        List<DVD> dvds = this.getAllDVDs();
        for(DVD currentDVD : dvds){
            out.println(currentDVD.getId() + DELIMITER
                    + currentDVD.getTitle() + DELIMITER
                    + currentDVD.getReleaseDate() + DELIMITER
                    + currentDVD.getMpaaRating() + DELIMITER
                    + currentDVD.getDirector() + DELIMITER
                    + currentDVD.getStudio() + DELIMITER
                    + currentDVD.getNote() + DELIMITER + ENDTAG);
            out.flush();
        }
        
    }
}
