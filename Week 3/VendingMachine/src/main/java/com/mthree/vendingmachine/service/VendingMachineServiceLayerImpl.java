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
import java.util.List;

/**
 *
 * @author Josef
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public void addFunds(BigDecimal funds)throws VendingMachinePersistenceException{
        dao.addFunds(funds);
        auditDao.writeAuditEntry("Funds added: $" + funds.toString());
    }
    
    @Override
    public BigDecimal getFunds()
    {
        return dao.getFunds();
    }
    
    @Override
    public BigDecimal pay(Item item) throws InsufficientFundsException
    {
        validateFundsException(item);
        return dao.pay(item);
    }
    
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    @Override
    public Item editItem(String itemId, Item item) throws VendingMachinePersistenceException,
            VendingMachineDataValidationException,
            NoItemInventoryException
    {
        validateItemData(item);
        validateItemCount(item);
        auditDao.writeAuditEntry("Item " + item.getName() + " DECREMENTED.");
        return dao.editItem(itemId, item);
    }
    
    private void validateItemData(Item item) throws
        VendingMachineDataValidationException {

        if (item.getId() == null
                || item.getId().trim().length() == 0
                || item.getName() == null
                || item.getName().trim().length() == 0
                || item.getCost() == null
                || item.getCost().toString().trim().length() == 0
                || item.getNum() < 0
                || String.valueOf(item.getNum()).trim().length() == 0)
                {
            throw new VendingMachineDataValidationException(
                    "ERROR: All fields [Id, Name, Cost, Num] are required.");
        }
    }
    
    private void validateItemCount(Item item) throws
            NoItemInventoryException {
        
        if (item.getNum() == 0)
        {
            throw new NoItemInventoryException(
                    "ERROR: " + item.getName() + " is all out!");
        }
    }
    
    private void validateFundsException(Item item) throws
            InsufficientFundsException {
        if(getFunds().compareTo(item.getCost()) < 0)
        {
            throw new InsufficientFundsException (
                    "ERROR: You do not have enough to pay $" + item.getCost().toString() + "!");
        }
    }
}
