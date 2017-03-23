/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.util.List;

/**
 *
 * @author jayce
 */
public interface FlooringMasteryOrderDao {
    
    
    /**
         * Adds the given Order to the order book and associates it with the given 
         * order id. If there is already a order associated with the given 
         * order id it will return that order object, otherwise it will 
         * return null.
         * 
         * @param orderNumber id with which order is to be associated
         * @param order order to be added to the order book
         * @return the Order object previously associated with the given  
         * order id if it exists, null otherwise
         * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
         */
    Order addOrder(String orderNumber, Order order)
            throws FlooringMasteryPersistenceException;
    
    /**
     * Returns a String array containing the order ids of all 
     * orders in the order book.
     * 
     * @return String array containing the ids of all the orders 
     * in the order book
     ** @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    List<Order> getAllOrders()
            throws FlooringMasteryPersistenceException;
    
    /**
     * Returns the order object associated with the given order id.
     * Returns null if no such order exists
     * 
     * @param orderNumber ID of the order to retrieve
     * @return the Order object associated with the given order id,  
     * null if no such order exists
     * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    Order getOrder(String orderNumber)
            throws FlooringMasteryPersistenceException;
    
    /**
     * Removes from the order book the order associated with the given id. 
     * Returns the order object that is being removed or null if 
     * there is no order associated with the given id
     * 
     * @param orderNumber id of order to be removed
     * @return Order object that was removed or null if no order 
     * was associated with the given order id
     * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    Order removeOrder(String orderNumber)
            throws FlooringMasteryPersistenceException;
    
    /**
     * Edits from the order book the order associated with the given id. 
     * Returns the order object that is being removed or null if 
     * there is no order associated with the given id
     * 
     * @param orderNumber id of order to be removed
     * @param order order object with the edited information
     * @return Order object that was removed or null if no order 
     * was associated with the given order id
     * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    Order replaceOrder(String orderNumber, Order order)
     throws FlooringMasteryPersistenceException;
    
    /**
     * Loads a list of all orders in the order book  with the given date. 
     * Returns the order object that is being removed or null if 
     * there is no order associated with the given id
     * 
     * @param date 
     * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    void loadOrders(String date)
     throws FlooringMasteryPersistenceException;
    
    /**
     * Writes to file a list of all orders in the order book  with the given date. 
     * Returns the order object that is being removed
     * 
     * @param date
     * @param orders
     * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    void saveOrders(String date)
     throws FlooringMasteryPersistenceException;
    
    /**
     * Searches a directory for a file and sees if there is a file that exists
     * for a give date. 
     * Returns a boolean value
     * 
     * @param date
     * @return 
     * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    boolean  doesFileExist(String date)
     throws FlooringMasteryPersistenceException;
}
