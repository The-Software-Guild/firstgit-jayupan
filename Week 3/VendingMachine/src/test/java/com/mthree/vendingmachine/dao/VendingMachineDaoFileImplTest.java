/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.dao;

import com.mthree.vendingmachine.dto.Item;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Josef
 */
public class VendingMachineDaoFileImplTest {
    
    static VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() {
        testDao = new VendingMachineDaoFileImpl("testitem.txt");
    }
    
    @Test
    public void testEditStudents() throws Exception 
    {
        Item firstItem = new Item();
        firstItem.setId("1");
        firstItem.setName("Pepsi");
        firstItem.setCost("1.29");
        firstItem.setNum(10);
        Item secondItem = new Item(firstItem);
        testDao.editItem(firstItem.getId(), firstItem);
        testDao.editItem(secondItem.getId(), secondItem);
        
        assertEquals(firstItem.getId(), secondItem.getId(), "Checking item id.");
        assertEquals(firstItem.getName(), secondItem.getName(), "Checking item name.");
        assertEquals(firstItem.getCost(), secondItem.getCost(), "Checking item cost.");
        assertEquals(firstItem.getNum(), secondItem.getNum(), "Checking item num.");
    }
    
    @Test
    public void testGetAllItems() throws Exception
    {
        Item firstItem = new Item();
        firstItem.setId("1");
        firstItem.setName("Pepsi");
        firstItem.setCost("1.29");
        firstItem.setNum(9);
        
        Item secondItem = new Item();
        secondItem.setId("2");
        secondItem.setName("Headphones");
        secondItem.setCost("9.99");
        secondItem.setNum(10);
        
        Item thirdItem = new Item();
        thirdItem.setId("3");
        thirdItem.setName("Ham Sandwich");
        thirdItem.setCost("4.99");
        thirdItem.setNum(10);
        
        List<Item> allItems = testDao.getAllItems();
        
        assertNotNull(allItems, "The list of items must not be null");
        assertEquals(3, allItems.size(), "The list of items should have 3 items");
        
        assertTrue(testDao.getAllItems().contains(firstItem), "The list of items should include Pepsi.");
        assertTrue(testDao.getAllItems().contains(secondItem), "The list of items should include Headphones.");
        assertTrue(testDao.getAllItems().contains(thirdItem), "The list of items should include Ham Sandwich.");
        
    }
   
    @Test
    public void testAddGetAndPayFunds() throws Exception
    {
        BigDecimal funds = new BigDecimal("5.00").setScale(2);
        testDao.addFunds(funds);
        
        assertEquals(funds, testDao.getFunds(), "Should be 0.00 + 5.00 = 5.00");
        
        Item item = new Item();
        item.setId("2");
        item.setName("");
        item.setCost("1.99");
        item.setNum(5);
        BigDecimal result = testDao.pay(item);
        
        assertEquals(result, testDao.getFunds(), "Should be 5.00 - 1.99 = 3.01");
    }
    
}
