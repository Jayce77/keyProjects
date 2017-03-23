/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jayce
 */
public class FlooringMasteryTaxDaoTest {
    
    FlooringMasteryTaxDao dao = new FlooringMasteryTaxDaoFileImpl();
    
    public FlooringMasteryTaxDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
        List<Tax> taxes = dao.getAllTaxes();
        for(Tax tax :  taxes){
            dao.removeTax(tax.getState());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addTax method, of class FlooringMasteryTaxDao.
     */
    @Test
    public void testAddGetTax() throws Exception {
        Tax taxA = new Tax("CA");
        taxA.setTaxRate(new BigDecimal(".08"));
        dao.addTax(taxA.getState(), taxA);
        
        Tax fromDao = dao.getTax("CA");
        assertEquals(fromDao, taxA);
    }

    /**
     * Test of getAllTaxes method, of class FlooringMasteryTaxDao.
     */
    @Test
    public void testGetAllTaxes() throws Exception {
        Tax taxA = new Tax("CA");
        taxA.setTaxRate(new BigDecimal(".08"));
        dao.addTax(taxA.getState(), taxA);
        
        Tax taxB = new Tax("NY");
        taxA.setTaxRate(new BigDecimal(".06"));
        dao.addTax(taxB.getState(), taxB);
        
        assertEquals(6, dao.getAllTaxes().size());
    }

    /**
     * Test of removeTax method, of class FlooringMasteryTaxDao.
     */
    @Test
    public void testRemoveTax() throws Exception {
        Tax taxA = new Tax("CA");
        taxA.setTaxRate(new BigDecimal(".08"));
        dao.addTax(taxA.getState(), taxA);
        
        Tax taxB = new Tax("NY");
        taxA.setTaxRate(new BigDecimal(".06"));
        dao.addTax(taxB.getState(), taxB);
        
        assertEquals(6, dao.getAllTaxes().size());
        Tax removedTax = dao.removeTax("CA");
        assertEquals(removedTax, taxA);
        assertEquals(5, dao.getAllTaxes().size());
        removedTax = dao.removeTax("NY");
        assertEquals(removedTax, taxB);
        assertEquals(4, dao.getAllTaxes().size());
        
    }

    /**
     * Test of replaceTax method, of class FlooringMasteryTaxDao.
     */
    @Test
    public void testReplaceTax() throws Exception {
        Tax taxA = new Tax("CA");
        taxA.setTaxRate(new BigDecimal(".08"));
        dao.addTax(taxA.getState(), taxA);
        
        taxA.setTaxRate(new BigDecimal(".01"));
        assertEquals(dao.getTax("CA").getTaxRate(), new BigDecimal(".01"));
        assertEquals(dao.getAllTaxes().size(), 5);
    }

}
