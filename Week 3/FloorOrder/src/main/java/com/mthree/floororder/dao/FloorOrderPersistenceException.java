/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.floororder.dao;

/**
 *
 * @author Josef
 */
public class FloorOrderPersistenceException extends Exception{
    public FloorOrderPersistenceException(String message)
    {
        super(message);
    }
    
    public FloorOrderPersistenceException(String message, Throwable cause)
    {
        super(message);
    }
}
