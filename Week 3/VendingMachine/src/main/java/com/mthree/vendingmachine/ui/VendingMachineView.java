/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingmachine.ui;

import com.mthree.vendingmachine.dto.Change;
import com.mthree.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
/**
 *
 * @author Josef
 */
public class VendingMachineView {
    private UserIO io;
    private BigDecimal funds = new BigDecimal("5.00");
    
    public VendingMachineView(UserIO io)
    {
        this.io = io;
    }
    

    public int printMenuAndGetSelection(List<Item> itemList) {
        io.print("Main Menu");
        int count = 1;
        for (Item currentItem : itemList) {
            String itemInfo = String.format("%s. %s: $%s, %s left",
                  count,
                  currentItem.getName(),
                  currentItem.getCost(),
                  currentItem.getNum());
            io.print(itemInfo);
            count++;
        }
        
        io.print(count + ". Add funds. Current funds: " + funds);
        count++;
        io.print(count + ". Exit");
        return io.readInt("Please select from the above choices.", 1, count+1);
    }
    
    public void pay(Item item)
    {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0; 
        BigDecimal cost = new BigDecimal(item.getCost());
        funds = funds.subtract(cost);
        while(!funds.equals(0))
        {
            if(funds.compareTo(Change.QUARTER.getVal()) > 0)
            {
                funds = funds.subtract(Change.QUARTER.getVal());
                quarters++;
            }
            else if(funds.compareTo(Change.DIME.getVal()) > 0)
            {
                funds = funds.subtract(Change.DIME.getVal());
                dimes++;
            }
            else if(funds.compareTo(Change.NICKEL.getVal()) > 0)
            {
                funds = funds.subtract(Change.DIME.getVal());
                nickels++;
            }
            else //pennies
            {
                funds = funds.subtract(Change.PENNY.getVal());
                pennies++;
            }
        }
        io.print("Quarters: " + quarters);
        io.print("Dimes: " + dimes);
        io.print("Nickels: " + nickels);
        io.print("Pennies: " + pennies);
    }
    
    public void displayBuyItemBanner (String itemName)
    {
        io.print("=== Buying " + itemName + " ===");
    }
    
    public void displayExitBanner() 
    {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
}
