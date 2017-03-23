/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jayce
 */
public class FlooringMasteryOrderDaoFileImpl implements FlooringMasteryOrderDao{
    
    private static final String DELIMITER = ",";
    private String filename;
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public Order addOrder(String orderNumber, Order order) throws FlooringMasteryPersistenceException {
        orders.put(orderNumber, order);
        Order newOrder = orders.get(orderNumber);
        return newOrder;
    }

    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
        return new ArrayList<Order>(orders.values());
    }

    @Override
    public Order getOrder(String orderNumber) throws FlooringMasteryPersistenceException {
        Order saughtOrder = orders.get(orderNumber);
        return saughtOrder;
    }

    @Override
    public Order removeOrder(String orderNumber) throws FlooringMasteryPersistenceException {
        Order removedOrder = orders.remove(orderNumber);
        return removedOrder;
    }

    @Override
    public Order replaceOrder(String orderNumber, Order order) throws FlooringMasteryPersistenceException {
        Order editedOrder = orders.put(orderNumber, order);
        return editedOrder;
    }
    
    @Override
    public boolean doesFileExist(String date) throws FlooringMasteryPersistenceException {
        String directoryName = "/home/jayce/NetBeansProjects/FlooringMastery/";
        File directory = new File(directoryName);
        boolean doesFileExist = false;
        File[] flist = directory.listFiles();
        
        for( File file : flist){
            if (file.isFile()) {
                if (file.getName().contains(date)) {
                    doesFileExist = true;
                    break;
                }
            }
        }
        return doesFileExist;
    }
    
    public void loadOrders(String date) throws FlooringMasteryPersistenceException {
        filename = "Orders_" + date + ".txt";
        List<String[]> fileData= loadFiles(filename);
        for (int i = 1; i < fileData.size(); i++) {
            String[] fileLine = fileData.get(i);
            Order currentOrder = new Order();
            currentOrder.setOrderDate(date);
            currentOrder.setOrderNumber(fileLine[0]);
            currentOrder.setCustomerName(fileLine[1]);
            currentOrder.setState(fileLine[2]);
            currentOrder.setTaxRate(new BigDecimal(fileLine[3]));
            currentOrder.setProductType(fileLine[4]);
            currentOrder.setArea(new BigDecimal(fileLine[5]));
            currentOrder.setMaterialCostPerSqFt(new BigDecimal(fileLine[6]));
            currentOrder.setLaborCostPerSqFt(new BigDecimal(fileLine[7]));
            currentOrder.setMaterialCost(new BigDecimal(fileLine[8]));
            currentOrder.setLaborCost(new BigDecimal(fileLine[9]));
            currentOrder.setTotalTax(new BigDecimal(fileLine[10]));
            currentOrder.setTotal(new BigDecimal(fileLine[11]));
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
    }
    
    @Override
    public void saveOrders(String date) throws FlooringMasteryPersistenceException {
        writeFiles(date);
        orders.clear();
    }
    
    private List<String[]> loadFiles(String file) throws FlooringMasteryPersistenceException{
        
        Scanner scanner;
        
        try{
            scanner = new Scanner(
                new BufferedReader(
                    new FileReader(file)));
        } catch(FileNotFoundException e){
            throw new FlooringMasteryPersistenceException(
                "-_- Could not load the " + file + " to Memory", e);
        }
        String currentLine;
        List<String[]> fileLines = new ArrayList<>();
        String[] currentTokens;
        int counter = 0;
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            fileLines.add(counter, currentTokens);
            counter++;
        }
        scanner.close();
        return fileLines;
    }
    
    private void writeFiles(String date) throws FlooringMasteryPersistenceException{
        boolean fileExist = doesFileExist(date);
        PrintWriter out;
        filename = "Orders_" + date + ".txt";
        try{
            out =  new PrintWriter(new FileWriter(filename));
        } catch(IOException e) {
            throw new FlooringMasteryPersistenceException(
                "Could not save the order data.", e);
        }
        List<Order> orderList = this.getAllOrders();
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
        for (Order currentOrder : orderList) {
            out.println(currentOrder.getOrderNumber() + DELIMITER
            + currentOrder.getCustomerName() + DELIMITER
            + currentOrder.getState() + DELIMITER
            + currentOrder.getTaxRate().toString() + DELIMITER
            + currentOrder.getProductType() + DELIMITER
            + currentOrder.getArea().toString() + DELIMITER
            + currentOrder.getMaterialCostPerSqFt().toString() + DELIMITER
            + currentOrder.getLaborCostPerSqFt().toString() + DELIMITER
            + currentOrder.getMaterialCost().toString() + DELIMITER
            + currentOrder.getLaborCost().toString() + DELIMITER
            + currentOrder.getTotalTax().toString() + DELIMITER
            + currentOrder.getTotal().toString());
            out.flush();
        }
        out.close();
    }

}
