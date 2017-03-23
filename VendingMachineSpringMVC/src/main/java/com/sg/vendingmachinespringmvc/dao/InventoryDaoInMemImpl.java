/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.MachineSlot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jayce
 */
public class InventoryDaoInMemImpl implements  InventoryDao{
    
    private Map<Integer, MachineSlot> slotMap = new HashMap<>();
    private static int slotIdCounter = 0;

    @Override
    public MachineSlot addMachineSlot(MachineSlot machineSlot) {
        machineSlot.setSlotId(slotIdCounter);
        slotIdCounter++;
        slotMap.put(machineSlot.getSlotId(), machineSlot);
        return machineSlot;
    }

    @Override
    public void removeMachineSlot(int slotId) {
        slotMap.remove(slotId);
    }

    @Override
    public void updateMachineSlot(MachineSlot machineSlot) {
        slotMap.put(machineSlot.getSlotId(),machineSlot);
    }

    @Override
    public List<MachineSlot> getAllMachineSlots() {
        Collection<MachineSlot> c = slotMap.values();
        return new ArrayList(c);
    }

    @Override
    public MachineSlot getMachineSlotById(int slotId) {
        return slotMap.get(slotId);
    }
    
    
}
