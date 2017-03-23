/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author jayce
 */
public class FlooringMasteryView {
    private UserIO io;
    
    public FlooringMasteryView(UserIO io){
        this.io = io;
    }
    
    public boolean getDisplayMode(){
        io.print("\t======= Choose Mode =======");
        io.print("\t[1] Training");
        io.print("\t[2] Production");
        int selection = io.readInt("\n\tPlease make a selection", 1, 2);
        if (selection == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public int diplayMenuGetSelection(){
        io.print("\t======= Main Menu =======");
        io.print("\t[1] Display an Order");
        io.print("\t[2] Add a New Order");
        io.print("\t[3] Edit an Existing Order");
        io.print("\t[4] Cancel an Order");
        io.print("\t[5] Save All Unsaved Data");
        io.print("\t[6] Exit");
        int selection = io.readInt("\n\tPlease make a selection", 1, 6);
        return selection;
    }
    
    public void displayErrorMessage(String errorMsg){
        io.print("\t>>>>>>>>>>>> ERROR <<<<<<<<<<<<<<");
    }
    
    public void displayOrderBanner(){
        io.print("\t==== This is your Order ====");
    }
    
    public void displayAddOrderBanner(){
        io.print("\tEnter the details of the new Order");
    }
    
    public void diplayEditOrderBanner(){
        io.print("\t====== Edit Mode ======");
    }
    
    public void displayRemoveOrderBanner(){
        io.print("\t====== Order Cancelation Screen ======");
    }
    
    public void displaySaveSuccessBanner(){
        io.print("\t====== Order Successfully Saved ======");
    }
    
    public void displayExitMessage(){
        io.print("\t====== Good Bye ======");
    }
    
    public String getDate(){
        String userDate = "";
        LocalDate ld;
        boolean isValidDate = false;
        while(!isValidDate){
            try{
                userDate = io.readString("What's the date of the order?\nPlease enter in month/day/year (MM/DD/YYYY) format");
                ld = LocalDate.parse(userDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                userDate = ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
                isValidDate = true;
            } catch(Exception e) {
                io.print("Please use proper formatting");
            }
        }
        return userDate;
    }
    
    public String displayAndGetOrder(List<Order> ordersOnDate){
        io.print("\t These are the order on your selected date");
        for (int i = 0; i < ordersOnDate.size(); i++) {
            io.print("\t Order Number: " + ordersOnDate.get(i).getOrderNumber() +
                    "\t Customer Name: " + ordersOnDate.get(i).getCustomerName() +
                    "\t Product: " + ordersOnDate.get(i).getProductType() +
                    "\t Total: " + ordersOnDate.get(i).getTotal());
        }
        String orderNum = io.readString("Please select an order");
        return orderNum;
    }
    
    public void displayOrder(Order order){
        io.print("\t Order Date: " + order.getOrderDate());
        io.print("\t Order Number: " + order.getOrderNumber());
        io.print("\t Customer Name: " + order.getCustomerName());
        io.print("\t Customer Location: " + order.getState());
        io.print("\t Size in SqFt: " + order.getArea());
        io.print("\t Product Type: " + order.getProductType());
        io.print("\t Labor per SqFt: $" + order.getLaborCostPerSqFt());
        io.print("\t Total Labor: $" + order.getLaborCost());
        io.print("\t Material per SqFt: $" + order.getMaterialCostPerSqFt());
        io.print("\t Total Material: $" + order.getMaterialCostPerSqFt());
        io.print("\t Tax Rate: " + order.getTaxRate() +"%");
        io.print("\t Total Tax: $" + order.getTotalTax());
        io.print("\t Total Price: $" + order.getTotal());
        io.print("\n\n");
        io.readString("Press Enter to Continue \n\n");
    }
    
    public String getCustomerName(){
        String customerName =  io.readString("\tPlease enter the Customer's name");
        customerName = validateCustomerName(customerName);
        return customerName;
    }
    
    private String validateCustomerName(String customerName){
        
        boolean isValidName = false;
        while(!isValidName){
            if (!customerName.contains(",")) {
                isValidName = true;
            } else {
                io.print("\t>>>>>>> Please do not insert commas into the name. <<<<<<<");
                customerName = io.readString("\t+++++++ Enter Customer Name +++++++");
            }
        }
        return customerName;
    }
    
    public String getState(List<Tax> servedStates){
        String customerState = "";
        String availableStates = " \n\t\t";
        for (int i = 0; i < servedStates.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (i * 10 + j < servedStates.size()) {
                    if (i * 10 + j == servedStates.size() - 1) {
                        availableStates += servedStates.get(i * 10 + j).getState() + " ";
                    } else {
                         availableStates += servedStates.get(i * 10 + j).getState() + ", ";
                    }
                }
                
            }
            if (servedStates.size() - i > 0) {
                availableStates += "\n\t\t\t";
            }
        }
        boolean isServedState = false;
        while(!isServedState){
            try{
                io.print("\tPlease Type in the Customers State Abbreviation From "
                    + "the Following List in Upper Case Form:\n" + availableStates);
                customerState = io.readString("\tPlease enter the Customer's state\n").toUpperCase().trim().substring(0, 2);
                for(Tax state : servedStates){
                    if (customerState.equals(state.getState())) {
                        isServedState = true;
                        break;
                    }
                }
            }catch(Exception e){
                io.print("\t>>>>>>> Please select a state where we have service. <<<<<<<");
            }
            
        }
        return customerState;
    }    
        
    public BigDecimal getArea(){
        return io.readBigDecimal("\tPlease Enter the Size of the Flooring Area in Sq. Feet");
    }
    public String getProductType(List<Product> products){
        io.print("\tPlease enter the Customer's Choice of Product");
        int width = 25;
        io.print("Product Type               Material Per Sq Ft.      Labor Per Sq Ft.");
        for (int i = 0; i < products.size(); i++) {
            String display = "[" + Integer.toString(i+1) + "]: ";
            String field = products.get(i).getName();
            field = String.format("%-" + width  + "s", field);
            display += field;
            field = products.get(i).getCostPerSqFt().toString();
            field = String.format("%-" + width + "s", field);
            display += field;
            field = products.get(i).getLaborCostPerSqFt().toString();
            field = String.format("%-" + width + "s", field);
            display += field;
            io.print(display);
        }
        int customerChoice = 
                io.readInt("Please Enter the Number for the Product", 1, products.size()) - 1;
        return products.get(customerChoice).getName();
    }
    
    public boolean getSave(){
        int saveChoice = io.readInt("Would You Like to Save This Order\n[1] YES\n[2] NO", 1, 2);
        if (saveChoice == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public void displayOrderNotSavedBanner(){
        io.print("____This Order Has Been Discarded____");
    }
    
    public String displayEditName(Order order){
        io.print("\tEdit Customer Name: " + order.getCustomerName());
        String customerName = io.readString("Enter a New Name or Press Enter to Continue to the Next Item");
        customerName = validateCustomerName(customerName);
        return customerName;
    }
    
    public String displayEditArea(Order order){
        String stringAnswer = "";
        io.print("\tEdit Area Size: " + order.getArea());
        boolean isBigDecimal = false;
        while(!isBigDecimal){
            try{
            stringAnswer = io.readString("Enter the New Size or Press Enter to Continue to the Next Item");
                if (stringAnswer.matches("(\\d*\\.?\\d*)") == true) {
                    isBigDecimal = true;
                } 
            }catch(Exception e){
               io.print("\tPlease type in numbers only");
            }
        }
        return stringAnswer;
    }
    
    public String displayEditState(Order order, List<Tax> servedStates){
        String currentState = order.getState();
        io.print("\tEdit Customer's State: " + currentState);
        String availableStates = " \n\t\t";
        for (int i = 0; i < servedStates.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (i * 10 + j < servedStates.size()) {
                    if (i * 10 + j == servedStates.size() - 1) {
                        availableStates += servedStates.get(i * 10 + j).getState() + " ";
                    } else {
                         availableStates += servedStates.get(i * 10 + j).getState() + ", ";
                    }
                }
                
            }
            if (servedStates.size() - i > 0) {
                availableStates += "\n\t\t\t";
            }
        }
        boolean isServedState = false;
        while(!isServedState){
            try{
                io.print("\tPlease Type in the Customers State Abbreviation "
                        + "or Press Enter to Continue to the Next Item\n"+  availableStates);
                currentState = io.readString("\tEnter the Customer's State or Press Enter to Continue to the Next Item").toUpperCase();
                for(Tax state : servedStates){
                    if (currentState.equals(state.getState()) || currentState.equals("")) {
                        isServedState = true;
                        break;
                    }
                }
            }catch(Exception e){
                io.print("\t>>>>>>> Please select a state where we have service. <<<<<<<");
            }
            
        }
        return currentState;
    }
    
    public String displayEditProductType(Order order, List<Product> products){
        io.print("\tEdit Product: Current Product" + order.getProductType());
        int width = 25;
        io.print("Product Type               Material Per Sq Ft.      Labor Per Sq Ft.");
        for (int i = 0; i < products.size(); i++) {
            if (order.getProductType().equals(products.get(i).getName())) {
                continue;
            }
            String display = "[" + Integer.toString(i+1) + "]: ";
            String field = products.get(i).getName();
            field = String.format("%-" + width  + "s", field);
            display += field;
            field = products.get(i).getCostPerSqFt().toString();
            field = String.format("%-" + width + "s", field);
            display += field;
            field = products.get(i).getLaborCostPerSqFt().toString();
            field = String.format("%-" + width + "s", field);
            display += field;
            io.print(display);
        }
        boolean hasProduct = false;
        String customerChoice = "";
        while(!hasProduct){
            try{
                customerChoice = io.readString("Enter a Number for a New Product Type or Press Enter to Continue");
                if (customerChoice.equals("")) {
                    hasProduct = true;
                } else {
                    for(Product product : products){
                        if (customerChoice.matches("\\d*") && Integer.parseInt(customerChoice) < products.size() && Integer.parseInt(customerChoice) > 0) {
                            customerChoice = products.get(Integer.parseInt(customerChoice) - 1).getName();
                            hasProduct = true;
                            break;
                        }
                    }
                }
                
            }catch(Exception e){
                io.print("\t>>>>>>> Please select a product we have carry. <<<<<<<");
            }
        }
        return customerChoice;
    }
    
    public boolean getRemove(){
        int saveChoice = io.readInt("Would You Like to Remove This Order\n[1] YES\n[2] NO", 1, 2);
        if (saveChoice == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public void displayRemoveSuccessBanner(String orderNumber){
        io.print("\t====== Order Number: " + orderNumber + " Successfully Removed ======");
    }
    
    public void displayOrderNotRemovedBanner(String orderNumber){
        io.print("\t====== Order " + orderNumber + "Was Not Removed ======");
    }
    
    public boolean getSaveAll(){
        int saveChoice = io.readInt("Would You Like to Save Your Work\n[1] YES\n[2] NO", 1, 2);
        if (saveChoice == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public void diplayNothingToSave(){
        io.print("\t==== There Were No Items to Save ====");
    }
}