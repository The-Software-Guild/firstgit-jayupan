/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.controller;

import com.mthree.vendingmachine.dao.VendingMachinePersistenceException;
import com.mthree.vendingmachine.dto.Item;
import com.mthree.vendingmachine.service.InsufficientFundsException;
import com.mthree.vendingmachine.service.NoItemInventoryException;
import com.mthree.vendingmachine.service.VendingMachineDataValidationException;
import com.mthree.vendingmachine.service.VendingMachineServiceLayer;
import com.mthree.vendingmachine.ui.UserIO;
import com.mthree.vendingmachine.ui.UserIOConsoleImpl;
import com.mthree.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Josef
 */
public class VendingMachineController {
        
    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) 
    {
        this.service = service;
        this.view = view;
    }
    
    public void run() 
    {
        boolean keepGoing = true;
        int menuSelection = 0;
        while(keepGoing)
        {
            try {
                menuSelection = view.printMenuAndGetSelection(service.getAllItems(), service.getFunds());
                if(menuSelection > 0 && menuSelection <= service.getAllItems().size()) //all item options
                {
                    buyItem(menuSelection);
                }
                else if(menuSelection == service.getAllItems().size()+1) //add change
                {
                    addFunds();
                }
                else if(menuSelection == service.getAllItems().size()+2) //exit
                {
                    keepGoing = false;
                }
                else unknownCommand();
            
            }
            catch (VendingMachinePersistenceException|VendingMachineDataValidationException|NoItemInventoryException|InsufficientFundsException e)
            {
                view.displayErrorMessage(e.getMessage());
            }
        }
        exitMessage();
    }
    
    private void buyItem(int choice) throws VendingMachinePersistenceException,
            VendingMachineDataValidationException,
            NoItemInventoryException,
            InsufficientFundsException
    {
        List<Item> itemList = service.getAllItems();
        Item item = itemList.get(choice-1);
        view.displayBuyItemBanner(item.getName());
            BigDecimal funds = service.pay(item);
            service.editItem(item.getId(), item);
            view.calculateChange(funds);
    }
    
    private void addFunds() throws VendingMachinePersistenceException
    {
        view.displayAddFundsBanner();
        BigDecimal newFunds = view.promptFunds();
        service.addFunds(newFunds);
        view.displayAddFundsSuccessBanner();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}
