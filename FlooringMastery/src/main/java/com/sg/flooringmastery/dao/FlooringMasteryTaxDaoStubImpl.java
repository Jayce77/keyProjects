/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jayce
 */
public class FlooringMasteryTaxDaoStubImpl implements FlooringMasteryTaxDao{
    
    private Tax onlyTax;
    private List<Tax> taxes = new ArrayList<>();
    
    public FlooringMasteryTaxDaoStubImpl(){
        onlyTax = new Tax("CA");
        onlyTax.setTaxRate(new BigDecimal("10.0"));
        
        taxes.add(onlyTax);
    }

    @Override
    public Tax addTax(String state, Tax tax) throws FlooringMasteryPersistenceException {
        if (state.equals(onlyTax.getState())) {
            return onlyTax;
        }else {
            return null;
        }
    }

    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        return taxes;
    }

    @Override
    public Tax getTax(String state) throws FlooringMasteryPersistenceException {
        if (state.equals(onlyTax.getState())) {
            return onlyTax;
        }else {
            return null;
        }
    }

    @Override
    public Tax removeTax(String state) throws FlooringMasteryPersistenceException {
        if (state.equals(onlyTax.getState())) {
            return onlyTax;
        }else {
            return null;
        }
    }

    @Override
    public Tax replaceTax(String state, Tax tax) throws FlooringMasteryPersistenceException {
        if (state.equals(onlyTax.getState())) {
            return onlyTax;
        }else {
            return null;
        }
    }
    
}
