/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class FlooringMasteryTaxDaoFileImpl implements FlooringMasteryTaxDao{
    
    private static final String TAX_RATES_FILE = "Taxes.txt";
    private static final String DELIMITER = ",";
    private Map<String, Tax> taxes = new HashMap<>();

    @Override
    public Tax addTax(String state, Tax tax) throws FlooringMasteryPersistenceException {
        taxes.put(state, tax);
        Tax addedTax = taxes.get(state);
        return addedTax;
    }

    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        loadTaxes();
        return new ArrayList<Tax>(taxes.values());
    }

    @Override
    public Tax getTax(String state) throws FlooringMasteryPersistenceException {
        loadTaxes();
        Tax saughtTax = taxes.get(state);
        return saughtTax;
    }

    @Override
    public Tax removeTax(String state) throws FlooringMasteryPersistenceException {
        Tax removedTax = taxes.remove(state);
        return removedTax;
    }

    @Override
    public Tax replaceTax(String state, Tax tax) throws FlooringMasteryPersistenceException {
        Tax editedTax = taxes.put(state, tax);
        return editedTax;
    }
    
    private void loadTaxes() throws FlooringMasteryPersistenceException {
        List<String[]> fileData= loadFiles(TAX_RATES_FILE);
        for (int i = 1; i < fileData.size(); i++) {
            String[] fileLine = fileData.get(i);
            Tax currentTax = new Tax(fileLine[0]);
            currentTax.setState(fileLine[0]);
            currentTax.setTaxRate(new BigDecimal(fileLine[1]));
            taxes.put(fileLine[0], currentTax);
        }
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
    
}
