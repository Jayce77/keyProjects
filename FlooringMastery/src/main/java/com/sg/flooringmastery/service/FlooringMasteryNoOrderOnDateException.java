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
public class FlooringMasteryNoOrderOnDateException extends Exception{
    public FlooringMasteryNoOrderOnDateException(String message){
        super(message);
    }
    
    public FlooringMasteryNoOrderOnDateException(String message, Throwable cause){
        super(message, cause);
    }
}
