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
public class DoItBetter {
    public static void main(String[] args)
    {
        Scanner myScanner = new Scanner(System.in);
        int miles, hotDogs, languages;
       
        System.out.println("How many miles can you run?");
        miles = Integer.parseInt(myScanner.nextLine());
        
        System.out.println("Wow you can run " + miles + "?");
        System.out.println("That's nothing. I can run " + (miles * 2 + 1) + "!");
        
        System.out.println("How many hot dogs can you eat?");
        hotDogs = Integer.parseInt(myScanner.nextLine());
        
        System.out.println("Only " + hotDogs + "?");
        System.out.println("That's nothing. I can eat " + (hotDogs * 2 + 1) + "!");
        
        System.out.println("How many languages do you know?");
        languages = Integer.parseInt(myScanner.nextLine());
        
        System.out.println("You only know " + languages + "?");
        System.out.println("That's nothing. I know " + (languages * 2 + 1) + "!");
        System.out.println("And one of them is Klingon!");
        
    }
}
