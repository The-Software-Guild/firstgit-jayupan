/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.service;

import com.mthree.vendingmachine.dao.VendingMachinePersistenceException;
import com.mthree.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Josef
 */
public interface VendingMachineServiceLayer {
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    Item editItem(String itemId, Item item) throws VendingMachinePersistenceException, 
            VendingMachineDataValidationException,
            NoItemInventoryException;
    
    public void addFunds(BigDecimal funds) throws VendingMachinePersistenceException;
    public BigDecimal getFunds();
    public BigDecimal pay(Item item) throws InsufficientFundsException;
}
