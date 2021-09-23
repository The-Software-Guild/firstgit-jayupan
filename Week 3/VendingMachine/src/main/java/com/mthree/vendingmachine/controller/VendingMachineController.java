/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.controller;

import com.mthree.vendingmachine.dao.VendingMachineDao;
import com.mthree.vendingmachine.dao.VendingMachineDaoException;
import com.mthree.vendingmachine.dto.Item;
import com.mthree.vendingmachine.ui.UserIO;
import com.mthree.vendingmachine.ui.UserIOConsoleImpl;
import com.mthree.vendingmachine.ui.VendingMachineView;
import java.util.List;

/**
 *
 * @author Josef
 */
public class VendingMachineController {
        
    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineView view;
    private VendingMachineDao dao;
    
    public VendingMachineController(VendingMachineDao dao, VendingMachineView view) 
    {
        this.dao = dao;
        this.view = view;
    }
    
    public void run() 
    {
        boolean keepGoing = true;
        int menuSelection = 0;
        while(keepGoing)
        {
            try {
                menuSelection = view.printMenuAndGetSelection(dao.getAllItems());
                if(menuSelection > 0 && menuSelection <= dao.getAllItems().size()) //all item options
                {
                    buyItem(menuSelection);
                }
                else if(menuSelection == dao.getAllItems().size()+1) //add change
                {
                    io.print("Changing change");
                }
                else if(menuSelection == dao.getAllItems().size()+2) //exit
                {
                    keepGoing = false;
                }
                else unknownCommand();
            
            }
            catch (VendingMachineDaoException e)
            {
                
            }
        }
        exitMessage();
    }
    
    private void buyItem(int choice) throws VendingMachineDaoException
    {
        List<Item> itemList = dao.getAllItems();
        Item item = itemList.get(choice-1);
        view.displayBuyItemBanner(item.getName());
        Item editedItem = dao.editItem(item.getId(), item);
        //view.pay(item);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}
