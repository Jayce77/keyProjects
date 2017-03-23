/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.model.MachineSlot;
import com.sg.vendingmachinespringmvc.model.MoneyNames;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jayce
 */

@Controller
@RequestMapping(value = "/")
public class VendingMachineController {
    
    private boolean hasInventory = false;
    private boolean hasMoney = false;
    private boolean isMachineRowSelected = false;
    private boolean hasInsertedMoney = false;
    private boolean canMakePurchase = false;
    private boolean hasMadePurchase = false;
    public MachineSlot selectedMachineSlot;
    public BigDecimal totalInserted = new BigDecimal("0.00");
    public BigDecimal changeDue;
    public String changeString;
    public  String messageString = "";
    
    VendingMachineServiceLayer service;
    
    @Inject 
    public  VendingMachineController(VendingMachineServiceLayer service){
        this.service = service;
    }
    
    @RequestMapping
    public String displayVendingMachine(Model model){
        if (!hasInventory) {
            service.populateMachineSlots();
            hasInventory = true;
        }
        
        if (!hasMoney) {
            service.populateMoney();
            hasMoney = true;
        }
        
        if (isMachineRowSelected) {
            model.addAttribute("selectedMachineSlot", selectedMachineSlot);
        }
        
        if (hasInsertedMoney) {
            model.addAttribute("totalInserted", totalInserted);
        }
        
        if (hasMadePurchase) {
            model.addAttribute("changeString", changeString);
        }
        
        List<MachineSlot> machineSlotList = service.getAllSlots();
        model.addAttribute("machineSlotList", machineSlotList);
        
        return "index";
    }
    
    @RequestMapping(value="/selectMachineSlot", method = RequestMethod.GET)
    public String selectMachineSlot(HttpServletRequest request){
        String machineSlotIdParameter = request.getParameter("slotId");
        int slotId = Integer.parseInt(machineSlotIdParameter);
        isMachineRowSelected = true;
        selectedMachineSlot = service.getMachineSlotById(slotId);
        return "redirect:/";

    }
    
    @RequestMapping(value="/addMoney", method = RequestMethod.GET)
    public String addMoney(HttpServletRequest request){
        String adddButtonIdParameter = request.getParameter("addButtonId");
        service.insertMoney(MoneyNames.valueOf(adddButtonIdParameter));
        hasInsertedMoney = true;
        totalInserted = service.getTotalInserted();
        return "redirect:/";
    }
    
    @RequestMapping(value="/makePurchase", method = RequestMethod.GET)
    public String makePurcahase(HttpServletRequest request){
        canMakePurchase = service.canPurchaseItem(selectedMachineSlot.getSlotId());
        if (canMakePurchase) {
            changeDue = service.purchaseItem(selectedMachineSlot.getSlotId());
            hasMadePurchase = true;
            totalInserted = new BigDecimal("0.00");
            hasInsertedMoney = false;
            changeString = service.returnChange(changeDue);
            
        }
        return "redirect:/";
    }
    
    @RequestMapping(value="/getChange", method = RequestMethod.GET)
    public String getChange(HttpServletRequest request){
        if (hasMadePurchase) {
            service.reset();
            totalInserted = new BigDecimal("0.00");
            changeDue =  new BigDecimal("0.00");
            changeString = "";
            messageString = "";
            hasMadePurchase = false;
            isMachineRowSelected = false;
            hasInsertedMoney = false;
            canMakePurchase = false;
            
        } else {
            changeString = service.returnChange(totalInserted);
            hasMadePurchase = true;
        }
        
        
       return "redirect:/"; 
    }
}
