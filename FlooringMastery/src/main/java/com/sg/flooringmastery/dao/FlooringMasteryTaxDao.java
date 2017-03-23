/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author jayce
 */
public interface FlooringMasteryTaxDao {
    
    
    /**
         * Adds the given Tax to the tax list and associates it with the given 
         * tax state. If there is already a tax associated with the given 
         * tax state it will return that tax object, otherwise it will 
         * return null.
         * 
         * @param state with which tax is to be associated
         * @param tax tax to be added to the tax list
         * @return the Tax object previously associated with the given  
         * tax state if it exists, null otherwise
         * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
         */
    Tax addTax(String state, Tax tax)
            throws FlooringMasteryPersistenceException;
    
    /**
     * Returns a String array containing the tax types of all 
     * taxes in the tax list.
     * 
     * @return String array containing the types of all the taxes 
     * in the tax list
     ** @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    List<Tax> getAllTaxes()
            throws FlooringMasteryPersistenceException;
    
    /**
     * Returns the tax object associated with the given tax state.
     * Returns null if no such tax exists
     * 
     * @param state of the tax to retrieve
     * @return the Tax object associated with the given tax state,  
     * null if no such tax exists
     * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    Tax getTax(String state)
            throws FlooringMasteryPersistenceException;
    
    /**
     * Removes from the tax list the tax associated with the given state. 
     * Returns the tax object that is being removed or null if 
     * there is no tax associated with the given state
     * 
     * @param state of tax to be removed
     * @return Tax object that was removed or null if no tax 
     * was associated with the given tax state
     * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    Tax removeTax(String state)
            throws FlooringMasteryPersistenceException;
    
    /**
     * Edits from the tax list the tax associated with the given state. 
     * Returns the tax object that is being removed or null if 
     * there is no tax associated with the given state
     * 
     * @param state of tax to be removed
     * @param tax tax object with the edited information
     * @return Tax object that was removed or null if no tax 
     * was associated with the given tax state
     * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    Tax replaceTax(String state, Tax tax)
     throws FlooringMasteryPersistenceException;
}
