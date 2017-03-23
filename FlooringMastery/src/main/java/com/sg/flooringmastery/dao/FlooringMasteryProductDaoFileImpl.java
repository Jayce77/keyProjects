/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
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
public class FlooringMasteryProductDaoFileImpl implements FlooringMasteryProductDao{
    
    private static final String PRODUCT_TYPES_FILE = "Products.txt";
    private static final String DELIMITER = ",";
    private Map<String, Product> products = new HashMap<>();

    @Override
    public Product addProduct(String productType, Product product) throws FlooringMasteryPersistenceException {
        products.put(productType, product);
        Product addedProduct = products.get(productType);
        return addedProduct;
    }

    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        loadProducts();
        return new ArrayList<Product>(products.values());
    }

    @Override
    public Product getProduct(String productType) throws FlooringMasteryPersistenceException {
        loadProducts();
        Product saughtProduct = products.get(productType);
        return saughtProduct;
    }

    @Override
    public Product removeProduct(String productType) throws FlooringMasteryPersistenceException {
        Product removedProduct = products.remove(productType);
        return removedProduct;
    }

    @Override
    public Product replaceProduct(String productType, Product product) throws FlooringMasteryPersistenceException {
        Product editedProduct = products.put(productType, product);
        return editedProduct;
    }
    
    private void loadProducts() throws FlooringMasteryPersistenceException {
        List<String[]> fileData= loadFiles(PRODUCT_TYPES_FILE);
        for (int i = 1; i < fileData.size(); i++) {
            String[] fileLine = fileData.get(i);
            Product currentProduct = new Product(fileLine[0]);
            currentProduct.setCostPerSqFt(new BigDecimal(fileLine[1]));
            currentProduct.setLaborCostPerSqFt(new BigDecimal(fileLine[2]));
            products.put(currentProduct.getName(), currentProduct);
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
