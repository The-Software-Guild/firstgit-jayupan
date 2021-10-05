/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.service;

import com.mthree.vendingmachine.dao.VendingMachineDao;
import com.mthree.vendingmachine.dao.VendingMachinePersistenceException;
import com.mthree.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josef
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao{
    public Item onlyItem;
    public BigDecimal onlyFunds;
    
    public VendingMachineDaoStubImpl() {
        Item item = new Item();
        item.setId("1");
        item.setName("Hershey's Bar");
        item.setCost("1.99");
        item.setNum(10);
        onlyItem = item;
        
        onlyFunds = BigDecimal.ZERO;
    }
    
    public VendingMachineDaoStubImpl(Item testItem) {
        this.onlyItem = testItem;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        List<Item> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    @Override
    public Item editItem(String itemId, Item item) throws VendingMachinePersistenceException {
        if (itemId.equals(onlyItem.getId())) {
            return onlyItem;
        } else return null; 
    }

    @Override
    public void addFunds(BigDecimal funds) throws VendingMachinePersistenceException {
        onlyFunds = onlyFunds.add(funds).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getFunds() {
        return onlyFunds;
    }

    @Override
    public BigDecimal pay(Item item) throws InsufficientFundsException {
        onlyFunds = onlyFunds.subtract(item.getCost()).setScale(2, RoundingMode.HALF_UP);
        return onlyFunds;
    }
}
