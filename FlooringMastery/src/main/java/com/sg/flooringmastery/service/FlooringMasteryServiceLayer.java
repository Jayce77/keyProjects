/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jayce
 */
public interface FlooringMasteryServiceLayer {
    
    List<Order> getAllOrders() throws FlooringMasteryPersistenceException;
    
    List<Order> getAllOrdersForDate(String date) throws 
            FlooringMasteryPersistenceException,
            FlooringMasteryNoOrderOnDateException;
    
    List<Product> getAllProducts() throws 
            FlooringMasteryPersistenceException;
    
    List<Tax> getAllTaxes() throws 
            FlooringMasteryPersistenceException;
    
    Order getOrder(String orderNumber) throws 
            FlooringMasteryPersistenceException, 
            FlooringMasteryNoOrderOnDateException;
    
    Product getProduct(String ProductName) throws FlooringMasteryPersistenceException;
    
    Tax getTax(String state) throws 
            FlooringMasteryPersistenceException;
    
    Order createOrder(String customerName, BigDecimal area, String state, String productType) throws
            FlooringMasteryPersistenceException,
            FlooringMasteryNoOrderOnDateException,
            FlooringMasteryDataValidationException;
    
    Order editOrder(Order order, String customerName, String area, String state, String productType) throws 
            FlooringMasteryPersistenceException;
    
    Order removeOrder(String orderNumber) throws 
            FlooringMasteryPersistenceException;
    
    Order saveOrder(Order order) throws 
            FlooringMasteryPersistenceException;
    
    
}
