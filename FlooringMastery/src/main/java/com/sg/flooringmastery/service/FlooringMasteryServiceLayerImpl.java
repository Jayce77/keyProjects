/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryOrderDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryProductDao;
import com.sg.flooringmastery.dao.FlooringMasteryTaxDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author jayce
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer{
    
    private FlooringMasteryOrderDao orderDao;
    private FlooringMasteryProductDao productDao;
    private FlooringMasteryTaxDao taxDao;
    private FlooringMasteryAuditDao auditDao;
    
    public FlooringMasteryServiceLayerImpl(
            FlooringMasteryOrderDao orderDao, FlooringMasteryProductDao productDao,
            FlooringMasteryTaxDao taxDao, FlooringMasteryAuditDao auditDao){
        
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException{
        List<Order>orders = orderDao.getAllOrders();
        return orders;
    }
    
    @Override
    public List<Order> getAllOrdersForDate(String date) throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderOnDateException {
        orderDao.loadOrders(date);
        List<Order>ordersForDate = orderDao.getAllOrders();
        return ordersForDate;
    }

    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        List<Product> products = productDao.getAllProducts();
        return products;
    }

    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        List<Tax>taxes = taxDao.getAllTaxes();
        return taxes;
    }

    @Override
    public Order getOrder(String orderNumber) throws FlooringMasteryPersistenceException
                                            , FlooringMasteryNoOrderOnDateException {
        return orderDao.getOrder(orderNumber);
    }
    
    @Override
    public Tax getTax(String state) throws FlooringMasteryPersistenceException {
        return taxDao.getTax(state);
    }
    
    @Override
    public Product getProduct(String productName) throws FlooringMasteryPersistenceException {
        return productDao.getProduct(productName);
    }

    @Override
    public Order createOrder(String customerName, BigDecimal area, String state, String productType) 
                            throws FlooringMasteryPersistenceException,
                                   FlooringMasteryNoOrderOnDateException,
                                   FlooringMasteryDataValidationException {
        validateEntries(customerName, area, state, productType);
        LocalDate ld = LocalDate.now();
        String dateToday = ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        Order newOrder = new Order();
        newOrder.setOrderDate(dateToday);
        Product product = productDao.getProduct(productType);
        newOrder.setProduct(product);
        Tax tax = taxDao.getTax(state);
        newOrder.setTax(tax);
        newOrder.setOrderNumber(getOrderNumber(newOrder));
        newOrder.setCustomerName(customerName);
        newOrder.setArea(area);
        newOrder.setState(tax.getState());
        newOrder.setTaxRate(tax.getTaxRate());
        newOrder.setProductType(product.getName());
        newOrder.setMaterialCostPerSqFt(product.getCostPerSqFt());
        newOrder.setLaborCostPerSqFt(product.getLaborCostPerSqFt());
        newOrder.setProductType(product.getName());
        newOrder.setMaterialCost(getMaterialCost(newOrder));
        newOrder.setLaborCost(getLaborCost(newOrder));
        newOrder.setTotalTax(getTotalTax(newOrder));
        newOrder.setTotal(getTotal(newOrder));
        return orderDao.addOrder(dateToday, newOrder);
    }
    
    private void validateEntries(
            String customerName, BigDecimal area, String state, String productType)
                throws FlooringMasteryDataValidationException {
        String stringArea = area.toString();
        if (customerName == null
                || customerName.trim().length() == 0
                || area == null
                || stringArea.trim().length() == 0
                || state == null
                || state.trim().length() == 0
                || productType == null
                || productType.trim().length() == 0) {
           throw new FlooringMasteryDataValidationException(
            "ERROR: All Fields [Customer Name, Area, State, Product type] are"
                    + "Required");
        }
    }
    
    private BigDecimal getMaterialCost(Order order){
        BigDecimal Area = order.getArea();
        BigDecimal perSqFt = order.getProduct().getCostPerSqFt();
        BigDecimal materialCost = Area.multiply(perSqFt);
        return materialCost;
    }
    
    private BigDecimal getLaborCost(Order order){
        BigDecimal Area = order.getArea();
        BigDecimal perSqFt = order.getProduct().getLaborCostPerSqFt();
        BigDecimal laborCost = Area.multiply(perSqFt);
        return laborCost;
    }
    
    private BigDecimal getTotalTax(Order order){
        BigDecimal materialCost = order.getMaterialCost();
        BigDecimal laborCost = order.getLaborCost();
        BigDecimal taxRate = order.getTaxRate().movePointLeft(2);
        BigDecimal totalTax = 
                taxRate.multiply(materialCost.add(laborCost))
                        .setScale(2, RoundingMode.HALF_UP);
        return totalTax;
    }
    
    private BigDecimal getTotal(Order order){
        BigDecimal beforeTax = order.getLaborCost().add(order.getMaterialCost());
        BigDecimal tax = order.getTotalTax();
        BigDecimal total = beforeTax.add(tax);
        return total;
    }
    
    private String getOrderNumber(Order order) throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderOnDateException {
        String date = order.getOrderDate();
        String orderNum;
        if (orderDao.doesFileExist(date)) {
            List<Order> ordersOnDate = getAllOrdersForDate(order.getOrderDate());
            int highNum = 0;
            for (int i = 0; i < ordersOnDate.size(); i++) {
                int ElementOrderNum =  Integer.parseInt(ordersOnDate.get(i).getOrderNumber());
                if (ElementOrderNum > highNum) {
                    highNum = ElementOrderNum;
                }
            }
            highNum += 1;
            orderNum = String.valueOf(highNum);
        } else {
            orderNum = "1";
        }
        return orderNum;
    }

    @Override
    public Order editOrder(Order order, String customerName, String area, String state, String productType) throws FlooringMasteryPersistenceException {
        if (customerName != null && customerName.trim().length() != 0){
            order.setCustomerName(customerName);
        }
        if (area != null && area.trim().length() != 0) {
            order.setArea(new BigDecimal(area));
            Product product = productDao.getProduct(order.getProductType());
            order.setProduct(product);
            order.setMaterialCostPerSqFt(order.getProduct().getCostPerSqFt());
            order.setLaborCostPerSqFt(order.getProduct().getLaborCostPerSqFt());
            order.setProductType(order.getProduct().getName());
            order.setMaterialCost(getMaterialCost(order));
            order.setLaborCost(getLaborCost(order));
            order.setTotalTax(getTotalTax(order));
            order.setTotal(getTotal(order));
        }
        if (state != null && state.trim().length() != 0) {
            Tax tax = taxDao.getTax(state);
            order.setTax(tax);
            order.setState(tax.getState());
            order.setTaxRate(tax.getTaxRate());
            order.setTotalTax(getTotalTax(order));
            order.setTotal(getTotal(order));
        }
        if (productType != null && productType.trim().length() != 0) {
            Product product = productDao.getProduct(productType);
            order.setProduct(product);
            order.setProductType(product.getName());
            order.setMaterialCostPerSqFt(product.getCostPerSqFt());
            order.setLaborCostPerSqFt(product.getLaborCostPerSqFt());
            order.setProductType(product.getName());
            order.setMaterialCost(getMaterialCost(order));
            order.setLaborCost(getLaborCost(order));
            order.setTotalTax(getTotalTax(order));
            order.setTotal(getTotal(order));
        }
        removeOrder(order.getOrderNumber());
        return orderDao.addOrder(order.getOrderNumber(), order);
    }
    
    @Override
    public Order removeOrder(String orderNumber) throws FlooringMasteryPersistenceException{
        Order removedOrder = orderDao.removeOrder(orderNumber);
        return removedOrder;
    }

    @Override
    public Order saveOrder(Order order) throws FlooringMasteryPersistenceException {
        orderDao.saveOrders(order.getOrderDate());
        return order;
    }
}
