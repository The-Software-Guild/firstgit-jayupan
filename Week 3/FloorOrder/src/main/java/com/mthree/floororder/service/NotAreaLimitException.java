/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.floororder.service;

/**
 *
 * @author Josef
 */
public class NotAreaLimitException extends Exception{
    public NotAreaLimitException(String message)
    {
        super(message);
    }
    
    public NotAreaLimitException(String message, Throwable cause)
    {
        super(message, cause);
    }
}