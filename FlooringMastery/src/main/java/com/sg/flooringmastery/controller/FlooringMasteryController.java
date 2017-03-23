/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.service.FlooringMasteryDataValidationException;
import com.sg.flooringmastery.service.FlooringMasteryNoOrderFoundException;
import com.sg.flooringmastery.service.FlooringMasteryNoOrderOnDateException;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jayce
 */
public class FlooringMasteryController {
    
    private FlooringMasteryView view;
    private FlooringMasteryServiceLayer service;
    
    public FlooringMasteryController(FlooringMasteryView view){
        this.view = view;
    }
    
    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayer service){
        this.view = view;
        this.service = service;
    }
    
    public void run(){
        
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try {
            while(keepGoing){
                try{
                    menuSelection = getMenuSelection();

                    switch (menuSelection) {
                        case 1:
                            getOrder();
                            break;
                        case 2:
                            addOrder();
                            break;
                        case 3:
                            editOrder();
                            break;
                        case 4:
                            removeOrder();
                            break;
                        case 5:
                            saveAllwork();
                            break;
                        case 6:
                            keepGoing = false;
                            break;
                        default:
                            throw new AssertionError();
                    }
                } catch(FlooringMasteryNoOrderOnDateException|
                        FlooringMasteryNoOrderFoundException e){
                    
                }
            }
        } catch (FlooringMasteryPersistenceException|
                FlooringMasteryDataValidationException e){
            view.displayErrorMessage(e.getMessage());
        }    
        view.displayExitMessage();
    }
    
    public  boolean getMode(){
        boolean isTraining = view.getDisplayMode();
        return isTraining;
    }
    
    private int getMenuSelection(){
        int menuSelection = view.diplayMenuGetSelection();
        return menuSelection;
    }
    
    private void getOrder() throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderOnDateException, FlooringMasteryNoOrderFoundException{
        String date = view.getDate();
        List<Order> orders = service.getAllOrdersForDate(date);
        String orderNum = view.displayAndGetOrder(orders);
        Order order = service.getOrder(orderNum);
        view.displayOrderBanner();
        view.displayOrder(order);
    }
    
    private void addOrder() throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderOnDateException, FlooringMasteryDataValidationException{
        view.displayAddOrderBanner();
        String customerName = view.getCustomerName();
        List<Tax> servedStates = service.getAllTaxes();
        List<Product> products = service.getAllProducts();
        String state = view.getState(servedStates);
        BigDecimal area = view.getArea();
        String productType = view.getProductType(products);
        Order newOrder = service.createOrder(customerName, area, state, productType);
        view.displayOrderBanner();
        view.displayOrder(newOrder);
        boolean willSave = view.getSave();
        if (willSave) {
            service.saveOrder(newOrder);
            view.displaySaveSuccessBanner();
        } else {
            view.displayOrderNotSavedBanner();
        }
    }
    
    private void editOrder()  throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderOnDateException, FlooringMasteryDataValidationException, FlooringMasteryNoOrderFoundException{
        view.diplayEditOrderBanner();
        String date = view.getDate();
        List<Order> orders = service.getAllOrdersForDate(date);
        List<Tax> servedStates = service.getAllTaxes();
        List<Product> products = service.getAllProducts();
        String orderNum = view.displayAndGetOrder(orders);
        Order order = service.getOrder(orderNum);
        view.displayOrderBanner();
        view.displayOrder(order);
        String name = view.displayEditName(order);
        String area = view.displayEditArea(order);
        String state = view.displayEditState(order, servedStates);
        String productType = view.displayEditProductType(order, products);
        Order editedOrder = service.editOrder(order, name, area, state, productType);
        view.displayOrderBanner();
        view.displayOrder(editedOrder);
        boolean willSave = view.getSave();
        if (willSave) {
            service.saveOrder(editedOrder);
            view.displaySaveSuccessBanner();
        } else {
            view.displayOrderNotSavedBanner();
        }
    }
    
    private void removeOrder() throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderOnDateException, FlooringMasteryDataValidationException,FlooringMasteryNoOrderFoundException{
        view.displayRemoveOrderBanner();
        String date = view.getDate();
        List<Order> orders = service.getAllOrdersForDate(date);
        String orderNum = view.displayAndGetOrder(orders);
        Order order = service.getOrder(orderNum);
        view.displayOrderBanner();
        view.displayOrder(order);
        boolean willRemove = view.getRemove();
        if (willRemove) {
            order = service.removeOrder(order.getOrderNumber());
            view.displayRemoveSuccessBanner(order.getOrderNumber());
        } else {
            view.displayOrderNotRemovedBanner(order.getOrderNumber());
        }
        boolean willSave = view.getSave();
        if (willSave) {
            service.saveOrder(order);
            view.displaySaveSuccessBanner();
        } else {
            view.displayOrderNotSavedBanner();
        }
    }
    
    private void saveAllwork() throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderOnDateException, FlooringMasteryDataValidationException{
        List<Order> orders = service.getAllOrders();
        if (orders.size() > 0) {
            boolean willSave = view.getSaveAll();
                if (willSave) {
                    service.saveOrder(orders.get(0));
                    view.displaySaveSuccessBanner();
                } else {
                    view.displayOrderNotSavedBanner();
                }
        } else {
            view.diplayNothingToSave();
        }
    } 
}
