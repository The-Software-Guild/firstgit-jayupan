/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.floororder.service;

import com.mthree.floororder.dao.FloorOrderAuditDao;
import com.mthree.floororder.dao.FloorOrderDao;
import com.mthree.floororder.dao.FloorOrderPersistenceException;
import com.mthree.floororder.dto.Order;
import com.mthree.floororder.dto.Product;
import com.mthree.floororder.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Josef
 */
public class FloorOrderServiceLayerImpl implements FloorOrderServiceLayer{
    
    private FloorOrderDao dao;
    private FloorOrderAuditDao auditDao;
    
    public FloorOrderServiceLayerImpl(FloorOrderAuditDao auditDao, FloorOrderDao dao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public List<Order> getOrders()
    {
        return dao.getOrders();
    }
   
    @Override
    public List<Order> getOrdersByDate(LocalDate ld) throws InvalidDateException
    {
        validateDate(ld);
        return dao.getOrdersByDate(ld);
    }
    
    @Override
    public Order addOrder(Order order)
    {
        return dao.addOrder(order);
    }
    
    @Override
    public Order calculate(Order order) throws InvalidStateException, InvalidNameException, 
            NotFutureDateException, InvalidAreaException, InvalidNameException, InvalidProductException,
            NotAreaLimitException
    {
        validateDateFuture(order);
        validateState(order);
        validateName(order);
        validateProduct(order);
        validateAreaLimit(order);
        
        return dao.calculate(order);
    }
    
    @Override
    public Order getOrder(LocalDate ld, int orderNum)
    {
        return dao.getOrder(ld, orderNum);
    }
    
    @Override
    public Order editOrder(Order order) 
    {
        return dao.editOrder(order);
    }
  
    @Override
    public Order removeOrder(Order order)
    {
        return dao.removeOrder(order);
    }
    
    @Override
    public void export() throws FloorOrderPersistenceException
    {
        dao.export();
    }
    
    @Override
    public List<Product> getProducts()
    {
        return dao.getProducts();
    }
    
    private void validateDate(LocalDate ld) throws InvalidDateException
    {
        boolean isDate = false;
        for(Order thisOrder : dao.getOrders())
        {
            if (thisOrder.getDate().equals(ld) )
            {
                isDate = true;
            }
        }
        if(!isDate)
        {
            throw new InvalidDateException(
                    "ERROR: Date not found!");
        }
    }
    
    private void validateDateFuture(Order order) throws NotFutureDateException
    {
        LocalDate ld = order.getDate();
        if(ld.compareTo(LocalDate.now()) < 0)
        {
            throw new NotFutureDateException(
                    "ERROR: Date must be set to the future!");
        }
    }
    
    private void validateName(Order order) throws InvalidNameException
    {
        boolean isName = true;
        boolean isValid = true;
        char[] chars = order.getName().toCharArray();
        StringBuilder sb = new StringBuilder();
        if(order.getName().equals(""))
        {
            isName = false;
        }
        for(char c: chars)
        {
            if(Character.isDigit(c))
            {
            }
            else if(Character.isLetter(c))
            {
                
            }
            else if(c == '.')
            {
                
            }
            else if(c == ',')
            {
                
            }
            else isValid = false;
        }
        
        if(!isName || !isValid)
        {
            throw new InvalidNameException(
                    "ERROR: Name not valid!");
        }
    }
    
    private void validateState(Order order) throws InvalidStateException
    {
        boolean isState = false;
        String state = order.getState();
        for(Tax tax : dao.getTaxes())
        {
            if (state.equals(tax.getState()))
            {
                isState = true;
            }
        }
        if(!isState)
        {
            throw new InvalidStateException(
                    "ERROR: State not found!");
        }
    }
    
    private void validateProduct(Order order) throws InvalidProductException
    {
        boolean isProduct = false;
        String productType = order.getProductType();
        for(Product product : dao.getProducts())
        {
            if (productType.equals(product.getProductType()))
            {
                isProduct = true;
            }
        }
        if(!isProduct)
        {
            throw new InvalidProductException(
                    "ERROR: Product not found!");
        }
    }
    
    private void validateAreaLimit(Order order) throws NotAreaLimitException
    {
        if(order.getArea().compareTo(new BigDecimal(100)) < 0)
        {
            throw new NotAreaLimitException(
                    "ERROR: Area size must be 100ft or above!");
        }
        
    }
    
}
