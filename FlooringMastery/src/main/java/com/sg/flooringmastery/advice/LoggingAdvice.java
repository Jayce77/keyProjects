/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.advice;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author jayce
 */
public class LoggingAdvice {
    
    FlooringMasteryAuditDao auditDao;
    
    public LoggingAdvice(FlooringMasteryAuditDao auditDao){
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp, Object retVal){
        String auditEntry = jp.getSignature().getName() + ": ";
        Object order = retVal;
        auditEntry += retVal.toString();
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch(FlooringMasteryPersistenceException e){
            System.err.println(
                "ERROR: Could not create the audit entry in LoggingAdvice.");
        }
    }
}
