/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.scanner;

/**
 *
 * @author Josef
 */
import java.util.Scanner;

public class BiggerBetterAdder {
    public static void main (String[] args)
    {
        int num1, num2, num3, sum;
        
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("First number: ");
        num1 = Integer.parseInt(myScanner.nextLine());
        
        System.out.println("Second number: ");
        num2 = Integer.parseInt(myScanner.nextLine());
        
        System.out.println("Third number: ");
        num3 = Integer.parseInt(myScanner.nextLine());
        
        sum = num1 + num2 + num3;
        
        System.out.println(num1 + " + " + num2 + " + " + num3 + " = " + sum);
    }
}
