/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

/**
 *
 * @author jayce
 */
public class FlooringMasteryNoOrderFoundException extends Exception{
    public FlooringMasteryNoOrderFoundException(String message){
        super(message);
    }
    
    public FlooringMasteryNoOrderFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
