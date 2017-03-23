/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jayce
 */
public class FlooringMasteryServiceLayerTest {
    
    private FlooringMasteryServiceLayer service;
    
    public FlooringMasteryServiceLayerTest() {
//        FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderDaoStubImpl();
//        FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoStubImpl();
//        FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoStubImpl();
//        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoStubImpl();
//        service = new FlooringMasteryServiceLayerImpl(orderDao, productDao, taxDao, auditDao);
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", FlooringMasteryServiceLayer.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllOrdersForDate method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetAllOrdersForDate() throws Exception {
        assertEquals(1, service.getAllOrdersForDate("06012013").size());
    }

    /**
     * Test of getAllProducts method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        assertEquals(1, service.getAllProducts().size());
    }

    /**
     * Test of getAllTaxes method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetAllTaxes() throws Exception {
        assertEquals(1, service.getAllTaxes().size());
    }

    /**
     * Test of getOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetOrder() throws Exception {
        Order order = service.getOrder("123");
        assertNotNull(order);
        order = service.getOrder("321");
        assertNull(order);
    }

    /**
     * Test of getProduct method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetProduct() throws Exception {
        Product product = service.getProduct("Wood");
        assertNotNull(product);
        product = service.getProduct("Adamantium");
        assertNull(product);
    }

    /**
     * Test of getTax method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetTax() throws Exception {
        Tax tax = service.getTax("CA");
        assertNotNull(tax);
        tax = service.getTax("HI");
        assertNull(tax);
    }

    /**
     * Test of createOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCreateOrder() throws Exception {
        String customerName = "Bobby Jones";
        String state = "CA";
        String productType = "Wood";
        BigDecimal area = new BigDecimal("90");
        
        service.createOrder(customerName, area, state, productType);
    }
    
    @Test
    public void testCreateOrderInvalidData() throws Exception{
        String customerName = "Bobby Jones";
        String state = "CA";
        String productType = "";
        BigDecimal area = new BigDecimal("90");
        
        try{
            service.createOrder(customerName, area, state, productType);
            fail("Expected DataValidationException was not Thrown");
        } catch (FlooringMasteryDataValidationException e){
            return;
        }
    }
    // All total values have been set to 0 and tax at 5% and per valuees at 1
    // area at matCost and LaborCost at 10 each
    // Product is Wood and at 10 and 10
    // Tax is CA and 10%
    @Test
    public void testEditOrderName() throws Exception {
        Order originalOrder = service.getOrder("123");
        
        String customerName = "Hulk Hogan";
        String area = "";
        String state = "";
        String productType = "";
        
        Order editedOrder = service.editOrder(originalOrder, customerName, area, state, productType);
        assertEquals(editedOrder.getCustomerName(), "Hulk Hogan");
        
    }
    
    @Test
    public void testEditOrderArea() throws Exception {
        Order originalOrder = service.getOrder("123");
        
        String customerName = "";
        String area = "50";
        String state = "";
        String productType = "";
        
        Order editedOrder = service.editOrder(originalOrder, customerName, area, state, productType);
        BigDecimal expectedArea = new BigDecimal("50");
        BigDecimal expectedLcost = new BigDecimal("500.00");
        BigDecimal expectedMcost = new BigDecimal("500.00");
        BigDecimal expectedTotalCost = new BigDecimal("1050.00");
        assertEquals(expectedArea, editedOrder.getArea());
        assertEquals(expectedLcost, editedOrder.getLaborCost());
        assertEquals(expectedMcost, editedOrder.getMaterialCost());
        assertEquals(expectedTotalCost, editedOrder.getTotal());
        
    }
    
    @Test
    public void testEditOrderState() throws Exception {
        Order originalOrder = service.getOrder("123");
        
        String customerName = "";
        String area = "";
        String state = "CA";
        String productType = "";
        
        Order editedOrder = service.editOrder(originalOrder, customerName, area, state, productType);
        BigDecimal expectedTax = new BigDecimal("10.0");
        BigDecimal expectedLcost = new BigDecimal("10");
        BigDecimal expectedMcost = new BigDecimal("10");
        BigDecimal expectedTotalTax = new BigDecimal("2.00");
        BigDecimal expectedTotalCost = new BigDecimal("22.00");
        assertEquals(expectedTax, editedOrder.getTaxRate());
        assertEquals(expectedLcost, editedOrder.getLaborCost());
        assertEquals(expectedMcost, editedOrder.getMaterialCost());
        assertEquals(expectedTotalTax, editedOrder.getTotalTax());
        assertEquals(expectedTotalCost, editedOrder.getTotal());
        
    }
    
    @Test
    public void testEditOrderProduct() throws Exception {
        Order originalOrder = service.getOrder("123");
        
        String customerName = "";
        String area = "";
        String state = "";
        String productType = "Wood";
        
        Order editedOrder = service.editOrder(originalOrder, customerName, area, state, productType);
        BigDecimal expectedTax = new BigDecimal("5.0");
        BigDecimal expectedLcost = new BigDecimal("100.00");
        BigDecimal expectedMcost = new BigDecimal("100.00");
        BigDecimal expectedTotalTax = new BigDecimal("10.00");
        BigDecimal expectedTotalCost = new BigDecimal("210.00");
        assertEquals(expectedTax, editedOrder.getTaxRate());
        assertEquals(expectedLcost, editedOrder.getLaborCost());
        assertEquals(expectedMcost, editedOrder.getMaterialCost());
        assertEquals(expectedTotalTax, editedOrder.getTotalTax());
        assertEquals(expectedTotalCost, editedOrder.getTotal());
        
    }
    
    @Test
    public void removeOrder() throws Exception {
        Order order = service.removeOrder("123");
        assertNotNull(order);
        order = service.getOrder("321");
        assertNull(order);
    }
    
}
