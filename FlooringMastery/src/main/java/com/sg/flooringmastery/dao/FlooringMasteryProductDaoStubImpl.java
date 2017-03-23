/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jayce
 */
public class FlooringMasteryProductDaoStubImpl implements FlooringMasteryProductDao{
    
    private Product onlyProduct;
    private List<Product> products = new ArrayList<>();
    
    public FlooringMasteryProductDaoStubImpl(){
        onlyProduct = new Product("Wood");
        onlyProduct.setLaborCostPerSqFt(new BigDecimal("10.00"));
        onlyProduct.setCostPerSqFt(new BigDecimal("10.00"));
        
        products.add(onlyProduct);
    }

    @Override
    public Product addProduct(String productType, Product product) throws FlooringMasteryPersistenceException {
        if (productType.equals(onlyProduct.getName())) {
           return onlyProduct; 
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        return products;
    }

    @Override
    public Product getProduct(String productType) throws FlooringMasteryPersistenceException {
        if (productType.equals(onlyProduct.getName())) {
           return onlyProduct; 
        } else {
            return null;
        }
    }

    @Override
    public Product removeProduct(String productType) throws FlooringMasteryPersistenceException {
        if (productType.equals(onlyProduct.getName())) {
           return onlyProduct; 
        } else {
            return null;
        }
    }

    @Override
    public Product replaceProduct(String productType, Product product) throws FlooringMasteryPersistenceException {
        if (productType.equals(onlyProduct.getName())) {
           return onlyProduct; 
        } else {
            return null;
        }
    }
    
}
