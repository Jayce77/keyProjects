/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author jayce
 */
public class Product {
    
    private String name;
    private BigDecimal costPerSqFt;
    private BigDecimal LaborCostPerSqFt;
    
    public Product(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public BigDecimal getLaborCostPerSqFt() {
        return LaborCostPerSqFt;
    }

    public void setLaborCostPerSqFt(BigDecimal LaborCostPerSqFt) {
        this.LaborCostPerSqFt = LaborCostPerSqFt;
    }
    
    
}
