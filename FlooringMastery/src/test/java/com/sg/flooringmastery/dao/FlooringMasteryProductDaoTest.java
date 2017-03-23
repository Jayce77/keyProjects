/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
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
public class FlooringMasteryProductDaoTest {
    
    FlooringMasteryProductDao dao = new FlooringMasteryProductDaoFileImpl();
    
    public FlooringMasteryProductDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
        List<Product> products = dao.getAllProducts();
        for(Product product :  products){
            dao.removeProduct(product.getName());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addProduct method, of class FlooringMasteryProductDao.
     */
    @Test
    public void testAddProduct() throws Exception {
        Product newMaterial = new Product("adamantium");
        newMaterial.setLaborCostPerSqFt(new BigDecimal("100.00"));
        newMaterial.setLaborCostPerSqFt(new BigDecimal("88.00"));
        dao.addProduct(newMaterial.getName(), newMaterial);
        
        Product productFromDao = dao.getProduct("adamantium");
        
        assertEquals(newMaterial, productFromDao);
    }

    /**
     * Test of getAllProducts method, of class FlooringMasteryProductDao.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        Product newMaterialB = new Product("vibranium");
        newMaterialB.setLaborCostPerSqFt(new BigDecimal("80.00"));
        newMaterialB.setLaborCostPerSqFt(new BigDecimal("56.00"));
        dao.addProduct(newMaterialB.getName(), newMaterialB);
        
        Product newMaterialA = new Product("adamantium");
        newMaterialA.setLaborCostPerSqFt(new BigDecimal("100.00"));
        newMaterialA.setLaborCostPerSqFt(new BigDecimal("88.00"));
        dao.addProduct(newMaterialA.getName(), newMaterialA);
        
        assertEquals(6, dao.getAllProducts().size());
        
    }

    /**
     * Test of removeProduct method, of class FlooringMasteryProductDao.
     */
    @Test
    public void testRemoveProduct() throws Exception {
        Product newMaterialB = new Product("vibranium");
        newMaterialB.setLaborCostPerSqFt(new BigDecimal("80.00"));
        newMaterialB.setLaborCostPerSqFt(new BigDecimal("56.00"));
        dao.addProduct(newMaterialB.getName(), newMaterialB);
        
        Product newMaterialA = new Product("adamantium");
        newMaterialA.setLaborCostPerSqFt(new BigDecimal("100.00"));
        newMaterialA.setLaborCostPerSqFt(new BigDecimal("88.00"));
        dao.addProduct(newMaterialA.getName(), newMaterialA);
        
        assertEquals(6, dao.getAllProducts().size());
        Product fromDao = dao.removeProduct("vibranium");
        assertEquals(fromDao, newMaterialB);
        assertEquals(5, dao.getAllProducts().size());
        fromDao = dao.removeProduct("adamantium");
        assertEquals(fromDao, newMaterialA);
        assertEquals(4, dao.getAllProducts().size());
    }

    /**
     * Test of replaceProduct method, of class FlooringMasteryProductDao.
     */
    @Test
    public void testReplaceProduct() throws Exception {
        Product newMaterialB = new Product("vibranium");
        newMaterialB.setCostPerSqFt(new BigDecimal("80.00"));
        newMaterialB.setLaborCostPerSqFt(new BigDecimal("56.00"));
        dao.addProduct(newMaterialB.getName(), newMaterialB);
        
        assertEquals(new BigDecimal("80.00"), newMaterialB.getCostPerSqFt());
        newMaterialB.setCostPerSqFt(new BigDecimal("22.22"));
        dao.replaceProduct(newMaterialB.getName(), newMaterialB);
        
        assertEquals(dao.getProduct("vibranium").getCostPerSqFt(), new BigDecimal("22.22"));
        assertEquals(dao.getAllProducts().size(), 5);
    }

    
}
