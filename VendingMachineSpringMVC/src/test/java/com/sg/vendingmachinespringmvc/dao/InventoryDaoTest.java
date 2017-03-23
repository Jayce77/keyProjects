/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.MachineSlot;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jayce
 */
public class InventoryDaoTest {
    
    private InventoryDao dao;
    
    public InventoryDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "test-applicationContext.xml");
        dao = ctx.getBean("inventoryDao", InventoryDao.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addMachineSlot method, of class InventoryDao.
     */
    @Test
    public void testAddGetDeleteMachineSlot() {
        
        MachineSlot ms = new MachineSlot();
        ms.setProductName("Twix");
        ms.setPrice(new BigDecimal("1.35"));
        ms.setQuantity(3);
        dao.addMachineSlot(ms);
        MachineSlot fromDb = dao.getMachineSlotById(ms.getSlotId());
        assertEquals(fromDb, ms);
        dao.removeMachineSlot(ms.getSlotId());
        assertNull(dao.getMachineSlotById(ms.getSlotId()));
    }

    /**
     * Test of updateMachineSlot method, of class InventoryDao.
     */
    @Test
    public void testUpdateMachineSlot() {
        MachineSlot ms = new MachineSlot();
        ms.setProductName("Doritos");
        ms.setPrice(new BigDecimal("1.25"));
        ms.setQuantity(2);
        dao.addMachineSlot(ms);
        ms.setPrice(new BigDecimal("2.25"));
        dao.updateMachineSlot(ms);
        MachineSlot fromDb = dao.getMachineSlotById(ms.getSlotId());
        assertEquals(fromDb, ms);
    }

    /**
     * Test of getAllMachineSlots method, of class InventoryDao.
     */
    @Test
    public void testGetAllMachineSlots() {
        MachineSlot ms = new MachineSlot();
        ms.setProductName("Doritos");
        ms.setPrice(new BigDecimal("1.25"));
        ms.setQuantity(2);
        dao.addMachineSlot(ms);
        MachineSlot ms2 = new MachineSlot();
        ms2.setProductName("Twix");
        ms2.setPrice(new BigDecimal("1.35"));
        ms2.setQuantity(3);
        dao.addMachineSlot(ms2);
        List<MachineSlot> slotList = dao.getAllMachineSlots();
        assertEquals(slotList.size(), 2);
    }

}
