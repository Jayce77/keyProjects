/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Money;
import com.sg.vendingmachinespringmvc.model.MoneyNames;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jayce
 */
public class MoneyDaoInMemImpl implements MoneyDao{
    
    private Map<MoneyNames , Money> moneyMap =  new HashMap<>();

    @Override
    public Money addMoney(Money money) {
        moneyMap.put(money.getName(), money);
        return money;
    }

    @Override
    public void removeMoney(MoneyNames name) {
        moneyMap.remove(name);
    }

    @Override
    public void updateMoney(Money money) {
        moneyMap.put(money.getName(), money);
    }

    @Override
    public List<Money> getAllMoney() {
        Collection<Money> c = moneyMap.values();
        return new ArrayList(c);
    }

    @Override
    public Money getMoneyByName(MoneyNames name) {
        return moneyMap.get(name);
    }
    
}
