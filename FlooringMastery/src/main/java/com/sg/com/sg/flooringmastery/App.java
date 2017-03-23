/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FlooringMasteryController;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.service.FlooringMasteryDataValidationException;
import com.sg.flooringmastery.service.FlooringMasteryNoOrderOnDateException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 *
 * @author jayce
 */
public class App {
    public static void main(String[] args) throws FlooringMasteryPersistenceException, FlooringMasteryNoOrderOnDateException, FlooringMasteryDataValidationException {
//        UserIO io = new UserIOConsoleImpl();
//        FlooringMasteryView view = new FlooringMasteryView(io);
//        FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderDaoFileImpl();
//        FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoFileImpl();
//        FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoFileImpl();
//        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoFileImpl();
//        FlooringMasteryServiceLayer service = new FlooringMasteryServiceLayerImpl(orderDao, productDao, taxDao, auditDao);
//        FlooringMasteryController controller 
//                = new FlooringMasteryController(view, service);
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConfigurableEnvironment env = ctx.getEnvironment();
        FlooringMasteryController controller = 
                ctx.getBean("controller", FlooringMasteryController.class);
        boolean isTrainig = controller.getMode();
        if (isTrainig) {
           env.setActiveProfiles("training");
        } else {
            env.setActiveProfiles("production");
        }
        ctx.refresh();
        controller = 
                ctx.getBean("controller", FlooringMasteryController.class);
        controller.run();
    }
    
       
}
