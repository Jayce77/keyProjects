/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.util.List;

/**
 *
 * @author jayce
 */
public interface FlooringMasteryProductDao {
    
    
    /**
         * Adds the given Product to the product list and associates it with the given 
         * product id. If there is already a product associated with the given 
         * product id it will return that product object, otherwise it will 
         * return null.
         * 
         * @param productType id with which product is to be associated
         * @param product product to be added to the product list
         * @return the Product object previously associated with the given  
         * product id if it exists, null otherwise
         * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
         */
    Product addProduct(String productType, Product product)
            throws FlooringMasteryPersistenceException;
    
    /**
     * Returns a String array containing the product types of all 
     * products in the product list.
     * 
     * @return String array containing the types of all the products 
     * in the product list
     ** @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    List<Product> getAllProducts()
            throws FlooringMasteryPersistenceException;
    
    /**
     * Returns the product object associated with the given product id.
     * Returns null if no such product exists
     * 
     * @param productType ID of the product to retrieve
     * @return the Product object associated with the given product id,  
     * null if no such product exists
     * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    Product getProduct(String productType)
            throws FlooringMasteryPersistenceException;
    
    /**
     * Removes from the product list the product associated with the given id. 
     * Returns the product object that is being removed or null if 
     * there is no product associated with the given id
     * 
     * @param productType id of product to be removed
     * @return Product object that was removed or null if no product 
     * was associated with the given product id
     * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    Product removeProduct(String productType)
            throws FlooringMasteryPersistenceException;
    
    /**
     * Edits from the product list the product associated with the given id. 
     * Returns the product object that is being removed or null if 
     * there is no product associated with the given id
     * 
     * @param productType id of product to be removed
     * @param product product object with the edited information
     * @return Product object that was removed or null if no product 
     * was associated with the given product id
     * @throws com.sg.flooringmastery.dao.FlooringMasteryPersistenceException
     */
    Product replaceProduct(String productType, Product product)
     throws FlooringMasteryPersistenceException;
}
