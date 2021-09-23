/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dao;

import com.mthree.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author Josef
 */
public interface VendingMachineDao {
    
    List<Item> getAllItems() throws VendingMachineDaoException;
    
    Item editItem(String itemId, Item item) throws VendingMachineDaoException;
    
}
