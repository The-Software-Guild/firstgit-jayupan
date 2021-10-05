/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.floororder.controller;

import com.mthree.floororder.dao.FloorOrderPersistenceException;
import com.mthree.floororder.dto.Order;
import com.mthree.floororder.dto.Product;
import com.mthree.floororder.service.FloorOrderServiceLayer;
import com.mthree.floororder.service.InvalidAreaException;
import com.mthree.floororder.service.InvalidDateException;
import com.mthree.floororder.service.InvalidNameException;
import com.mthree.floororder.service.InvalidProductException;
import com.mthree.floororder.service.InvalidStateException;
import com.mthree.floororder.service.NotAreaLimitException;
import com.mthree.floororder.service.NotFutureDateException;
import com.mthree.floororder.ui.FloorOrderView;
import com.mthree.floororder.ui.UserIO;
import com.mthree.floororder.ui.UserIOConsoleImpl;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author Josef
 */
public class FloorOrderController {
    private UserIO io = new UserIOConsoleImpl();
    private FloorOrderView view;
    private FloorOrderServiceLayer service;
    
    public FloorOrderController(FloorOrderServiceLayer service, FloorOrderView view) 
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
                menuSelection = view.printMenuAndGetSelection();
                switch(menuSelection)
                {
                    case 1: 
                        displayOrder();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        export();
                        break;
                    case 6:
                        exitPrompt();
                        export();
                        keepGoing = false;
                        break; 
                    default:
                        unknownCommand();
                        break;
                }
            } catch (FloorOrderPersistenceException|
                     InvalidDateException|
                     NotFutureDateException|
                     InvalidStateException|
                     DateTimeParseException|
                     NotAreaLimitException|
                     InvalidAreaException|
                     InvalidProductException|
                     NumberFormatException|
                     InvalidNameException e)
            {
                view.displayErrorMessage(e.getMessage());
            }
        }
        exitMessage();
    }
    
    private void displayOrder() throws InvalidDateException
    {
        LocalDate ld = view.getDate();
        List<Order> orderList = service.getOrdersByDate(ld);
        view.displayOrder(orderList);
        
    }
    
    private void addOrder() throws NotFutureDateException, InvalidStateException, InvalidNameException, InvalidAreaException, InvalidProductException, NotAreaLimitException
    {
        List<Product> products = service.getProducts();
        Order order = view.getOrder(products);
        order = service.calculate(order);
        view.displayOrder(order);
        if(view.addOrder(order))
        {
            order = service.addOrder(order);
            view.displayAddOrderSuccess();
        }
        else view.displayAddOrderCancel();
    }
   
    private void editOrder() throws NotFutureDateException, InvalidStateException, InvalidNameException, InvalidAreaException, InvalidProductException, NotAreaLimitException
    {
        LocalDate ld = view.getDate();
        int orderNum = view.getOrderNum();
        Order order = service.getOrder(ld, orderNum);
        order = view.editOrder(order);
        order = service.calculate(order);
        view.displayOrder(order);
        if(view.addOrder(order))
        {
            order = service.editOrder(order);
            view.displayEditOrderSuccess();
        }
        else view.displayEditOrderCancel();
    }
    
    private void removeOrder()
    {
        LocalDate ld = view.getDate();
        int orderNum = view.getOrderNum();
        Order order = service.getOrder(ld, orderNum);
        view.displayOrder(order);
        if(view.removeOrder())
        {
            order = service.removeOrder(order);
            view.displayEditOrderSuccess();
        }
        else view.displayEditOrderCancel();
    }
    
    private void export() throws FloorOrderPersistenceException
    {
        if(view.displayExport())
        {
            service.export();
            view.displayExportSuccess();
        }
        else view.displayExportCancel();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    private void exitPrompt() {
        view.displayExitPrompt();
    }
    
}
