/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jayce
 */
public class FlooringMasteryOrderDaoTest {
    
    FlooringMasteryOrderDao dao = new FlooringMasteryOrderDaoFileImpl();
    
    public FlooringMasteryOrderDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
        List<Order> orders = dao.getAllOrders();
        for(Order order :  orders){
            dao.removeOrder(order.getOrderNumber());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testAddGetOrder() throws Exception {
        Order newOrder =  new Order("123");
        newOrder.setOrderDate("2/22/2016");
        newOrder.setCustomerName("Jayce");
        newOrder.setState("CA");
        newOrder.setProductType("Wood");
        newOrder.setTaxRate(new BigDecimal("7.5"));
        newOrder.setArea(new BigDecimal("90"));
        newOrder.setMaterialCostPerSqFt(new BigDecimal("5.25"));
        newOrder.setLaborCostPerSqFt(new BigDecimal("1.25"));
        newOrder.setLaborCost(new BigDecimal("40.25"));
        newOrder.setMaterialCost(new BigDecimal("150"));
        newOrder.setTotalTax(new BigDecimal("12.75"));
        newOrder.setTotal(new BigDecimal("250.94"));
        
        dao.addOrder("123", newOrder);
        
        Order fromDao = dao.getOrder(newOrder.getOrderNumber());
        
        assertEquals(fromDao, newOrder);
    }

    /**
     * Test of getAllOrders method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        Order orderA =  new Order("123");
        orderA.setOrderDate("2/22/2016");
        orderA.setCustomerName("Jayce");
        orderA.setState("CA");
        orderA.setProductType("Wood");
        orderA.setTaxRate(new BigDecimal("7.5"));
        orderA.setArea(new BigDecimal("90"));
        orderA.setMaterialCostPerSqFt(new BigDecimal("5.25"));
        orderA.setLaborCostPerSqFt(new BigDecimal("1.25"));
        orderA.setLaborCost(new BigDecimal("40.25"));
        orderA.setMaterialCost(new BigDecimal("150"));
        orderA.setTotalTax(new BigDecimal("12.75"));
        orderA.setTotal(new BigDecimal("250.94"));
        
        dao.addOrder("123", orderA);
        
        Order orderB =  new Order("098");
        orderB.setOrderDate("2/24/2016");
        orderB.setCustomerName("Fukuko");
        orderB.setState("CA");
        orderB.setProductType("Wood");
        orderB.setTaxRate(new BigDecimal("7.5"));
        orderB.setArea(new BigDecimal("90"));
        orderB.setMaterialCostPerSqFt(new BigDecimal("5.25"));
        orderB.setLaborCostPerSqFt(new BigDecimal("1.25"));
        orderB.setLaborCost(new BigDecimal("40.25"));
        orderB.setMaterialCost(new BigDecimal("150"));
        orderB.setTotalTax(new BigDecimal("12.75"));
        orderB.setTotal(new BigDecimal("250.94"));
        
        dao.addOrder("098", orderB);
        
        assertEquals(2, dao.getAllOrders().size());
        
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        Order orderA =  new Order("123");
        orderA.setOrderDate("2/22/2016");
        orderA.setCustomerName("Jayce");
        orderA.setState("CA");
        orderA.setProductType("Wood");
        orderA.setTaxRate(new BigDecimal("7.5"));
        orderA.setArea(new BigDecimal("90"));
        orderA.setMaterialCostPerSqFt(new BigDecimal("5.25"));
        orderA.setLaborCostPerSqFt(new BigDecimal("1.25"));
        orderA.setLaborCost(new BigDecimal("40.25"));
        orderA.setMaterialCost(new BigDecimal("150"));
        orderA.setTotalTax(new BigDecimal("12.75"));
        orderA.setTotal(new BigDecimal("250.94"));
        
        dao.addOrder("123", orderA);
        
        Order orderB =  new Order("098");
        orderB.setOrderDate("2/24/2016");
        orderB.setCustomerName("Fukuko");
        orderB.setState("CA");
        orderB.setProductType("Wood");
        orderB.setTaxRate(new BigDecimal("7.5"));
        orderB.setArea(new BigDecimal("90"));
        orderB.setMaterialCostPerSqFt(new BigDecimal("5.25"));
        orderB.setLaborCostPerSqFt(new BigDecimal("1.25"));
        orderB.setLaborCost(new BigDecimal("40.25"));
        orderB.setMaterialCost(new BigDecimal("150"));
        orderB.setTotalTax(new BigDecimal("12.75"));
        orderB.setTotal(new BigDecimal("250.94"));
        
        dao.addOrder("098", orderB);
        
        assertEquals(2, dao.getAllOrders().size());
        Order fromDao = dao.removeOrder("123");
        assertEquals(fromDao, orderA);
        assertEquals(1, dao.getAllOrders().size());
        fromDao = dao.removeOrder("098");
        assertEquals(fromDao, orderB);
        assertEquals(0, dao.getAllOrders().size());
    }

    /**
     * Test of replaceOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testReplaceOrder() throws Exception {
        Order orderA =  new Order("123");
        orderA.setOrderDate("2/22/2016");
        orderA.setCustomerName("Jayce");
        orderA.setState("CA");
        orderA.setProductType("Wood");
        orderA.setTaxRate(new BigDecimal("7.5"));
        orderA.setArea(new BigDecimal("90"));
        orderA.setMaterialCostPerSqFt(new BigDecimal("5.25"));
        orderA.setLaborCostPerSqFt(new BigDecimal("1.25"));
        orderA.setLaborCost(new BigDecimal("40.25"));
        orderA.setMaterialCost(new BigDecimal("150"));
        orderA.setTotalTax(new BigDecimal("12.75"));
        orderA.setTotal(new BigDecimal("250.94"));
        
        dao.addOrder("123", orderA);
        assertEquals(dao.getOrder("123").getState(), "CA");
        
        orderA.setState("AZ");
        dao.replaceOrder(orderA.getOrderNumber(), orderA);
        assertEquals(dao.getOrder("123").getState(), "AZ");
        assertEquals(1, dao.getAllOrders().size());
    }

    /**
     * Test of loadOrders method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testLoadOrders() throws Exception {
        dao.loadOrders("06012013");
        assertEquals(1, dao.getAllOrders().size());
        String expectValue = "Wise";
        Order fromDao = dao.getOrder("1");
        assertEquals(expectValue, fromDao.getCustomerName());
        
    }
    
    /**
     * Test of loadOrders method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void doesFileExist() throws Exception {
        
        assertTrue(dao.doesFileExist("06012013"));
        assertFalse(dao.doesFileExist("06012014"));
        
    }


}
