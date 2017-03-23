/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jayce
 */
public class FlooringMasteryOrderDaoStubImpl implements FlooringMasteryOrderDao{
    
    private Order onlyOrder;
    private List<Order> orders = new ArrayList<>();
    
    public FlooringMasteryOrderDaoStubImpl(){
        onlyOrder =  new Order("123");
        onlyOrder.setOrderDate("2222016");
        onlyOrder.setCustomerName("Jayce");
        onlyOrder.setState("CA");
        onlyOrder.setProductType("Wood");
        onlyOrder.setTaxRate(new BigDecimal("5.0"));
        onlyOrder.setArea(new BigDecimal("10"));
        onlyOrder.setMaterialCostPerSqFt(new BigDecimal("1"));
        onlyOrder.setLaborCostPerSqFt(new BigDecimal("1"));
        onlyOrder.setLaborCost(new BigDecimal("10"));
        onlyOrder.setMaterialCost(new BigDecimal("10"));
        onlyOrder.setTotalTax(new BigDecimal("1"));
        onlyOrder.setTotal(new BigDecimal("21"));
        onlyOrder.setTax(new Tax("CA"));
        onlyOrder.setProduct(new Product("Wood"));
        
        orders.add(onlyOrder);
    }

    @Override
    public Order addOrder(String orderNumber, Order order) throws FlooringMasteryPersistenceException {
        if (orderNumber.equals(onlyOrder.getOrderNumber())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
        return orders;
    }

    @Override
    public Order getOrder(String orderNumber) throws FlooringMasteryPersistenceException {
        if (orderNumber.equals(onlyOrder.getOrderNumber())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order removeOrder(String orderNumber) throws FlooringMasteryPersistenceException {
        if (orderNumber.equals(onlyOrder.getOrderNumber())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order replaceOrder(String orderNumber, Order order) throws FlooringMasteryPersistenceException {
        if (orderNumber.equals(onlyOrder.getOrderNumber())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public void loadOrders(String date) throws FlooringMasteryPersistenceException {
        //do nothing
    }

    @Override
    public void saveOrders(String Date) throws FlooringMasteryPersistenceException {
        //do nothing
    }

    @Override
    public boolean doesFileExist(String date) throws FlooringMasteryPersistenceException {
        //do nothing
        return true;
    }
}

