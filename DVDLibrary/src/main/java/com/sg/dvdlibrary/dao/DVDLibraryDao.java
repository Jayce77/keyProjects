/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author jayce
 */
public interface DVDLibraryDao {
    /**
     * Adds the given DVD to the DVDBook and associates it with the given 
     * id. If there is already an an associated with the given 
     * id it will return that dvd object, otherwise it will 
     * return null.
     * 
     * @param Id id with which dvd is to be associated
     * @param dvd dvd to be added to the dvdBook
     * @return the dvd object previously associated with the given  
     * id if it exists, null otherwise
     * @throws com.sg.dvdlibrary.dao.DVDsDaoException
     */
    DVD addDVD(String id, DVD dvd)
     throws DVDsDaoException;
    
    /**
     * Returns a integer array containing the ids of all 
     * dvdes in the dvdBook.
     * 
     * @return String array containing the ids of all the dvds 
     * in the dvd book
     * @throws com.sg.dvdlibrary.dao.DVDsDaoException
     */
    List<DVD> getAllDVDs()
     throws DVDsDaoException;
    
    /**
     * Returns the dvd object associated with the given id.
     * Returns null if no such dvd exists
     * 
     * @param id ID of the dvd to retrieve
     * @return the dvd object associated with the given id,  
     * null if no such dvd exists
     * @throws com.sg.dvdlibrary.dao.DVDsDaoException
     */
    DVD getDVD(String id)
     throws DVDsDaoException;
    
    /**
     * Removes from the dvdBook the dvd associated with the given id. 
     * Returns the dvd object that is being removed or null if 
     * there is no dvd associated with the given id
     * 
     * @param id id of dvd to be removed
     * @return dvd object that was removed or null if no dvd 
     * was associated with the given id
     * @throws com.sg.dvdlibrary.dao.DVDsDaoException
     */
    DVD removeDVD(String id)
     throws DVDsDaoException;
}
