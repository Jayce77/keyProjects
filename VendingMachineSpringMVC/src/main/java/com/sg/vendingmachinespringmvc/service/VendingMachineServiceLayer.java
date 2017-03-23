/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.MachineSlot;
import com.sg.vendingmachinespringmvc.model.Money;
import com.sg.vendingmachinespringmvc.model.MoneyNames;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jayce
 */
public interface VendingMachineServiceLayer {
    
    List<Money> getAllMoney();
    
    List<MachineSlot> getAllSlots();
    
    Money addMoney(Money money);
    
    MachineSlot addMachineSlot(MachineSlot slot);
    
    void removeMoney(MoneyNames name);
    
    void removeMachineSlot(int slotId);
    
    void updateMoney(Money money);
    
    void updateMachineSlot(MachineSlot slot);
    
    void populateMoney();
    
    void populateMachineSlots();
    
    void insertMoney(MoneyNames moneyChoice);
    
    BigDecimal purchaseItem(int selectedSlotId);
    
    boolean canPurchaseItem(int selectedSlotId);
    
    String returnChange(BigDecimal changeDue);
    
    MachineSlot getMachineSlotById(int selectedSlotId);
    
    BigDecimal getTotalInserted();
    
    void reset();
    
}
