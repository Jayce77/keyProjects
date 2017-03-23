/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.MachineSlot;
import java.util.List;

/**
 *
 * @author jayce
 */
public interface InventoryDao {
    
    public MachineSlot addMachineSlot(MachineSlot machineSlot);
    
    public void removeMachineSlot(int slotId);
    
    public void updateMachineSlot(MachineSlot machineSlot);
    
    public List<MachineSlot> getAllMachineSlots();
    
    public MachineSlot getMachineSlotById(int slotId);
    
}
