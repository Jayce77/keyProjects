/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Money;
import com.sg.vendingmachinespringmvc.model.MoneyNames;
import java.util.List;

/**
 *
 * @author jayce
 */
public interface MoneyDao {
    
    public Money addMoney(Money money);
    
    public void removeMoney(MoneyNames  name);
    
    public void updateMoney(Money money);
    
    public List<Money> getAllMoney();
    
    public Money getMoneyByName(MoneyNames name);
    
}
