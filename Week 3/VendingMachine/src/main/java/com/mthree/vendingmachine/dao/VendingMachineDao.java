/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dao;

import com.mthree.vendingmachine.dto.Item;
import com.mthree.vendingmachine.service.InsufficientFundsException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Josef
 */
public interface VendingMachineDao {
    
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    Item editItem(String itemId, Item item) throws VendingMachinePersistenceException;
    
    public void addFunds(BigDecimal funds) throws VendingMachinePersistenceException;
    public BigDecimal getFunds();
    public BigDecimal pay(Item item) throws InsufficientFundsException;
    
}
