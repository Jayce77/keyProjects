/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Money;
import com.sg.vendingmachinespringmvc.model.MoneyNames;
import java.math.BigDecimal;
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
public class MoneyDaoTest {
    
    private MoneyDao dao;
    
    public MoneyDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx =  new ClassPathXmlApplicationContext(
                "test-applicationContext.xml");
        dao = ctx.getBean("moneyDao", MoneyDao.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addMoney method, of class MoneyDao.
     */
    @Test
    public void testAddGetDeleteMoney() {
        Money nm = new Money();
        nm.setName(MoneyNames.DIMES);
        nm.setValue(new BigDecimal(".10"));
        nm.setQuantity(10);
        dao.addMoney(nm);
        Money fromDb = dao.getMoneyByName(MoneyNames.DIMES);
        assertEquals(fromDb, nm);
        dao.removeMoney(MoneyNames.DIMES);
        assertNull(dao.getMoneyByName(MoneyNames.DIMES));
    }

    /**
     * Test of updateMoney method, of class MoneyDao.
     */
    @Test
    public void testUpdateMoney() {
        Money nm = new Money();
        nm.setName(MoneyNames.NICKELS);
        nm.setValue(new BigDecimal(".05"));
        nm.setQuantity(15);
        dao.addMoney(nm);
        nm.setQuantity(16);
        dao.updateMoney(nm);
        Money fromDb = dao.getMoneyByName(MoneyNames.NICKELS);
        assertEquals(fromDb, nm);
    }

    /**
     * Test of getAllMoney method, of class MoneyDao.
     */
    @Test
    public void testGetAllMoney() {
        Money nm = new Money();
        nm.setName(MoneyNames.DIMES);
        nm.setValue(new BigDecimal(".10"));
        nm.setQuantity(10);
        dao.addMoney(nm);
        Money nm2 = new Money();
        nm2.setName(MoneyNames.NICKELS);
        nm2.setValue(new BigDecimal(".05"));
        nm2.setQuantity(15);
        dao.addMoney(nm2);
        assertEquals(dao.getAllMoney().size(), 2);
    }
    
}
