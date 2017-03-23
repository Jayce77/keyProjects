/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.InventoryDao;
import com.sg.vendingmachinespringmvc.dao.MoneyDao;
import com.sg.vendingmachinespringmvc.model.MachineSlot;
import com.sg.vendingmachinespringmvc.model.Money;
import com.sg.vendingmachinespringmvc.model.MoneyNames;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jayce
 */

@Controller
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    
    private InventoryDao inventoryDao;
    private MoneyDao moneyDao;
    private static final BigDecimal DOLLABILL = new BigDecimal("1.00");
    private static final BigDecimal QUARTER = new BigDecimal(".25");
    private static final BigDecimal DIME = new BigDecimal(".10");
    private static final BigDecimal NICKEL = new BigDecimal(".05");
    public  Money insertedDollars = new Money(MoneyNames.DOLLARS, DOLLABILL, 0),
                  insertedQuarters = new Money(MoneyNames.QUARTERS, QUARTER, 0),
                  insertedDimes = new Money(MoneyNames.DIMES, DIME, 0),
                  insertedNickels = new Money(MoneyNames.NICKELS, NICKEL, 0);
    public  Money returningDollars = new Money(MoneyNames.DOLLARS, DOLLABILL, 0),
                  returningQuarters = new Money(MoneyNames.QUARTERS, QUARTER, 0),
                  returningDimes = new Money(MoneyNames.DIMES, DIME, 0),
                  returningNickels = new Money(MoneyNames.NICKELS, NICKEL, 0);
    public Money[] insertedMoney = {insertedDollars, insertedQuarters, insertedDimes, insertedNickels};
    public Money[] returningMoney = {returningDollars, returningQuarters, returningDimes, returningNickels};
    
    @Inject
    public  VendingMachineServiceLayerImpl(InventoryDao inventoryDao, 
            MoneyDao moneyDao){
        this.inventoryDao = inventoryDao;
        this.moneyDao = moneyDao;
        
    }

    @Override
    public List<Money> getAllMoney() {
        return moneyDao.getAllMoney();
    }

    @Override
    public List<MachineSlot> getAllSlots() {
        return inventoryDao.getAllMachineSlots();
    }

    @Override
    public Money addMoney(Money money) {
        return moneyDao.addMoney(money);
    }

    @Override
    public MachineSlot addMachineSlot(MachineSlot slot) {
        return inventoryDao.addMachineSlot(slot);
    }

    @Override
    public void removeMoney(MoneyNames name) {
        moneyDao.removeMoney(name);
    }

    @Override
    public void removeMachineSlot(int slotId) {
        inventoryDao.removeMachineSlot(slotId);
    }

    @Override
    public void updateMoney(Money money) {
        moneyDao.updateMoney(money);
    }

    @Override
    public void updateMachineSlot(MachineSlot slot) {
        inventoryDao.updateMachineSlot(slot);
    }

    @Override
    public void populateMoney() {
        Money nm = new Money();
        nm.setName(MoneyNames.QUARTERS);
        nm.setValue(QUARTER);
        nm.setQuantity(6);
        moneyDao.addMoney(nm);
        Money nm2 = new Money();
        nm2.setName(MoneyNames.DIMES);
        nm2.setValue(DIME);
        nm2.setQuantity(100);
        moneyDao.addMoney(nm2);
        Money nm3 = new Money();
        nm3.setName(MoneyNames.NICKELS);
        nm3.setValue(NICKEL);
        nm3.setQuantity(200);
        moneyDao.addMoney(nm3);
        Money nm4 = new Money();
        nm3.setName(MoneyNames.DOLLARS);
        nm3.setValue(DOLLABILL);
        nm3.setQuantity(0);
        moneyDao.addMoney(nm3);
    }

    @Override
    public void populateMachineSlots() {
        String[] itemName = {"Mars","Snickers","Skittles","Twinkies","Twix","Payday","Doritos","Cheetos","Oreos"};
        String[] itemPrice = {"1.15","1.25","1.35","1.50","1.25","1.60","1.45","1.25","1.15"};
        int[] quantity = {8,1,7,2,6,3,5,4,0};
        for (int i = 0; i < 9; i++) {
            MachineSlot ms = new MachineSlot();
            ms.setProductName(itemName[i]);
            ms.setPrice(new BigDecimal(itemPrice[i]));
            ms.setQuantity(quantity[i]);
            inventoryDao.addMachineSlot(ms);
        }
    }

    @Override
    public void insertMoney(MoneyNames moneyChoice) {
        
        switch (moneyChoice) {
            case DOLLARS:
                insertedMoney[0].setQuantity(insertedMoney[0].getQuantity()+1);
                break;
            case QUARTERS:
                insertedMoney[1].setQuantity(insertedMoney[1].getQuantity()+1);
                break;
            case DIMES:
                insertedMoney[2].setQuantity(insertedMoney[2].getQuantity()+1);
                break;
            case NICKELS:
                insertedMoney[3].setQuantity(insertedMoney[3].getQuantity()+1);
                break;
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    public BigDecimal getTotalInserted(){
        BigDecimal totalInserted = new BigDecimal("0.00");
        for (int i = 0; i < insertedMoney.length; i++) {
            for (int j = 0; j < insertedMoney[i].getQuantity(); j++) {
                totalInserted = totalInserted.add(insertedMoney[i].getValue());
            }
        }
        return totalInserted;
    }
    
    @Override
    public boolean canPurchaseItem(int selectedSlotId) {
        MachineSlot selectedSlot = inventoryDao.getMachineSlotById(selectedSlotId);
        BigDecimal totalInserted = getTotalInserted();
        BigDecimal changeDue = new BigDecimal("0.00");
        
        if (selectedSlot.getQuantity() <= 0) {
            return false;
        }
        if (totalInserted.compareTo(selectedSlot.getPrice()) == -1){
            return false;
        }

        return true;
    }
    
    @Override
    public BigDecimal purchaseItem(int selectedSlotId) {
        MachineSlot selectedSlot = inventoryDao.getMachineSlotById(selectedSlotId);
        BigDecimal totalInserted = getTotalInserted();
        BigDecimal changeDue = new BigDecimal("0.00");
        
        
        selectedSlot.setQuantity(selectedSlot.getQuantity()-1);
        
        for (int i = 0; i < insertedMoney.length; i++) {
            Money currentMoney = moneyDao.getMoneyByName(insertedMoney[i].getName());
            currentMoney.setQuantity(currentMoney.getQuantity()+insertedMoney[i].getQuantity());
            moneyDao.updateMoney(currentMoney);
            insertedMoney[i].setQuantity(0);
        }
        
        
        changeDue = totalInserted.subtract(selectedSlot.getPrice());
        totalInserted = new BigDecimal("0.00");
        return changeDue;
    }

    @Override
    public String returnChange(BigDecimal changeDue) {
        
        Money MachineQuarters = moneyDao.getMoneyByName(MoneyNames.QUARTERS);
        Money MachineDimes = moneyDao.getMoneyByName(MoneyNames.DIMES);
        Money MachineNickels = moneyDao.getMoneyByName(MoneyNames.NICKELS);
        
        while(changeDue.compareTo(MachineQuarters.getValue()) >= 0
                && MachineQuarters.getQuantity() > 0){
                changeDue = changeDue.subtract(MachineQuarters.getValue());
                returningMoney[0].setQuantity(returningMoney[0].getQuantity()+1);
        }
        
        while(changeDue.compareTo(MachineDimes.getValue()) >= 0
                && MachineDimes.getQuantity() > 0){
                changeDue = changeDue.subtract(MachineDimes.getValue());
                returningMoney[1].setQuantity(returningMoney[1].getQuantity()+1);
        }
        
        while(changeDue.compareTo(MachineNickels.getValue()) >= 0
                && MachineNickels.getQuantity() > 0){
                changeDue = changeDue.subtract(MachineNickels.getValue());
                returningMoney[2].setQuantity(returningMoney[2].getQuantity()+1);
        }
        
        String changeReturnString = "";
        if (returningMoney[0].getQuantity() > 0) {
            changeReturnString += Integer.toString(returningMoney[0].getQuantity()) + " Quarters. ";
        }
        if (returningMoney[1].getQuantity() > 0) {
            changeReturnString += Integer.toString(returningMoney[1].getQuantity()) + " Dimes. ";
        }
        if (returningMoney[0].getQuantity() > 0) {
            changeReturnString += Integer.toString(returningMoney[2].getQuantity()) + " Nickels. ";
        }
        
        return changeReturnString;
    }

    @Override
    public MachineSlot getMachineSlotById(int selectedSlotId) {
        MachineSlot selectedSlot = inventoryDao.getMachineSlotById(selectedSlotId);
        return selectedSlot;
    }

    @Override
    public void reset() {
        returningMoney[0].setQuantity(0);
        returningMoney[1].setQuantity(0);
        returningMoney[2].setQuantity(0);
        insertedMoney[0].setQuantity(0);
        insertedMoney[1].setQuantity(0);
        insertedMoney[2].setQuantity(0);
        insertedMoney[3].setQuantity(0);
    }

}
