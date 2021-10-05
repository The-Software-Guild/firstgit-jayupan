/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.service;

import com.mthree.vendingmachine.dao.VendingMachineAuditDao;
import com.mthree.vendingmachine.dao.VendingMachineDao;
import com.mthree.vendingmachine.dao.VendingMachinePersistenceException;
import com.mthree.vendingmachine.dto.Item;
import java.math.BigDecimal;
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
public class VendingMachineServiceLayerImplTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }
    
    @Test
    public void testCreateValidItem()
    {
        Item item = new Item();
        item.setId("2");
        item.setName("Pringles");
        item.setCost("1.99");
        item.setNum(5);
        try {
            service.editItem(item.getId(), item);
        }
        catch ( VendingMachineDataValidationException 
                | VendingMachinePersistenceException 
                | NoItemInventoryException e) 
        {
            fail("Item was valid. No exception should have been thrown.");
        }
    }
    
    @Test
    public void testCreateItemInvalidData() throws Exception
    {
        Item item = new Item();
        item.setId("2");
        item.setName("");
        item.setCost("1.99");
        item.setNum(5);
        
        try {
            service.editItem(item.getId(), item);
            fail("Expected ValidationException was not thrown");
        }
        catch (VendingMachinePersistenceException
                | NoItemInventoryException e)
        {
            fail("Incorrect exception was thrown.");
        }
        catch (VendingMachineDataValidationException e)
        {
            return;
        }
    }
    
    @Test
    public void testGetAllItems() throws Exception
    {
        Item testClone = new Item();
        testClone.setId("1");
        testClone.setName("Hershey's Bar");
        testClone.setCost("1.99");
        testClone.setNum(10);
        
        assertEquals(1, service.getAllItems().size(), "Should only have one Item.");
        assertTrue(service.getAllItems().contains(testClone), "The one item should be Hershey's.");
    }
    
    @Test
    public void testAddGetAndPayFunds() throws Exception
    {
        BigDecimal funds = new BigDecimal("5.00").setScale(2);
        service.addFunds(funds);
        
        assertEquals(funds, service.getFunds(), "Should be 0.00 + 5.00 = 5.00");
        
        Item item = new Item();
        item.setId("2");
        item.setName("");
        item.setCost("1.99");
        item.setNum(5);
        BigDecimal result = service.pay(item);
        
        assertEquals(result, service.getFunds(), "Should be 5.00 - 1.99 = 3.01");
        
        service.pay(item); // 3.01-1.99 = 1.02
        
        try {
            service.pay(item);
        }
        catch (InsufficientFundsException e)
        {
            return;
        }
        fail("Should not get to this point.");
    }
    
    @Test
    
    public void testItemCount() throws Exception
    {
        Item item = new Item();
        item.setId("2");
        item.setName("Hershey's Bar");
        item.setCost("1.99");
        item.setNum(0);
        
        try{
            service.editItem(item.getId(), item);
        }
        catch (NoItemInventoryException e)
        {
            return;
        }
        fail("Should not get to this point.");
    }
    
    
    
}
